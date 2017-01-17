package Gomoku.jeu;
import Gomoku.gui.*;
import Gomoku.regles.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;

public class GameController implements MouseListener { 
    Model model;
    View view;
    InfoView infoView;
    int result = -1;
    IAGomoku ia = null;

    /** construit le controleur du jeu sélectionné pour la gestion de son affichage
    * @param model: Model
    * @param view : View
    * @param infoView : InfoView */
    public GameController(Model model, View view, InfoView infoView) {
    	this.model = model;
    	this.view = view;
    	this.infoView = infoView;
        view.addMouseListener(this);
    }

    /** construit le controleur et l'IA du jeu sélectionné pour la gestion de son affichage
    * @param model: Model
    * @param view : View
    * @param infoView : InfoView
    * @param ia IAGomoku */
    public GameController(Model model, View view, InfoView infoView, IAGomoku ia) {
        this.model = model;
        this.view = view;
        this.infoView = infoView;
        this.ia = ia;
        view.addMouseListener(this);
    }
    
    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {
        int x = -1;
        int y = -1;
        //gestion de la saisie des coordonnées pour le Gomoku
        if(model.getNumberAligned() == 5){
            System.out.println("Gomoku Controller");
        	x = (e.getX()-10)/40;
        	y = (e.getY()-50)/40;
        }
        //gestion de la saisie des coordonnées pour le Morpion
        else if(model.getNumberAligned() == 3){
            System.out.println("Morpion Controller");
            if(e.getX() > 20 && e.getX() < 250)
                x = 0;
            if(e.getX() > 250 && e.getX() < 520)
                x = 1;
            if(e.getX() > 520 && e.getX() < 780)
                x = 2;
            if(e.getY() > 40 && e.getY() < 270)
                y = 0;
            if(e.getY() > 270 && e.getY() < 520)
                y = 1;
            if(e.getY() > 520 && e.getY() < 770)
                y = 2;
        }
        //gestion de la saisie des coordonnées pour le Puissance 4
        else if(model.getNumberAligned() == 4){
            System.out.println("Puissance Controller");
            if(e.getX() > 20 && e.getX() < 128)
                x = 0;
            if(e.getX() > 128 && e.getX() < 236)
                x = 1;
            if(e.getX() > 236 && e.getX() < 344)
                x = 2;
            if(e.getX() > 344 && e.getX() < 452)
                x = 3;
            if(e.getX() > 452 && e.getX() < 560)
                x = 4;
            if(e.getX() > 560 && e.getX() < 668)
                x = 5;
            if(e.getX() > 668 && e.getX() < 776)
                x = 6;
        }

        System.out.println("x = " + x + " y = " + y);
        //si la saisie a été prise en compte
        //et qu'il s'agit du Gomoku ou du Morpion
        if(x != -1 && y != -1 && model.getNumberAligned() != 4){
            if(model.addStone(x, y))
                gomokuTour(x, y);
        }
        //si la saisie a été prise en compte
        //et qu'il s'agit du Puissance 4
        else if(x != -1 && model.getNumberAligned() == 4){
            if(model.addStone(x))
               puissanceTour(x);
        }
    }

    public void gomokuTour(int x, int y){
        if(result == -1){
            //bug
            //if(model.addStone(x, y))
                model.getSupport().incr();
            System.out.println(model.getSupport().toString());
            result = model.endGame();
            this.view.update(model, null);
            this.infoView.update(model, null);
            System.out.println("état de la partie = " + result);
            System.out.println("nombre de pierres restantes : blanc = " + model.getSupport().getStonesWhite() + " noir = " + model.getSupport().getStonesBlack());
            //tour de l'IA si elle existe
            if(this.ia != null){
                iaTour(x, y);
            }
        }
    }

    public void puissanceTour(int x){
        if(result == -1){
            //if(model.addStone(x))
                model.getSupport().incr();
            System.out.println(model.getSupport().toString());
            result = model.endGame();
            this.view.update(model, null);
            this.infoView.update(model, null);
            System.out.println("état de la partie = " + result);
            System.out.println("nombre de pierres restantes : blanc = " + model.getSupport().getStonesWhite() + " noir = " + model.getSupport().getStonesBlack());
        }
    }

    public void iaTour(int x, int y){
        //tant que le coup ne convient pas au plateau, génère des coordonnées aléatoires
        do{
            this.ia.playRandom(x, y);
        } while(!model.addStone(this.ia.getX(), this.ia.getY()));
        model.getSupport().incr();
        System.out.println(model.getSupport().toString());
        result = model.endGame();
        this.view.update(model, null);
        this.infoView.update(model, null);
        System.out.println("état de la partie = " + result);
        System.out.println("nombre de pierres restantes : blanc = " + model.getSupport().getStonesWhite() + " noir = " + model.getSupport().getStonesBlack());
    }
}