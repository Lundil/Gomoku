import java.util.Observer;
import java.util.Observable;

class Model extends Observable {

    int numberAligned;
    
    public Model(int numberAligned){
        this.numberAligned = numberAligned;
    }

    /** parcourt le plateau pour vérifier l'alignement pour chaque joueur
    * @param support : Support
    * @param stone : Stone
    * @param direction : Direction
    * @return boolean */
    public boolean decrement(Support support, boolean black){
        if(black){
            if(support.getStonesBlack() > 0){
                support.setStonesBlack(support.getStonesBlack()-1);
                return true;
            }
        }
        else{
            if(support.getStonesWhite() > 0){
                support.setStonesWhite(support.getStonesWhite()-1);
                return true;
            }
        }
        return false;
    }

    public boolean aligned(Support support, boolean black, int numberAligned){
        Stone stone;
        for(int i = 0; i < support.getWidth(); i++){
            for(int j = 0; j < support.getHeight(); j++){
                stone = support.getStone(i, j);
                if(this.areOnLine(support, stone, Direction.LEFT, numberAligned))
                    return true;
                else if(this.areOnLine(support, stone, Direction.RIGHT, numberAligned))
                    return true;
                else if(this.areOnLine(support, stone, Direction.UP, numberAligned))
                    return true;
                else if(this.areOnLine(support, stone, Direction.DOWN, numberAligned))
                    return true;
                else if(this.areOnDiagonal(support, stone, Direction.RIGHTDOWN, numberAligned))
                    return true;
                else if(this.areOnDiagonal(support, stone, Direction.RIGHTDOWN, numberAligned))
                    return true;
                else if(this.areOnDiagonal(support, stone, Direction.LEFTDOWN, numberAligned))
                    return true;
                else if(this.areOnDiagonal(support, stone, Direction.RIGHTUP, numberAligned))
                    return true;
                else if(this.areOnDiagonal(support, stone, Direction.LEFTUP, numberAligned))
                    return true;
            }
        }
        return false;
    }

    /** vérifie un alignement de 5 pierres en ligne sur le plateau en fonction
    * d' une direction (gauche, droite, haut, bas) et de la position de la pierre
    * @param support : Support
    * @param stone : Stone
    * @param direction : Direction
    * @return boolean */
	public boolean areOnLine(Support support, Stone stone, Direction direction, int numberAligned){
		Stone s;
		for(int cpt = 0; cpt < numberAligned; cpt ++){
			if(direction == Direction.LEFT && stone.getX() - numberAligned > -1)
				s = support.getStone(stone.getX() - cpt, stone.getY());
			else if (direction == Direction.RIGHT && stone.getX() + numberAligned < 19)
				s = support.getStone(stone.getX() + cpt, stone.getY());
			else if (direction == Direction.UP && stone.getY() - numberAligned > -1)
				s = support.getStone(stone.getX(), stone.getY() - cpt);
			else if (direction == Direction.DOWN && stone.getY() + numberAligned < 19)
				s = support.getStone(stone.getX(), stone.getY() + cpt);
			else
				return false;
			if(s.getBlack() != stone.getBlack())
				return false;
		}
		return true;
	}

    /** vérifie un alignement de 5 pierres en diagonale sur le plateau en fonction
    * d' une direction et de la position de la pierre
    * @param support : Support
    * @param stone : Stone
    * @param direction : Direction
    * @return boolean */
    public boolean areOnDiagonal(Support support, Stone stone, Direction direction, int numberAligned){
        Stone s;
        for(int cpt = 0; cpt < numberAligned; cpt ++){
            if(direction == Direction.LEFTUP && stone.getX() - numberAligned > -1)
                s = support.getStone(stone.getX() - cpt, stone.getY());
            else if (direction == Direction.RIGHT && stone.getX() + numberAligned < 19)
                s = support.getStone(stone.getX() + cpt, stone.getY());
            else if (direction == Direction.UP && stone.getY() - numberAligned > -1)
                s = support.getStone(stone.getX(), stone.getY() - cpt);
            else if (direction == Direction.DOWN && stone.getY() + numberAligned < 19)
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
    * selon s'il y a des pierres à proximité
    * et le nombre de pierres restant au joueur
    * @param support : Support
    * @param black : boolean
    * @param x : int
    * @param y : int
    * @return boolean */
    public boolean addStone(Support support, int x, int y){
        //case déjà occupée
        if(!this.free(support, x, y))
            return false;
        //au premier tour
        if(support.getNb() == 1){
            support.setStone(x, y, false);
            return true;
        }
        //coordonnées de départ et d'arrivée pour la boucle imbriquée
        int depX = x - 1;
        int arrX = x + 1;
        int depY = y - 1; 
        int arrY = y +1;
        
        //bord gauche
        if(x==0)
            depX = x;
        //bord droit
        if(x==support.getWidth())
            arrX = x;
        //bord haut
        if(y==0)
            depY = y;
        //bord bas
        if(y==support.getHeight())
            arrY = y;
        //traitement
    	for(int i = depX - 1; i <= arrX; i ++){
        	for(int j = depY; j <= arrY; j ++){
    			if(!this.free(support, i, j)){
                    //tour du joueur noir
                    if(support.getNb()% 2 == 0 && decrement(support, true))
                        support.setStone(x, y, true);
                    else if(support.getNb()% 2 != 0 && decrement(support, false))
                    //tour du joueur blanc
                        support.setStone(x, y, false);
    	            return true;
    			}
    		}
    	}
    	return false;
    }
}