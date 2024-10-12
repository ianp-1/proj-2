public class Point {
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setZ(double z) {
        this.z = z;
    }

    public boolean compareWith(Point point) {
        return Math.abs(this.x - point.getX()) <= 0.0001 &&
                Math.abs(this.y - point.getY()) <= 0.0001 &&
                Math.abs(this.z - point.getZ()) <= 0.0001;
    }

    public String toString() {
        return String.format("(x%.3f, y%.3f, z%.3f)", x, y, z);
    }
}
