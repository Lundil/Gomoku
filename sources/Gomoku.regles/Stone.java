package Gomoku.regles;

public class Stone {
	private boolean black ;
	private int x,y;

	public Stone(boolean black, int x, int y){
		this.black = black;
		this.x = x;
		this.y = y;
	}
	public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getBlack(){
        if(black)
        	return 1;
        else
        	return 2;
    }
}