package Gomoku.regles;
import Gomoku.jeu.*;
import Gomoku.gui.*;

import java.util.Random;
import java.util.Observer;
import java.util.Observable;

public class IAGomoku{

	public int x, y;

	/* construit une IA simple avec des coordonnées x et y initialisées à 0*/
	public IAGomoku(){
		this.x = 0;
		this.y = 0;
	}

	/* modifie les coordonnées du coup porté par l'IA en fonction du dernier coup porté par le joueur
	* @param lastPlayerX : int
	* @param lastPlayerY ! int */
	public void playRandom(int lastPlayerX, int lastPlayerY){
		Random r = new Random();
		this.x = r.nextInt(18);
		this.y = r.nextInt(18);
	}

    /** récupère la coordonnée x du coup aléatoire de l'IA
    * @return int */
	public int getX(){
		return this.x;
	}

    /** récupère la coordonnée y du coup aléatoire de l'IA
    * @return int */
   	public int getY(){
		return this.y;
	}
}