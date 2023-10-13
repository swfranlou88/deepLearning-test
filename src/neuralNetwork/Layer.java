package neuralNetwork;

public class Layer {

    private Neuron[] neurons;
    private Layer priviousLayer;

    public Layer(int numberOfNeurons) {
        neurons = new Neuron[numberOfNeurons];
        priviousLayer = null;
        
        for (int i = 0; i < numberOfNeurons; i++) {
            neurons[i] = new Neuron(0.0f);
        }
    }

    public Layer(int numberOfNeurons, Layer priviousLayer) {
        neurons = new Neuron[numberOfNeurons];
        this.priviousLayer = priviousLayer;

        for (int i = 0; i < numberOfNeurons; i++) {
            neurons[i] = new Neuron(priviousLayer);
        }
    }

    public Layer getPriviousLayer() {
        return priviousLayer;
    }

    public int getNumberOfNeurons() {
        return neurons.length;
    }

    public Neuron getNeuron(int index) {
        return neurons[index];
    }
}
