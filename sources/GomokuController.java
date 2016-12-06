import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;

class GomokuController implements MouseListener { 
    Model model;
    GomokuView view;
    
    GomokuController(Model model, GomokuView view) {
    	this.model = model;
    	this.view = view;
    	view.addMouseListener(this);
    }
    
    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {
    	int x = e.getX() / 40;
    	int y = e.getY() / 40;
    	System.out.println("x = " + x + " y = " + y);
    	model.addStone(view.support, true, x, y);
        System.out.println(view.support.toString());
    }
}