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

	public String readAction() {
		Boolean lectureNonValide = true;
		Boolean isAbscisse;
		Boolean isOrdonne;
		String saisieUtilisateur = "";

		while(lectureNonValide=true) {
			isAbscisse = false;
			isOrdonne = false;
			System.out.println();
			System.out.println("Veuillez-sélectionner le chiffre de votre première tuile");
			String abs=sc.next();
			try {
				int abscisses = Integer.parseInt(abs);//conversion en int
			}
			catch(NumberFormatException e){
				System.out.println("Exception occurred");
			}




			//longueur de la saisie

			if(saisieUtilisateur.length()!=2) { 
				System.out.println("Trop ou pas assez de caractères");
			} 
			else {
				lectureNonValide = false;
			}
			//coordonnées valide
			//check ordonnee
			//check abscisse
			//pour chaque lettre saisie

			for (char lettre : saisieUtilisateur.toCharArray()) { //string saisi et on veut parcourir chacune des lettres
				//je regarde si lettre est dans mes ordonnées
				if(new String(ordonnees).indexOf(lettre) != -1) { //on veut savoir si la lettre appartient à un tableau et pour valueof on a besion d'un string mais valueof renvoie la lettre donc compare lettre
					isOrdonne = true;
					System.out.println("ordonne ok");
				}
				else {
					System.out.println("ordonnee pas bon");
					//et si lettre est dans mes abscisses

				}

			}

			for (char chiffre : saisieUtilisateur.toCharArray()) { //string saisi et on veut parcourir chacune des lettres
				//je regarde si lettre est dans mes ordonnées
				int temp = Character.getNumericValue(chiffre);//conversion en int
				if (temp <= Board.NB_COLUMNS && temp >= 0) { //on veut savoir si la lettre appartient à un tableau et pour valueof on a besion d'un string mais valueof renvoie la lettre donc compare lettre
					isAbscisse = true;
					System.out.println("abscisse ok");
				}
				else {
					System.out.println("abscisse pas bon");
				}

			}

			if (isAbscisse && isOrdonne) {
				lectureNonValide = false;
			}
			else {
				System.out.println("Il me faut une lettre et un chiffre");
			}

		}//end while


		System.out.println("Vous avez choisi la tuile : " + sc);
		return saisieUtilisateur;


	}
}
