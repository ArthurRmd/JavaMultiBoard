package game;

import gui.Menu;

import observer.Observer;

import java.awt.Color;
import java.util.ArrayList;

/**
 * La classe correspond au fonctionnement du jeu : Dame
 *
 * @author REMOND Arthur, LESECQ Océan, VIGNOLLE Thomas
 */
public class Dame  extends Game {

    private int joueurDoitJouer = 1;
    boolean prendrePlusieurPion = false;
    Case caseSelection = null;

    /**
     * Construction du jeu de taille x et y
     * @param x longueur du plateau
     * @param y largeur du plateau
     */
    public Dame(int x, int y) {

        board = new Board(x, y, "solitaire");
        int dimX = board.getDimX();
        int dimY = board.getDimY();

        this.remplir();

        System.out.println("construction d'un jeu de Dame " + dimX + "x" + dimY);


        System.out.println("\n\n\n****************************");
        System.out.println("\nBienvenue dans le jeu de java");
        System.out.println("Voici les règles");
        System.out.println("\n- Vous êtes obligé de prendre un pion, si vous le pouvez ");
        System.out.println("- Vous pouvez prendre plusieur pion à la suite (pas disponible avec la dame)");
        System.out.println("\n****************************\n\n\n");
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
        colorPawnList.add(Color.PINK);
        colorPawnList.add(Color.gray);
        colorPawnList.add(Color.red);
        return colorPawnList;
    }

    /**
     * Méthode qui décrit ce qui se passe lorsqu'un pion est déposé.
     *
     * @param x position horizontale
     * @param y position verticale
     */
    @Override
    public void playAtPosition (int x, int y) {

        int posX, posY;

        if (!partieFini()){

            // Si la partie n'est pas terminé on éxécute le code

            // Verif si la case sélectionné est un bien le joueur qui doit jouer
            if ((caseSelection == null && ((board.getCase(x, y).getValue() == joueurDoitJouer) ||
                    (board.getCase(x, y).getValue() == joueurDoitJouer+2))) ||
                    (caseSelection != null && board.getCase(x, y).getValue() == 0)) {

                // Si une case cliqué est déja sélectionné alors on la désélectionne sinon on la sélectione
                if (board.getCase(x, y).isSelected()) {
                    this.board.getCase(x, y).setSelected(false);
                } else {
                    this.board.getCase(x, y).setSelected(true);
                }

                // Si pas de case séléctionné alors on la sélectionne
                if (caseSelection == null) {
                    caseSelection = board.getCase(x, y);
                }
                //Si la deuxièmre case selectionné est la même que la première
                else if (caseSelection == board.getCase(x, y)) {
                    caseSelection = null;
                }

                else {
                    posX = caseSelection.getX();
                    posY = caseSelection.getY();

                    if (caseSelection.getValue() == joueurDoitJouer || caseSelection.getValue() == joueurDoitJouer+2) {
                        if (estUnPion(caseSelection)) {
                            //Code a excuter lorsque c'est un pion

                            //Verifie si le pion peut prendre un autre pion
                            if (verifCotes(caseSelection)) {
                                System.out.println("pion mangé");
                                mangerPion(caseSelection, this.board.getCase(x, y));
                                if (verifCotes(board.getCase(x, y))) {
                                    System.out.println("Vous pouvez encore prendre un/des pions");
                                    prendrePlusieurPion = true;
                                    this.board.getCase(posX, posY).setSelected(false);
                                    caseSelection = board.getCase(x, y);
                                } else {
                                    if (caseSelection.getValue() == 0) {
                                        joueurDoitJouer = changerJoueur(joueurDoitJouer);
                                        prendrePlusieurPion = false;
                                    }
                                }
                            }
                            //Si il ne peut pas manger de pion
                            else if (caseSelection.getValue() == joueurDoitJouer && !peutPrendre(joueurDoitJouer)) {
                                if (deplacementPossible(caseSelection, this.board.getCase(x, y), joueurDoitJouer)) {
                                    bougerPion(caseSelection, this.board.getCase(x, y));
                                    joueurDoitJouer = changerJoueur(joueurDoitJouer);
                                }
                            }
                        }
                        else{
                            //Code a executer lorsque c'est une dame
                            if (deplacementPossible(caseSelection, this.board.getCase(x, y), joueurDoitJouer)){
                                bougerPion(caseSelection, this.board.getCase(x, y));
                            }
                            joueurDoitJouer = changerJoueur(joueurDoitJouer);
                        }

                        //Après avoir joué, remet les cases déselectionné
                        if (!prendrePlusieurPion) {
                            this.board.getCase(posX, posY).setSelected(false); // premier pion choisi ( celui qui va se déplacer
                            this.board.getCase(x, y).setSelected(false); // la deuxieme case chosi (vide)
                            caseSelection = null;
                        }
                    }
                }
            }
            else if (caseSelection == board.getCase(x, y)) {
                caseSelection = null;
                board.getCase(x, y).setSelected(false);
            }

            devienUneDame();
        }

        // Si la partie est termniné
        else {
            System.out.println("La partie est terminé");
        }


    }


