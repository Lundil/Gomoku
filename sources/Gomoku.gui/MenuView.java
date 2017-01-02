package Gomoku.gui;
import Gomoku.jeu.*;
import Gomoku.regles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
import java.io.*;


public class MenuView  extends JFrame implements Observer {
    private JButton gomoku, gomokuIA, morpion, puissance;

    public MenuView() {
        this.gomoku = new JButton("2 joueurs");
        this.gomokuIA = new JButton("jouer contre l'IA");
        this.morpion = new JButton("Version Morpion");
        this.puissance = new JButton("Version Puissance 4");

        setLayout(null);
        setContentPane(new JLabel(new ImageIcon("../img/fond01.jpg")));

        gomoku.setBounds(130, 50, 160, 30);
        gomokuIA.setBounds(130, 110, 160, 30);
        morpion.setBounds(130, 170, 160, 30);
        puissance.setBounds(130, 230, 160, 30);
        
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

    public void update(Observable o, Object arg) {
		this.repaint();
    }


    public void addController(MenuController controller){
        gomoku.addActionListener(controller);
        gomokuIA.addActionListener(controller);
        morpion.addActionListener(controller);
        puissance.addActionListener(controller);
    }
}