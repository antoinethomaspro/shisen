import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import javafx.scene.shape.Line;

/**
 * 
 */

/**
 * @author Antoine
 *
 */
public class Tile extends JButton  { //JButton car la tuile doit être cliquable 

	private int value;     //valeur de la tuile (1 à 9)
	private Color color;   //couleur de la tuile
	private int x;         //positionnement de la tuile (axe des abscisses)
	private int y;         //positionnement de la tuile (axe des ordonnées)
	boolean deleted;       //état de la tuile (détruite ou non) 
	
    
	public Tile(int value, Color color, int x, int y){ //constructeur
		super(Integer.toString(value));                //tostring car super demande un string
		Font police = new Font("Tahoma", Font.BOLD, 16); 
		this.setFont(police);
		this.setBackground(color);
		this.setBorder(new LineBorder(Color.BLACK, 1));
		this.deleted = false;
		
		this.value = value;
		this.color = color;
		this.x = x;
		this.y = y;
	
	}

	public int getGridX() { //NB : on a renommé getX en getGridX car c'était une méthode déjà utilisé dans JComponent et donc conflit (GRID = coordonnées en Anglais).
		return x;
	}

	public void setGridX(int x) {
		this.x = x;
	}

	public int getGridY() { 
		return y;
	}

	public void setGridY(int y) {
		this.y = y;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public boolean isDeleted() {
		return deleted;
	}
	
	//méthode equals pour comparer les tuiles sélectionnées : les tuiles doivent être identiques
	public boolean equals(Object a) { //on vérifie que l'object est bien une tuile
		if (a == null) { //convention au cas où il y a un pb
			return false;
		}
		if (!(a instanceof Tile)) { //est-ce que l'objet passé en paramètre est bien une tuile
			return false;
		}

		if (((Tile) a).getValue()==(this.value) && //this.value = valeur de x a.getValue : valeur de y ???
				((Tile) a).getColor().equals(this.color)) {
			return true;
		}
		else return false;
	}

	//méthode comparer les tuiles sélectionnéees : les tuiles doivent être à une certaine distance
	public boolean isNear(Tile a) {
		int total1 = Math.abs(this.x - a.getGridX());
		int total2 = Math.abs(this.y - a.getGridY());
		int addition = total1 + total2;
		if (addition <= 3) {
			return true;
		}
		else return false;

	}

	public void delete() { //méthode qui "détruit" la tuile à l'aide des méthode repaint et revalidate
		setBackground(Color.WHITE);
		setText("");
		deleted = true;
		//refresh swing
		repaint(); 
		revalidate(); 
		
	}
	
	public void select() {
		setBorder(new LineBorder(Color.BLACK, 10));
	}
	
	public void deselect() {
		setBorder(new LineBorder(Color.BLACK, 1));
	}
}
