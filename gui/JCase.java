package gui;

import observer.Observer;
import game.Case;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe permettant d'afficher une case.
 */
public class JCase extends JPanel implements Observer {

    /* Couleur du pion représenté */
    private final Color color;

    /* Case représentée */
    private final Case c;

    /* Table de correspondance entre valeur et couleur */
    private List<Color> colorPawnList = new ArrayList<>();

    public void setColorPawnList(List<Color> colorPawnList) {
        this.colorPawnList = colorPawnList;
    }

    /**
     * Constructeur de JCase.
     *
     *
     * @param c la case représentée
     * @param color la couleur
     * @param a un arbitre pour la gestion des événements
     */
    public JCase(Case c, Color color, Arbiter a) {

        this.c = c;
        this.color = color;

        colorPawnList.add(Color.WHITE);
        colorPawnList.add(Color.BLACK);

        addMouseListener(a);
        
        // écoute des évenements de la case
        c.addObserver(this);
    }

    /**
     * Méthode de l'interface Observer
     */
    @Override
    public void update() {
        this.revalidate();
        this.repaint();
    }

    /**
     * Dessin de la JCase (avec éventuellement un pion).
     *
     * @param g contexte graphique
     */
    @Override

    public void paintComponent(Graphics g) {

        Paint paint;
        Graphics2D g2d;

        try {
            g2d = (Graphics2D) g;

            Color c1 = Color.black;
            Color c2 = color;

            paint = new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2);
            g2d.setPaint(paint);

            g.fillRect(0, 0, getWidth(), getHeight());

            int val = c.getValue();
            if (val != 0) {
                g.setColor(colorPawnList.get(val - 1));
                g.fillOval(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
            }

            if (c.isSelected()) {
                Graphics2D g2 = (Graphics2D) g;
                float thickness = 10;
                g2.setStroke(new BasicStroke(thickness));
                g2.setColor(Color.RED);
                g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            }

        } catch (Exception e) {
            System.err.println("error");

        }
    }

}
