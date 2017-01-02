package Gomoku.gui;
import Gomoku.jeu.*;
import Gomoku.regles.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class View extends JFrame implements Observer {
    Model model;
    private Image image = null;

    public View(Model model) {
		this.model = model;
		model.addObserver(this);
		//Basics
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(600, 300, 800, 800);
		setTitle("Game -- Tour 1");
		setVisible(true);
    }

    public void update(Observable o, Object arg) {
		this.repaint();
    }

    public void addController(GameController controller){
    }
}