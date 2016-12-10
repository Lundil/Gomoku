public class RunMVC {

    public static void main(String[] args){
        MenuView menuView = new MenuView();
        MenuController controller = new MenuController(menuView);
        menuView.addController(controller);
    }
}