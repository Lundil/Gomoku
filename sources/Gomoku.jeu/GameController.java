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
        play();
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
        play();
    }

    public void play(){
    	view.addMouseListener(this);
        this.infoView.cancel.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {
                System.out.println("annulation du dernier coup");
                model.setSupport(model.getLastVersion());
                view.update(model, null);
            }
            public void mouseEntered(MouseEvent e){}
    
            public void mouseExited(MouseEvent e){}

            public void mousePressed(MouseEvent e){}

            public void mouseReleased(MouseEvent e){}   
        });
    }
    
    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {
        int x = -1;
        int y = -1;
        if(model.getNumberAligned() == 5){
            System.out.println("Gomoku Controller");
        	x = (e.getX()-10)/40;
        	y = (e.getY()-50)/40;
        }
        else if(model.getNumberAligned() == 3){
            System.out.println("Morpion Controller");
            //Gestion précise des cellules
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
        System.out.println("x = " + x + " y = " + y);
        if(x != -1 && y != -1){
            if(result == -1){
                model.setLastVersion(model.getSupport());
            	if(model.addStone(x, y))
                    model.getSupport().incr();
                System.out.println(model.getSupport().toString());
                result = model.endGame();
                this.view.update(model, null);
                this.infoView.update(model, null);
                System.out.println("état de la partie = " + result);
                System.out.println("nombre de pierres restantes : blanc = " + model.getSupport().getStonesWhite() + " noir = " + model.getSupport().getStonesBlack());
                //tour de l'IA
                if(this.ia != null){
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
        }
    }
}