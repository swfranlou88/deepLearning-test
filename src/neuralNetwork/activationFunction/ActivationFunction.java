package neuralNetwork.activationFunction;

public interface ActivationFunction {
    float apply(float value);
    float derivative(float value);
}