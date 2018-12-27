

public class ShisenGame {





	public static void main(String[] args) {
		
		Board jeu = new Board();
		jeu.init();
		jeu.shuffleArray();
		Renderer render = new Renderer();
		render.draw(jeu);

		while(jeu.etape != "jeu terminé") {
			
			
			//jeu.setCoordonnees1(render.readAction());
			//jeu.setCoordonnees2(render.readAction());
			
			render.draw(jeu);
		}




	}

}
