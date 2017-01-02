package Gomoku.regles;

public class Stone {
	private boolean black ;
	private int x,y;

    /** construit une pierre avec une couleur et des coordonnées
    * @param black : boolean
    * @param x : int
    * @param y : int */
	public Stone(boolean black, int x, int y){
		this.black = black;
		this.x = x;
		this.y = y;
	}

    /** récupère la coordonnée x d'une pierre
    * @return int */
	public int getX(){
        return this.x;
    }

    /** récupère la coordonnée y d'une pierre
    * @return int */
    public int getY(){
        return this.y;
    }

    /**récupère la couleur d'une pierre 1 si noire, 2 si blanche
    * @return int */
    public int getBlack(){
        if(black)
        	return 1;
        else
        	return 2;
    }
}