package controller;

import model.Couple;
import model.Position;
import model.PositionLibreInexistanteException;

/**
 * Interface permettant accès à une collection à 2 dimensions contenant des
 * Positions {@link Position}
 *
 * @param <V> type d'information stockée dans une position
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public interface AccesPosition2D<V> extends Acces2D<Position, V> {
	
	/**
	 * Renvoie une position libre aléatoire.
	 *
	 * @return position libre
	 * @throws PositionLibreInexistanteException il n'y a aucune position libre
	 */
	public Position getPositionLibreAleatoire() throws PositionLibreInexistanteException;
	
	/**
	 * Libère une position particulière.
	 * Autrement dit efface la valeur stockée dans une position.
	 *
	 * @param i première composante de la position
	 * @param j deuxième composante de la position
	 */
	public void libererPosition(int i, int j);
	
	/**
	 * Calcule l'identificateur en fonction des coordonnées.
	 * Cet identificateur peut être utilisé pour retrouver les coordonnées.
	 *
	 * @param i première composante des coordonnées
	 * @param j deuxième composante des coordonnées
	 * @return identificateur
	 * @see getCoordinates
	 */
	public int getId(int i, int j);

	/**
	 * Renvoie les coordonnées à partir d'un identificateur.
	 *
	 * @param id identificateur d'une position
	 * @return coordonnées de la position
	 */
	public Couple<Integer,Integer> getCoordinates(int id);

}
