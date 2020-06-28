import java.util.Iterator;

public class Grille implements Iterable<Case> {
	
	private Case[][] cases;
	private int largeur, hauteur;
	
	public Grille(int largeur, int hauteur, int x, int y, int largeurPx, int hauteurPx) {
		cases = new Case[hauteur][largeur];
		this.largeur = largeur;
		this.hauteur = hauteur;
		int largeurCase = largeurPx/largeur;
		int hauteurCase = hauteurPx/hauteur;
		for (int i = 0; i<hauteur; i++) {
			for (int j = 0; j<largeur; j++) {
				int caseX = x+j*largeurCase;
				int caseY = y+i*hauteurCase;
				Case c = new Case(caseX, caseY, largeurCase, hauteurCase);
				cases[i][j] = c;
			}
		}
		initialiserVoisins();
	}
	
	private void initialiserVoisins() {
		int i,j;
		Case c;
		for (i = 1; i<hauteur-1; i++) {
			for (j = 1; j<largeur-1; j++) {
				c = cases[i][j];
				if (i>0 && j>0) 
					c.addCaseVoisine(cases[i-1][j-1]);
				if (i>0)
					c.addCaseVoisine(cases[i-1][j]);
				if (i>0 && j<largeur-1)
					c.addCaseVoisine(cases[i-1][j+1]);
				if (j>0)
					c.addCaseVoisine(cases[i][j-1]);
				if (j<largeur-1)
					c.addCaseVoisine(cases[i][j+1]);
				if (i<hauteur-1 && j>0)
					c.addCaseVoisine(cases[i+1][j-1]);
				if (i<hauteur-1)
					c.addCaseVoisine(cases[i+1][j]);
				if (i<hauteur-1 && j<largeur-1)
					c.addCaseVoisine(cases[i+1][j+1]);
			}
		}
	}

	@Override
	public Iterator<Case> iterator() {
		return new GrilleIterator();

	}
	
	public class GrilleIterator implements Iterator<Case> {
		
		private int i, j;
		
		public GrilleIterator() {
			i = 0;
			j = 0;
		}

		@Override
		public boolean hasNext() {
			return i<hauteur && j<largeur;
		}

		@Override
		public Case next() {
			Case c = cases[i][j];
			j++;
			if (j >= largeur) {
				j = 0;
				i++;
			}
			return c;
		}
		
	}

}
