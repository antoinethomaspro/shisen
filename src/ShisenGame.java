//gestion game over?

public class ShisenGame {





	public static void main(String[] args) {

		//playWithASCII();
		playWithSwing();
		

	}


	public static void playWithASCII() {
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
		RendererASCII render = new RendererASCII(jeu);
		render.draw();

		while(jeu.isFinished()==false) {
			Tile firstTile = render.readAction(); //création d'une variable coordonnees1 affectée par ce que l'utilisateur entre. 
			Tile secondTile = render.readAction(); 

			jeu.actionOnSelectedTiles(firstTile, secondTile);
			jeu.collapse(); // on modifie le tableau

			render.draw();
		}

		System.out.println("Jeu terminé");
	
	}
	
	public static void playWithSwing() {
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
		RendererSwing render = new RendererSwing(jeu);
		

		while(jeu.isFinished()==false) {
			

			
			jeu.collapse(); // on modifie le tableau

			
		}

		System.out.println("Jeu terminé");
	}

}

