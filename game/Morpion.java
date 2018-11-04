package game;

import gui.Menu;
import observer.Observer;

import java.awt.Color;
import java.util.ArrayList;

/**
 * La classe correspond au fonctionnement du jeu : Morpion
 *
 * @author REMOND Arthur, LESECQ Océan, VIGNOLLE Thomas
 */
public class Morpion extends Game {

	public Case caseSelection = null;

	/**
	 * Construction du jeu de taille x et y
	 * @param x longueur du tableau
	 * @param y largeur du tableau
	 */
	public Morpion(int x, int y){
		board = new Board(x, y, "Morpion");

		int dimX = board.getDimX();
		int dimY = board.getDimY();

		System.out.println("construction d'un Morpion " + dimX + "x" + dimY);
		System.out.println("\nJoueur 1 : Bleu");
		System.out.println("Joueur 2 : Rouge\n");
		System.out.println("Au joueur 1 de jouer !\n");
	}

	/**
	 * Methode qui gere le placement des pions, si un joueur a gagne et les tours pour savoir a qui de jouer,
	 * verification si la partie a été gagné par un joueur, si la partie se termine sur une egalité
	 *
	 * @param x emplacement horizontal de la case clique
	 * @param y emplacement vertical de la case clique
	 */
	public void playAtPosition(int x, int y) {
		if(!gagne() && !pTerminee()) {
			if (board.getCase(x, y).isEmpty()) {
				board.getCase(x, y).setValue(this.player);
					if (gagne()) {
						System.out.println("Fin de la partie");
						System.out.println("Le joueur " + this.player + " a gagne !");
					} else if (!pTerminee()){
						if(this.player == 1){
							this.player = 2;
						}else {
							this.player = 1;
						}
						System.out.println("Au joueur "+this.player+" de jouer");
					}
				}if (pTerminee()) {
					System.out.println("C'est fini !!!");
					System.out.println("Egalite !");
				}
		}else{
			System.out.println("Fini");
		}
	}

	/**
	 * Methode qui permet de choisir les couleurs des pions du joueur 1 et du joueur 2
	 *
	 * @return colorPawnList tableau comportant les couleurs des pions (bleu et rouge)
	 */
	public ArrayList<Color> getArrayListColor(){
		ArrayList<Color> colorPawnList = new ArrayList<>();
		colorPawnList.add(Color.BLUE);
		colorPawnList.add(Color.RED);
		return colorPawnList;
	}

	/**
	 * Methode qui retourne le score
	 *
	 *  @return Le score
	 */
	@Override
	public String getScore() {
		return "not yet     implemented";
	}

	/**
	 * Methode qui verifie si la partie est terminee
	 *
	 * @return false et true si la partie est terminee (tableau plein)
	 */
	public boolean pTerminee() {
		int i;
		int j;
		int k = 0;
		boolean pTerminee = false;
		for(i=0; i<3; i++){
			for(j=0; j<3; j++){
				if(board.getCase(i,j).getValue()==1 || board.getCase(i,j).getValue()==2){
					k++;
				}
			}
		}
		if(k==9){
			pTerminee=true;
		}
		return pTerminee;
	}


	/**
	 * Methode qui verifie si un joueur a gagne la partie
	 *
	 * @return false et true si le joueur a gagne
	 */
	public boolean gagne() {
		boolean gagne = false;
		if (vertical()==true) {
			gagne = true;
		}
		if (horizontal()==true) {
			gagne = true;
		}
		if (diagonal()==true) {
			gagne = true;
		}
		return gagne;
	}


	/**
	 * Methode verifiant les pions à la vertical
	 *
	 * @return true s'il y a une lignée verticale de pions du joueur
	 */
	public boolean vertical() {
		int i=0;
		int j=0;
		int k=0;
		boolean vertical = false;
		for(i=0; i<3; i++){
			for(j=0; j<3; j++){
				if(board.getCase(i,j).getValue()==this.player){
					k++;
				}
				if(k==3){
					vertical = true;
				}
			}
			k=0;
		}
		return vertical;
	}

	/**
	 * Methode verifiant les pions à la horizontal
	 *
	 * @return true s'il y a une lignée horizontale de pions du joueur
	 */
	public boolean horizontal() {
		int i = 0;
		int j = 0;
		int k = 0;
		boolean horizontal = false;
		for (j = 0; j < 3; j++) {
			for (i = 0; i < 3; i++) {
				if (board.getCase(i, j).getValue() == this.player) {
					k++;
				}
				if (k == 3) {
					horizontal = true;
				}
			}
			k = 0;
		}
		return horizontal;
	}

	/**
	 * Methode verifiant les pions en diagonal
	 *
	 * @return true s'il y a une lignée diagonale de pions du joueur
	 */
	public boolean diagonal() {
		int i = 0;
		int j = 0;
		int k = 0;
		boolean diagonal = false;
		for(i=0; i<3; i++){
			if(board.getCase(i,j).getValue()==this.player){
				k++;
			}
			j++;
			if(k==3){
				diagonal = true;
			}
		}
		i=2;
		j=0;
		k=0;
		for(i=2; i>=0; i--){
			if(board.getCase(i,j).getValue()==this.player){
				k++;
			}
			j++;
			if(k==3){
				diagonal = true;
			}
		}
		k=0;
		return diagonal;
	}

}