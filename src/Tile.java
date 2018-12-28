import java.awt.Color;

/**
 * 
 */

/**
 * @author Antoine
 *
 */
public class Tile {

	private int value;
	private Color color;
	private int x;
	private int y;



	public Tile(int value, Color color, int x, int y){
		this.value = value;
		this.color = color;
		this.x = x;
		this.y = y;

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
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
		int total1 = this.x + this.y;
		int total2 = a.getX() + a.getY();
		int soustraction = total1 - total2;
		if ((Math.abs(soustraction)) <= 3) {
			return true;
		}
		else return false;

	}

	public void delete() {

	}




}
