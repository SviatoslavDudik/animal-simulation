import java.awt.Color;
import java.awt.Graphics;

public class AffichageRectangle implements AffichageStrategy {

	@Override
	public void afficher(Graphics g, Case c) {
		int x = c.getX();
		int y = c.getY();
		int l = c.getLargeur();
		int h = c.getHauteur();
		Animal a = c.getAnimal();
		if (a != null) {
			g.setColor(a.getCouleur());
			g.fillRect(x, y, l, h);
		}
		g.setColor(Color.BLACK);
		g.drawRect(x, y, l, h);
	}

}
