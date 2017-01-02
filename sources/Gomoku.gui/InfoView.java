package Gomoku.gui;
import Gomoku.jeu.*;
import Gomoku.regles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
import java.io.*;


public class InfoView  extends JFrame implements Observer {
    Model model;
    private Image image = null;
    public JButton cancel;

    public InfoView(Model model) {
		this.model = model;
		model.addObserver(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setBounds(100, 100, 533, 300);
		setTitle("Informations sur Gomoku");
		setVisible(true);
        this.cancel = new JButton("annuler le coup");
        cancel.setBounds( 40, 30, 160, 50);
    }

    public void update(Observable o, Object arg) {
		this.repaint();
    }

    public void addController(GameController controller){
    }

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