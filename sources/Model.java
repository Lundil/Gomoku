import java.util.Observer;
import java.util.Observable;

class Model extends Observable {
    private boolean existe;

    public Model(){
    	this.existe=true;
    }

    void setExiste(boolean existe) {
		this.existe = existe;
		setChanged();
		notifyObservers();
    } 

    boolean getExiste() {
	return existe;
    }
    //ajouter pion, alignement
}