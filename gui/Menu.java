package gui;

import game.Dame;
import game.Game;
import game.Solitaire;
import game.Puissance;
import game.Morpion;

import gui.Menu;

import javax.swing.*;
import javax.swing.JComponent;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JTextPane {

    private int valeur;

    public Menu () {

        JFrame choix = new JFrame();

        choix.setSize(700,500);
        choix.setTitle("Choix jeu");
        choix.setLocationRelativeTo(null);
        choix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
        JLabel label1 = new JLabel("Test");
        label1.setText("Choix du jeu");
        label1.setPreferredSize(new Dimension(300,150));
        label1.setFont(new Font("Arial", Font.PLAIN, 45));
          
        JButton cSolitaire =new JButton("Solitaire");
        cSolitaire.setPreferredSize(new Dimension(300,90));
        cSolitaire.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton cDames = new JButton("Dames");
        cDames.setPreferredSize(new Dimension(300,90));
        cDames.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton cP4 = new JButton("Puissance 4");
        cP4.setPreferredSize(new Dimension(300,90));
        cP4.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton cMorpion = new JButton("Morpion");
        cMorpion.setPreferredSize(new Dimension(300,90));
        cMorpion.setFont(new Font("Arial", Font.PLAIN, 20));
          
        JPanel J3 = new JPanel();
        JPanel J4 = new JPanel();
          
        choix.getContentPane().setLayout(new GridLayout(2,1,100,0));
          
        J3.add(cSolitaire);
        J3.add(cDames);
        J3.add(cP4);
        J3.add(cMorpion);
        J4.add(label1);

        choix.add(J4);
        choix.add(J3);
          
        choix.setVisible(true);

        cSolitaire.addActionListener(new ActionListener() {
    	
            public void actionPerformed(ActionEvent actionEvent) {
        	
                System.out.println("Bonjour");

                JFrame menu = new JFrame();

                menu.setSize(700,500);
                menu.setTitle("Menu Solitaire");
                menu.setLocationRelativeTo(null);
                menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JButton bt1 =new JButton("Lancer le jeu de la MORT");
                bt1.setPreferredSize(new Dimension(300,90));
                bt1.setFont(new Font("Arial", Font.PLAIN, 20));
                bt1.setBackground(Color.YELLOW);
                bt1.setForeground(Color.BLUE);

                JLabel text = new JLabel("11");
                text.setPreferredSize(new Dimension(150,50));
                text.setHorizontalAlignment(JLabel.CENTER);
                text.setFont(new Font("Arial", Font.PLAIN, 53));
                
                JButton plus = new JButton("+");
                plus.setPreferredSize(new Dimension(150,75));
                plus.setFont(new Font("Arial", Font.PLAIN, 53));
                plus.setBackground(Color.GREEN);
                plus.setForeground(Color.BLACK);

                JButton moins = new JButton("-");
                moins.setPreferredSize(new Dimension(150,75));
                moins.setFont(new Font("Arial", Font.PLAIN, 53));
                moins.setBackground(Color.RED);
                moins.setForeground(Color.BLACK);
            
                JButton retour = new JButton("Retour au menu");
                retour.setPreferredSize(new Dimension(300,50));
                retour.setFont(new Font("Arial", Font.PLAIN, 20));
                retour.setBackground(Color.WHITE);
                retour.setForeground(Color.BLACK);
            
                JPanel J1 = new JPanel();
                JPanel J2 = new JPanel();
                JPanel J3 = new JPanel();

                menu.getContentPane().setLayout(new GridLayout(3,1,50,0));
                
                J1.add(moins);
                J1.add(text);
                J1.add(plus);
                J2.add(bt1);
                J3.add(retour);

                menu.add(J1);
                menu.add(J2);
                menu.add(J3);

                choix.setVisible(false);
                menu.setVisible(true);
        	
                bt1.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        JFrame f = new JFrame();
                        f.setSize(900, 900);
                        f.setLocationRelativeTo(null);

                        valeur =  Integer.valueOf(text.getText()).intValue();
                        System.out.println(valeur);

                        Game g = new Solitaire(valeur, valeur);

                        Arbiter a = new Arbiter(g);
                        JBoard jb = new JBoard(g.getBoard(), a, g.getArrayListColor());

                        f.add(jb);

                        menu.setVisible(false);
                        choix.setVisible(true);
                        f.setVisible(true);
                    }
                });

                plus.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        int temp;
                        String texteTemp = new String();

                        temp =  Integer.valueOf(text.getText()).intValue();
                        if(temp<11){
                    	   temp +=  2;
                        }
                        texteTemp = texteTemp.valueOf(temp);

                        text.setText(texteTemp);
                    }
                });

                moins.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        int temp;
                        String texteTemp = new String();

                        temp =  Integer.valueOf(text.getText()).intValue();
                        if(temp>5){
                        	temp -=  2;
                        }
                        texteTemp = texteTemp.valueOf(temp);

                        text.setText(texteTemp);
                    }
                });

                retour.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        choix.setVisible(true);
                        menu.setVisible(false);
                    }
                });
           	}
        });

        cDames.addActionListener(new ActionListener() {
          	
            public void actionPerformed(ActionEvent actionEvent) {

                JFrame f = new JFrame();

        	    f.setSize(900, 900);
                f.setLocationRelativeTo(null);

    	        Game g = new Dame(10, 10);

                Arbiter a = new Arbiter(g);
    	        JBoard jb = new JBoard(g.getBoard(), a, g.getArrayListColor(), Color.white, Color.black);

                f.add(jb);

    	        choix.setVisible(true);
    	        f.setVisible(true);
        	}
          });

        cP4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                JFrame f = new JFrame();
                f.setSize(900, 900);
                f.setLocationRelativeTo(null);

                Game g = new Puissance(7, 6);


                Arbiter a = new Arbiter(g);
                JBoard jb = new JBoard(g.getBoard(), a, g.getArrayListColor(), Color.white, Color.white);

                f.add(jb);

                choix.setVisible(true);
                f.setVisible(true);
            }
        });

        cMorpion.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {

                JFrame f = new JFrame();

                f.setSize(600, 600);
                f.setLocationRelativeTo(null);

                Game g = new Morpion(3, 3);

                Arbiter a = new Arbiter(g);
                JBoard jb = new JBoard(g.getBoard(), a, g.getArrayListColor(), Color.white, Color.white);

                f.add(jb);

                choix.setVisible(true);
                f.setVisible(true);
            }
        });
    }

    public int getMenu () {
        return this.valeur;
    }

}