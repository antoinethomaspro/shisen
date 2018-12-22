import java.awt.Color;

/**
 * 
 */

/**
 * @author Antoine
 *
 */
public class Tile {
	
	int value;
	Color color;
	boolean selected;
	boolean exist;
	
	public void marque(){
		selected = true;
	}
	
	public void demarque() {
		selected = false;
	}
	
	public void supprimer() {
		exist = false;
	}
	

	
	
}
