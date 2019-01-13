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
public class Tile extends JButton  { //JButton car la tuile doit �tre cliquable 

	private int value;     //valeur de la tuile (1 � 9)
	private Color color;   //couleur de la tuile
	private int x;         //positionnement de la tuile (axe des abscisses)
	private int y;         //positionnement de la tuile (axe des ordonn�es)
	boolean deleted;       //�tat de la tuile (d�truite ou non) 
	
    
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

	public int getGridX() { //NB : on a renomm� getX en getGridX car c'�tait une m�thode d�j� utilis� dans JComponent et donc conflit (GRID = coordonn�es en Anglais).
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
	
	//m�thode equals pour comparer les tuiles s�lectionn�es : les tuiles doivent �tre identiques
	public boolean equals(Object a) { //on v�rifie que l'object est bien une tuile
		if (a == null) { //convention au cas o� il y a un pb
			return false;
		}
		if (!(a instanceof Tile)) { //est-ce que l'objet pass� en param�tre est bien une tuile
			return false;
		}

		if (((Tile) a).getValue()==(this.value) && //this.value = valeur de x a.getValue : valeur de y ???
				((Tile) a).getColor().equals(this.color)) {
			return true;
		}
		else return false;
	}

	//m�thode comparer les tuiles s�lectionn�ees : les tuiles doivent �tre � une certaine distance
	public boolean isNear(Tile a) {
		int total1 = Math.abs(this.x - a.getGridX());
		int total2 = Math.abs(this.y - a.getGridY());
		int addition = total1 + total2;
		if (addition <= 3) {
			return true;
		}
		else return false;

	}

	public void delete() { //m�thode qui "d�truit" la tuile � l'aide des m�thode repaint et revalidate
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
