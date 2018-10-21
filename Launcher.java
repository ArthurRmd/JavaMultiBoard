
import game.Dame;
import game.Game;
import game.Solitaire;

import gui.Arbiter;
import gui.JBoard;
import gui.Menu;

import javax.swing.JFrame;
import java.awt.*;


public final class Launcher {

    private Launcher() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {




       Menu test = new Menu();


        JFrame f = new JFrame();
        f.setSize(900, 900);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        Game g = new Dame( 10 , 10);

        Arbiter a = new Arbiter(g);
        JBoard jb = new JBoard(g.getBoard(), a, g.getArrayListColor(), Color.white, Color.black);

        f.add(jb);

        f.setVisible(true);











    }

}
