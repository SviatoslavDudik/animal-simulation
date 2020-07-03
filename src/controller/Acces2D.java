package controller;

/**
 * Interface pour accéder aux éléments d'une collection à 2 dimensions.
 * La classe réalisant cette interface pourra donc donner accès aux informations
 * qu'elle stocke dans une collection à 2 dimensions sans permettre de modifier
 * les éléments.
 *
 * @param <T> type d'éléments de la collection
 * @param <V> type d'information stockée dans la collection
 */
public interface Acces2D<T,V> extends Iterable<T> {
	
	/**
	 * Renvoie l'élément occupant une position particulière.
	 *
	 * @param i première composante de la position
	 * @param j deuxième composante de la position
	 * @return élément à la position (i,j)
	 */
	public T getElement(int i, int j);
	
	/**
	 * Met une information dans la collection à une position particulière.
	 *
	 * @param v information à sauvegarder
	 * @param i première composante de la position
	 * @param j deuxième composante de la position
	 */
	public void setElementContent(V v, int i, int j);
	
	/**
	 * Renvoie la largeur de la collection.
	 * C'est-à-dire, la valeur que la première composante ne peut pas dépasser.
	 * @return largeur de la collection
	 */
	public int getLargeur();

	/**
	 * Renvoie la hauteur de la collection.
	 * C'est-à-dire, la valeur que la deuxième composante ne peut pas dépasser.
	 * @return hauteur de la collection
	 */
	public int getHauteur();

}
