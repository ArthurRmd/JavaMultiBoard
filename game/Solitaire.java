package game;

import gui.Menu;

import observer.Observer;

import java.awt.Color;
import java.util.ArrayList;

/**
 * La classe correspond au fonctionnement du jeu : Solitaire
 *
 * @author REMOND Arthur, VIGNOLLE Thomas, LESECQ Océan
 */
public class Solitaire extends Game {

    Case caseSelection = null;
    boolean partieTermine = false;
    int score = 0;

    /**
     * Construction du jeu de taille x et y
     *
     * @param x longueur du plateau
     * @param y largueur du plateau
     */
    public Solitaire(int x, int y) {
        board = new Board(x, y, "solitaire");

        int dimX = board.getDimX();
        int dimY = board.getDimY();

        System.out.println(board.getDimX()+ "\n\n");

        board.getCase(0,0).setValue(0);
        board.getCase(0,0).canGo();

        System.out.println(board.getCase(0,0).isEmpty());
        this.remplir();

        System.out.println("construction d'un Solitaire " + dimX + "x" + dimY);
    }

    /**
     * Methode permettant le deplacement d'un pion
     *
     * @param anciennePosition emplacement du pion
     * @param nouvellePosition emplacement du deplacement du pion
     */
    public void bougerPion(Case anciennePosition, Case nouvellePosition) {

        if (nouvellePosition.getInGame() &&  anciennePosition.getInGame() &&  nouvellePosition.getValue() == 0){
            nouvellePosition.setValue(1);
            anciennePosition.setValue(0);
        }

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

    /**
     * Methode qui permet d'avoir la couleur blanc
     *
     * @return colorPawnList tableau comportant la couleur blanc pour les pions
     */
    @Override
    public ArrayList<Color> getArrayListColor() {
        ArrayList<Color> colorPawnList = new ArrayList<>();
        colorPawnList.add(Color.WHITE);
        return colorPawnList;
    }

    /**
     * Methode qui gère le deplacements des pions,
     * en fonction du pion qu'on selectionne et
     * qui vérifie où le pion a le droit de se deplacer
     *
     * @param x position horizontale
     * @param y position verticale
     */
    @Override
    public void playAtPosition (int x, int y) {

        int posX, posY;

        if(!partieTermine) {

            if (board.getCase(x, y).isSelected()) {
                this.board.getCase(x, y).setSelected(false);
            } else {
                this.board.getCase(x, y).setSelected(true);
            }

            if (caseSelection == null) {
                caseSelection = board.getCase(x, y);
            }
            else if (caseSelection == board.getCase(x,y)){
                caseSelection = null;
            }
            else {
                posX = caseSelection.getX();
                posY = caseSelection.getY();

                this.board.getCase(posX, posY).setSelected(false); // premier pion choisi (celui qui va se déplacer)
                this.board.getCase(x, y).setSelected(false); // la deuxieme case chosi (vide)
                System.out.println("\n\n" +  " Partie gagner --->  "+ partieGagne()+ "\n\n" );

                mangerPion(caseSelection, this.board.getCase(x, y));

                score += 10;

                caseSelection = null;

                System.out.println("Votre score est : " + score );

                if(partiePerdu()){
                    partieTermine = true;
                    System.out.println("\n Vous avez perdu, vous pouvez retenter votre chance");
                }
                if(partieGagne()){
                    partieTermine = true;
                    System.out.println("\n Vous avez gagner bravo");
                }
            }
        }
        else {
            if(partieGagne()){
                System.out.println("\n Vous avez gagner bravo");
            }
            else {
                System.out.println("\n Vous avez perdu, vous pouvez retenter votre chance");
            }
            System.out.println("Votre score est : " + score );
        }

    }

    /**
     * Methode verifiant la case du haut
     *
     * @param c Case selectionnee
     * @return true si se deplacer en haut est valable si non false
     */
    public boolean verifHaut(Case c) {

        // Il veut pas, à revoir au niveau des board.[...]
        int x = c.getX();
        int y = c.getY();

        if (y >= 2 && board.getCase(x,y).getValue() == 1 && board.getCase(x, y - 1).getInGame() == true &&
            board.getCase(x, y - 1).getValue() == 1 && board.getCase(x, y - 2).getInGame() == true && 
            board.getCase(x, y - 2).getValue() == 0) {

            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Methode verifiant la case du bas
     *
     * @param c Case selectionnee
     * @return true si se deplacer en bas est valable si non false
     */
    public boolean verifBas(Case c) {

        int x = c.getX();
        int y = c.getY();

        if (y<= (board.getDimY()-3) &&  board.getCase(x,y).getValue() == 1 && 
            board.getCase(x,y+1).getInGame()==true && board.getCase(x,y+1).getValue()==1 && 
            board.getCase(x,y+2).getInGame()==true && board.getCase(x,y+2).getValue()==0) {

            return true;
        }else{
            return false;
        }
    }

    /**
     * Methode verifiant la case de gauche
     *
     * @param c Case selectionnee
     * @return true si se deplacer a gauche est valable si non false
     */
    public boolean verifGauche(Case c){

        int x = c.getX();
        int y = c.getY();

        if (x>=2 &&  board.getCase(x,y).getValue() == 1    &&  board.getCase(x-1,y).getInGame()==true &&
            board.getCase(x-1,y).getValue()==1 && board.getCase(x-2,y).getInGame()==true && 
            board.getCase(x-2,y).getValue()==0) {

            return true;
        }else{
            return false;
        }
    }

    /**
     * Methode verifiant la case de droite
     *
     * @param c Case selectionnee
     * @return true si se deplacer a droite est valable si non false
     */
    public boolean verifDroite(Case c) {

        int x = c.getX();
        int y = c.getY();

        if (x<=(board.getDimX()-3) &&  board.getCase(x,y).getValue()==1 && 
            board.getCase(x+1,y).getInGame()==true && board.getCase(x+1,y).getValue()==1 &&
            board.getCase(x+2,y).getInGame()==true && board.getCase(x+2,y).getValue()==0) {

            return true;
        }else{
            return false;
        }

    }

    /**
     * Methode permettant de manger le pion qui se trouve entre  les deux cases
     *
     * @param c1 Case de depart
     * @param c2 Case d'arrivee
     */
    public void mangerPion(Case c1, Case c2) {

        int x1 = c1.getX();
        int x2 = c2.getX();
        int y1 = c1.getY();
        int y2 = c2.getY();

        if((y2-y1)==(-2) && verifHaut(c1)==true){

            if (x1==x2) {
                bougerPion(board.getCase(x1, y1), board.getCase(x2, y2));
                board.getCase(x1, y1 - 1).setValue(0);
            }
        }
        else if((y2-y1)==(2) && verifBas(c1)==true){

            if (x1==x2) {
                bougerPion(board.getCase(x1, y1), board.getCase(x2, y2));
                board.getCase(x1, y1 + 1).setValue(0);
            }
        }
        else if((x2-x1)==(-2) && verifGauche(c1)==true){

            if (y1==y2) {
                bougerPion(board.getCase(x1, y1), board.getCase(x2, y2));
                board.getCase(x1 - 1, y1).setValue(0);
            }
        }
        else if((x2-x1)==(2) && verifDroite(c1)==true){
            if (y1==y2) {
                bougerPion(board.getCase(x1, y1), board.getCase(x2, y2));
                board.getCase(x1 + 1, y1).setValue(0);
            }
        }
    }

    /**
     * Methode verifiant les cotes du pion
     *
     * @param c Case selectionnee
     * @return true si toutes les verifications sont bonnes ou sinon false
     */
    public boolean verifCotes (Case c) {
       if (verifHaut(c) || verifBas(c) || verifGauche(c) || verifDroite(c))
            return true;
        else
            return false;
    }

    /**
     * Methode permettant de verifier si la partie est perdu ou non
     * (marge de 6 pions autorisé pour faciliter le jeu)
     *
     * @return false et true si la partie est perdu
     */
    public boolean partiePerdu () {

        int x = board.getDimX();
        int i;
        int j;

        int valeurHaut =  ((x - 3)/2); // 7 -> 2
        int valeurBas =  ((x - 3)/2); // 7 -> 2
        int valeur =  ((x - 3)/2); // 7 -> 2
        int valeurGauche = ((x - 3)/2) - 1; // 7 -> 2
        int valeurDroite =  1;

        boolean partiePerdu = true;

        System.out.println(valeurHaut);

        for (i=0 ; i<x; i++){

            if (i > (valeur + 3)){

                valeurGauche--;
                valeurDroite++;

            }
            for (j=0 ; j<x; j++){

                if ( i < valeur){

                    if (j >= valeurHaut && j< ( x-valeurHaut)){

                        if (verifCotes(board.getCase(i,j)))
                            return false;

                    }
                }
                else if (i >= (x - valeur)){

                    if (j >= (valeurBas - valeurGauche) && j< ( x- valeurDroite)){

                        if (verifCotes(board.getCase(i,j)))
                            return false;
                    }
                }
                else {
                    if (verifCotes(board.getCase(i,j)))
                        return false;
                }
            }
            valeurHaut--;
        }
        return partiePerdu;

    }


    /**
     * Methode permettant de verifier si la partie est gagné ou non
     * (marge de 6 pions autorisé pour faciliter le jeu)
     *
     * @return false et true si la partie est gagne
     */
    public boolean partieGagne() {

        int i = 0;
        int j = 0;
        int nbPion = 0;
        boolean continuer = true;
        boolean gagner = true;

        while(i<board.getDimX() && continuer){

            while (j<board.getDimX() && continuer){

                if (board.getCase(i,j).getValue()==1){
                    nbPion++;
                }
                if (nbPion > 1){
                    continuer = false;
                    gagner = false;
                    
                }
                j++;
            }
            j=0;
            i++;
        }

        System.out.println(nbPion);
        return gagner;
    }

    /**
     * Fonction permettant de remplir le plateau de pions
     */
    public void remplir () {

        int x = board.getDimX();
        int i;
        int j;

        int valeurHaut =  ((x - 3)/2); // 7 -> 2
        int valeurBas =  ((x - 3)/2); // 7 -> 2
        int valeur =  ((x - 3)/2); // 7 -> 2
        int valeurGauche = ((x - 3)/2) - 1; // 7 -> 2
        int valeurDroite =  1;

        System.out.println(valeurHaut);

        for (i=0 ; i<x; i++) {

            if (i > (valeur + 3)){
                valeurGauche--;
                valeurDroite++;
            }
            for (j=0 ; j<x; j++){

                if (i < valeur){

                    if (j >= valeurHaut && j< (x-valeurHaut)){
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
