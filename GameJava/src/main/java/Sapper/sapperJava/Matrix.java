package Sapper.sapperJava;

class Matrix {
    private sapper [][] matrix;

    Matrix (sapper defaultbox) {
        matrix = new sapper[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord: Ranges.getAllCoords()) {
            matrix [coord.x][coord.y] = defaultbox;
        }
    }

    sapper get (Coord coord) {
        if (Ranges.inRange(coord)) {
            return matrix[coord.x][coord.y];
        }
        else return null;
    }
    void set (Coord coord, sapper sap) {
        if (Ranges.inRange(coord)) {
            matrix[coord.x][coord.y] = sap;
        }
    }
}
