import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;



public class RendererSwing extends JFrame implements ActionListener{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_WHITE = "\u001B[37m";

	Board board;	



	public RendererSwing(Board board) {
		super("Shisen");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		JPanel zone = new JPanel();
		zone.setLayout(new GridLayout(Board.NB_LINES, Board.NB_COLUMNS, 10, 10));


		Tile[][] tilesArray = board.getTilesArray();

		for (int i = 0; i < Board.NB_LINES; i++) {
			for (int j = 0; j < Board.NB_COLUMNS; j++) {
				tilesArray[i][j].addActionListener(this);
				zone.add(tilesArray[i][j]);

			}
		}
		this.add(zone, BorderLayout.CENTER);
		this.setVisible(true);
		this.board = board;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Tile tile = (Tile)e.getSource(); //r�cup�re la r�f�rence de la tuile cliqu�e
		//on veut savoir si il y a d�j� une tuile de s�lectionn�e. 
		//si c'est la m�me tuile qu'on a d�j� s�lectionn�, on met tileSelected � null et on enl�ve le setborder. 
		if(board.getTileSelected()==null) {
			board.setTileSelected(tile);
			tile.setBorder(new LineBorder(Color.RED, 10));
		}
		else {
			if(board.getTileSelected() == tile) {
				board.setTileSelected(null);
				tile.setBorder(new LineBorder(Color.BLACK, 1));
			}
			else {
				board.actionOnSelectedTiles(board.getTileSelected(), tile);
				tile.setBorder(new LineBorder(Color.BLACK, 1));
				board.setTileSelected(null);
				this.repaint();
			}
		}
		
		//si c'est le cas on compare tile et la tuile d�j� s�lectionn�e, puis on remet tileSelected � null. 
		//si ce n'est pas le cas alors on set tileSelected � la valeur de tile et on fait tile.setborder
		
		//devoir
		//quand on supprime �a marche pas. voir collapse. Elles sont mises � nulles mais restent afficher. Soit 
		//debug, voir si les tuiles dans tilesarray sont bien � nulles. 
		//Probl�me d'affichage? 
		//Si elles sont bien � nulles et probl�me d'affichage persistant, va falloir les d�truire, changer les attributs (is delete). 
		

	}
}
