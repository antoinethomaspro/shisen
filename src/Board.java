import java.awt.Color;

public class Board {
	Tile tilesArray[][];
	Tile selectedTiles[];
	String etape;
	private String coordonnees1;
	private String coordonnees2;


	public String getCoordonnees1() {
		return coordonnees1;
	}

	public String getCoordonnees2() {
		return coordonnees2;
	}
	
	public void setCoordonnees1(String value) {
		coordonnees1 = value;
	}
	
	public void setCoordonnees2(String value) {
		coordonnees2 = value;
	}

	public final static int NB_LINES = 12;
	public final static int NB_COLUMNS = 24;

	private static int randomWithRange(int min, int max)
	{
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min;
	}	



	public Board(){
		this.tilesArray = new Tile[12][24];
		this.selectedTiles = new Tile[2];
		this.etape = "en cours";

	}
	public Tile[][] getTilesArray() {
		return tilesArray;
	}

	public void shuffleArray() {
		for (int i = 0; i < tilesArray.length; i++) {
			for (int j = 0; j < tilesArray[0].length; j++) {
				swapRandomMatrix(tilesArray, i, j);
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


	public void init() {   //but de cette méthode : initialiser un tableau de toutes les tuiles possibles 

		int switchcolor = 0; //couleur 0 = bleu
		int switchvalue = 1; //les tuiles sont numérotées de 1 à 9
		int cpt = 1; //compteur qui permet de distribuer les tuiles de façon à ce qu'il y ait 8 tuiles par numéro/couleur


		for (int i = 0; i < this.tilesArray.length; i++) { //Rappel : TilesArray = tableau de 12/24 tuiles
			for (int j = 0; j < this.tilesArray[i].length; j++) { //On parourt TilesArray pour modifier la valeur de chaque tuile


				Tile a = new Tile(switchvalue, getDynamicColor(switchcolor)); //création d'une "tuile a" qui a des arguments dynamiques
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



}
