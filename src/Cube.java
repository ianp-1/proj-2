/**
 * Cube.java
 *
 * <p>Purdue University -- CS18000 -- Fall 2024</p>
 *
 * @author Ian Pang
 * @version Oct 14, 2024
 */
public class Cube {
    private Face[] mesh = new Face[6];

    public Cube(Face one, Face two, Face three, Face four, Face five, Face six) {
        this.mesh[0] = one;
        this.mesh[1] = two;
        this.mesh[2] = three;
        this.mesh[3] = four;
        this.mesh[4] = five;
        this.mesh[5] = six;
        if (!edgeCheck() || !uniqueFaceCheck() || !opposingCheck()) {
            for (int i = 0; i < 6; i++) {
                mesh[i] = new Face();
            }
        }
    }

    public Cube() {
        for (int i = 0; i < mesh.length; i++) {
            mesh[i] = new Face();
        }
    }

    public Face[] getMesh() {
        return mesh;
    }

    public String toString() {
        for (Face face : mesh) {
            if (face.toString().equals("{InvalidFace}")) {
                return "|InvalidCube|";
            }
        }

        StringBuilder result = new StringBuilder("|C");
        for (Face face : mesh) {
            result.append(face.toString());
        }
        result.append("|");
        return result.toString();
    }

    private boolean edgeCheck() {
        for (int i = 0; i < 6; i++) {
            Face currentFace = mesh[i];
            int sharedEdges = 0;
            for (int j = 0; j < 6; j++) {
                if (i == j) {
                    if (sharedEdge(currentFace, mesh[j])) {
                        sharedEdges++;
                    }
                }
            }
            if (sharedEdges != 4) {
                return false;
            }
        }
        return true;
    }

    private boolean uniqueFaceCheck() {
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (mesh[i].compareWith(mesh[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean opposingCheck() {
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (!sharedEdge(mesh[i], mesh[j])) {
                    UnitVector normal1 = mesh[i].getSurfaceNormal();
                    UnitVector normal2 = mesh[j].getSurfaceNormal();

                    // dot produt of the normals
                    double dotProduct = normal1.getI() * normal2.getI()
                            + normal1.getJ() * normal2.getJ()
                            + normal1.getK() * normal2.getK();

                    // check if opposite
                    if (Math.abs(dotProduct + 1.0) > 0.0001) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean sharedEdge(Face face1, Face face2) {
        Triangle[] mesh1 = face1.getMesh();
        Triangle[] mesh2 = face2.getMesh();

        for (Triangle triangle1 : mesh1) {
            for (Triangle triangle2 : mesh2) {
                Point[] vertices1 = triangle1.getVertices();
                Point[] vertices2 = triangle2.getVertices();
                int sharedVertexCount = 0;
                for (Point v1 : vertices1) {
                    for (Point v2 : vertices2) {
                        if (v1.compareWith(v2)) {
                            sharedVertexCount++;
                            break;
                        }
                    }
                }
                // face share edgew if there are 2 vertices
                if (sharedVertexCount == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}
