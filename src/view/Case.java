package view;

import java.awt.Color;

/**
 * Cette classe regroupe les informations nécessaires pour afficher une case.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class Case {
	
	/** Abscisse du coin haut gauche de la case. */
	private int x;
	/** Ordonnée du coin haut gauche de la case. */
	private int y;
	/** Largeur de la case en pixels. */
	private int largeur;
	/** Hauteur de la case en pixels. */
	private int hauteur;
	/** Couleur de la case. */
	private Color couleur;

	/**
	 * Constructeur.
	 *
	 * @param x abscisse (en pixels) du coin haut gauche de la case
	 * @param y ordonnée (en pixels) du coin haut gauche de la case
	 * @param largeur largeur (en pixels) de la case
	 * @param hauteur hauteur (en pixels) de la case
	 */
	public Case(int x, int y, int largeur, int hauteur) {
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		couleur = null;
	}

	/**
	 * Accesseur.
	 *
	 * @return abscisse du coin haut gauche de la case
	 */
	public int getX() {
		return x;
	}

	/**
	 * Accesseur.
	 *
	 * @return ordonnée (en pixels) du coin haut gauche de la case
	 */
	public int getY() {
		return y;
	}

	/**
	 * Accesseur.
	 *
	 * @return largeur (en pixels) de la case
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * Accesseur.
	 *
	 * @return hauteur (en pixels) de la case
	 */
	public int getHauteur() {
		return hauteur;
	}
	
	/**
	 * Accesseur.
	 *
	 * @return couleur de la case
	 */
	public Color getCouleur() {
		return couleur;
	}
	
	/**
	 * Accesseur.
	 *
	 * @param c nouvelle couleur de la case
	 */
	void setCouleur(Color c) {
		couleur = c;
	}

}
