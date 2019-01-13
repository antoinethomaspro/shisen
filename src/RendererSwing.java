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
		this.setSize(1000, 1000);
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

		zone.removeAll();//replace les tuiles dans le bon ordre (on a remodifié toutes les coordonnées des tuiles, donc on les replace au bon endroit)

		Tile[][] tilesArray = board.getTilesArray();

		for (int i = 0; i < Board.NB_LINES; i++) {
			for (int j = 0; j < Board.NB_COLUMNS; j++) {
				zone.add(tilesArray[i][j]);

			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("clicked");
		Tile tile = (Tile)e.getSource(); //récupère la référence de la tuile cliquée
		//on veut savoir si il y a déjà une tuile de sélectionnée. 
		//si c'est la même tuile qu'on a déjà sélectionné, on met tileSelected à null et on enlève le setborder. 
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
					if(board.actionOnSelectedTiles(board.getTileSelected(), tile)) { // si modifié
						//						draw();
					}
					tile.deselect();
					board.getTileSelected().deselect();
					//tile.setBorder(new LineBorder(Color.BLACK, 1));
					board.setTileSelected(null);
				}
			}
		} else {
			System.out.println("La tuile selectionnée est nulle.");
		}

		draw();
//refresh swing
		revalidate();
		repaint();
		zone.updateUI();

		if (board.isFinished()) {
			javax.swing.JOptionPane.showMessageDialog(null,"Partie terminée"); 
			dispose();
		}

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
