import java.util.Observer;
import java.util.Observable;

class Model extends Observable {
    private boolean exist;

    public Model(){
    	this.existe=true;
    }

    void setExist(boolean exist) {
		this.exist = exist;
		setChanged();
		notifyObservers();
    } 

    boolean getExist() {
		return exist;
    }

    public boolean aligned(Table table, boolean black){
    }

    /** vérifie un alignement de 5 pierres sur le plateau en fonction
    * d'une direction (gauche, droite, haut, bas) et de la position de la pierre
    * @param support : Support
    * @param stone : Stone
    * @param direction : Direction
    * @return boolean */
	public boolean are5(Support support, Stone stone, Direction direction){
		Stone s;
		for(int cpt = 0; cpt<5; cpt ++){
			if(direction == Direction.LEFT && stone.getX() - 5 > -1)
				s = support.getStone(stone.getX() - cpt, stone.getY());
			else if (direction == Direction.RIGHT && stone.getX() + 5 < 19)
				s = support.getStone(stone.getX() + cpt, stone.getY());
			else if (direction == Direction.UP && stone.getY() - 5 > -1)
				s = support.getStone(stone.getX(), stone.getY() - cpt);
			else if (direction == Direction.DOWN && stone.getY() + 5 < 19)
				s = support.getStone(stone.getX(), stone.getY() + cpt);
			else
				return false;
			if(s.getBlack() != stone.getBlack())
				return false;
		}
		return true;
	}

    /** vérifie que la case aux coordonnées x et y du plateau est vide
    * @param support : Support
    * @param x : int
    * @param y : int
    * @return boolean */
    public boolean free(Support support, int x, int y){
    	return support.getStone(x, y) == null;
    }

    /** ajoute une pierre au plateau
    * @param support : Support
    * @param black : boolean
    * @param x : int
    * @param y : int
    * @return boolean */
    public boolean addStone(Support support, boolean black, int x, int y){
    	if(!this.free(support, x, y))
    		return false;
    	for(int i = x - 1; i < x + 2; i ++){
    		for(int j = y - 1; y< y + 2; j ++){
    			if(!this.free(support, i, j)){
    				support.setStone(x, y, black);
    				return true;
    			}
    		}
    	}
    	return false;
    }
}