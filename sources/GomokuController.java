import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;

class Controleur implements ActionListener { 
    Model model;
    GomokuView view;
    
    Controleur(Model model, GomokuView view) {
    	this.model = model;
    	this.view = view;
    }
    
    public void actionPerformed(ActionEvent e) {
	   
    }
}