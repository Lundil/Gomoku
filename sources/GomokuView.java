import javax.swing.*;
import java.awt.Color;
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

    public GomokuView(Model model) {
    	this.support = new Support(19,19);
		this.model = model;
		model.addObserver(this);

		//Basics
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(700, 400, 600, 600);
		setTitle("Gomoku Game -- Tour 1");
		setVisible(true);
    }

    public void update(Observable o, Object arg) {
		this.repaint();
    }


    public void addController(GomokuController controller){
        //button.addActionListener(controller);
    }

    public void paint(Graphics g) {
        g.setColor(new Color(255, 163, 102));
        g.fillRect(0, 0, 600, 600);
    	counter++;
		this.setTitle("Gomoku Game -- Tour " + counter);
        Stone stone;
		for(int i = 0; i < support.getWidth(); i++){
            for(int j = 0; j < support.getHeight(); j++){
                stone = support.getStone(i, j);
                if(stone == null)
            	   g.fillRect(i*60+10,j*60+10,10,10);
                else{
                    if(stone.getBlack())
                        g.setColor(Color.BLACK);
                    else
                        g.setColor(Color.WHITE);
                   g.fillOval(i*60+10,j*60+10,10,10);
               }
            }
        }
    }
}
	