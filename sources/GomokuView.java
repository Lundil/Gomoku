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
    JButton reset,south,east,west;
    JPanel content,content1,content2,content3;
    int counter=0;
    Support support;
    Image IIntersection = null;

    public GomokuView(Model model) {
    	this.support=new Support(19,19);
    	//buttons
		this.model = model;
		this.reset = new JButton("reset");
		this.south = new JButton("south");
		this.east = new JButton("east");
		this.west = new JButton("west");
		//jpanels
		this.content = new JPanel();
		this.content1 = new JPanel();
		this.content2 = new JPanel();
		this.content3 = new JPanel();
		//model
		model.addObserver(this);
		//add buttons
		add(reset, BorderLayout.NORTH);
		add(south, BorderLayout.SOUTH);
		add(west, BorderLayout.WEST);
		add(east, BorderLayout.EAST);
		add(content, BorderLayout.CENTER);
		//
		
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
		repaint();
		setVisible(true);
    }

    public void update(Observable o, Object arg) {
		//plateau.repaint();
    }

    public void repaint(Graphics g) {
    	counter++;
		this.setTitle("Gomoku Game -- Tour " + counter);
		for(int i = 0; i < this.support.getWidth(); i++){
            for(int j = 0; j < this.support.getHeight(); j++){
                //dessine
                g.drawImage(IIntersection,j*30,i*30+30,this);
            }
        }
    }
}
	