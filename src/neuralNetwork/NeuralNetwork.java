package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private List<Layer> layers;

    public NeuralNetwork(int inputSize, int outputSize, int[] midleLayersSize) {
        layers = new ArrayList<>();

        layers.add(new Layer(inputSize));

        for(int i = 0; i < midleLayersSize.length; i++) {
            layers.add(new Layer(midleLayersSize[i], layers.get(i)));
        }

        layers.add(new Layer(outputSize, layers.get(layers.size() - 1)));
    }

    public void randomizeWeights() {
        for(int i = 1; i < layers.size(); i++) {
            Layer layer = layers.get(i);

            for(int j = 0; j < layer.getNumberOfNeurons(); j++) {
                Neuron neuron = layer.getNeuron(j);

                for(int k = 0; k < neuron.getWeights().length; k++) {
                    neuron.getWeights()[k] = (float) (Math.random() * 2 - 1);
                }

                neuron.setBias((float) (Math.random() * 2 - 1));
            }
        }
    }

    public float[] process(float[] inputs) {
        for(int i = 0; i < inputs.length; i++) {
            layers.get(0).getNeuron(i).setOutput(inputs[i]);
        }

        for(int i = 1; i < layers.size(); i++) {
            Layer layer = layers.get(i);

            for(int j = 0; j < layer.getNumberOfNeurons(); j++) {
                layer.getNeuron(j).calculateOutput();
            }
        }

        Layer outputLayer = layers.get(layers.size() - 1);
        float[] outputs = new float[outputLayer.getNumberOfNeurons()];

        for(int i = 0; i < outputs.length; i++) {
            outputs[i] = outputLayer.getNeuron(i).getOutput();
        }

        return outputs;
    }

}
