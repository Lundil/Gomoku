package Gomoku.gui;
import Gomoku.jeu.*;
import Gomoku.regles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
import java.io.*;


public class MenuView  extends JFrame {
    private JButton gomoku, gomokuIA, morpion, puissance;

    /* construit la vue correspondant au menu principal appelé au lancement du jeu*/
    public MenuView() {
        this.gomoku = new JButton("2 joueurs");
        this.gomokuIA = new JButton("jouer contre l'IA");
        this.morpion = new JButton("Morpion");
        this.puissance = new JButton("Puissance 4");

        setLayout(null);
        setContentPane(new JLabel(new ImageIcon("../img/fond01.jpg")));

        gomoku.setBounds(100, 50, 200, 30);
        gomokuIA.setBounds(100, 110, 200, 30);
        morpion.setBounds(100, 170, 200, 30);
        puissance.setBounds(100, 230, 200, 30);
        
        setBounds(500, 300, 533, 300);

        add(gomoku);
        add(gomokuIA);
        add(morpion);
        add(puissance);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
		setTitle("Gomoku - Menu");
		setVisible(true);
    }

    /** relie la vue à un controller (ici MenuController, implémentant un action listener)
    * @param controller : MenuController */
    public void addController(MenuController controller){
        gomoku.addActionListener(controller);
        gomokuIA.addActionListener(controller);
        morpion.addActionListener(controller);
        puissance.addActionListener(controller);
    }
}