package view;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Strategie d'affichage qui affiche les cases comme des cercles.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class AffichageCercle implements AffichageStrategy {

	@Override
	public void afficher(Graphics g, Case c) {
		int x = c.getX();
		int y = c.getY();
		int l = c.getLargeur();
		int h = c.getHauteur();
		Color col = c.getCouleur();
		if (col != null) {
			g.setColor(col);
			g.fillOval(x, y, l, h);
		}
		g.setColor(Color.BLACK);
		g.drawOval(x, y, l, h);
	}

	@Override
	public TypeAffichage getType() {
		return TypeAffichage.CERCLE;
	}

}
