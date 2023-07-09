package main;
public class Point {
    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float distance(Point point) {
        return (float) Math.sqrt(Math.pow(point.getX() - x, 2) + Math.pow(point.getY() - y, 2));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    // Autres méthodes nécessaires (setter, toString(), etc.)
}
