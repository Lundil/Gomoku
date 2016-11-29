import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;

class GomokuView  extends JFrame implements Observer {
    Model model;
    JButton reset;
    JPanel content;

    public GomokuView(Model model) {
		this.model = model;
		this.reset = new JButton("reset");
		this.content = new JPanel();
		
		model.addObserver(this);
		add(reset, BorderLayout.NORTH);
		add(content, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200,200);
		pack();
		setVisible(true);
    }

    public void update(Observable o, Object arg) {
		//plateau.repaint();
    }
}
	