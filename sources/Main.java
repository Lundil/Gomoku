public class Main {
    public static void main(String[] args){
         
        Model mod = new Model();
        GomokuView gmkV = new GomokuView(mod);
        gmkV.setVisible(true);
        //gmkV.update()
    }
}