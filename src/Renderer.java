import java.awt.Color;
import java.util.Scanner;



public class Renderer {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
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

				if (j>0) System.out.print("\t");

				String color = "";
				if(mat[i][j].getColor() == Color.blue) {
					color = ANSI_BLUE;
				}
				if(mat[i][j].getColor() == Color.red) {
					color = ANSI_RED;
				}
				if(mat[i][j].getColor() == Color.yellow) {
					color = ANSI_YELLOW;
				}
				if(mat[i][j].getColor() == Color.green) {
					color = ANSI_GREEN;
				}

				System.out.print(color+ mat[i][j].getValue() + ANSI_RESET);
			}
			System.out.println("}");
		}

	}

	public int[] readAction() {
		System.out.println("veuillez sélectionner tuile ex: A2");
		String saisie = sc.nextLine();//récupère A2
		char lettre = saisie.charAt(0);
		int chiffre = Integer.parseInt(saisie.substring(1));
		if(lettre < 65 || lettre >65 + Board.NB_LINES) {
			throw new IllegalArgumentException("lettre non comprise entre A et "+ (char)(65+ Board.NB_LINES));
		}
		if(chiffre < 1 || chiffre > Board.NB_COLUMNS) {
			throw new IllegalArgumentException("lettre non comprise entre A et "+ (char)(65+ Board.NB_LINES));// à changer
		}
		
		int[] result = {lettre , chiffre};
		return result;
		
	}
}
