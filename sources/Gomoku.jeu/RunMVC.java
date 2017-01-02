package Gomoku.jeu;
import Gomoku.regles.*;
import Gomoku.gui.*;

public class RunMVC {

	/** lance le menu principal permettant la sélection du jeu souhaité
	* @param args : String[] */
    public static void main(String[] args){
        MenuView menuView = new MenuView();
        MenuController controller = new MenuController(menuView);
        menuView.addController(controller);
    }
}