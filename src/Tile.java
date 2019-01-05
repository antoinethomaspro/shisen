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
public class Tile extends JButton  {

	private int value;
	private Color color;
	private int x;
	private int y;
	boolean deleted;
	
    
	public Tile(int value, Color color, int x, int y){
		super(Integer.toString(value));
		Font police = new Font("Tahoma", Font.BOLD, 16);
		this.setFont(police);
		this.setBackground(color);
		this.setBorder(new LineBorder(Color.BLACK, 1));
		this.deleted = false;
		
		this.value = value;
		this.color = color;
		this.x = x;
		this.y = y;
		
		
		//boolean  : quselectand on clique sur une tuile ça change le boolean select. 
		//déselectionne si on clique sur une autre tuile, impossibilité de sélectionner plusieurs tuiles...
		//méthode getselected tiles pour savoir le nombre de tuile sélectionnées. 
		

	}

	public boolean isDeleted() {
		return deleted;
	}
	

	public int getGridX() {
		return x;
	}

	public void setGridX(int x) {
		this.x = x;
	}

	public int getGridY() { //on a renommé getX en getGridX car c'était une méthode déjà utilisé dans JComponent
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

	//méthode comparer les tuiles sélectionnées : les tuiles doivent être identiques
	public boolean equals(Object a) {
		if (a == null) { //convention au cas où il y a un pb
			return false;
		}
		if (!(a instanceof Tile)) { //est-ce que l'objet passé en paramètre est bien une tuile
			return false;
		}

		if (((Tile) a).getValue()==(this.value) && //this.value = valeur de x a.getValue : valeur de y
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

	public void delete() {
		setBackground(Color.WHITE);
		setText("");
		deleted = true;
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
