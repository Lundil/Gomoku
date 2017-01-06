package Gomoku.regles;
import Gomoku.jeu.*;
import Gomoku.gui.*;

import java.util.Random;
import java.util.Observer;
import java.util.Observable;

public class IAGomoku{

	public int x, y;

	/* construit une IA simple avec des coordonnées x et y */
	public IAGomoku(){
		this.x = 0;
		this.y =0;
	}

	/* modifie les coordonnées du coup porté par l'IA en fonction du dernier coup porté par le joueur
	* @param lastPlayerX int
	* @param lastPlayerY int */
	public void playRandom(int lastPlayerX, int lastPlayerY){
		Random r = new Random();
		if(r.nextBoolean())
			this.x = lastPlayerX - 1;
		else
			this.x = lastPlayerX + 1;
		if(r.nextBoolean())
			this.y = lastPlayerY - 1;
		else
			this.y = lastPlayerY + 1;
		//vérifie qu'elles correspondent au plateau
		if(this.x < 0 || this.x > 18)
			this.x = r.nextInt(19);
		if(this.y < 0 || this.y > 18)
			this.y = r.nextInt(19);
		System.out.println("coup porté par l'IA : x = " + x + " y = " + y);
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