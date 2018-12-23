import java.awt.Color;

public class Renderer {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	
	public Renderer() {
		
	}
	
	public void draw(Board board) {
	Tile mat[][] = board.getTilesArray();
			for (int i = 0; i < mat.length; i++) {
				System.out.print("{");
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
}
