package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Position {

	private List<Position> voisines;
	private Animal a;
	private int id;
	private static Random random = new Random();
	
	public Position(int id) {
		this.id = id;
		a = null;
		voisines = new ArrayList<Position>();
	}
	
	public void addPositionVoisine(Position c) {
		voisines.add(c);
	}
	
	public int getId() {
		return id;
	}
	
	public Animal getAnimal() {
		return a;
	}
	
	void setAnimal(Animal c) {
		this.a = c;
	}
	
	void liberer() {
		setAnimal(null);
	}
	
	public List<Position> getPositionVoisines() {
		List<Position> copy = new ArrayList<Position>(voisines.size());
		copy.addAll(voisines);
		return copy;
	}

	public boolean estLibre() {
		return getAnimal() == null;
	}
	
	private List<Position> getPositionLibres() {
		List<Position> l = new ArrayList<Position>();
		for (Position p: voisines) {
			if (p.estLibre()) {
				l.add(p);
			}
		}
		return l;
	}

	public Position getPositionLibreAleatoire() throws PositionLibreInexistanteException {
		List<Position> libres = getPositionLibres();
		if (libres.size() == 0) {
			throw new PositionLibreInexistanteException();
		}
		int i = random.nextInt(libres.size());
		return libres.get(i);
	}

}
