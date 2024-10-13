/**
 * UnitVector.java
 *
 * <p>Purdue University -- CS18000 -- Fall 2024</p>
 *
 * @author Ian Pang
 * @version Oct 14, 2024
 */

public class UnitVector {
    private double i;
    private double j;
    private double k;

    public UnitVector(double i, double j, double k) {
        double magnitude = Math.sqrt(i * i + j * j + k * k);
        if (Math.abs(magnitude - 1.0) > 0.0001) {
            this.i = i / magnitude;
            this.j = j / magnitude;
            this.k = k / magnitude;
        } else {
            this.i = i;
            this.j = j;
            this.k = k;
        }
        if (magnitude == 0) {
            this.i = 0.0;
            this.j = 0.0;
            this.k = 0.0;
        }
    }

    public UnitVector(Point start, Point end) {
        double newI = end.getX() - start.getX();
        double newJ = end.getY() - start.getY();
        double newK = end.getZ() - start.getZ();

        double magnitude = Math.sqrt(newI * newI + newJ * newJ + newK * newK);
        if (Math.abs(magnitude - 1.0) > 0.0001) {
            this.i = newI / magnitude;
            this.j = newJ / magnitude;
            this.k = newK / magnitude;
        } else {
            this.i = newI;
            this.j = newJ;
            this.k = newK;
        }
        if (magnitude == 0) {
            this.i = 0.0;
            this.j = 0.0;
            this.k = 0.0;
        }
    }

    public UnitVector() {
        this.i = 0.0;
        this.j = 0.0;
        this.k = 0.0;
    }

    public double getI() {
        return i;
    }

    public double getJ() {
        return j;
    }

    public double getK() {
        return k;
    }

    public void setI(double i) {
        this.i = i;
    }

    public void setJ(double j) {
        this.j = j;
    }

    public void setK(double k) {
        this.k = k;
    }

    public boolean compareWith(UnitVector vector) {
        return Math.abs(this.i - vector.getI()) <= 0.0001 &&
                Math.abs(this.j - vector.getJ()) <= 0.0001 &&
                Math.abs(this.k - vector.getK()) <= 0.0001;
    }

    public UnitVector crossProduct(UnitVector b) {
        double newI = (this.j * b.getK()) - (this.k * b.getJ());
        double newJ = (this.k * b.getI()) - (this.i * b.getK());
        double newK = (this.i * b.getJ()) - (this.j * b.getI());
        double magnitude = Math.sqrt(newI * newI + newJ * newJ + newK * newK);

        if (Math.abs(magnitude) <= 0.0001) {
            return new UnitVector();
        } else {
            return new UnitVector(newI / magnitude, newJ / magnitude, newK / magnitude);
        }
    }

    public String toString() {
        if (Math.abs(i) <= 0.0001 && Math.abs(j) <= 0.0001 && Math.abs(k) <= 0.0001) {
            return "<InvalidUnitVector>";
        }
        return String.format("<%.3fi, %.3fj, %.3fk>", i, j, k);
    }

}
