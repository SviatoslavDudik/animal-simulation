package view;
import java.awt.Graphics;

public interface AffichageStrategy {
	
	public void afficher(Graphics g, Case c);
	
	public TypeAffichage getType();

}
