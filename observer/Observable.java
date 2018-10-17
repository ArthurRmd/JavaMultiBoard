package observer;

/**
 * Propriétés que doivent posséder un Observable.
 * @author samuel
 */
public interface Observable {

    /**
     * Ajouter un observateur
     *
     * @param o
     */
    void addObserver(Observer o);

    /**
     * Retirer un observateur
     *
     * @param o
     */
    void removeObserver(Observer o);

    /**
     * notifier tout le monde
     */
    void notifyObservers();

}
