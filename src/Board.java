import java.awt.Color;

public class Board {
	Tile tilesArray[][];
	
	String etape;





	public final static int NB_LINES = 12;
	public final static int NB_COLUMNS = 24;





	public Board(){
		this.tilesArray = new Tile[12][24];
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
	
	public Tile[][] getTilesArray() { //retourne tableau de deux tuiles;
		return tilesArray;
	}

	public void shuffleArray() {
		for (int i = 0; i < tilesArray.length; i++) {
			for (int j = 0; j < tilesArray[0].length; j++) {
				swapRandomMatrix(tilesArray, i, j);
			}
		}
		for (int i = 0; i < tilesArray.length; i++) {
			for (int j = 0; j < tilesArray[0].length; j++) {
				tilesArray[i][j];
			}
		}

	}

	private static void swapRandomMatrix(Tile[][] matrix, int i, int j) {
		int rdni = randomWithRange(0, matrix.length -1);
		int rdnj = randomWithRange(0, matrix[0].length -1);
		Tile temp = matrix[i][j];
		matrix[i][j] = matrix[rdni][rdnj];
		matrix[rdni][rdnj] = temp;
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
				
				if ((cpt%8)==0) {
					switchvalue++;
				}

				if (switchvalue == 10) {
					switchvalue = 1;
				}

				if ((cpt%72)==0) {
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
		System.out.println("tableau modifié");
	}

	public void actionOnSelectedTiles(Tile a, Tile b) {
		if (a.equals(b) && a.isNear(b)){
		a.delete();
		b.delete();
		
		}
		
	}



}