    /**
     * Permet de remplir le plateau des pions du joueur 1 et 2
     */
    public void remplir () {

        int i, j;
        boolean pair = false;

        for (i=0 ; i< 10; i++) {
            for (j=0 ; j< 10; j++) {
                if ( j <= 3 || j>= 6) {
                    if (pair && ((j % 2) == 0)) {
                        if(j>= 5) {
                            board.getCase(i, j).setValue(1);
                        }
                        else {
                            board.getCase(i, j).setValue(2);
                        }
                        board.getCase(i,j).setInGame(true);
                    } else if ((!pair && ((j % 2) == 1))) {
                        if(j>= 5) {
                            board.getCase(i, j).setValue(1);
                        }
                        else {
                            board.getCase(i, j).setValue(2);
                        }
                        board.getCase(i,j).setInGame(true);
                    }
                    else {
                        board.getCase(i,j).setInGame(false);
                    }
                }
                else if ((pair && ((j % 2) == 0))){
                    board.getCase(i,j).setInGame(true);

                }
                else if ((!pair && ((j % 2) == 1))){
                    board.getCase(i,j).setInGame(true);

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

    /**
     * @param c une case du plateau
     * @return true si un pion à la case C peut prendre un pion du cotès Haut|Gauche
     */
    public boolean verifHautGauche(Case c) {

        int x = c.getX();
        int y = c.getY();
        int pion = board.getCase(x, y).getValue();
        int pionAdverse = changerJoueur(pion);
        int pionAdverse2 = pionAdverse+2;

        //Si c'est un pion alors , sinon (dame)
        if (pion == 1  || pion == 2) {



            if (y >= 2 && x>=2  &&  ( board.getCase(x-1, y - 1).getValue() == pionAdverse ||
                    board.getCase(x-1, y - 1).getValue() == pionAdverse2) && board.getCase(x-2, y - 2).getValue() == 0) {
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * @param c une case du plateau
     * @return true si un pion à la case C peut prendre un pion du cotès Haut|Droit
     */
    public boolean verifHautDroit(Case c) {

        int x = c.getX();
        int y = c.getY();
        int pion = board.getCase(x, y).getValue();
        int pionAdverse = changerJoueur(pion);
        int pionAdverse2 = pionAdverse+2;

        //Si c'est un pion alors , sinon (dame)
        if (pion == 1  || pion == 2) {


            if (y >= 2 && x<=(board.getDimX()-3)  && (board.getCase(x+1, y - 1).getValue() == pionAdverse ||
                    board.getCase(x+1, y - 1).getValue() == pionAdverse2)  && board.getCase(x+2, y - 2).getValue() == 0) {
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * @param c une case du plateau
     * @return true si un pion à la case C peut prendre un pion du cotès Bas|Gauche
     */
    public boolean verifBasGauche(Case c) {

        int x = c.getX();
        int y = c.getY();
        int pion = board.getCase(x, y).getValue();
        int pionAdverse = changerJoueur(pion);
        int pionAdverse2 = pionAdverse+2;

        //Si c'est un pion alors , sinon (dame)
        if (pion == 1  || pion == 2) {

            if (y<= (board.getDimY()-3) && x>=2  && (board.getCase(x-1, y +1).getValue() == pionAdverse ||
                    board.getCase(x-1, y +1).getValue() == pionAdverse2)&& board.getCase(x-2, y + 2).getValue() == 0) {

                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * @param c une case du plateau
     * @return true si un pion à la case C peut prendre un pion du cotès Bas|Droit
     */
    public boolean verifBasDroit(Case c) {

        int x = c.getX();
        int y = c.getY();
        int pion = board.getCase(x, y).getValue();
        int pionAdverse = changerJoueur(pion);
        int pionAdverse2 = pionAdverse+2;

        //Si c'est un pion alors , sinon (dame)
        if (pion == 1  || pion == 2) {


            if (y<= (board.getDimY()-3) && x<=(board.getDimX()-3)   && ( board.getCase(x+1, y + 1).getValue() == pionAdverse ||
                    board.getCase(x+1, y + 1).getValue() == pionAdverse2 ) && board.getCase(x+2, y + 2).getValue() == 0) {

                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * @param c une case du plateau
     * @return true si un pion à la case C peut prendre au moins un pion d'un des cotès
     */
    public boolean verifCotes (Case c){
        if (verifHautGauche(c) || verifHautDroit(c) || verifBasGauche(c) || verifBasDroit(c))
            return true;
        else
            return false;
    }


    /**
     * Permet de manger et de déplacer un pion (verifie si la manipulation est possible avant de faire)
     *
     * @param c1 Case du plateau (pion qui veut manger)
     * @param c2 Case du plateau (position du pion après avoir mangé)
     */
    public void mangerPion(Case c1, Case c2){

        int x1 = c1.getX();
        int x2 = c2.getX();
        int y1 = c1.getY();
        int y2 = c2.getY();

        if((y2-y1)==(-2) && (x2-x1)==(-2) && verifHautGauche(c1)){

            bougerPion(board.getCase(x1, y1), board.getCase(x2, y2));
            board.getCase(x1-1, y1 - 1).setValue(0);
        }
        else if( (y2-y1)==(-2) && (x2-x1)==(2) && verifHautDroit(c1)){

            bougerPion(board.getCase(x1, y1), board.getCase(x2, y2));
            board.getCase(x1+1, y1 -1).setValue(0);
        }
        else if( (y2-y1)==(2) && (x2-x1)==(-2) && verifBasGauche(c1)){

            bougerPion(board.getCase(x1, y1), board.getCase(x2, y2));
            board.getCase(x1 - 1, y1+1).setValue(0);
        }
        else if( (y2-y1)==(2) && (x2-x1)==(2) && verifBasDroit(c1)){

            bougerPion(board.getCase(x1, y1), board.getCase(x2, y2));
            board.getCase(x1 + 1, y1+1).setValue(0);
        }
    }

    /**
     * @param c Case du plateau
     * @return true si à la case c il y a un pion, sinon renvoie false
     */
    public boolean estUnPion(Case c){

        int x = c.getX();
        int y = c.getY();

        if (board.getCase(x,y).getValue() == 1 || board.getCase(x,y).getValue() == 2){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Permet de bouger un pion / dame d'un endroit A à un endroit B
     *
     * @param anciennePosition position du pion (A)
     * @param nouvellePosition position du pion a la fin du déplacement (B)
     */
    public void bougerPion(Case anciennePosition, Case nouvellePosition){

        int valeurPion = anciennePosition.getValue();

        if (nouvellePosition.getInGame() &&  anciennePosition.getInGame() &&  nouvellePosition.getValue() == 0){

            nouvellePosition.setValue(valeurPion);
            anciennePosition.setValue(0);
        }
    }


    /**
     * Permet de savoir si un pion / Dame position a un endroit A peut aller a un endroit B
     *
     * @param anciennePosition position du pion (A)
     * @param nouvellePosition position du pion a la fin du déplacement (B)
     * @param joueurDoitJouer numéro du joueur
     * @return true si le pion peut se déplacer, sinon false
     */
    public boolean deplacementPossible (Case anciennePosition, Case nouvellePosition, int joueurDoitJouer) {

        int x1 = anciennePosition.getX();
        int x2 = nouvellePosition.getX();
        int y1 = anciennePosition.getY();
        int y2 = nouvellePosition.getY();

        int test1 = x1-x2;
        int test2 = y1-y2;

        if (estUnPion(anciennePosition)){

            // Code s'excecute si c'est un pion
            if (joueurDoitJouer == 1) {
                //Si la valeur du pion est 2
                if ( y1 >= 1 && x1 >=1  && test1==1 && test2==1 && board.getCase(x1-1, y1 - 1).getValue() == 0 ){
                    return true;
                }
                else if (y1 >= 1 && x1<=(board.getDimX()-2) && test1==-1 && test2==1 && board.getCase(x1+1, y1 - 1).getValue() == 0){
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                //Si la valeur du pion est 2
                if ( y1<= (board.getDimY()-2) && x1>=1 && test1==1  && test2==-1 &&  board.getCase(x1-1, y1 +1).getValue() == 0 ){
                    return true;
                }
                else if (y1<= (board.getDimY()-2) && x1<=(board.getDimX()-2) && test1==-1 && test2==-1  && board.getCase(x1+1, y1 + 1).getValue() == 0){
                    return true;
                }
                else
                    return false;
            }

        }
        else {
            // Code s'excecute si c'est une dame

            //Renvoi false si la position ou la dame doit attérir n'est pas vide
            if (board.getCase(x2,y2).getValue() != 0){
                return false;
            }

            //Sauvegarde de la position de la dame
            int x_1 = x1;
            int y_1 = y1;

            //Variable utilisé pour la boucle
            int x = 0;
            int y = 0;

            //Permet de savoir comment veut se déplacer la dame
            boolean deplacementVersLeHaut;
            boolean deplacementVersLaGauche;

            // Booleen pour arreter la boucle
            boolean continuer = true;

            //Savoir si la dame a déja passer un pion (Ne peux pas prendre plusieur pion)
            boolean aDejaPasserUnPion = false;

            // Se que la fonction va retourner

            boolean peutSeDeplacer = true;

            // Valeur des pions que la dame peut prendre
            int valeurPionDamePeutPrendre;


            // Definie la variable valeurPionDamePeutPrendre
            if (anciennePosition.getValue() == 3)
                valeurPionDamePeutPrendre = 2;
            else
                valeurPionDamePeutPrendre = 1;


            // Test pour connaitre le déplacement
            if (test1 > 0)
                deplacementVersLaGauche = true;
            else
                deplacementVersLaGauche = false;

            if (test2 > 0)
                deplacementVersLeHaut = true;
            else
                deplacementVersLeHaut = false;

            // 4 testes, en execute seulement un en fonction du déplacement de la dame (diagonale Haut/Droit et Bas/Gauche)


            if (deplacementVersLaGauche && deplacementVersLeHaut){
                x_1--;
                y_1--;
                while ( x_1>=0 && y_1>=0 && continuer){
                    if (x_1 == x2 && y_1 == y2){
                        continuer = false;
                    }
                    //Si passe sur un pion qui peut prendre aDejaPasserUnPion égale a true, comme il ne peut pas passer deux fois sur une diagonale
                    // si il repasse sur un autre pion le déplacement est impossible
                    if (board.getCase(x_1,y_1).getValue() == valeurPionDamePeutPrendre || board.getCase(x_1,y_1).getValue() == valeurPionDamePeutPrendre+2 ){

                        if (aDejaPasserUnPion)
                            peutSeDeplacer = false;
                        else {
                            aDejaPasserUnPion = true;
                            x = x_1;
                            y = y_1;
                        }
                    }
                    x_1--;
                    y_1--;
                }

            }
            else if (deplacementVersLaGauche && !deplacementVersLeHaut){
                x_1--;
                y_1++;
                while (x_1>=0 && y_1<=9 && continuer){
                    if (x_1 == x2 && y_1 == y2){
                        continuer = false;
                    }
                    //Si passe sur un pion qui peut prendre aDejaPasserUnPion égale a true, comme il ne peut pas passer deux fois sur une diagonale
                    // si il repasse sur un autre pion le déplacement est impossible
                    if (board.getCase(x_1,y_1).getValue() == valeurPionDamePeutPrendre || board.getCase(x_1,y_1).getValue() == valeurPionDamePeutPrendre+2 ){
                        if (aDejaPasserUnPion)
                            peutSeDeplacer = false;
                        else {
                            aDejaPasserUnPion = true;
                            x = x_1;
                            y = y_1;
                        }
                    }
                    x_1--;
                    y_1++;
                }

            }
            else if (!deplacementVersLaGauche && deplacementVersLeHaut){
                System.out.println("On va ici");
                x_1++;
                y_1--;
                while ( x_1<=9 && y_1>=0 && continuer){
                    if (x_1 == x2 && y_1 == y2){
                        continuer = false;
                    }
                    //Si passe sur un pion qui peut prendre aDejaPasserUnPion égale a true, comme il ne peut pas passer deux fois sur une diagonale
                    // si il repasse sur un autre pion le déplacement est impossible
                    if (board.getCase(x_1,y_1).getValue() == valeurPionDamePeutPrendre || board.getCase(x_1,y_1).getValue() == valeurPionDamePeutPrendre+2 ){
                        if (aDejaPasserUnPion)
                            peutSeDeplacer = false;
                        else {
                            aDejaPasserUnPion = true;
                            x = x_1;
                            y = y_1;
                        }
                    }
                    x_1++;
                    y_1--;
                }

            }

            else if (!deplacementVersLaGauche && !deplacementVersLeHaut){
                x_1++;
                y_1++;
                while ( x_1<=9 && y_1<=9 && continuer){
                    if (x_1 == x2 && y_1 == y2){
                        continuer = false;
                    }
                    // Si passe sur un pion qui peut prendre aDejaPasserUnPion égale a true,
                    // comme il ne peut pas passer deux fois sur une diagonale
                    // si il repasse sur un autre pion le déplacement est impossible
                    if (board.getCase(x_1,y_1).getValue() == valeurPionDamePeutPrendre ||
                            board.getCase(x_1,y_1).getValue() == valeurPionDamePeutPrendre+2 ){
                        if (aDejaPasserUnPion)
                            peutSeDeplacer = false;
                        else {
                            aDejaPasserUnPion = true;
                            x = x_1;
                            y = y_1;
                        }
                    }
                    x_1++;
                    y_1++;
                }
            }

            //Permet de manger le pion si la dame passe sur un pion
            if (aDejaPasserUnPion && peutSeDeplacer){
                board.getCase(x, y).setValue(0);
            }

            return (peutSeDeplacer && !continuer);
        }
    }


    /**
     * Permet de savoir si un joueur peut prendre un pion adverse, utiliser pour la règle "Soufflet Mais pas jouer"
     *
     * @param joueurDoitJouer numéro du joueur
     * @return true si le joueur peut prendre au moins un pion, sinon false
     */
    public boolean peutPrendre (int joueurDoitJouer) {

        boolean continuer = true;
        int i = 0;
        int j = 0;

        while (i < 10 && continuer == true){

            while (j<10 && continuer == true){

                // if (!board.getCase(i,j).getInGame()){
                if (board.getCase(i,j).getValue() == joueurDoitJouer){

                    if (verifCotes(board.getCase(i,j))) {
                        continuer = false;
                        System.out.println("Le joueur " + joueurDoitJouer + "peut prendre au moins un pion");
                    }
                }
                //}
                j++;
            }
            j=0;
            i++;
        }

        return !continuer;
    }

    /**
     * Transforme un pion en dame lorsqu'il atteint le fond du plateau
     */
    public void devienUneDame () {

        int i;

        for (i=1; i<10; i+=2){
            if (board.getCase(i,0).getValue() == 1){
                board.getCase(i,0).setValue(3);
                System.out.println("Dame !!!");
            }
        }

        for (i=0; i<=8; i+=2 ){
            if (board.getCase(i,9).getValue() == 2){
                board.getCase(i,9).setValue(4);
                System.out.println("Dame !!!");
            }
        }

    }

    /**Permet de savoir si la partie est terminé
     * @return true si la partie est terminé, sinon renvoi false
     */
    public boolean partieFini () {
        int nombrePion = 0;
        int i,j;
        for (i=0; i<10; i++){
            for (j=0; j<10; j++){
                if (board.getCase(i,j).getValue() !=0){
                    nombrePion++;
                }
            }
        }
        if (nombrePion > 1)
            return false;
        else
            return true;
    }


    /** Permet de changer de joueur facilement
     * @param joueur  numéro du joueur qui vient de jouer
     * @return numéro du joueur qui va joueur le prochain tour
     */
    public int changerJoueur (int joueur) {

        if (joueur == 1){
            return 2;
        }
        else {
            return 1;
        }

    }

}