package gui;

import com.sun.org.apache.xpath.internal.operations.Plus;
import game.Game;
import game.Solitaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu {

    private int valeur;

    public Menu (){

        System.out.println("Bonjour");



            JFrame menu = new JFrame();
            menu.setSize(700,500);
            menu.setTitle("Menu Solitaire");
            menu.setLocationRelativeTo(null);



            JButton bt1 =new JButton("bonjour");
            JLabel text = new JLabel("11");

            JButton plus = new JButton("+");
            JButton moins = new JButton("-");



            menu.getContentPane().setLayout(new FlowLayout());
            menu.getContentPane().add(bt1);
            menu.getContentPane().add(text);
          menu.getContentPane().add(plus);
          menu.getContentPane().add(moins);

             menu.setVisible(true);



            bt1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {


                    JFrame f = new JFrame();
                    f.setSize(900, 900);
                    f.setLocationRelativeTo(null);
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                    valeur =  Integer.valueOf(text.getText()).intValue();
                    System.out.println(valeur);

                    Game g = new Solitaire(valeur, valeur);

                    Arbiter a = new Arbiter(g);
                    JBoard jb = new JBoard(g.getBoard(), a, g.getArrayListColor());

                    f.add(jb);

                    f.setVisible(true);

                    menu.setVisible(false);

                  //((Solitaire) g).remplir();
                 }
             });


            plus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    int temp;
                    String texteTemp = new String();

                    temp =  Integer.valueOf(text.getText()).intValue();
                    temp +=  1;
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
                temp -=  1;
                texteTemp = texteTemp.valueOf(temp);

                text.setText(texteTemp);


             }
           });







    }






}
