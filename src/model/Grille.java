package model;
import java.util.Iterator;
import java.util.Random;
import controller.AccesPosition2D;

/**
 * Grille rectangulaire composée des positions.
 * Chaque position a 8 voisins autour de lui.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class Grille implements AccesPosition2D<Animal> {
	
	/** Tableau de positions de la grille. */
	private Position[][] positions;
	/** Largeur de la grille en nombre de cases. */
	private int largeur;
	/** Hauteur de la grille en nombre de cases. */
	private int hauteur;
	/** Nombre de cases libres. */
	private int nbLibres;
	/** Générateur de nombres aléatoires. */
	private Random r;
	
	/**
	 * Constructeur.
	 *
	 * @param largeur largeur en nombre de cases
	 * @param hauteur hauteur en nombre de cases
	 */
	public Grille(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		r = new Random();
		nbLibres = largeur*hauteur;
		positions = new Position[hauteur][largeur];
		for (int i = 0; i<hauteur; i++) {
			for (int j = 0; j<largeur; j++) {
				Position p = new Position(getId(i,j));
				positions[i][j] = p;
			}
		}
		initialiserVoisins();
	}
	
	/**
	 * Initialisation des voisins.
	 * Ajoute à chaque position la liste de ses voisins.
	 */
	private void initialiserVoisins() {
		int i, j;
		Position p;
		for (i = 0; i<hauteur; i++) {
			for (j = 0; j<largeur; j++) {
				p = positions[i][j];
				if (i>0 && j>0) 
					p.addPositionVoisine(positions[i-1][j-1]);
				if (i>0)
					p.addPositionVoisine(positions[i-1][j]);
				if (i>0 && j<largeur-1)
					p.addPositionVoisine(positions[i-1][j+1]);
				if (j>0)
					p.addPositionVoisine(positions[i][j-1]);
				if (j<largeur-1)
					p.addPositionVoisine(positions[i][j+1]);
				if (i<hauteur-1 && j>0)
					p.addPositionVoisine(positions[i+1][j-1]);
				if (i<hauteur-1)
					p.addPositionVoisine(positions[i+1][j]);
				if (i<hauteur-1 && j<largeur-1)
					p.addPositionVoisine(positions[i+1][j+1]);
			}
		}
	}
	
	@Override
	public int getId(int i, int j) {
		return i*largeur+j;
	}
	
	@Override
	public Couple<Integer,Integer> getCoordinates(int id) {
		int i = id/largeur;
		int j = id%largeur;
		return new Couple<Integer,Integer>(i,j);
	}

	@Override
	public Position getElement(int i, int j) {
		return positions[i][j];
	}


	@Override
	public void setElementContent(Animal a, int i, int j) {
		if (a == null) 
			throw new NullPointerException();
		if (positions[i][j].estLibre())
			nbLibres--;
		positions[i][j].setAnimal(a);
	}


	@Override
	public Position getPositionLibreAleatoire() throws PositionLibreInexistanteException {
		Position p = null;
		if (nbLibres == 0)
			throw new PositionLibreInexistanteException();
		int n = r.nextInt(nbLibres);
		for (int i = 0; i<hauteur; i++) {
			for (int j = 0; j<largeur; j++) {
				p = positions[i][j];
				if (p.estLibre()) {
					if (n == 0) 
						return p;
					n--;
				}
			}
		}
		return null;
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
	public void libererPosition(int i, int j) {
		if (positions[i][j].estLibre() == false)
			nbLibres++;
		positions[i][j].liberer();
	}

	@Override
	public Iterator<Position> iterator() {
		return new GrilleIterator();

	}
	
	/**
	 * Itérateur permettant de  parcourir les positions de gauche à droite, puis
	 * de haut en bas.
	 *
	 * @author Ali Aboutarik
	 * @author Sviatoslav Dudik
	 */
	public class GrilleIterator implements Iterator<Position> {
		
		/** Première composante du parcours. */
		private int i;
		/** Deuxième composante du parcours. */
		private int j;
		
		public GrilleIterator() {
			i = 0;
			j = 0;
		}

		@Override
		public boolean hasNext() {
			return i<hauteur && j<largeur;
		}

		@Override
		public Position next() {
			Position p = positions[i][j];
			j++;
			if (j >= largeur) {
				j = 0;
				i++;
			}
			return p;
		}
		
	}

}
