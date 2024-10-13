/**
 * Triangle.java
 *
 * <p>Purdue University -- CS18000 -- Fall 2024</p>
 *
 * @author Ian Pang
 * @version Oct 14, 2024
 */

public class Triangle {
    private Point vertexA;
    private Point vertexB;
    private Point vertexC;
    private UnitVector surfaceNormal;

    public Triangle(Point vertA, Point vertB, Point vertC) {
        this.vertexA = vertA;
        this.vertexB = vertB;
        this.vertexC = vertC;
        UnitVector vectorAB = new UnitVector(vertA, vertB);
        UnitVector vectorAC = new UnitVector(vertA, vertC);
        this.surfaceNormal = vectorAB.crossProduct(vectorAC);
    }

    public Triangle() {
        this.vertexA = new Point(0.0, 0.0, 0.0);
        this.vertexB = new Point(0.0, 0.0, 0.0);
        this.vertexC = new Point(0.0, 0.0, 0.0);
        this.surfaceNormal = new UnitVector();
    }

    public Point getVertexA() {
        return vertexA;
    }

    public void setVertexA(Point vertexA) {
        this.vertexA = vertexA;
    }

    public Point getVertexB() {
        return vertexB;
    }

    public void setVertexB(Point vertexB) {
        this.vertexB = vertexB;
    }

    public Point getVertexC() {
        return vertexC;
    }

    public void setVertexC(Point vertexC) {
        this.vertexC = vertexC;
    }

    public UnitVector getSurfaceNormal() {
        return surfaceNormal;
    }

    public Point[] getVertices() {
        Point[] vertices = new Point[3];
        vertices[0] = this.vertexA;
        vertices[1] = this.vertexB;
        vertices[2] = this.vertexC;
        return vertices;
    }

    public boolean compareWith(Triangle triangle) {
        return this.vertexA.compareWith(triangle.getVertexA()) &&
                this.vertexB.compareWith(triangle.getVertexB()) &&
                this.vertexC.compareWith(triangle.getVertexC()) &&
                this.surfaceNormal.compareWith(triangle.getSurfaceNormal());
    }

    public String toString() {
        if (vertexA.compareWith(vertexB) || vertexA.compareWith(vertexC) || vertexB.compareWith(vertexC) ||
                surfaceNormal.toString().equals("<InvalidUnitVector>")) {
            return "[InvalidTriangle]";
        }
        return String.format("[A%s; B%s; C%s; N%s]", vertexA.toString(), vertexB.toString(), vertexC.toString(),
                surfaceNormal.toString());
    }


}