import java.util.Observer;
import java.util.Observable;

class Model extends Observable {

    /** parcourt le plateau pour vérifier l'alignement pour chaque joueur
    * @param support : Support
    * @param stone : Stone
    * @param direction : Direction
    * @return boolean */
    public boolean aligned(Support support, boolean black){
        Stone stone;
        for(int i = 0; i < support.getWidth(); i++){
            for(int j = 0; j < support.getHeight(); j++){
                stone = support.getStone(i, j);
                if(this.are5(support, stone, Direction.LEFT))
                    return true;
                else if(this.are5(support, stone, Direction.RIGHT))
                    return true;
                else if(this.are5(support, stone, Direction.UP))
                    return true;
                else if(this.are5(support, stone, Direction.DOWN))
                    return true;
            }
        }
        return false;
    }

    /** vérifie un alignement de 5 pierres sur le plateau en fonction
    * d' une direction (gauche, droite, haut, bas) et de la position de la pierre
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

    /** vérifie la possibilite d'ajouter une pierre au plateau
    * @param support : Support
    * @param black : boolean
    * @param x : int
    * @param y : int
    * @return boolean */
    public boolean addStone(Support support, boolean black, int x, int y){
    	if(!this.free(support, x, y))
    		return false;
    	for(int i = x - 1; i < x + 2; i ++){
    		for(int j = y - 1; j < y + 2; j ++){
    			if(!this.free(support, i, j)){
    				support.setStone(x, y, black);
    				return true;
    			}
    		}
    	}
    	return false;
    }
}