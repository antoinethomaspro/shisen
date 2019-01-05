import java.awt.Color;
import java.util.Scanner;



public class RendererASCII {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_WHITE = "\u001B[37m";


	private Scanner sc;
	char[] ordonnees = new char[Board.NB_LINES];
	int[] abscisses = new int[Board.NB_COLUMNS];
	Board board;	



	public RendererASCII(Board board) {
		
		this.board = board;
		sc = new Scanner(System.in);

		//création d'un tableau pour les ordonnes
		for (int i = 0; i < Board.NB_LINES; i++) {
			ordonnees[i]= (char)(65 + i); //nécessite de cast les int en char

		}
		//idem abscisse
		for (int i = 0; i < Board.NB_COLUMNS; i++) {
			abscisses[i]= i;
		}
	}

	public void draw() {
		char ordonnee = 65;
		Tile mat[][] = board.getTilesArray();

		//affichage des abscisses;
		System.out.print("   ");
		int abscisse = 0;
		for (int i=0; i < mat[0].length; i++) {
			if (i>0) System.out.print("\t");
			System.out.print(abscisse);
			abscisse++;
		}
		System.out.println();
		System.out.println();

		//méthode d'affichage matrice et des ordonnées
		for (int i = 0; i < mat.length; i++) {
			System.out.print(ordonnee+" {"); //affichage des ordonnées
			ordonnee++;

			for (int j = 0; j < mat[i].length; j++) {
				Tile tileToDisplay = mat[i][j];
				if (j>0) System.out.print("\t");

				String color =  ANSI_WHITE; //on affiche un 0 blanc si une tuile a été détruite auparavant

				if(tileToDisplay != null) {
					if(tileToDisplay.getColor() == Color.blue) {
						color = ANSI_BLUE;
					}
					if(tileToDisplay.getColor() == Color.red) {
						color = ANSI_RED;
					}
					if(tileToDisplay.getColor() == Color.yellow) {
						color = ANSI_YELLOW;
					}
					if(tileToDisplay.getColor() == Color.green) {
						color = ANSI_GREEN;
					}
				}
				System.out.print(color+ (tileToDisplay.isDeleted() ? "0" : tileToDisplay.getValue()) + ANSI_RESET); //si a est null alors je renvoie 0 sinon je renvoie sa valeur
			}
			System.out.println("}");
		}

	}

	//traduit ce que l'utilisateur entre en un tableau de deux éléments
	//ce qui ressort de la méthode c'est l'index de tilesarray
	public Tile readAction() {
		System.out.println("veuillez sélectionner tuile ex: A2");
		String saisie = sc.nextLine();//récupère A2
		char lettre = saisie.charAt(0);
		int chiffre = Integer.parseInt(saisie.substring(1));
		if(lettre < 65 || lettre >65 + Board.NB_LINES) {
			System.out.println("lettre non comprise entre A et "+ (char)(65+ Board.NB_LINES));//throw new : retourne une nouvelle exception. arrête l'action en affichant un message
			return readAction();	
		}
		if(chiffre < 0 || chiffre > Board.NB_COLUMNS -1) {
			System.out.println("lettre non comprise entre A et "+ (char)(Board.NB_COLUMNS));
			return readAction();
		}
		int x = chiffre;
		int y = lettre -65;
		Tile selectedTile = board.getTile(x , y);
		if (selectedTile.isDeleted()) {
			System.out.println("Tuile déjà détruite");
			return readAction(); //relance la saisie si la tuile est inexistante.
		}
		return selectedTile;


	}
}
