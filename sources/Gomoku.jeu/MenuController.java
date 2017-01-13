package Gomoku.jeu;
import Gomoku.gui.*;
import Gomoku.regles.*;

import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class MenuController implements ActionListener { 
    MenuView view;


    /** construit le listener du menu principal
    * @param view : MenuView */
    MenuController(MenuView view) {
    	this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        String st = ((JButton) e.getSource()).getText();
        if(st.equals("2 joueurs")){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ModelGomoku model = new ModelGomoku(19, 19, 60, 5);
                    GomokuView view = new GomokuView(model);
                    InfoView infoView = new InfoView(model);
                    GameController controller = new GameController(model, view, infoView);
                }
            });
        }
        else if(st.equals("jouer contre l'IA")){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    IAGomoku ia = new IAGomoku();
                    System.out.println("IA créée");
                    ModelGomoku model = new ModelGomoku(19, 19, 60, 5);
                    GomokuView view = new GomokuView(model);
                    InfoView infoView = new InfoView(model);
                    GameController controller = new GameController(model, view, infoView, ia);
                }
            });
        }
        else if(st.equals("Morpion")){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ModelMorpion model = new ModelMorpion( 3, 3, 5, 3);
                    MorpionView morpionView = new MorpionView(model);
                    InfoView infoView = new InfoView(model);
                    GameController controller = new GameController(model, morpionView, infoView);
                }
            });
        }
        if(st.equals("Puissance 4")){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ModelPuissance model = new ModelPuissance( 7, 6, 21, 4);
                    PuissanceView puissanceView = new PuissanceView(model);
                    InfoView infoView = new InfoView(model);
                    GameController controller = new GameController(model, puissanceView, infoView);
                }
            });
        }
    }
}