package Gomoku.regles;
import Gomoku.jeu.*;
import Gomoku.gui.*;

import java.util.Observer;
import java.util.Observable;

public class Model extends Observable {
    private Support support;
    private Support lastVersion;
    private int numberAligned;
    
    public Model(int width, int heigth, int maxStones, int numberAligned){
        this.support = new Support(width, heigth, maxStones);
        this.lastVersion = this.support;
        this.numberAligned = numberAligned;
    }

    /** retourne un nombre pour savoir si le joueur noir a gagné (1),
    * si le joueur blanc a gagné (2), sinon 0
    * @param support : Support
    * @return int */
    public int endGame(){
        if(this.numberAligned == 5){
            //victoire des noirs
            if(alignedGomoku(true) || support.getStonesWhite() == 0)
                return 1;
            //victoire des blancs
            else if(alignedGomoku(false) || support.getStonesBlack() == 0)
                return 2;
        }
        else if(this.numberAligned == 3){
            //victoire des noirs
            if(alignedMorpion(true) || support.getStonesWhite() == 0)
                return 1;
            //victoire des blancs
            else if(alignedMorpion(false) || support.getStonesBlack() == 0)
                return 2;
        }
        //partie nulle
        if(support.getStonesBlack() == 0 && support.getStonesWhite() == 0)
            return 0;
        //continuité du jeu
        return -1;
    }


    public boolean decrement(boolean black){
        if(black){
            if(support.getStonesBlack() > 0){
                support.setStonesBlack(support.getStonesBlack()-1);
                return true;
            }
        } else{
            if(support.getStonesWhite() > 0){
                support.setStonesWhite(support.getStonesWhite()-1);
                return true;
            }
        }
        return false;
    }

