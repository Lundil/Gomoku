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

    MenuController(MenuView view) {
    	this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        String st = ((JButton) e.getSource()).getText();
        if(st.equals("2 joueurs")){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Model model = new Model(19, 19, 60, 5);
                    GomokuView view = new GomokuView(model);
                    InfoView infoView = new InfoView(model);
                    GameController controller = new GameController(model, view, infoView);
                }
            });
        }
        else if(st.equals("jouer contre l'IA")){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Model model = new Model(19, 19, 60, 5);
                    GomokuView gomokuView = new GomokuView(model);
                    InfoView infoView = new InfoView(model);
                    GameController controller = new GameController(model, gomokuView, infoView);
                }
            });
        }
        else if(st.equals("Version Morpion")){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Model model = new Model( 3, 3, 5, 3);
                    MorpionView morpionView = new MorpionView(model);
                    InfoView infoView = new InfoView(model);
                    GameController controller = new GameController(model, morpionView, infoView);
                }
            });
        }
        if(st.equals("Version Puissance 4")){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Model model = new Model(6, 6, 10, 4);
                    GomokuView gomokuView = new GomokuView(model);
                    InfoView infoView = new InfoView(model);
                    GameController controller = new GameController(model, gomokuView, infoView);
                }
            });
        }
    }
}