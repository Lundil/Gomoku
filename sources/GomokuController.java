import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;

class GomokuController implements MouseListener { 
    Model model;
    GomokuView view;
    InfoView infoView;

    GomokuController(Model model, GomokuView view, InfoView infoView) {
    	this.model = model;
    	this.view = view;
    	this.infoView = infoView;
    	view.addMouseListener(this);
    }
    
    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {
    	int x = e.getX()/40;
    	int y = e.getY()/40;
    	System.out.println("x = " + x + " y = " + y);

    	if(model.addStone(view.support, x, y))
            view.support.incr();
        System.out.println(view.support.toString());
        this.view.update(model,null);
    }
}