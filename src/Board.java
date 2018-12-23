import java.awt.Color;

public class Board {
	Tile tilesArray[][];
	Tile selectedTiles[];
	
	private static int randomWithRange(int min, int max)
	{
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min;
	}	



	public Board(){
		this.tilesArray = new Tile[12][24];
		this.selectedTiles = new Tile[2];

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


	public void init() {

		int switchcolor = 0;
		int switchvalue = 1;
		int cpt = 1;


		for (int i = 0; i < this.tilesArray.length; i++) {
			for (int j = 0; j < this.tilesArray[i].length; j++) {


				if ((cpt%8)==0) {
					switchvalue++;
				}

				if (switchvalue == 10) {
					switchvalue = 1;
				}

				if ((cpt%72)==0) {
					switchcolor++; 
				}

				Tile a = new Tile(switchvalue, getDynamicColor(switchcolor));
				this.tilesArray[i][j] = a;

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
