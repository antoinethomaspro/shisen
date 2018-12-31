//gestion game over?

public class ShisenGame {





	public static void main(String[] args) {
		
		//ce constructeur peut retourner une exception donc il faut le surround si jamais il y a une exception
		Board jeu = null;
		try {
			jeu = new Board();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jeu.init();
		jeu.shuffleArray();
		Renderer render = new Renderer();
		render.draw(jeu);

		while(jeu.isFinished()==false) {
			Tile firstTile = render.readAction(jeu); //création d'une variable coordonnees1 affectée par ce que l'utilisateur entre. 
			Tile secondTile = render.readAction(jeu); 
	
			jeu.actionOnSelectedTiles(firstTile, secondTile);
			jeu.collapse(); // on modifie le tableau
			
			render.draw(jeu);
		}

		System.out.println("Jeu terminé");



	}

}
