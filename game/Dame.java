package game;

import gui.Menu;
import observer.Observer;

import java.awt.Color;
import java.util.ArrayList;


public class Dame  extends Game {




    Case caseSelection = null;

    public Dame(int x, int y) {
        board = new Board(x, y, "solitaire");

        int dimX = board.getDimX();
        int dimY = board.getDimY();





        this.remplir(x);

       // board.getCase(1,1).







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
            this.board.getCase(posX, posY).setSelected(false); // premier pion choisi ( celui qui va se déplacer

            this.board.getCase(x, y).setSelected(false); // la deuxieme case chosi (vide)
            caseSelection = null;










            bougerPion(board.getCase(posX,posY), board.getCase(x,y));

        }



    }






    public void remplir (int x) {



       int i, j;
       boolean pair = false;


       for (i=0 ; i< 10; i++) {

           for (j=0 ; j< 10; j++) {


               if ( j <= 3 || j>= 6) {
                   if (pair && ((j % 2) == 0)) {
                       board.getCase(i, j).setValue(1);
                       board.getCase(i,j).setInGame(true);
                   } else if ((!pair && ((j % 2) == 1))) {
                       board.getCase(i, j).setValue(1);
                       board.getCase(i,j).setInGame(true);
                   }
                   else {
                       board.getCase(i,j).setInGame(false);
                   }
               }
               else if ((pair && ((j % 2) == 0))){
                   board.getCase(i,j).setInGame(true);
                   System.out.println(i+"ok\n");
               }
               else if ((!pair && ((j % 2) == 1))){
                   board.getCase(i,j).setInGame(true);
                   System.out.println(i+"ok\n");
               }
               else {
                   board.getCase(i,j).setInGame(false);
               }



           }

           if (pair) {
               pair = false;
           } else {
               pair = true;
           }

        }

    }
}
