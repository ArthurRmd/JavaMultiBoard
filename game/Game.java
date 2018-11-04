package game;

import gui.Arbiter;

import java.awt.Color;
import java.util.List;


/**
 * Classe qui représente un jeu abstrait.
 * @author samuel
 */
public abstract class Game {

    protected Board board;
    protected Arbiter a;
    protected int player = 1;
    protected int move = 1;

    public abstract String getScore();

    public abstract void playAtPosition(int x, int y);

    public boolean isEndGame() {
        return false;
    }

    public void setArbiter(Arbiter a) {
        this.a = a;
    }

    public Board getBoard() {
        return board;
    }

    public void finDeTour() {
        // rien pour l'instant
        throw new UnsupportedOperationException();
    }

    public abstract List<Color> getArrayListColor();

}