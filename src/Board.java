import java.awt.Color;



public class Board {
	Tile tilesArray[][];

	String etape;
	//paramétrable
	public final static int NB_LINES = 2;
	public final static int NB_COLUMNS = 4;
	public final static int NB_NUMBERS = 9;
	//non paramétrable
	public final static int NB_MAXCOLORS = 4;
	public final static int NB_SAMETILES = 8;

	//ce constructeur peut rejeter une exception
	public Board() throws Exception{
		if( NB_LINES*NB_COLUMNS%NB_SAMETILES !=0) {
			throw new Exception("Impossible de créer le jeu : nombre de lignes/colonnes incorrect");
		}

		this.tilesArray = new Tile[NB_LINES][NB_COLUMNS];
		this.etape = "en cours";
	}

	private static int randomWithRange(int min, int max)
	{
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min;
	}	

	public Tile getTile(int x, int y) { //retourne une tuile aux coordonnées données
		return tilesArray[y][x];
	}

	public Tile[][] getTilesArray() { //retourne tableau de tuiles 2d;
		return tilesArray;
	}

	//méthode qui parcour tous les index du tableau et échange chaque index du tableau avec des index générés aléatoirement
	public void shuffleArray() {
		for (int i = 0; i < tilesArray.length; i++) {
			for (int j = 0; j < tilesArray[0].length; j++) {
				int rdni = randomWithRange(0, tilesArray.length -1); //génère un random entre 0 et le nombre de lignes -1 (on va de 0 à 11)
				int rdnj = randomWithRange(0, tilesArray[0].length -1); //idem pour les colonnes
				swapTilesMatrix(tilesArray, i, j, rdni, rdnj);
			}
		}
		setAllcoordinates();
	}

	// permet de réaffecter les coordonnées de chaque tuile
	public void setAllcoordinates() {
		for (int i = 0; i < tilesArray.length; i++) {
			for (int j = 0; j < tilesArray[0].length; j++) {
				if(tilesArray[i][j] != null) {
					tilesArray[i][j].setX(j); //attention les i = y, les j = x. On va modifier la valeur des tuiles.
					tilesArray[i][j].setY(i);
				}
			}
		}
	}

	// méthode qui échange les tuiles sur un tableau 2d. 
	private static void swapTilesMatrix(Tile[][] matrix, int i, int j, int i2, int j2) {
		Tile temp = matrix[i][j];
		matrix[i][j] = matrix[i2][j2];
		matrix[i2][j2] = temp;
	}

	//but de cette méthode : initialiser un tableau de toutes les tuiles possibles 
	public void init() {  

		int switchcolor = 0; //couleur 0 = bleu
		int switchvalue = 1; //les tuiles sont numérotées de 1 à 9
		int cpt = 1; //compteur qui permet de distribuer les tuiles de façon à ce qu'il y ait 8 tuiles par numéro/couleur


		for (int i = 0; i < this.tilesArray.length; i++) { //Rappel : TilesArray = tableau de 12/24 tuiles
			for (int j = 0; j < this.tilesArray[i].length; j++) { //On parourt TilesArray pour modifier la valeur de chaque tuile


				Tile a = new Tile(switchvalue, getDynamicColor(switchcolor), j, i); //création d'une "tuile a" qui a des arguments dynamiques
				this.tilesArray[i][j] = a; // on affecte la tuile créé 

				if (cpt % NB_SAMETILES == 0) {
					switchvalue++;
				}
				if (switchvalue == NB_NUMBERS +1) {
					switchvalue = 1;
				}
				if (cpt%(NB_NUMBERS*NB_SAMETILES)==0) {
					switchcolor++; 
				}
				cpt++;
			}

		}

	}

	private Color getDynamicColor(int switchcolor) {
		Color dynamic_color = Color.black;
		switch (switchcolor) {
		case 0:
			dynamic_color = Color.blue;
			break;
		case 1:
			dynamic_color = Color.green;
			break;
		case 2:
			dynamic_color = Color.red;
			break;
		case 3:
			dynamic_color = Color.yellow;
		default:
			break;
		}
		return dynamic_color;
	}

	//enregistre les tuiles sélectionnées par l'utilisateur dans un tableau comportant 2 tuiles

	public void delete(Tile tuileadetruire) {
		tilesArray[tuileadetruire.getY()][tuileadetruire.getX()] = null;
	}

	public void collapse() {

		// Ecroullement des tuiles
		for (int j = 0; j <= NB_COLUMNS - 1; j++) { // on parcours chaque colonne
			for (int i = NB_LINES - 1; i >= 1 ; i--) { // on parcours chaque lignes de la fin jusqu'au début + 1

				if(tilesArray[i][j]==null) { // si une tuille dans la colonne est vide il va y avoir un écroullement
					// nous allons calculer l'écart de l'écoulement
					int k = i - 1; // k est l'indice de la tuile qui va s'écrouler, on l'initialise à la tuile juste au dessus
					while (k > 0 && tilesArray[k][j] == null) { // si la tuile à l'indice k est aussi détruite
						k--; // on prends la tuile encore au dessus
					}
					swapTilesMatrix(tilesArray, i, j, k, j); // on swap la tuile actuel avec celle à l'indice ligne k
				} 
			}
		}

		// Décalage des colonnes
		for (int j = 0; j <= NB_COLUMNS - 2; j++) { // on parcours chaque colonnes du début jusqu'à la fin - 1

			if (tilesArray[NB_LINES - 1][j] == null) { // si une colonne est vide il va y avoir un décalage de colonne
				int k = j+1; // k est l'indice de la tuile de la colonne qui va décaler, on l'initialise à la colonne juste à gauche
				while (k < NB_COLUMNS - 1 && tilesArray[NB_LINES - 1][k] == null) { // si la colonne à l'indice k est aussi détruite
					k++; // on prends la colonne encore à gauche
				}
				for (int l = 0; l <= NB_LINES-1; l++) {  // pour chaque ligne dans la colonne
					swapTilesMatrix(tilesArray, l, j, l, k); // on swap les tuiles celle de la colonne à l'indice k
				}
			}
		}
		setAllcoordinates();//remet les bonnes coordonnees pour chaque tuile (x et y). 
	}

	public void actionOnSelectedTiles(Tile a, Tile b) {
		if (a.equals(b) && a.isNear(b) && (a != b)){//on compare les adresses pour savoir si l'utilisateur n'a pas sélectionné la même tuile deux fois
			delete(a);
			delete(b);
			System.out.println("tuiles détruites");
		}
		else{
			System.out.println("ça a pas marché");
		}
	}

	//si la dernière ligne de la première colonne est nulle ça signifie qu'on a gagné!
	public boolean isFinished() {
		if(tilesArray[NB_LINES-1][0] != null) { 
			return false;
		}
		return true;
	}



}
