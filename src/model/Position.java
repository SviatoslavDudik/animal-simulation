package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Représentation d'un emplacement pour un animal.
 * Une position peut contenir un animal. Elle possède aussi des positions
 * voisines.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class Position {

	private List<Position> voisines;
	private Animal a;
	private int id;
	private static Random random = new Random();
	
	/**
	 * Constructeur.
	 * Crée une position non occupée et sans voisins.
	 *
	 * @param id identificateur de la position.
	 */
	public Position(int id) {
		this.id = id;
		a = null;
		voisines = new ArrayList<Position>();
	}
	
	/**
	 * Ajoute une position à la liste des voisins.
	 *
	 * @param c position voisine qu'il faut ajouter.
	 */
	public void addPositionVoisine(Position c) {
		if (voisines.contains(c))
			throw new IllegalArgumentException();
		voisines.add(c);
	}
	
	/**
	 * Accesseur.
	 *
	 * @return identificateur
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Accesseur.
	 *
	 * @return animal qui occupe cette position
	 */
	public Animal getAnimal() {
		return a;
	}
	
	/**
	 * Accesseur.
	 *
	 * @param c animal qui doit se trouver à cette position.
	 */
	void setAnimal(Animal c) {
		this.a = c;
	}
	
	/**
	 * Libère la position.
	 * Après cette méthode la position est libre.
	 */
	void liberer() {
		setAnimal(null);
	}
	
	/**
	 * Accesseur.
	 *
	 * @return liste de toutes les positions voisines
	 */
	public List<Position> getPositionVoisines() {
		List<Position> copy = new ArrayList<Position>(voisines.size());
		copy.addAll(voisines);
		return copy;
	}

	/**
	 * Vérifie si la position est libre.
	 *
	 * @return true si la position est libre, false sinon.
	 */
	public boolean estLibre() {
		return getAnimal() == null;
	}
	
	/**
	 * Obtenir toutes les positions voisines libres.
	 *
	 * @return liste de positions voisines libres
	 */
	private List<Position> getPositionLibres() {
		List<Position> l = new ArrayList<Position>();
		for (Position p: voisines) {
			if (p.estLibre()) {
				l.add(p);
			}
		}
		return l;
	}

	/**
	 * Obtenir une position voisine libre.
	 *
	 * @return une position libre
	 * @throws PositionLibreInexistanteException s'il n'y a pas de position
	 * libre à côté de cette position
	 */
	public Position getPositionLibreAleatoire() throws PositionLibreInexistanteException {
		List<Position> libres = getPositionLibres();
		if (libres.size() == 0) {
			throw new PositionLibreInexistanteException();
		}
		int i = random.nextInt(libres.size());
		return libres.get(i);
	}

}
