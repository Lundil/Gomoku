package Gomoku.regles;
import Gomoku.jeu.*;
import Gomoku.gui.*;

import java.util.Observer;
import java.util.Observable;

public class ModelMorpion extends Model {
    
    /** construit un modèle sur lequel se base les règles du jeu
    * il prend en compte les paramètres du plateau (cf Support)
    * et le nombre de pierres à aligner pour gagner
    * @param width : int
    * @param height : int
    * @param maxStones : int
    * @param numberAligned : int */
    public ModelMorpion(int width, int height, int maxStones, int numberAligned){
        super(width, height, maxStones, numberAligned);
    }

    /** retourne un nombre pour savoir si le joueur noir a gagné (1),
    * si le joueur blanc a gagné (2), si la partie est nulle (0), sinon -1
    * @return int */
    public int endGame(){
        //victoire des noirs
        if(alignedMorpion(true))
            return 1;
        //victoire des blancs
        else if(alignedMorpion(false))
            return 2;
        //partie nulle
        if((!alignedMorpion(false) && super.support.getStonesWhite() == 0) || (!alignedMorpion(true) && super.support.getStonesBlack() == 0))
            return 0;
        //continuité du jeu
        return -1;
    }


    /** parcourt le plateau pour vérifier l'alignement pour chaque joueur dans chaque direction version Morpion
    * @param black : boolean
    * @return boolean */
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

    /** vérifie un alignement de 5 pierres en ligne sur le plateau en fonction 
    * d' une direction (gauche, droite, haut, bas) et de la position de la pierre version Morpion
    * @param color : int
    * @return boolean */
    public boolean areOnLineMorpion(int color){
        if(super.support.getStone(0, 0) == super.support.getStone(0, 1) && super.support.getStone(0, 1) == super.support.getStone(0, 2) && super.support.getStone(0, 2) == color)
            return true;
        if(super.support.getStone(1, 0) == super.support.getStone(1, 1) && super.support.getStone(1, 1) == super.support.getStone(1, 2) && super.support.getStone(1, 2) == color)
            return true;
        if(super.support.getStone(2, 0) == super.support.getStone(2, 1) && super.support.getStone(2, 1) == super.support.getStone(2, 2) && super.support.getStone(2, 2) == color)
            return true;
        if(super.support.getStone(0, 0) == super.support.getStone(1, 0) && super.support.getStone(1, 0) == super.support.getStone(2, 0) && super.support.getStone(2, 0) == color)
            return true;
        if(super.support.getStone(0, 1) == super.support.getStone(1, 1) && super.support.getStone(1, 1) == super.support.getStone(2, 1) && super.support.getStone(2, 1) == color)
            return true;
        if(super.support.getStone(0, 2) == super.support.getStone(1, 2) && super.support.getStone(1, 2) == super.support.getStone(2, 2) && super.support.getStone(2, 2) == color)
            return true;
        return false;
    }

    /** vérifie un alignement de 5 pierres en diagonale sur le plateau en fonction 
    * d' une direction (gauche, droite, haut, bas) et de la position de la pierre version Morpion
    * @param color : int
    * @return boolean */
    public boolean areOnDiagonalMorpion(int color){
        if(super.support.getStone(1, 1) == color){
            if(color == super.support.getStone(0, 0) && color == super.support.getStone(2, 2))
                return true;
            else if(color == super.support.getStone(2, 0) && color == super.support.getStone(0, 2))
                return true;
        }
        return false;
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
        //place la pierre sur le plateau et supprime une pierre du stock
        //tour du joueur noir
        if(super.support.getNb()% 2 == 0 && super.decrement(true)){
            super.support.setStone(x, y, true);
            return true;
        }
        else if(super.support.getNb()% 2 != 0 && super.decrement(false)){
        //tour du joueur blanc
            super.support.setStone(x, y, false);
            return true;
        }
        return false;
    }
}