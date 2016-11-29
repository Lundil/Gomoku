class Controleur implements ActionListener { 
    Model model;
    GomokuView view;
    
    Controleur(Model model, View view) {
    	this.model = model;
    	this.view = view;
    }
    
    public void actionPerformed(ActionEvent e) {
	   if (e.getSource() == vue.trace) modele.setExiste(true);
	   else if (e.getSource() == vue.efface ) modele.setExiste(false);
    }
}