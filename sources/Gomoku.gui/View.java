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

public class View extends JFrame {
    Model model;
    private Image image = null;

    /** construit la vue originale du jeu quelque soit sa version selon le modèle
    * @param model : Model */
    public View(Model model) {
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(600, 300, 800, 800);
		setVisible(true);
    }

    /** met à jour l'affichage en fonction du modèle donné*/
    public void update() {
		this.repaint();
    }

    /** lie un controller (ici un mouse listener) à la vue
    * @param controller : MouseListener */
    public void addController(MouseListener controller){
        this.addMouseListener(controller);
    }
}