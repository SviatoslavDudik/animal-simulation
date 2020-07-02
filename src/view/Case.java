package view;

import java.awt.Color;

public class Case {
	
	private int x, y;
	private int largeur, hauteur;
	private Color couleur;

	public Case(int x, int y, int largeur, int hauteur) {
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		couleur = null;
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
	
	public Color getCouleur() {
		return couleur;
	}
	
	void setCouleur(Color c) {
		couleur = c;
	}

}
