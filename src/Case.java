import java.util.ArrayList;
import java.util.List;

public class Case {

	private List<Case> voisines;
	private int x, y;
	private int largeur, hauteur;
	private Animal a;
	
	public Case(int x, int y, int largeur, int hauteur) {
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;

		a = null;
		voisines = new ArrayList<Case>();
	}
	
	public void addCaseVoisine(Case c) {
		voisines.add(c);
	}
	
	public Animal getAnimal() {
		return a;
	}
	
	public void setAnimal(Animal c) {
		this.a = c;
	}
	
	public void liberer() {
		setAnimal(null);
	}
	
	public Case[] getCaseVoisines() {
		return (Case[]) voisines.toArray();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

}
