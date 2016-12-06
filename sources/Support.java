
public class Support {
	private int width, height;
	private Stone[][] stones;

	public Support(int width,int height){
		this.stones = new Stone[width][height];
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++)
                this.stones[i][j] = null;
        }
        this.width = width;
        this.height = height;
	}

	/**
     * récupère le tableau de Stones du plateau
     * @return stones
     */
    public Stone[][] getStones(){
        return this.stones;
    }
    /**
     * récupère le tableau de Stones du plateau
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
    
}