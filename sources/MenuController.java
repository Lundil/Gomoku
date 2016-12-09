import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

class MenuController implements ActionListener { 
    Model model;
    MenuView view;

    MenuController(Model model, MenuView view) {
    	this.model = model;
    	this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        String st = ((JButton) e.getSource()).getText();
        if(st.equals("Gomoku")){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GomokuView view = new GomokuView(model);
                    InfoView infoView = new InfoView(model);
                    GomokuController controller = new GomokuController(model, view, infoView);
                }
            });
        }
    }
}