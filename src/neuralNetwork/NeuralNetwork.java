package neuralNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import main.Point;

public class NeuralNetwork {
    private List<Layer> layers;

    public NeuralNetwork() {
        layers = new ArrayList<>();
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public void train(Point point1, Point point2, Function<Point, Float> callback) {
        Point outputPoint = process(point1, point2);
        float score = callback.apply(outputPoint);

        float[] target = new float[2];
        target[0] = (point1.getX() + point2.getX()) / 2.0f;
        target[1] = (point1.getY() + point2.getY()) / 2.0f;

        float[] error = new float[2];
        error[0] = target[0] - outputPoint.getX();
        error[1] = target[1] - outputPoint.getY();

        backwardPropagation(error, score);
    }

    private void backwardPropagation(float[] error, float score) {
        for (int i = layers.size() - 1; i >= 0; i--) {
            Layer layer = layers.get(i);
            float[] gradients = new float[layer.getOutputSize()];

            if (i == layers.size() - 1) {
                // Dérivée de la fonction d'activation de la dernière couche
                for (int j = 0; j < layer.getOutputSize(); j++) {
                    float output = layer.getActivationFunction().derivative(layer.getOutputs()[j]);
                    gradients[j] = error[j] * output * score;
                }
            } else {
                // Dérivée de la fonction d'activation des couches précédentes
                Layer nextLayer = layers.get(i + 1);
                for (int j = 0; j < layer.getOutputSize(); j++) {
                    float output = layer.getActivationFunction().derivative(layer.getOutputs()[j]);
                    float sum = 0.0f;
                    for (int k = 0; k < nextLayer.getOutputSize(); k++) {
                        sum += nextLayer.getWeights()[j][k] * nextLayer.getGradients()[k];
                    }
                    gradients[j] = output * sum;
                }
            }

            layer.setGradients(gradients);
            updateWeights(layer);
        }
    }

    private void updateWeights(Layer layer) {
        float learningRate = 0.1f; // Taux d'apprentissage

        for (int j = 0; j < layer.getOutputSize(); j++) {
            for (int i = 0; i < layer.getInputSize(); i++) {
                float weight = layer.getWeights()[i][j];
                float gradient = layer.getGradients()[j];
                float delta = learningRate * gradient * layer.getInputs()[i];
                layer.getWeights()[i][j] = weight + delta;
            }

            float bias = layer.getBiases()[j];
            float delta = learningRate * layer.getGradients()[j];
            layer.getBiases()[j] = bias + delta;
        }
    }

    public Point process(Point point1, Point point2) {
        float[] input = new float[4];
        input[0] = point1.getX();
        input[1] = point1.getY();
        input[2] = point2.getX();
        input[3] = point2.getY();

        float[] output = new float[2];

        for (Layer layer : layers) {
            layer.setInputs(input);
            output = layer.forwardPropagation(input);
        }

        return new Point(output[0]*10, output[1]*10);
    }
}
