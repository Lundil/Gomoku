# Gomoku
Projet long Génie Logiciel

Réalisé par Xavier Lamarque et Aurélia Catrice

- Décompressez l'archive

- Pour lancer le jeu, cliquez sur Gomoku.jar

----------------------------Vérifications préalables----------------------------

- Vérifier la présence du dossier img avec la ressource : fond01.jpg

----------------------------Compilation des sources----------------------------

- Si le dossier classes n'existe pas, le créer
- Se placer dans le dossier sources
- Exécuter la commande suivante : javac -d ../classes Gomoku.jeu/* Gomoku.regles/* Gomoku.gui/*

----------------------------Exécution en ligne de commande----------------------------

- Se placer dans le dossier classes
- Exécuter la commande suivant : java Gomoku.jeu.RunMVC
- ou : java Gomoku/jeu/RunMVC

----------------------------Génération de la javadoc----------------------------

- Si le dossier doc n'existe pas, le créer
- Se placer dans le dossier sources
- Exécuter la commande suivante : javadoc -d ../doc Gomoku.jeu/* Gomoku.regles/* Gomoku.gui/*


----------------------------Génération du .jar----------------------------

----------------------------Description des classes----------------------------

Gomoku suit le design pattern Modèle-Vue-Controler.

Trois versions du jeu sont déployées : Morion, Puissance 4 et Gomoku dont l'affichage (package gui) est conservé respectivement dans les classes MorpionView, PuissanceView et GomokuView. Chacunes de ces classes sont héritées de la classe View.
Deux autres affichages sont également pris en compte à savoir le menu principal (MenuView) et la fenêtre d'information du jeu en cours (InfoView).

En ce qui concerne la gestion de la souris et la saisie utilisateur (package jeu), il existe le controler du menu (MenuController) et celui du jeu choisi (GameController), ce dernier est commun à tous les jeux.

Enfin, la gestion du jeu en lui-même (package regles) est retranscrite dans la classe Model. Quelque soit sa version, elle est gérée selon les paramètres du constructeur.