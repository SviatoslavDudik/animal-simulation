package model;

/**
 * Couple de deux éléments.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class Couple<T,V> {
	/** Premier élément. */
	public T a;
	/** Deuxième élément. */
	public V b;
	
	/**
	 * Constructeur.
	 * 
	 * @param a premier élément
	 * @param b deuxième élément
	 */
	public Couple(T a, V b) {
		this.a = a;
		this.b = b;
	}

}
