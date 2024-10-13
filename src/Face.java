/**
 * Face.java
 *
 * <p>Purdue University -- CS18000 -- Fall 2024</p>
 *
 * @author Ian Pang
 * @version Oct 14, 2024
 */
public class Face {
    private Triangle[] mesh = new Triangle[2];
    private UnitVector surfaceNormal;

    public Face(Triangle one, Triangle two) {
        this.mesh = new Triangle[2];
        if (checkFaces(one, two)) {
            this.mesh[0] = one;
            this.mesh[1] = two;
            this.surfaceNormal = one.getSurfaceNormal();
        } else {
            this.mesh[0] = new Triangle();
            this.mesh[1] = new Triangle();
            this.surfaceNormal = new UnitVector();
        }
    }

    public Face() {
        mesh[0] = new Triangle();
        mesh[1] = new Triangle();
        surfaceNormal = new UnitVector();
    }

    public Triangle[] getMesh() {
        return mesh;
    }

    public UnitVector getSurfaceNormal() {
        return surfaceNormal;
    }

    public boolean compareWith(Face face) {
        if (face == null) {
            return false;
        }
        return this.mesh[0].compareWith(face.mesh[0])
                && this.mesh[1].compareWith(face.mesh[1])
                && this.surfaceNormal.compareWith(face.surfaceNormal);
    }

    public String toString() {
        if (surfaceNormal.toString().equals("<InvalidUnitVector>")) {
            return "{InvalidFace}";
        }

        String triangle1Str = String.format("[%s; %s; %s]",
                mesh[0].getVertices()[0],
                mesh[0].getVertices()[1],
                mesh[0].getVertices()[2]);

        String triangle2Str = String.format("[%s; %s; %s]",
                mesh[1].getVertices()[0],
                mesh[1].getVertices()[1],
                mesh[1].getVertices()[2]);

        return String.format("{F%s %s N%s}", triangle1Str, triangle2Str, surfaceNormal.toString());
    }

    private boolean checkVertices(Triangle one, Triangle two) {
        int vertices = 0;
        Point[] v1 = one.getVertices();
        Point[] v2 = two.getVertices();
        for (Point p1 : v1) {
            for (Point p2 : v2) {
                if (p1.compareWith(p2)) {
                    vertices++;
                }
            }
        }
        return vertices == 2;
    }

    private boolean checkFaces(Triangle one, Triangle two) {
        return !one.compareWith(two) && checkVertices(one, two)
                && one.getSurfaceNormal().compareWith(two.getSurfaceNormal());
    }
}
