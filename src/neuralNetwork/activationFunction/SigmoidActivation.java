package neuralNetwork.activationFunction;

public class SigmoidActivation implements ActivationFunction {
    @Override
    public float apply(float value) {
        return (float) (1 / (1 + Math.exp(-value)));
    }

    @Override
    public float derivative(float value) {
        float sigmoid = apply(value);
        return sigmoid * (1 - sigmoid);
    }
}
