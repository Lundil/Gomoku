import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
import java.io.*;


class MenuView  extends JFrame implements Observer {
    Model model;
    private JButton gomoku, morpion;

    public MenuView(Model model) {
		this.model = model;
		this.model.addObserver(this);
        this.gomoku = new JButton("Gomoku");
        this.morpion = new JButton("Morpion");

        setLayout(null);
        setContentPane(new JLabel(new ImageIcon("../img/fond01.jpg")));

        gomoku.setBounds(200, 100, 100, 30);
        morpion.setBounds(200, 160, 100, 30);
        setBounds(500, 300, 533, 300);

        add(gomoku);
        add(morpion);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
		setTitle("Menu principal");
		setVisible(true);
    }

    public void update(Observable o, Object arg) {
		this.repaint();
    }


    public void addController(MenuController controller){
        morpion.addActionListener(controller);
        gomoku.addActionListener(controller);
    }
}