package Gomoku.gui;
import Gomoku.jeu.*;
import Gomoku.regles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
import java.io.*;


public class InfoView  extends View {

    private Image image = null;

    public InfoView(Model model) {
        super(model);
        super.setBounds(100, 100, 533, 300);
        super.setTitle("Informations sur Gomoku");

    }

    /** mets à jour l'affichage en fonction du modèle donné
    * @param o : Observable
    * @param arg : Object
    */
    public void update(Observable o, Object arg) {
		this.repaint();
    }

    public void addController(GameController controller){
    }


    /** Affiche la vue du plateau de jeu à chaque saisie du joueur
    * @param g : Graphics
    */
    public void paint(Graphics g) {
        image = getToolkit().getImage("../img/fond01.jpg");
        if(image != null)
            g.drawImage(image, 0, 0, this);

        if(model.getSupport().getNb()% 2 == 0 && model.endGame() == -1)
            g.drawString("Tour " + model.getSupport().getNb() + "  Shogun", 70, 100);
        else if(model.getSupport().getNb()% 2 != 0 && model.endGame() == -1)
            g.drawString("Tour " + model.getSupport().getNb() + "  Yakuza", 70, 100);

        if(model.getSupport().getNb() == 1 || model.endGame() == -1)
            g.drawString("Affrontement en cours ...", 100, 200);
        else{
            if(model.endGame() == 1)
                g.drawString("Avènement du Shogun", 100, 200);
            else if(model.endGame() == 2)
                g.drawString("Renversement des Yakuzas", 100, 200);
            else if(model.endGame() == 0)
                g.drawString("Aucune faction ne remporte la victoire", 60, 200);
        }
    }
}