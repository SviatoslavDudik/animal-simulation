import javax.swing.JFrame;

import controller.Simulation;
import model.Grille;
import view.AffichageRectangle;
import view.AffichageStrategy;
import view.ChampGraphique;
import view.GrilleView;

import java.awt.Color;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Simulation sim = new Simulation(4, 4);
		sim.lancer();
		/*
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
		*/
	}
	
	

}
