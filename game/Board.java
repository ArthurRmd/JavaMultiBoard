package game;

import gui.JBoard;

import javax.swing.*;


/**
 * Représente un plateau de jeu
 *
 * Codage : 0 = pas de pion 1 = pion du joueur 1 2 = pion du joueur 2 ...
 */
public class Board {

    /**
     * Dimensions en X (horizontalement) et Y (verticalement)
     */
    private final int dimX;
    private final int dimY;

    /**
     * Tableau de Cases
     */
    private final Case[][] board;

    /**
     * Constructeur
     *
     * @param dimX
     * @param dimY
     */
    public Board(int dimX, int dimY) {
        this.dimX = dimX;
        this.dimY = dimY;
        board = new Case[dimX][dimY];
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                board[i][j] = new Case(i, j);
            }
        }
    }

    public Board(int dimX, int dimY, String jeu) {

        this.dimX = dimX;
        this.dimY = dimY;
        board = new Case[dimX][dimY];
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                board[i][j] = new Case(i, j);
            }
        }
    }

    /**
     * La case de coordonnées x, y
     *
     * @param x
     * @param y
     * @return
     */
    public Case getCase(int x, int y) {
        return this.board[x][y];
    }

    /**
     * Dimension en X
     *
     * @return
     */
    public int getDimX() {
        return this.dimX;
    }

    /**
     * Dimension en y
     *
     * @return
     */
    public int getDimY() {
        return this.dimY;
    }

    /**
     * Retourne la valeur de la case.
     *
     * @param x
     * @param y
     * @return
     */
    public int getValue(int x, int y) {
        return this.board[x][y].getValue();
    }

}