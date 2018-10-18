package game;

import gui.Menu;
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

    Case caseSelection = null;

    public Solitaire(int x, int y) {
        board = new Board(x, y, "solitaire");

        int dimX = board.getDimX();
        int dimY = board.getDimY();




       board.getCase(0,0).setValue(0);

       board.getCase(0,0).canGo();


        System.out.println(board.getCase(0,0).isEmpty());
        this.remplir(x);

        bougerPion(board.getCase(4,4), board.getCase(3,3));







        System.out.println("construction d'un Solitaire " + dimX + "x" + dimY);
    }


    public void bougerPion(Case anciennePosition, Case nouvellePosition){

        if ( nouvellePosition.getInGame() &&  anciennePosition.getInGame() &&  nouvellePosition.getValue() == 0){
            nouvellePosition.setValue(1);
            anciennePosition.setValue(0);
        }

    }
    



    /**
     * Méthode qui décrit ce qui se passe lorsqu'un pion est déposé.
     *
     * @param x position horizontale
     * @param y position verticale
     */



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














    @Override
    public void playAtPosition ( int x, int y ) {

        int posX, posY;


        if ( board.getCase(x,y).isSelected()) {
            this.board.getCase(x,y).setSelected(false);
        }
        else {
            this.board.getCase(x, y).setSelected(true);
        }

        if (caseSelection == null || caseSelection == board.getCase(x,y) ){
            caseSelection = board.getCase(x,y);
        }
        else {
            System.out.println("Bonjour");

            posX = caseSelection.getX();
            posY = caseSelection.getY();
            this.board.getCase(posX, posY).setSelected(false);

            this.board.getCase(x, y).setSelected(false);
            caseSelection = null;

            bougerPion(board.getCase(posX,posY), board.getCase(x,y));
        }



    }





    public void remplir (int x) {

        int i;
        int j;
        int valeurHaut =  ((x - 3)/2); // 7 -> 2
        int valeurBas =  ((x - 3)/2); // 7 -> 2
        int valeur =  ((x - 3)/2); // 7 -> 2

        int valeurGauche = ((x - 3)/2) - 1; // 7 -> 2
        int valeurDroite =  1;





        System.out.println(valeurHaut);

        for ( i=0 ; i<x; i++) {

            if (i > (valeur + 3)) {
                valeurGauche--;
                valeurDroite++;
            }

            for ( j=0 ; j<x; j++) {

                if ( i < valeur){

                    if (j >= valeurHaut && j< ( x-valeurHaut)){
                        board.getCase(j,i).setValue(1);
                        board.getCase(i,j).setInGame(true);



                    }
                    else {
                        board.getCase(j,i).setValue(0);
                        board.getCase(i,j).setInGame(false);
                    }

                }
                else if (i >= (x - valeur)){

                    if (j >= (valeurBas - valeurGauche) && j< ( x- valeurDroite)){
                        board.getCase(j,i).setValue(1);
                        board.getCase(i,j).setInGame(true);

                    }
                    else {
                        board.getCase(j,i).setValue(0);
                        board.getCase(i,j).setInGame(false);
                    }
                }
                else {

                    board.getCase(j,i).setValue(1);
                    board.getCase(i,j).setInGame(true);
                }




            }

            valeurHaut--;

        }

        int middle = ((x - 3)/2)+1;
        board.getCase(middle,middle).setValue(0);




    }

}
