package Gomoku.regles;
import Gomoku.jeu.*;
import Gomoku.gui.*;

import java.util.Observer;
import java.util.Observable;

public class ModelPuissance extends Model {
    
    /** construit un modèle sur lequel se base les règles du jeu
    * il prend en compte les paramètres du plateau (cf Support)
    * et le nombre de pierres à aligner pour gagner
    * @param width : int
    * @param height : int
    * @param maxStones : int
    * @param numberAligned : int */
    public ModelPuissance(int width, int height, int maxStones, int numberAligned){
        super(width, height, maxStones, numberAligned);
    }

    /** retourne un nombre pour savoir si le joueur noir a gagné (1),
    * si le joueur blanc a gagné (2), si la partie est nulle (0), sinon -1
    * @return int */
    public int endGame(){
        //victoire des noirs
        if(alignedGomoku(true))
            return 1;
        //victoire des blancs
        else if(alignedGomoku(false))
            return 2;
        //partie nulle
        if(super.support.getStonesBlack() == 0 && super.support.getStonesWhite() == 0)
            return 0;
        //continuité du jeu
        return -1;
    }

    public boolean free(int x){
        for(int i = super.support.getHeight()-1; i >= 0; i --){
            if(super.support.getStone(x, i) == 0)
                return true;
        }
        return false;
    }

    /** parcourt le plateau pour vérifier l'alignement pour chaque joueur dans chaque direction version Gomoku
    * @param black : boolean
    * @return boolean */
    public boolean alignedGomoku(boolean black){
        for(int x = 0; x < super.support.getWidth(); x ++){
            for(int y = 0; y < super.support.getHeight(); y ++){
                int color = super.support.getStone(x, y);
                if(color != 0){
                    if(black)
                        color = 1;
                    else
                        color = 2;
                    if(this.areOnLinePuissance(color, x, y, Direction.LEFT))
                        return true;
                    else if(this.areOnLinePuissance(color, x, y, Direction.RIGHT))
                        return true;
                    else if(this.areOnLinePuissance(color, x, y, Direction.UP))
                        return true;
                    else if(this.areOnLinePuissance(color, x, y, Direction.DOWN))
                        return true;
                    else if(this.areOnDiagonalPuissance(color, x, y, Direction.RIGHTDOWN))
                        return true;
                    else if(this.areOnDiagonalPuissance(color, x, y, Direction.RIGHTDOWN))
                        return true;
                    else if(this.areOnDiagonalPuissance(color, x, y, Direction.LEFTDOWN))
                        return true;
                    else if(this.areOnDiagonalPuissance(color, x, y, Direction.RIGHTUP))
                        return true;
                    else if(this.areOnDiagonalPuissance(color, x, y, Direction.LEFTUP))
                        return true;
                }
            }
        }
        return false;
    }

    /** vérifie un alignement de 5 pierres en ligne sur le plateau en fonction 
    * d' une direction (gauche, droite, haut, bas) et de la position de la pierre version Gomoku
    * @param color : int
    * @param x : int
    * @param y : int
    * @param direction : Direction
    * @return boolean */
	public boolean areOnLinePuissance(int color, int x, int y, Direction direction){
        int result = 0;
        for(int cpt = 0; cpt < super.numberAligned; cpt ++){
            if(direction == Direction.LEFT && x >= 4){
                if(super.support.getStone(x - cpt, y) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.RIGHT && x <= 2){
                if(super.support.getStone(x + cpt, y) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.UP && y >= 3){
                if(super.support.getStone(x, y - cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.DOWN && y <= 2){
                if(super.support.getStone(x, y + cpt) == color)
                    result ++;
                else
                    result = 0;
            }
        }
        System.out.println("nombre pierres alignées = " + result);
        return result == 4;
	}

    /** vérifie un alignement de 5 pierres en diagonale sur le plateau en fonction 
    * d' une direction (gauche, droite, haut, bas) et de la position de la pierre version Gomoku
    * @param color : int
    * @param x : int
    * @param y : int
    * @param direction : Direction
    * @return boolean */
    public boolean areOnDiagonalPuissance(int color, int x, int y, Direction direction){
        int result = 0;
        for(int cpt = 0; cpt < super.numberAligned; cpt ++){
            if(direction == Direction.LEFTUP && x >= 4 && y >= 3){
                if(super.support.getStone(x - cpt, y - cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.LEFTDOWN && x >= 4 && y <= 2){
                if(super.support.getStone(x - cpt, y + cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.RIGHTUP && x <= 2 && y >= 3){
                if(super.support.getStone(x + cpt, y - cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.RIGHTDOWN && x <= 2 && y <= 2){
                if(super.support.getStone(x + cpt, y + cpt) == color)
                    result ++;
                else
                    result = 0;
            }
        }
        System.out.println("nombre pierres alignées = " + result);
        return result == 4;
    }

    /** vérifie la possibilité d'ajouter une pierre au plateau
    * selon s'il y a des pierres à proximité
    * et le nombre de pierres restant au joueur
    * @param x : int
    * @return boolean */
    public boolean addStone(int x){
        //case déjà occupée
        if(!free(x))
            return false;
        //au premier tour
        if(super.support.getNb() == 1){
            super.support.setStone(x, false);
            super.decrement(false);
            return true;
        }
        //place la pierre sur le plateau et supprime une pierre de son stock
        //tour du joueur noir
        if(super.support.getNb()% 2 == 0 && decrement(true)){
            super.support.setStone(x, true);
            return true;
        }
        //tour du joueur blanc
        else if(super.support.getNb()% 2 != 0 && decrement(false)){
           super.support.setStone(x, false);
    	   return true;
        }
        return false;
    }
}