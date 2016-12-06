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
        setResizable(false);
        setBounds(700, 400, 800, 800);
		setTitle("Gomoku Game -- Tour 1");
		setVisible(true);
    }

    public void update(Observable o, Object arg) {
		this.repaint();
    }


    public void addController(GomokuController controller){
    }

    public void paint(Graphics g) {
        g.setColor(new Color(255, 163, 102));
        g.fillRect(0, 0, getWidth(), getHeight());
    	counter++;
		this.setTitle("Gomoku Game -- Tour " + counter);
        Stone stone;
        g.setColor(Color.BLACK);
        g.drawRect(20, 20, getWidth()-40, getHeight()-40);
		for(int i = 0; i < support.getWidth(); i++){
            for(int j = 0; j < support.getHeight(); j++){
                //sauf les bords moches
                //if(i!=0 || j!=0){
                    g.drawLine(40*i+20, 60, i*40+20, getHeight()-20);
                    g.drawLine(20, 40*i+20, getWidth()-20, i*40+20);
                    stone = support.getStone(i, j);

                    if(stone != null){
                        if(stone.getBlack())
                            g.setColor(Color.BLACK);
                        else
                            g.setColor(Color.WHITE);
                       g.fillOval(i*50+10,j*50+10,10,10);
                   }
               //}
            }
        }
    }
}
	