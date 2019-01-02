import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * 
 */

/**
 * @author Antoine
 *
 */
public class Tile extends JButton implements MouseListener {

	private int value;
	private Color color;
	private int x;
	private int y;
    


	public Tile(int value, Color color, int x, int y){
		super(Integer.toString(value));
		Font police = new Font("Tahoma", Font.BOLD, 16);
		this.setFont(police);
		this.setBackground(color);
		this.addMouseListener(this);
		
		this.value = value;
		this.color = color;
		this.x = x;
		this.y = y;
		
		
		
		

	}

	public int getGridX() {
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
		int total1 = Math.abs(this.x - a.getX());
		int total2 = Math.abs(this.y - a.getY());
		int addition = total1 + total2;
		if (addition <= 3) {
			return true;
		}
		else return false;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
