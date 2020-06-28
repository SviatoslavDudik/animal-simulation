import javax.swing.JFrame;

import java.awt.Color;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int posx, posy;
		Grille grille = new Grille(30, 40, 10, 10, 300, 400);
		//On crée un objet ChampGraphique de 50 cases de large, et 60 de haut
		ChampGraphique grid = new ChampGraphique(320, 420);
		grid.setFrame(10, 10, 300, 400);
		AffichageStrategy s = new AffichageRectangle();
		grid.setAffichage(s);
		grid.setCases(grille);
		
		Random r = new Random();
		
		int i;
		//Trente fois...
		for(i=0; i<30; i++) {
			//on tire une case au hasard dans la grille
			posx = r.nextInt(grid.getLargeur());
			posy = r.nextInt(grid.getHauteur());
			
			//et on la colorie en rouge
			//Puis, pause de 2s
			try {
	            Thread.sleep(500);
	        } 
			catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	

}
