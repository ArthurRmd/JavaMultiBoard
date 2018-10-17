package game;

import observer.Observable;
import observer.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui représente une Case.
 *
 * Elle peut comporter un pion (valeur non nulle) Elle pourra être sélectionnée.
 *
 *
 * @author samuel
 */
public class Case implements Observable {

    private boolean selected = false;

    /**
     * Valeur du pion de la case (0 si vide).
     */
    private int value;

    /**
     * Position de la case horizontalement.
     */
    private int posX;

    /**
     * Position de la case verticalement.
     */
    private int posY;

    /**
     * Indique si la case est utilisable dans un jeu
     */
    private boolean inGame;
    /**
     * Cosntructeur.
     *
     * @param posX position de la case horizontalement.
     * @param posY position de la case verticalement.
     */
    public Case(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.inGame = true;
    }

    /**
     * liste des observer
     */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Ajoute un observateur
     *
     * @param o observateur
     */
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Retire un observateur
     *
     * @param o observateur
     */
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * notifie les observateurs
     *
     */
    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }

    /**
     * Est sélectionnée.
     *
     * @return
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Valeur de la case (le joueur qui a placé un pion).
     *
     * @param v
     */
    public void setValue(int v) {
        this.value = v;
        notifyObservers();
    }

    /**
     * retourne la valeur de la case (le joueur qui a placé un pion).
     *
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * Affiche la position de la case.
     *
     * @return position.
     */
    @Override
    public String toString() {
        return "(" + posX + "," + posY + ")";
    }



    public void setSelected (boolean selected) {
        this.selected = selected;
        this.notifyObservers();
    }



    public boolean canGo () {

        return (this.inGame);

    }

    public void setInGame(boolean bool){
        this.inGame = bool;
    }
    public boolean getInGame(){
        return this.inGame;
    }


    public boolean isEmpty(){
        return(this.value == 0);
    }






}
