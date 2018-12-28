import java.awt.Color;
import java.util.Scanner;



public class Renderer {
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




	public Renderer() {
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

	public void draw(Board board) {
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
				Tile a = mat[i][j];
				if (j>0) System.out.print("\t");

				String color =  ANSI_WHITE; //on affiche un 0 blanc si une tuile a été détruite auparavant

				if(a != null) {
					if(a.getColor() == Color.blue) {
						color = ANSI_BLUE;
					}
					if(a.getColor() == Color.red) {
						color = ANSI_RED;
					}
					if(a.getColor() == Color.yellow) {
						color = ANSI_YELLOW;
					}
					if(a.getColor() == Color.green) {
						color = ANSI_GREEN;
					}
				}
				System.out.print(color+ (a == null ? "0" : a.getValue()) + ANSI_RESET); //si a est null alors je renvoie 0 sinon je renvoie sa valeur
			}
			System.out.println("}");
		}

	}

	//traduit ce que l'utilisateur entre en un tableau de deux éléments
	//ce qui ressort de la méthode c'est l'index de tilesarray
	public Tile readAction(Board jeu) {
		System.out.println("veuillez sélectionner tuile ex: A2");
		String saisie = sc.nextLine();//récupère A2
		char lettre = saisie.charAt(0);
		int chiffre = Integer.parseInt(saisie.substring(1));
		if(lettre < 65 || lettre >65 + Board.NB_LINES) {
			throw new IllegalArgumentException("lettre non comprise entre A et "+ (char)(65+ Board.NB_LINES));//throw new : retourne une nouvelle exception. arrête l'action en affichant un message
		}
		if(chiffre < 1 || chiffre > Board.NB_COLUMNS) {
			throw new IllegalArgumentException("lettre non comprise entre A et "+ (char)(Board.NB_COLUMNS));// à changer
		}
		int x = chiffre;
		int y = lettre -65;
		Tile selected = jeu.getTile(x , y);
		if (selected == null) {
			System.out.println("Tuile déjà détruite");
			return readAction(jeu); //relance la saisie si la tuile est inexistante.
		}
		return selected;


	}
}
