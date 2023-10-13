package neuralNetwork;

public class Neuron {

    private Layer priviousLayer;
    private float[] weights;
    private float bias;
    private float output;

    public Neuron(float value) {
        this.bias = 0.0f;
        this.output = value;
    }

    public Neuron(Layer previousLayer) {
        weights = new float[previousLayer.getNumberOfNeurons()];
        this.priviousLayer = previousLayer;
        this.bias = 0.0f;
    }

    public void calculateOutput() {
        float sum = 0.0f;

        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * priviousLayer.getNeuron(i).getOutput();
        }

        output = SigmoidFunction.apply(sum + bias);
    }

    public float[] getWeights() {
        return weights;
    }

    public void setBias(float bias) {
        this.bias = bias;
    }
    public float getBias() {
        return bias;
    }

    public void setOutput(float output) {
        this.output = output;
    }
    public float getOutput() {
        return output;
    }
}
