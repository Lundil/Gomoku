import java.util.Observer;
import java.util.Observable;

class Model extends Observable {
    private boolean existe;

    void setExiste(boolean existe) {
		this.existe = existe;
		setChanged();
		notifyObservers();
    } 

    boolean getExiste() {
	return existe;
    }   
}