import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class RendererSwing extends JFrame{
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



	public RendererSwing(Board board) {
		super("Shisen");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		JPanel zone = new JPanel();
		zone.setLayout(new GridLayout(Board.NB_LINES, Board.NB_COLUMNS));
		
		Tile[][] tilesArray = board.getTilesArray();
		
		for (int i = 0; i < board.NB_LINES; i++) {
			for (int j = 0; j < board.NB_COLUMNS; j++) {
				zone.add(tilesArray[i][j]);
			}
		}
		this.add(zone, BorderLayout.CENTER);
		this.setVisible(true);
		
		
		
		
		this.board = board;
		
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

		//m�thode d'affichage matrice et des ordonn�es
		for (int i = 0; i < mat.length; i++) {
			System.out.print(ordonnee+" {"); //affichage des ordonn�es
			ordonnee++;

			for (int j = 0; j < mat[i].length; j++) {
				Tile a = mat[i][j];
				if (j>0) System.out.print("\t");

				String color =  ANSI_WHITE; //on affiche un 0 blanc si une tuile a �t� d�truite auparavant

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

	//traduit ce que l'utilisateur entre en un tableau de deux �l�ments
	//ce qui ressort de la m�thode c'est l'index de tilesarray
	public Tile readAction() {
		System.out.println("veuillez s�lectionner tuile ex: A2");
		String saisie = sc.nextLine();//r�cup�re A2
		char lettre = saisie.charAt(0);
		int chiffre = Integer.parseInt(saisie.substring(1));
		if(lettre < 65 || lettre >65 + Board.NB_LINES) {
			System.out.println("lettre non comprise entre A et "+ (char)(65+ Board.NB_LINES));//throw new : retourne une nouvelle exception. arr�te l'action en affichant un message
			return readAction();	
		}
		if(chiffre < 0 || chiffre > Board.NB_COLUMNS -1) {
			System.out.println("lettre non comprise entre A et "+ (char)(Board.NB_COLUMNS));
			return readAction();
		}
		int x = chiffre;
		int y = lettre -65;
		Tile selected = board.getTile(x , y);
		if (selected == null) {
			System.out.println("Tuile d�j� d�truite");
			return readAction(); //relance la saisie si la tuile est inexistante.
		}
		return selected;


	}
}
