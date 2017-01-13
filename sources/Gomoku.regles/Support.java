package Gomoku.regles;
import Gomoku.jeu.*;
import Gomoku.gui.*;

public class Support {
	private int width, height, nb, stonesBlack, stonesWhite;
	private int[][] stones;

    /** construit un plateua de jeu en fonction du nombre de lignes et de  colonnes
    * et du nombre total de pierres autorisées sur le plateau pour chaque joueur
    * @param width : int
    * @param height : int
    * @param maxStones : int */
	public Support(int width, int height, int maxStones){
		this.stones = new int[width][height];
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++)
                this.stones[i][j] = 0;
        }
        stonesWhite = maxStones;
        stonesBlack = maxStones;
        this.width = width;
        this.height = height;
        this.nb = 1;
	}

    /**
    * augmente le nombre de tours effectués
    */
    public void incr(){
        this.nb ++;
    }
    /**
    * récupère le nombre de tour
    * @return int
    */
    public int getNb(){
        return this.nb;
    }
    /**
    * récupère le nombre restant de pierres noires
    * @return int
    */
    public int getStonesBlack(){
        return this.stonesBlack;
    }
    /**
    * récupère le nombre restant de pierres blanches
    * @return nb
    */
    public int getStonesWhite(){
        return this.stonesWhite;
    }
	/**
    * récupère le tableau de pierres du plateau
    * @return stones
    */
    public int[][] getStones(){
        return this.stones;
    }
    /**
    * récupère la pierre de la celulle
    * @return stone
    */
    public int getStone(int x, int y){
        return this.stones[x][y];
    }
    /**
    * récupère la largeur du support
    * @return width
    */
    public int getWidth(){
        return this.width;
    }
    /**
    * récupère la hauteur du support
    * @return height
    */
    public int getHeight(){
        return this.height;
    }
    /* modifie le nombre de pierres noires restantes
    * @param i : int
    */
    public void setStonesBlack(int i){
        this.stonesBlack = i;
    }
    /* modifie le nombre de pierres blanches restantes
    * @param i : int */
    public void setStonesWhite(int i){
        this.stonesWhite = i;
    }
    /* place une pierre sur le plateau
    * @param x : int
    * @param y : int
    * @param boolean : black
    */
    public void setStone(int x, int y, boolean black){
        if(black)
    	   this.stones[x][y] = 1;
        else
            this.stones[x][y] = 2;
    }

    /* place une pierre sur le plateau version Puissance 4
    * @param x : int
    * @param boolean : black
    */
    public void setStone(int x, boolean black){
        int y = 0;
        for(int i = 0; i < height; i ++){
            if(this.stones[x][i] == 0){
                y = i;
                break;
            }
        }
        if(black)
           this.stones[x][y] = 1;
        else
            this.stones[x][y] = 2;
    }

    /**
    * version texte du plateau
    * @return String
    */
    public String toString(){
        String chaine = "";
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++)
                chaine += getStone(i, j) + " ";
            chaine += "\n";
        }
        return chaine;
    }
}