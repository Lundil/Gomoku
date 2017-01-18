package Gomoku.regles;
import Gomoku.jeu.*;
import Gomoku.gui.*;

import java.util.Observer;
import java.util.Observable;

public class Model {
    protected Support support;
    protected Support lastVersion;
    protected int numberAligned;
    
    /** construit un modèle sur lequel se base les règles du jeu
    * il prend en compte les paramètres du plateau (cf Support)
    * et le nombre de pierres à aligner pour gagner
    * @param width : int
    * @param height : int
    * @param maxStones : int
    * @param numberAligned : int */
    public Model(int width, int height, int maxStones, int numberAligned){
        this.support = new Support(width, height, maxStones);
        this.lastVersion = this.support;
        this.numberAligned = numberAligned;
    }

    public int endGame(){
        return -2;
    }

    public boolean addStone(int x, int y){
        return true;
    }

    public boolean addStone(int x){
        return true;
    }

    /** soustrait une pierre à la somme restante, retourne faux si la somme est à 0
    * @param black : boolean
    * @return boolean */
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

    /** vérifie que la case aux coordonnées x et y du plateau est vide
    * @param x : int
    * @param y : int
    * @return boolean */
    public boolean free(int x, int y){
    	return support.getStone(x, y) == 0;
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

    /**
     * récupère le nombre de pierres à aligner pour gagner
     * @return int
     */
    public int getNumberAligned(){
        return this.numberAligned;
    }

    /**
     * modifie la dernière version du plateau de jeu
     * @param support : Support
     */
    public void setLastVersion(Support support){
        this.lastVersion = support;
    }

    /**
     * modifie la version courante du plateau de jeu
     * @param support : Support
     */
    public void setSupport(Support support){
        this.support = support;
    }

    
}