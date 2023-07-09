package neuralNetwork;

import neuralNetwork.activationFunction.ActivationFunction;

public class Layer {
    private int inputSize;
    private int outputSize;
    private ActivationFunction activationFunction;
    private float[][] weights;
    private float[] biases;
    private float[] inputs;
    private float[] outputs;
    private float[] gradients;

    public Layer(int inputSize, int outputSize, ActivationFunction activationFunction) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.activationFunction = activationFunction;
        this.weights = new float[inputSize][outputSize];
        this.biases = new float[outputSize];
        this.inputs = new float[inputSize];
        this.outputs = new float[outputSize];
        this.gradients = new float[outputSize];

        initializeWeights();
    }

    private void initializeWeights() {
        // Initialisation aléatoire des poids et des biais
        // Vous pouvez utiliser différentes stratégies d'initialisation
        // pour les poids, telles que l'initialisation uniforme ou l'initialisation de Xavier/He.
        // Pour cet exemple, nous initialiserons les poids avec des valeurs aléatoires entre -1 et 1
        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                weights[i][j] = (float) (Math.random() * 2 - 1);
            }
        }

        // Initialisation des biais à zéro
        for (int j = 0; j < outputSize; j++) {
            biases[j] = 0.0f;
        }
    }

    public float[] forwardPropagation(float[] inputs) {
        this.inputs = inputs;

        // Calcul de la somme pondérée des entrées avec les poids et les biais
        for (int j = 0; j < outputSize; j++) {
            float sum = 0.0f;
            for (int i = 0; i < inputSize; i++) {
                sum += inputs[i] * weights[i][j];
            }
            outputs[j] = activationFunction.apply(sum + biases[j]);
        }

        return outputs;
    }

    public void setGradients(float[] gradients) {
        this.gradients = gradients;
    }
    public float[] getGradients() {
        return gradients;
    }

    public float[] getInputs() {
        return inputs;
    }
    public int getInputSize() {
        return inputSize;
    }
    public void setInputs(float[] inputs) {
        this.inputs = inputs;
    }

    public float[][] getWeights() {
        return weights;
    }

    public float[] getBiases() {
        return biases;
    }

    public float[] getOutputs() {
        return outputs;
    }
    public int getOutputSize() {
        return outputSize;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }
}
