

public class ShisenGame {





	public static void main(String[] args) {
		
		Board jeu = new Board();
		jeu.init();
		jeu.shuffleArray();
		Renderer render = new Renderer();
		render.draw(jeu);

		while(true) {
			Tile firstTile = render.readAction(jeu); //cr�ation d'une variable coordonnees1 affect�e par ce que l'utilisateur entre. 
			Tile secondTile = render.readAction(jeu); 
	
			jeu.actionOnSelectedTiles(firstTile, secondTile);
			jeu.collapse(); // on modifie le tableau
			
			render.draw(jeu);
		}




	}

}
