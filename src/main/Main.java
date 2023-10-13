package main;

import neuralNetwork.NeuralNetwork;

public class Main {
    public static void main(String[] args) throws Exception {
        // Initialiser l'IA
        NeuralNetwork neuralNetwork = new NeuralNetwork(4,2, new int[]{4});
        neuralNetwork.randomizeWeights();

        
        // Entraîner l'IA pendant 10 000 itérations
        /* 
        for (int i = 0; i < 1000000; i++) {
            // Générer deux points aléatoires
            Point point1 = generateRandomPoint();
            Point point2 = generateRandomPoint();

            // Obtenir le score pour le point généré
            Function<Point, Float> callback = pointGenerated -> {
                return getScore(point1, point2, pointGenerated);
            };

            // Entraîner l'IA avec les deux points et le score
            neuralNetwork.train(point1, point2, callback);
        }*/

        // Exemple d'utilisation de la fonction process après l'entraînement

        float score = 0.0f;
        int totalTest = 100000;
        for(int i = 0; i < totalTest; i++) {
            Point inputPoint1 = generateRandomPoint();
            Point inputPoint2 = generateRandomPoint();

            float[] output = neuralNetwork.process(new float[] {inputPoint1.getX()/10, inputPoint1.getY()/10, inputPoint2.getX()/10, inputPoint2.getY()/10});
            Point outputPoint = new Point(output[0] * 10, output[1] * 10);
            //Point outputPoint = new Point((inputPoint1.getX() + inputPoint2.getX())/2, (inputPoint1.getY() + inputPoint2.getY())/2);

            score += getScore(inputPoint1, inputPoint2, outputPoint);
        }
        System.out.println("Score moyen " + (score / totalTest));
    }

    private static Point generateRandomPoint() {
        Point randomPoint = new Point(
            (int)(10 * Math.random()),
            (int)(10 * Math.random())
        );

        return randomPoint;
    }

    private static Point calculateMidPoint(Point point1, Point point2) {
        Point midPoint = new Point(
            (point1.getX() + point2.getX()) / 2,
            (point1.getY() + point2.getY()) / 2
        );

        return midPoint;
    }

    private static float getScore(Point point1, Point point2, Point generatedPoint) {
        Point midPoint = calculateMidPoint(point1, point2);
        float score = 1 - (midPoint.distance(generatedPoint) / 15f);

        return score;
    }
}
