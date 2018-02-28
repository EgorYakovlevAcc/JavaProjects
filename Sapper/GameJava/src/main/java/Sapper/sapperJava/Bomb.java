package Sapper.sapperJava;

class Bomb {

    private Matrix bombMap;
    private int totalBomb;

    Bomb (int totalBomb) {
        this.totalBomb = totalBomb;
        fixBobmbNum();
    }

    void start() {
        bombMap = new Matrix(sapper.ZERO);
        for (int j = 0; j < totalBomb; j++) {
            placeBomb();
        }
    }

    int getTotalBombs () {
        return totalBomb;
    }

    sapper get (Coord coord) {
        return bombMap.get(coord);
    }

    private  void fixBobmbNum() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y/2;
        if (totalBomb > maxBombs)
            totalBomb = maxBombs;
    }

    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (sapper.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, sapper.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb (Coord coord) {
        for (Coord around: Ranges.getCoordsAround(coord)) {
            if (sapper.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).nextNumber());
        }
    }
}
