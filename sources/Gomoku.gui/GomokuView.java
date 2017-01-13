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

public class GomokuView extends View {

    public GomokuView(Model model) {
        super(model);
    }

    /** Affiche la vue du plateau de jeu à chaque saisie du joueur
    * @param g : Graphics
    */
    public void paint(Graphics g) {

        g.setColor(new Color(255, 163, 102));
        g.fillRect(0, 0, getWidth(), getHeight());
		
        this.setTitle("Gomoku");

        g.drawRect(20, 60, getWidth()-20, getHeight()-20);
		for(int i = 0; i < super.model.getSupport().getWidth(); i++){
            for(int j = 0; j < super.model.getSupport().getHeight(); j++){
                //sauf les bords moches
                if(i != 0 || j != 0){
                    g.setColor(Color.BLACK);
                    g.drawLine(40*i+20, 60, i*40+20, getHeight()-60);
                    g.drawLine(20, 40*i+20, getWidth()-60, i*40+20);
                }
            }
        }
        for(int i = 0; i < super.model.getSupport().getWidth(); i++){
            for(int j = 0; j < super.model.getSupport().getHeight(); j++){

                if(super.model.getSupport().getStone(i, j) == 1){
                    g.setColor(Color.BLACK);
                    g.fillOval(40*i+1, 40*j+40, 38, 38);
                }
                else if(super.model.getSupport().getStone(i, j) == 2){
                        g.setColor(Color.WHITE);
                        g.fillOval(40*i+1, 40*j+40, 38, 38);
                }
            }
        }
    }
}