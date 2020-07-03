package view;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import controller.Acces2D;

/**
 * Vue de la simulation.
 * Affiche les cases en utilisant une stratégie d'affichage.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class SimulationView extends JPanel {
	
	public Acces2D<Case,Color> cases;
	public AffichageStrategy strategy;
	
	/**
	 * Constructeur.
	 *
	 * @param cases cases à afficher
	 * @param strategy stratégie d'affichage à utiliser
	 */
	public SimulationView(Acces2D<Case,Color> cases, AffichageStrategy strategy) {
		this.cases = cases;
		this.strategy = strategy;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Case c: cases)
			strategy.afficher(g, c);
	}
	
	/**
	 * Accesseur.
	 *
	 * @return type de stratégie d'affichage utilisée
	 */
	public TypeAffichage getAffichage() {
		return strategy.getType();
	}

	/**
	 * Accesseur.
	 *
	 * @param s nouvelle stratégie d'affichage à utiliser
	 */
	public void setAffichage(AffichageStrategy s) {
		strategy = s;
	}

	/**
	 * Accesseur.
	 * Remplace les anciennes cases par les nouvelles.
	 *
	 * @param cases nouvelles cases à afficher
	 */
	public void setCases(Acces2D<Case,Color> cases) {
		this.cases = cases;
	}

}
