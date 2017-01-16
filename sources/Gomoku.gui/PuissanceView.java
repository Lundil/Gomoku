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

public class PuissanceView extends View {

    public PuissanceView(Model model) {
        super(model);
    }

    /** Affiche la vue du plateau de jeu à chaque saisie du joueur
    * @param g : Graphics
    */
    public void paint(Graphics g) {
        g.setColor(new Color(255, 163, 102));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        this.setTitle("Puissance 4");
        g.setColor(Color.BLACK);
        g.drawRect(20, 60, getWidth()-40, getHeight()-80);
        for(int i = 0; i < super.model.getSupport().getWidth(); i++){
            for(int j = 0; j < super.model.getSupport().getHeight(); j++){
                //sauf les bords moches
                if(i != 0 || j != 0){
                    g.drawLine(108*i+20, 60, i*108+20, getHeight()-20);
                    g.drawLine(20, 120*i+60, getWidth()-20, i*120+60);
                }
            }
        }
        for(int i = 0; i < super.model.getSupport().getWidth(); i++){
            for(int j = 0; j < super.model.getSupport().getHeight(); j++){

                if(super.model.getSupport().getStone(i, j) == 1){
                    g.setColor(Color.BLACK);
                    g.fillOval(108*i+35, (getHeight())-(120*j-1)-120, 70, 70);
                }
                else if(super.model.getSupport().getStone(i, j) == 2){
                        g.setColor(Color.WHITE);
                        g.fillOval(108*i+35, (getHeight())-(120*j-1)-120, 70, 70);
                }
            }
        }
    }
}