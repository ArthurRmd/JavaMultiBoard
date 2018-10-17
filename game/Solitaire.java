package game;

import observer.Observer;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Implémentation d'un jeu.
 *
 * Cette classe n'est donnée qu'à titre d'exemple.
 *
 * Il faudra écrire la méthode playAtPosition.
 *
 * @author samuel
 */
public class Solitaire extends Game {

    public Solitaire(int x, int y) {
        board = new Board(x, y, "solitaire");

        int dimX = board.getDimX();
        int dimY = board.getDimY();

       // this.remplir();


        System.out.println("construction d'un Solitaire " + dimX + "x" + dimY);
    }

    /**
     * Méthode qui décrit ce qui se passe lorsqu'un pion est déposé.
     *
     * @param x position horizontale
     * @param y position verticale
     */
    @Override
    public void playAtPosition(int x, int y) {
        System.out.println("not yet implemented");
    }

    /**
     * Calcule le score.
     *
     * @return
     */
    @Override
    public String getScore() {
        return "not yet implemented";
    }

    @Override
    public ArrayList<Color> getArrayListColor() {
        ArrayList<Color> colorPawnList = new ArrayList<>();
        colorPawnList.add(Color.WHITE);
        return colorPawnList;
    }

   /*
    @Override
    public void playAtPosition ( int x, int y ) {
        this.board.getCase(x,y).setSelected(true);
    }
    */



   /* public void remplir () {

        int i;
        int j;
        int valeur = (int)(7%2);

        for ( i=0 ; i<8; i++) {

            for ( j=0 ; j<8; j++) {

                board.getCase(i,j).setValue(1);

            }


        }
    }*/

}
