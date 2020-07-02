package view;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import controller.Acces2D;

public class SimulationView extends JPanel {
	
	public Acces2D<Case,Color> cases;
	public AffichageStrategy strategy;
	
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
	
	public TypeAffichage getAffichage() {
		return strategy.getType();
	}

	public void setAffichage(AffichageStrategy s) {
		strategy = s;
	}

	public void setCases(Acces2D<Case,Color> cases) {
		this.cases = cases;
	}

}
