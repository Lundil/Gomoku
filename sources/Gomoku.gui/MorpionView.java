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

    public MorpionView(Model model) {
        super(model);
    }

    /** Affiche la vue du plateau de jeu à chaque saisie du joueur
    * @param g : Graphics
    */
    public void paint(Graphics g) {
        super.setTitle("Version Morpion");
        g.setColor(new Color(255, 163, 102));
        g.fillRect(0, 0, getWidth(), getHeight());
        Stone stone;
        g.setColor(Color.BLACK);
        g.drawRect(20, 60, getWidth()-40, getHeight()-120);
        g.drawLine(250, 60, 250, getHeight()-60);
        g.drawLine(520, 60, 520, getHeight()-60);
        g.drawLine(20, 310, getWidth()-20, 310);
        g.drawLine(20, 570, getWidth()-20, 570);
        for(int i = 0; i < super.model.getSupport().getWidth(); i++){
            for(int j = 0; j < super.model.getSupport().getHeight(); j++){
                if(super.model.getSupport().getStone(i, j) == 1){
                    g.setColor(Color.BLACK);
                    g.fillOval(250*i+40, 250*j+90, 190, 190);
                }
                else if(super.model.getSupport().getStone(i, j) == 2){
                        g.setColor(Color.WHITE);
                        g.fillOval(250*i+40, 250*j+90, 190, 190);
                }
            }
        }
    }
}