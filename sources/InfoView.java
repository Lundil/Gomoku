import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
import java.io.*;


class InfoView  extends JFrame implements Observer {
    Model model;
    private Image image = null;


    public InfoView(Model model) {
		this.model = model;
		model.addObserver(this);
		//Basics
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 200, 533, 300);
		setTitle("Informations sur Gomoku");
		setVisible(true);
    }

    public void update(Observable o, Object arg) {
		this.repaint();
    }


    public void addController(GomokuController controller){
    }

    public void paint(Graphics g) {
        image = getToolkit().getImage("../img/fond01.jpg");
        if(image != null)
            g.drawImage(image, 0, 0, this);

        if(model.endGame(model.getSupport()) == 1)
            g.drawString("Sangoku remporte cette bataille", 200, 200);
        else if(model.endGame(model.getSupport()) == 2)
            g.drawString("Naruto remporte cette bataille", 200, 200);
    }
}
	