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


class InfoView  extends JFrame implements Observer {
    Model model;
    ImageIcon background;

    public InfoView(Model model) {
		this.model = model;
		model.addObserver(this);
		//Basics
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 200, 200, 200);
		setTitle("Informations sur Gomoku");
		setVisible(true);
        background = new ImageIcon("../img/fond.jpg");
    }

    public void update(Observable o, Object arg) {
		this.repaint();
    }


    public void addController(GomokuController controller){
    }

    public void paint(Graphics g) {
         g.drawImage (background, 0, 0, null);
    }
}
	