    /** parcourt le plateau pour vérifier l'alignement pour chaque joueur
    * @param support : Support
    * @param black : boolean
    * @return boolean */
    public boolean alignedGomoku(boolean black){
        for(int x = 0; x < support.getWidth(); x ++){
            for(int y = 0; y < support.getHeight(); y ++){
                int color = support.getStone(x, y);
                if(color != 0){
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
    * d' une direction (gauche, droite, haut, bas) et de la position de la pierre
    * @param support : Support
    * @param stone : Stone
    * @param direction : Direction
    * @return boolean */
	public boolean areOnLineGomoku(int color, int x, int y, Direction direction){
        int result = 0;
        for(int cpt = 0; cpt < this.numberAligned; cpt ++){
            if(direction == Direction.LEFT && x >= 4){
                if(support.getStone(x - cpt, y) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.RIGHT && x <= 15){
                if(support.getStone(x + cpt, y) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.UP && y >= 4){
                if(support.getStone(x, y - cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.DOWN && y <= 15){
                if(support.getStone(x, y + cpt) == color)
                    result ++;
                else
                    result = 0;
            }
        }
        return result == 4;
	}

    /** vérifie un alignement de 5 pierres en diagonale sur le plateau Gomoku
    * @param stone : Stone
    * @param direction : Direction
    * @return boolean */
    public boolean areOnDiagonalGomoku(int color, int x, int y, Direction direction){
        int result = 0;
        for(int cpt = 0; cpt < this.numberAligned; cpt ++){
            if(direction == Direction.LEFTUP && x >= 4 && y >= 4){
                if(support.getStone(x - cpt, y - cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.LEFTDOWN && x >= 4 && y <= 15){
                if(support.getStone(x - cpt, y + cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.RIGHTUP && x <= 15 && y >= 4){
                if(support.getStone(x + cpt, y - cpt) == color)
                    result ++;
                else
                    result = 0;
            }
            else if(direction == Direction.RIGHTDOWN && x <= 15 && y <= 15){
                if(support.getStone(x + cpt, y + cpt) == color)
                    result ++;
                else
                    result = 0;
            }
        }
        return result == 4;
    }

    public boolean alignedMorpion(boolean black){
        if(black){
            if(areOnLineMorpion(1) || areOnDiagonalMorpion(1))
                return true;
            return false;
        }
        else{
            if(areOnLineMorpion(2) || areOnDiagonalMorpion(2))
                return true;
            return false;
        }
    }

    public boolean areOnLineMorpion(int color){
        if(support.getStone(0, 0) == support.getStone(0, 1) && support.getStone(0, 1) == support.getStone(0, 2) && support.getStone(0, 2) == color)
            return true;
        if(support.getStone(1, 0) == support.getStone(1, 1) && support.getStone(1, 1) == support.getStone(1, 2) && support.getStone(1, 2) == color)
            return true;
        if(support.getStone(2, 0) == support.getStone(2, 1) && support.getStone(2, 1) == support.getStone(2, 2) && support.getStone(2, 2) == color)
            return true;
        if(support.getStone(0, 0) == support.getStone(1, 0) && support.getStone(1, 0) == support.getStone(2, 0) && support.getStone(2, 0) == color)
            return true;
        if(support.getStone(0, 1) == support.getStone(1, 1) && support.getStone(1, 1) == support.getStone(2, 1) && support.getStone(2, 1) == color)
            return true;
        if(support.getStone(0, 2) == support.getStone(1, 2) && support.getStone(1, 2) == support.getStone(2, 2) && support.getStone(2, 2) == color)
            return true;
        return false;
    }

    public boolean areOnDiagonalMorpion(int color){
        if(support.getStone(1, 1) == color){
            if(color == support.getStone(0, 0) && color == support.getStone(2, 2))
                return true;
            else if(color == support.getStone(2, 0) && color == support.getStone(0, 2))
                return true;
        }
        return false;
    }

    /** vérifie que la case aux coordonnées x et y du plateau est vide
    * @param support : Support
    * @param x : int
    * @param y : int
    * @return boolean */
    public boolean free(int x, int y){
    	return support.getStone(x, y) == 0;
    }

    /** vérifie la possibilite d'ajouter une pierre au plateau
    * selon s'il y a des pierres à proximité
    * et le nombre de pierres restant au joueur
    * @param support : Support
    * @param black : boolean
    * @param x : int
    * @param y : int
    * @return boolean */
    public boolean addStone(int x, int y){
        //case déjà occupée
        if(!this.free(x, y))
            return false;
        //au premier tour
        if(support.getNb() == 1){
            support.setStone(x, y, false);
            return true;
        }
        if(numberAligned == 5){
            //coordonnées de départ et d'arrivée pour la boucle imbriquée
            int depX = x - 1;
            int arrX = x + 1;
            int depY = y - 1; 
            int arrY = y +1;
            
            //bord gauche
            if(x==0)
                depX = x;
            //bord droit
            if(x==support.getWidth())
                arrX = x;
            //bord haut
            if(y==0)
                depY = y;
            //bord bas
            if(y==support.getHeight())
                arrY = y;
            //traitement
        	for(int i = depX; i <= arrX; i ++){
            	for(int j = depY; j <= arrY; j ++){
        			if(!this.free(i, j)){
                        //tour du joueur noir
                        if(support.getNb()% 2 == 0 && decrement(true))
                            support.setStone(x, y, true);
                        else if(support.getNb()% 2 != 0 && decrement(false))
                        //tour du joueur blanc
                            support.setStone(x, y, false);
        	            return true;
        			}
        		}
        	}
        	return false;
        }
        else {
            //tour du joueur noir
            if(support.getNb()% 2 == 0 && decrement(true)){
                support.setStone(x, y, true);
                return true;
            }
            else if(support.getNb()% 2 != 0 && decrement(false)){
            //tour du joueur blanc
                support.setStone(x, y, false);
                return true;
            }
            return false;
        }
    }

    /**
     * récupère le plateau du jeu
     * @return Support
     */
    public Support getSupport(){
        return this.support;
    }
    /**
     * récupère l'ancienne version du plateau de jeu
     * @return Support
     */
    public Support getLastVersion(){
        return this.lastVersion;
    }

    public int getNumberAligned(){
        return this.numberAligned;
    }

    public void setLastVersion(Support support){
        this.lastVersion = support;
    }

    public void setSupport(Support support){
        this.support = support;
    }
}