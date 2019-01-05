import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;



public class RendererSwing extends JFrame implements MouseListener {

	Board board;
	JPanel zone;



	public RendererSwing(Board board) {
		super("Shisen");
		this.board = board;
		zone = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		zone.setLayout(new GridLayout(Board.NB_LINES, Board.NB_COLUMNS, 10, 10));

		// set listener
		Tile[][] tilesArray = board.getTilesArray();
		for (int i = 0; i < Board.NB_LINES; i++) {
			for (int j = 0; j < Board.NB_COLUMNS; j++) {
				tilesArray[i][j].addMouseListener(this);
			}
		}

		draw();

		this.add(zone, BorderLayout.CENTER);
		this.setVisible(true);


	}

	public void draw() {

		zone.removeAll();

		Tile[][] tilesArray = board.getTilesArray();

		for (int i = 0; i < Board.NB_LINES; i++) {
			for (int j = 0; j < Board.NB_COLUMNS; j++) {
				zone.add(tilesArray[i][j]);

			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Tile tile = (Tile)e.getSource(); //r�cup�re la r�f�rence de la tuile cliqu�e
		//on veut savoir si il y a d�j� une tuile de s�lectionn�e. 
		//si c'est la m�me tuile qu'on a d�j� s�lectionn�, on met tileSelected � null et on enl�ve le setborder. 
		if (!tile.isDeleted()) {


			if(board.getTileSelected()==null) {
				board.setTileSelected(tile);
				tile.select();
			}
			else {
				if(board.getTileSelected() == tile) {
					board.setTileSelected(null);
					tile.deselect();
				}
				else {
					if(board.actionOnSelectedTiles(board.getTileSelected(), tile)) { // si modifi�
//						draw();
					}
					tile.deselect();
					board.getTileSelected().deselect();
					//tile.setBorder(new LineBorder(Color.BLACK, 1));
					 board.setTileSelected(null);
				}
			}
		} else {
			System.out.println("La tuile selectionn�e est nulle.");
		}
		
		draw();
		
		revalidate();
		repaint();
		zone.updateUI();
		
		if (board.isFinished()) {
			javax.swing.JOptionPane.showMessageDialog(null,"Partie termin�e"); 
			dispose();
		}
		

		//si c'est le cas on compare tile et la tuile d�j� s�lectionn�e, puis on remet tileSelected � null. 
		//si ce n'est pas le cas alors on set tileSelected � la valeur de tile et on fait tile.setborder

		//devoir
		//quand on supprime �a marche pas. voir collapse. Elles sont mises � nulles mais restent afficher. Soit 
		//debug, voir si les tuiles dans tilesarray sont bien � nulles. 
		//Probl�me d'affichage? 
		//Si elles sont bien � nulles et probl�me d'affichage persistant, va falloir les d�truire, changer les attributs (is delete). 



	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}
