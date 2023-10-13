package neuralNetwork;

public class SigmoidFunction {
    public static float apply(float value) {
        return (float) (1 / (1 + Math.exp(-value)));
    }
}
