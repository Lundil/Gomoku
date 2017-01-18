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

public class MorpionView extends View {

    /* construit la vue correspondant au jeu version Morpion
    * param model : Model */
    public MorpionView(Model model) {
        super(model);
    }

    /** Affiche la vue du plateau de jeu en fonction du modèle
    * qui s'actualise à chaque saisie du joueur
    * @param g : Graphics
    */
    public void paint(Graphics g) {
        super.setTitle("Version Morpion");
        g.setColor(new Color(255, 163, 102));
        g.fillRect(0, 0, getWidth(), getHeight());
        Stone stone;
        g.setColor(Color.BLACK);
        g.drawRect(20, 40, getWidth()-40, getHeight()-80);
        g.drawLine(250, 40, 250, getHeight()-40);
        g.drawLine(520, 40, 520, getHeight()-40);
        g.drawLine(20, 270, getWidth()-20, 270);
        g.drawLine(20, 520, getWidth()-20, 520);
        for(int i = 0; i < super.model.getSupport().getWidth(); i++){
            for(int j = 0; j < super.model.getSupport().getHeight(); j++){
                if(super.model.getSupport().getStone(i, j) == 1){
                    g.setColor(Color.BLACK);
                    g.fillOval(250*i+40, 240*j+70, 190, 190);
                }
                else if(super.model.getSupport().getStone(i, j) == 2){
                    g.setColor(Color.WHITE);
                    g.fillOval(250*i+40, 240*j+70, 190, 190);
                }
            }
        }
    }
}