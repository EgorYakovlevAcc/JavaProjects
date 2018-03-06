package Sapper.sapperJava;

class Flag {
    private Matrix flagMap;
    private int countofClosedSappers;

    void start () {
        flagMap = new Matrix(sapper.CLOSED);
        countofClosedSappers = Ranges.getSize().x * Ranges.getSize().y;
    }

    sapper get (Coord coord) {
        return flagMap.get(coord);
    }

    public void setOpenedToSapper(Coord coord) {
        flagMap.set(coord, sapper.OPENED);
        countofClosedSappers--;
    }
    private  void setFlagedToSapper(Coord coord) {
        flagMap.set(coord, sapper.FLAGED);
    }

    public void toggleFlagedToSapper(Coord coord) {
        switch (flagMap.get(coord)){
            case FLAGED: setCloseToSapper(coord); break;
            case CLOSED: setFlagedToSapper(coord); break;
        }
    }

    private void setCloseToSapper(Coord coord) {
        flagMap.set(coord, sapper.CLOSED);
    }

    public int getCountofClosedBoxes() {
        return countofClosedSappers;
    }

    public void setBombedToSapper(Coord coord) {
        flagMap.set(coord, sapper.BOMBED);
    }

    public void setCountofClosedBombBoxes(Coord coord) {
        if (flagMap.get(coord) == sapper.CLOSED) {
            flagMap.set(coord, sapper.OPENED);
        }
    }

    public void setNoBombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == sapper.FLAGED)
            flagMap.set(coord, sapper.NOBOMED);
    }
}
