
public class Support {
	private int width, height, nb, stonesBlack, stonesWhite;
	private Stone[][] stones;

	public Support(int width, int height, int maxStones){
		this.stones = new Stone[width][height];
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++)
                this.stones[i][j] = null;
        }
        stonesWhite = maxStones;
        stonesBlack = maxStones;
        this.width = width;
        this.height = height;
        this.nb = 1;
	}
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
    public Stone[][] getStones(){
        return this.stones;
    }
    /**
     * récupère la pierre de la celulle
     * @return stone
     */
    public Stone getStone(int x, int y){
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
    * @param i : int */
    public void setStonesBlack(int i){
        this.stonesBlack = i;
    }
    /* modifie le nombre de pierres blanches restantes
    * @param i : int */
    public void setStonesWhite(int i){
        this.stonesWhite = i;
    }
    /* modifie le plateau
    * @param x : int
    * @param y : int
    * @param boolean : black */
    public void setStone(int x, int y, boolean black){
    	this.stones[x][y]= new Stone(black,x,y);
    }
    /**
    * version texte du plateau
    * @return String
    */
    public String toString(){
        String chaine = "";
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                if(getStone(i, j) == null)
                    chaine += "0 ";
                else{
                    if(getStone(i, j).getBlack())
                        chaine += "2 ";
                    else
                        chaine += "1 ";
                }
            }
            chaine += "\n";
        }
        return chaine;
    }
}