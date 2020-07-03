package model;

/**
 * Class pour créer une couple de deux éléments.
 */
public class Couple<T,V> {
	/**
	 * Premier élément.
	 */
	public T a;
	/**
	 * Deuxième élément.
	 */
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
