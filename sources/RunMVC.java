import javax.swing.SwingUtilities;

public class RunMVC {

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Model model = new Model();
                GomokuView view = new GomokuView(model); 
                GomokuController controller = new GomokuController(model, view);
                view.addController(controller);
		    }
		});
    }
}