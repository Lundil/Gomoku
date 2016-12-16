import java.util.Observer;
import java.util.Observable;

class Model extends Observable {
    Support support;
    int numberAligned;
    
    public Model(int width, int heigth,int maxStones, int numberAligned){
        this.support = new Support(width, heigth, maxStones);
        this.numberAligned = numberAligned;
    }

    /** retourne un nombre pour savoir si le joueur noir a gagné (1),
    * si le joueur blanc a gagné (2), sinon 0
    * @param support : Support
    * @return int */
    public int endGame(Support support){
        if(aligned(support, true) || support.getStonesWhite() == 0)
            return 1;
        else if(aligned(support, false) || support.getStonesBlack() == 0)
            return 2;
        else
            return 0;
    }


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

    /** parcourt le plateau pour vérifier l'alignement pour chaque joueur
    * @param support : Support
    * @param black : boolean
    * @return boolean */
    public boolean aligned(Support support, boolean black){
        Stone stone;
        for(int i = 0; i < support.getWidth(); i++){
            for(int j = 0; j < support.getHeight(); j++){
                stone = support.getStone(i, j);
                if(stone != null){
                    if(this.areOnLine(support, stone, Direction.LEFT))
                        return true;
                    else if(this.areOnLine(support, stone, Direction.RIGHT))
                        return true;
                    else if(this.areOnLine(support, stone, Direction.UP))
                        return true;
                    else if(this.areOnLine(support, stone, Direction.DOWN))
                        return true;
                    else if(this.areOnDiagonal(support, stone, Direction.RIGHTDOWN))
                        return true;
                    else if(this.areOnDiagonal(support, stone, Direction.RIGHTDOWN))
                        return true;
                    else if(this.areOnDiagonal(support, stone, Direction.LEFTDOWN))
                        return true;
                    else if(this.areOnDiagonal(support, stone, Direction.RIGHTUP))
                        return true;
                    else if(this.areOnDiagonal(support, stone, Direction.LEFTUP))
                        return true;
                }
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
	public boolean areOnLine(Support support, Stone stone, Direction direction){
		Stone s;
		for(int cpt = 0; cpt < this.numberAligned; cpt ++){
			if(direction == Direction.LEFT && stone.getX() - this.numberAligned > -1)
				s = support.getStone(stone.getX() - cpt, stone.getY());
			else if (direction == Direction.RIGHT && stone.getX() + this.numberAligned < 19)
				s = support.getStone(stone.getX() + cpt, stone.getY());
			else if (direction == Direction.UP && stone.getY() - this.numberAligned > -1)
				s = support.getStone(stone.getX(), stone.getY() - cpt);
			else if (direction == Direction.DOWN && stone.getY() + this.numberAligned < 19)
				s = support.getStone(stone.getX(), stone.getY() + cpt);
			else
				return false;
            if(s != null){
                if(s.getBlack() == stone.getBlack())
				    return true;
            }
		}
        return false;
	}

    /** vérifie un alignement de 5 pierres en diagonale sur le plateau en fonction
    * d' une direction et de la position de la pierre
    * @param support : Support
    * @param stone : Stone
    * @param direction : Direction
    * @return boolean */
    public boolean areOnDiagonal(Support support, Stone stone, Direction direction){
        Stone s;
        for(int cpt = 0; cpt < this.numberAligned; cpt ++){
            if(direction == Direction.LEFTUP && stone.getX() - this.numberAligned > -1)
                s = support.getStone(stone.getX() - cpt, stone.getY());
            else if (direction == Direction.RIGHT && stone.getX() + this.numberAligned < 19)
                s = support.getStone(stone.getX() + cpt, stone.getY());
            else if (direction == Direction.UP && stone.getY() - this.numberAligned > -1)
                s = support.getStone(stone.getX(), stone.getY() - cpt);
            else if (direction == Direction.DOWN && stone.getY() + this.numberAligned < 19)
                s = support.getStone(stone.getX(), stone.getY() + cpt);
            else
                return false;
            if(s != null){
                if(s.getBlack() == stone.getBlack())
                    return true;
            }
        }
        return false;
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

    /**
     * récupère le plateau du jeu
     * @return Support
     */
    public Support getSupport(){
        return this.support;
    }
}