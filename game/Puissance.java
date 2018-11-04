package game;

import gui.Menu;
import observer.Observer;

import java.awt.Color;
import java.util.ArrayList;

/**
 * La classe correspond au fonctionnement du jeu : Puissance 4
 *
 * @author VIGNOLLE Thomas, REMOND Arthur, LESECQ Océan
 */
public class Puissance extends Game{

	public Case caseSelection = null;

	/**
	 * Construction du jeu de taille x et y
	 *
	 * @param x largeur du tableau
	 * @param y hauteur du tableau
	 */
	public Puissance(int x, int y) {
		board = new Board(x, y, "Puissance4");

		int dimX = board.getDimX();
		int dimY = board.getDimY();

		System.out.println("construction d'un Puissance4 " + dimX + "x" + dimY);
	}

	/**
	 * Methode qui gere le placement des pions, si un joueur a gagné et les tours pour savoir a qui de jouer
	 *
	 * @param x colonne de la case clique
	 * @param y ligne de la case clique
	 */
	public void playAtPosition(int x, int y) {

		boolean estPlace = false;
		int l = 5;

		if(!alignementHorizontal() && !alignementVertical() && !diagonaleDescendante() && !diagonaleMontante()){
			while(l >= 0 && estPlace == false){
				if (board.getCase(x,l).isEmpty()){
					board.getCase(x,l).setInGame(true);
					estPlace = true;
					if (this.player == 1) {
						board.getCase(x,l).setValue(1);
						if (alignementHorizontal() || alignementVertical() ||
								diagonaleDescendante() || diagonaleMontante()) {
							System.out.println("Le joueur "+this.player+" a gagnÃ© !");				    		        	}
						this.player = 2;
					} else {
						board.getCase(x,l).setValue(2);
						if(alignementHorizontal() || alignementVertical() ||
								diagonaleDescendante() || diagonaleMontante()) {
							System.out.println("Le joueur "+this.player+"a gagnÃ© !");				    		        	}
						this.player = 1;
					}
				}else{
					l--;
				}
			}
		}

	}


	/**
	 * Methode qui permet de choisir les couleurs des pions du joueur 1 et du joueur 2
	 *
	 * @return colorPawnList tableau comportant les couleurs des pions
	 */
	public ArrayList<Color> getArrayListColor() {
		ArrayList<Color> colorPawnList = new ArrayList<>();
		colorPawnList.add(Color.YELLOW);
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
	 * Methode qui verifie les lignes pour savoir si un joueur a gagne
	 *
	 * @return gagne booleen qui permet de savoir si un joueur a 4 pions alignes sur une ligne
	 */
	public boolean alignementHorizontal() {

		int i = 0;
		int j = 0;
		int cpt = 1;
		boolean gagne = false;

		while(!gagne && j<6){
			if(board.getCase(i,j).getValue() == 1 || board.getCase(i,j).getValue() == 2) {
				if(board.getCase(i, j).getValue() == board.getCase(i+1,j).getValue()){
					cpt++;
					if(cpt == 4) {
						gagne = true;
					}
				}else{
					cpt = 1;
				}
			}
			if(i == 5) {
				i=0;
				j++;
				cpt=1;
			}else {
				i++;
			}
		}

		return gagne;
	}


	/**
	 * Methode qui verifie les colonnes pour savoir si un joueur a gagne
	 *
	 * @return gagne booleen qui permet de savoir si un joueur a 4 pions alignes sur une colonne
	 */
	public boolean alignementVertical() {

		int i = 0;
		int j = 0;
		int cpt = 1;
		boolean gagne = false;

		while(!gagne && i<7){
			if(board.getCase(i,j).getValue() == 1 || board.getCase(i,j).getValue() == 2) {
				if(board.getCase(i, j).getValue() == board.getCase(i,j+1).getValue()){
					cpt++;
					if(cpt == 4) {
						gagne = true;
					}
				}else{
					cpt = 1;
				}
			}
			if(j == 4) {
				j=0;
				i++;
				cpt=1;
			}else {
				j++;
			}
		}
		return gagne;

	}


	/**
	 * Methode qui verifie les diagonales descendantes "\" pour savoir si un joueur a gagne
	 *
	 * @return gagne booleen qui permet de savoir si un joueur a 4 pions alignes sur une diagonale descendante
	 */
	public boolean diagonaleDescendante() {

		int i = 0;
		int j = 0;
		boolean gagne = false;

		while(!gagne && i<4){
			if(board.getCase(i,j).getValue() == 1 || board.getCase(i,j).getValue() == 2) {
				if(board.getCase(i, j).getValue() == board.getCase(i+1,j+1).getValue()){
					if(board.getCase(i, j).getValue() == board.getCase(i+2,j+2).getValue()){
						if(board.getCase(i, j).getValue() == board.getCase(i+3,j+3).getValue()){
							gagne = true;
						}
					}
				}
			}
			if(j == 2){
				j=0;
				i++;
			}else {
				j++;
			}
		}

		return gagne;

	}


	/**
	 * Methode qui verifie les diagonales montantes "/" pour savoir si un joueur a gagne
	 *
	 * @return gagne booleen qui permet de savoir si un joueur a 4 pions alignes sur une diagonale montante
	 */
	public boolean diagonaleMontante() {

		int i = 0;
		int j = 3;
		boolean gagne = false;

		while (!gagne && i<5){
			if (board.getCase(i,j).getValue() == 1 || board.getCase(i,j).getValue() == 2){
				if (board.getCase(i, j).getValue() == board.getCase(i+1,j-1).getValue()){
					if (board.getCase(i, j).getValue() == board.getCase(i+2,j-2).getValue()){
						if (board.getCase(i, j).getValue() == board.getCase(i+3,j-3).getValue()){
							gagne = true;
						}
					}
				}
			}
			if (j == 5){
				j=3;
				i++;
			} else {
				j++;
			}
		}

		return gagne;

	}

}