# Gomoku
Projet long Génie Logiciel

Réalisé par Xavier Lamarque et Aurélia Catrice

Pour lancer le jeu, cliquez sur Gomoku.jar


----------------------------Compilation des sources----------------------------

- Se placer dans le dossier sources
- Exécuter la commande suivante : javac -d ../classes *.java

----------------------------Exécution en ligne de commande----------------------------

- Se placer dans le dossier classes
- Exécuter la commande suivant : java RunMVC

----------------------------Description des classes----------------------------

Gomoku suit le design pattern Modèle-Vue-Controler.

Deux versions du jeu sont déployées : Morion et Gomoku dont l'affichage est conservé respectivement dans les classes MorpionView et GomokuView.
Deux autres affichages sont également pris en compte à savoir le menu principale (MenuView) et la fenêtre d'information du jeu en cours (InfoView).

En ce qui concerne la gestion de la souris et la saisie utilisateur, il existe le controler du menu (MenuController) et celui du jeu choisi (GameController), ce dernier est commun à tous les jeux.

Enfin, la gestion du jeu en lui-même est retranscrite dans la classe Model. Quelque soit sa version, elle est gérée selon les paramètres du constructeur.