package Gomoku.regles;
import Gomoku.jeu.*;
import Gomoku.gui.*;

import java.util.Observer;
import java.util.Observable;

public class ModelGomoku extends Model {
    
    /** construit un modèle sur lequel se base les règles du jeu
    * il prend en compte les paramètres du plateau (cf Support)
    * et le nombre de pierres à aligner pour gagner
    * @param width : int
    * @param height : int
    * @param maxStones : int
    * @param numberAligned : int */
    public ModelGomoku(int width, int heigth, int maxStones, int numberAligned){
        super(width, heigth, maxStones, numberAligned);
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

    /** parcourt le plateau pour vérifier l'alignement pour chaque joueur version Gomoku
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
                    if(this.areOnLineGomoku(color, x, y, Direction.LEFT))
                        return true;
                    else if(this.areOnLineGomoku(color, x, y, Direction.RIGHT))
                        return true;
                    else if(this.areOnLineGomoku(color, x, y, Direction.UP))
                        return true;
                    else if(this.areOnLineGomoku(color, x, y, Direction.DOWN))
                        return true;
                    else if(this.areOnDiagonalGomoku(color, x, y, Direction.RIGHTDOWN))
                        return true;
                    else if(this.areOnDiagonalGomoku(color, x, y, Direction.RIGHTDOWN))
                        return true;
                    else if(this.areOnDiagonalGomoku(color, x, y, Direction.LEFTDOWN))
                        return true;
                    else if(this.areOnDiagonalGomoku(color, x, y, Direction.RIGHTUP))
                        return true;
                    else if(this.areOnDiagonalGomoku(color, x, y, Direction.LEFTUP))
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
	public boolean areOnLineGomoku(int color, int x, int y, Direction direction){
        int result = 0;
        for(int cpt = 0; cpt < super.numberAligned; cpt ++){
            if(direction == Direction.LEFT && x >= 4){
                if(super.support.getStone(x - cpt, y) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.RIGHT && x <= 14){
                if(super.support.getStone(x + cpt, y) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.UP && y >= 4){
                if(super.support.getStone(x, y - cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.DOWN && y <= 14){
                if(super.support.getStone(x, y + cpt) == color)
                    result ++;
                else
                    result = 0;
            }
        }
        System.out.println("nombre pierres alignées = " + result);
        return result == 5;
	}

    /** vérifie un alignement de 5 pierres en diagonale sur le plateau en fonction 
    * d' une direction (gauche, droite, haut, bas) et de la position de la pierre version Gomoku
    * @param color : int
    * @param x : int
    * @param y : int
    * @param direction : Direction
    * @return boolean */
    public boolean areOnDiagonalGomoku(int color, int x, int y, Direction direction){
        int result = 0;
        for(int cpt = 0; cpt < super.numberAligned; cpt ++){
            if(direction == Direction.LEFTUP && x >= 4 && y >= 4){
                if(super.support.getStone(x - cpt, y - cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.LEFTDOWN && x >= 4 && y <= 14){
                if(super.support.getStone(x - cpt, y + cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.RIGHTUP && x <= 14 && y >= 4){
                if(super.support.getStone(x + cpt, y - cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.RIGHTDOWN && x <= 14 && y <= 14){
                if(super.support.getStone(x + cpt, y + cpt) == color)
                    result ++;
                else
                    result = 0;
            }
        }
        System.out.println("nombre pierres alignées = " + result);
        return result == 5;
    }

    /** vérifie la possibilite d'ajouter une pierre au plateau
    * selon s'il y a des pierres à proximité
    * et le nombre de pierres restant au joueur
    * @param x : int
    * @param y : int
    * @return boolean */
    public boolean addStone(int x, int y){
        //case déjà occupée
        if(!super.free(x, y))
            return false;
        //au premier tour
        if(super.support.getNb() == 1){
            super.support.setStone(x, y, false);
            super.decrement(false);
            return true;
        }
        //coordonnées de départ et d'arrivée pour la boucle imbriquée
        int depX = x - 1;
        int arrX = x + 1;
        int depY = y - 1; 
        int arrY = y +1;
        
        //bord gauche
        if(x==0)
            depX = x;
        //bord droit
        if(x== super.support.getWidth())
            arrX = x;
        //bord haut
        if(y==0)
            depY = y;
        //bord bas
        if(y== super.support.getHeight())
            arrY = y;
        //traitement
    	for(int i = depX; i <= arrX; i ++){
        	for(int j = depY; j <= arrY; j ++){
    			if(!super.free(i, j)){
                    //tour du joueur noir
                    if(super.support.getNb()% 2 == 0 && decrement(true))
                        super.support.setStone(x, y, true);
                    else if(super.support.getNb()% 2 != 0 && decrement(false))
                    //tour du joueur blanc
                        super.support.setStone(x, y, false);
    	            return true;
    			}
    		}
    	}
    	return false;
    }
}