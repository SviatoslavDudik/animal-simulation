package view;

import java.awt.Color;
import java.util.Iterator;
import controller.Acces2D;

/**
 * Grille rectangulaire de cases.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class GrilleView implements Acces2D<Case,Color> {

	/** Tableau de cases de la grille. */
	private Case[][] cases;
	/** Largeur de la grille en nombre de cases. */
	private int largeur;
	/** Hauteur de la grille en nombre de cases. */
	private int hauteur;
	/** Largeur de la grille en pixels. */
	private int largeurView;
	/** Hauteur de la grille en pixels. */
	private int hauteurView;

	/**
	 * Constructeur.
	 *
	 * @param largeur largeur de la grille en nombre de cases
	 * @param hauteur hauteur de la grille en nombre de cases
	 * @param x abscisse du coin haut gauche de la grille
	 * @param y ordonnée du coin haut gauche de la grille
	 * @param largeurCase largeur d'une case en pixels
	 * @param hauteurCase hauteur d'une case en pixels
	 */
	public GrilleView(int largeur, int hauteur, int x, int y, int largeurCase, int hauteurCase) {
		cases = new Case[hauteur][largeur];
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.largeurView = largeur*largeurCase;
		this.hauteurView = hauteur*hauteurCase;
		for (int i = 0; i<hauteur; i++) {
			for (int j = 0; j<largeur; j++) {
				int caseX = x+j*largeurCase;
				int caseY = y+i*hauteurCase;
				Case p = new Case(caseX, caseY, largeurCase, hauteurCase);
				cases[i][j] = p;
			}
		}
	}

	/**
	 * Accesseur.
	 *
	 * @return largeur de la grille en pixels
	 */
	public int getLargeurView() {
		return largeurView;
	}

	/**
	 * Accesseur.
	 *
	 * @return hauteur de la grille en pixels
	 */
	public int getHauteurView() {
		return hauteurView;
	}

	@Override
	public Case getElement(int i, int j) {
		return cases[i][j];
	}

	@Override
	public void setElementContent(Color c, int i, int j) {
		cases[i][j].setCouleur(c);
	}

	@Override
	public int getLargeur() {
		return largeur;
	}

	@Override
	public int getHauteur() {
		return hauteur;
	}

	@Override
	public Iterator<Case> iterator() {
		return new GrilleViewIterator();

	}
	
	/**
	 * Iterateur permettant de parcourir les cases de gauche à droite, puis du
	 * haut en bas.
	 *
	 * @author Ali Aboutarik
	 * @author Sviatoslav Dudik
	 */
	public class GrilleViewIterator implements Iterator<Case> {
		
		/** Première composante du parcours. */
		private int i;
		/** Deuxième composante du parcours. */
		private int j;
		
		public GrilleViewIterator() {
			i = 0;
			j = 0;
		}

		@Override
		public boolean hasNext() {
			return i<hauteur && j<largeur;
		}

		@Override
		public Case next() {
			Case p = cases[i][j];
			j++;
			if (j >= largeur) {
				j = 0;
				i++;
			}
			return p;
		}

	}

}
