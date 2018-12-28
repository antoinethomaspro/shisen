

public class ShisenGame {





	public static void main(String[] args) {
		
		Board jeu = new Board();
		jeu.init();
		jeu.shuffleArray();
		Renderer render = new Renderer();
		render.draw(jeu);

		while(true) {
			Tile firstTile = render.readAction(jeu); //création d'une variable coordonnees1 affectée par ce que l'utilisateur entre. 
			Tile secondTile = render.readAction(jeu); 
	
			jeu.actionOnSelectedTiles(firstTile, secondTile);
			jeu.collapse(); // on modifie le tableau
			
			render.draw(jeu);
		}




	}

}
