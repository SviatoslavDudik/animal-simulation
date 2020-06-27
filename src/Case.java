import java.util.List;

public class Case {
	
	private List<Case> voisines;
	private int x, y;
	private int largeur, hauteur;
	private Creature c;
	
	public Case() {
	}
	
	public void addCaseVoisine(Case c) {
		voisines.add(c);
	}
	
	public void setCreature(Creature c) {
		this.c = c;
	}

}
