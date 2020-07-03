package view;
import java.awt.Graphics;

/**
 * L'interface des strategies d'affichage de cases.
 * La classe réalisant cette interface peut être utilisée 
 * pour afficher les cases du champ de la simulation.
 */
public interface AffichageStrategy {
	
	/**
	 * Affiche une case sur le champ graphique.
	 * @param g contexte graphique
	 * @param c case à afficher
	 */
	public void afficher(Graphics g, Case c);
	
	/**
	 * Renvoie le type de la strategie d'affichage.
	 * @return type de la strategie
	 */
	public TypeAffichage getType();

}
