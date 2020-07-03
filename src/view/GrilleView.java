package view;

import java.awt.Color;
import java.util.Iterator;
import controller.Acces2D;

public class GrilleView implements Acces2D<Case,Color> {
	private Case[][] cases;
	private int largeur, hauteur;
	private int largeurView, hauteurView;

	
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

	public int getLargeurView() {
		return largeurView;
	}

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
	
	public class GrilleViewIterator implements Iterator<Case> {
		
		private int i, j;
		
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
