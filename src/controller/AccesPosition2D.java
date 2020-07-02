package controller;

import model.Couple;
import model.Position;
import model.PositionLibreInexistanteException;

public interface AccesPosition2D<V> extends Acces2D<Position, V> {
	
	public Position getPositionLibreAleatoire() throws PositionLibreInexistanteException;
	
	public void libererPosition(int i, int j);
	
	public Couple<Integer,Integer> getCoordinates(int id);

}
