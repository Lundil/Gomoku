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
    	int x = (e.getX()-10)/40;
    	int y = (e.getY()-50)/40;

    	if(model.addStone(model.getSupport(), x, y))
            model.getSupport().incr();
        System.out.println(model.getSupport().toString());
        this.view.update(model, null);
    }
}