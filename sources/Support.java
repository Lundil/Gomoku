
public class Support {
	private int width, height, nb;
	private Stone[][] stones;

	public Support(int width,int height){
		this.stones = new Stone[width][height];
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++)
                this.stones[i][j] = null;
        }
        this.width = width;
        this.height = height;
        this.nb=1;
	}
    public void incr(){
        this.nb++;
    }
    /**
     * récupère le nombre de tour
     * @return nb
     */
    public int getNb(){
        return this.nb;
    }
	/**
     * récupère le tableau de Stones du plateau
     * @return stones
     */
    public Stone[][] getStones(){
        return this.stones;
    }
    /**
     * récupère la Stone de la celulle
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
    public void setStone(int x, int y, boolean black){
    	this.stones[x][y]= new Stone(black,x,y);
    }
    /**
     * 
     * @return 
     */
    public String toString(){
        String chaine="";
        for(int i = 0; i<this.width; i++){
            for(int j = 0; j<this.height; j++){
                if(getStone(i, j)==null){
                    chaine+="0 ";
                }
                else{
                    if(getStone(i, j).getBlack()){
                        chaine+="2 ";
                    }
                    else{
                        chaine+="1 ";
                    }
                }
            }
            chaine+="\n";
        }
        return chaine;
    }
}