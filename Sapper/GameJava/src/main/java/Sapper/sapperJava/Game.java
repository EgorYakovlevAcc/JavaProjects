package Sapper.sapperJava;


import java.awt.*;

public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState state;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord (cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }
    public sapper getSapper (Coord coord) {
        if (flag.get(coord) == sapper.OPENED)
            return bomb.get(coord);
        else
            return flag.get(coord);
    }

    public void start () {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    public void pressLeftButton(Coord coord) {
        if (gameOver()) {
            return;
        }
        openSapper (coord);
        checkWinner();
    }

    private boolean gameOver() {
        if (state == GameState.PLAYED) {
            return false;
        }
        else {
            return true;
        }
    }

    private void checkWinner () {
        if (state == GameState.PLAYED) {
            if (flag.getCountofClosedBoxes() == bomb.getTotalBombs()) {
                state = GameState.WINNER;
            }
        }
    }

    private void openSapper(Coord coord) {
        switch (flag.get(coord)) {
            case OPENED: return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get(coord)) {
                    case ZERO: openSappersAround(coord); return;
                    case BOMB: openBombs(coord); return;
                    default: flag.setOpenedToSapper(coord);return;
                }
        }
    }

    private void openBombs(Coord bombed) {
        state = GameState.BOMBED;
        flag.setBombedToSapper(bombed);
        for (Coord coord: Ranges.getAllCoords()) {
            if (bomb.get(coord) == sapper.BOMB) {
                flag.setCountofClosedBombBoxes(coord);
            }
            else {
                flag.setNoBombToFlagedSafeBox(coord);
            }
        }
    }

    private void openSappersAround(Coord coord) {
        flag.setOpenedToSapper(coord);
        for (Coord around: Ranges.getCoordsAround(coord))
            openSapper(around);
    }

    public void pressRightButton(Coord coord) {
        if (gameOver()) {
            return;
        }
        else {
            flag.toggleFlagedToSapper(coord);
        }
    }

    public GameState getState() {
        return state;
    }
}
