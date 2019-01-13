# Projet Shisen

Testé sous java version : 10

## Instruction



1. Etudiant impliqué : M. THOMAS Antoine (GR 1).
2. Comment configurer le projet : 
	-Bibliothèque : Swing.
    -Il suffit de cliquer sur "play" pour lancer le jeu. Le main est contenu dans la classe ShisenGame. Par défaut le jeu est en mode Swing. Pour passer le jeu en mode console allez dans la classe ShisenGame, décommenter playWithASCII() (ligne 11)et commenter playWithSwing (ligne 12).
3. Affichage en Swing ou en ASCII. 


Points faibles : 
* L'utilisateur n'a pas le choix de sélectionner le mode d'affichage au démarrage. C'est "hard codé".
* Pas d'animations pour les tuiles détruites/déplacées. 

Points forts : 
* Gère les erreurs de coordonnées entrées par l'utilisateur.
* Ergonomie et fluidité excellente en SWING. 
* Code effectif et clair. 
* Tous les paramètres sont dynamiques (possibilité de tester le jeu avec une taille différente en modifiant lignes/colonnes).

