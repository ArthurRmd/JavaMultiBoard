package gui;

import game.Game;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;


/**
 * Classe qui gère l'interface entre la logique du jeu et l'interface graphique.
 *
 * Son rôle principal est de se comporter comme un écouteur pour les clics des
 * JCase afin de répercuter ceux-ci au modèle.
 *
 * @author samuel
 */
public class Arbiter extends MouseAdapter {

    private final Game g;

    public Arbiter(Game g) {
        this.g = g;
    }

    /**
     * Méthode qui permet de retrourner le composant Parent (afin de retrouver
     * le JBoard)
     *
     * @param component
     * @return
     */
    Frame getFrame(Component component) {

        do {
            component = component.getParent();
        } while (!(component instanceof Frame));
        return (Frame) component;

    }

    @Override
    public void mousePressed(MouseEvent e) {

        JCase jc = (JCase) e.getComponent();

        Rectangle r = jc.getBounds();
        Point p = jc.getLocation();

        int x = p.x / r.width;
        int y = p.y / r.height;

        System.out.printf("At cell %d,%d\n", x, y);

        g.playAtPosition(x, y);

        if (g.isEndGame()) {
            JOptionPane d = new JOptionPane();
            String message = g.getScore();
            d.showMessageDialog(null, message, "le titre", JOptionPane.ERROR_MESSAGE);
        }

    }

}