import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;


class GomokuView  extends JFrame implements Observer {
    Model model;
    int counter = 1;
    Support support;
    Image IIntersection = null;

    public GomokuView(Model model) {
    	this.support = new Support(19,19);
		this.model = model;
		model.addObserver(this);
		try {
			IIntersection = ImageIO.read(getClass().getResource("pictures/wall.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

		//Basics
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(700,400);
		setSize(720,480);
		setTitle("Gomoku Game -- Tour 1");
		setVisible(true);
    }

    public void update(Observable o, Object arg) {
		this.repaint();
    }

    public void paint(Graphics g) {
    	counter++;
		this.setTitle("Gomoku Game -- Tour " + counter);
		for(int i = 0; i < this.support.getWidth(); i++){
            for(int j = 0; j < this.support.getHeight(); j++){
                //dessine
                //g.drawImage(IIntersection,j*30,i*30+30,this);
            	g.drawLine(i,j,i,j);
            }
        }
    }
}
	