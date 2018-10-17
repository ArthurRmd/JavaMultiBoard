package gui;

import game.Board;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;

public class JBoard extends JPanel {

    private final Board b;

    /**
     * Construit une représentation graphique d'un jeu de plateau
     *
     *
     * @param board Le plateau
     * @param a un arbitre
     * @param listColor une liste de couleur pour les joueurs
     */
    public JBoard(Board board, Arbiter a, List<Color> listColor) {
        this.b = board;

        int dimX = b.getDimX();
        int dimY = b.getDimY();

        // carefull : GridLayout (row, col) !!
        this.setLayout(new GridLayout(dimY, dimX));

        for (int j = 0; j < dimY; j++) {
            for (int i = 0; i < dimX; i++) {

                JCase jc = new JCase(b.getCase(i, j), Color.GREEN, a);

                jc.setColorPawnList(listColor);

                this.add(jc);
            }
        }
    }
}
