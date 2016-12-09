public class RunMVC {

    public static void main(String[] args){
    	Model model = new Model();
        MenuView menuView = new MenuView(model);
        MenuController controller = new MenuController(model, menuView);
        menuView.addController(controller);
    }
}