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
	
	public Tile(int value, Color color){
		this.value = value;
		this.color = color;
		
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
	
	
	
	
	public void delete() {
		
	}
	

	
	
}
