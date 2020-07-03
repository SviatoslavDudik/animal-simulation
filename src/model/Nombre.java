package model;

/**
 * Représentation d'un nombre ayant une valeur max.
 * Ce nombre peut être relié à un autre nombre. Dans ce cas ci, si ce nombre est
 * remis à zéro, alors il incrémente le nombre auquel il est relié.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class Nombre {
	
	/** Valeur du nombre. */
	private int valeur;
	/** Valeur max du nombre */
	private int limite;
	/** Nombre auquel ce nombre est relié. */
	private Nombre suivant;
	
	/**
	 * Constructeur.
	 * Le nombre a la valuer initiale de 0 et il n'est relié à aucun autre
	 * nombre.
	 * La valeur max doit être strictement positive.
	 *
	 * @param limite valeur max du nombre (qui ne sera jamais atteinte)
	 */
	public Nombre(int limite) {
		this(0, limite);
	}
	
	/**
	 * Constructeur.
	 * Le nombre n'est relié à aucun autre nombre.
	 * La valeur initiale doit être positive.
	 * La valeur max doit être strictement positive.
	 *
	 * @param valeur valeur initiale du nombre
	 * @param limite valeur max du nombre (qui ne sera jamais atteinte)
	 */
	public Nombre(int valeur, int limite) {
		this(valeur, limite, null);
	}

	/**
	 * Constructeur.
	 * Le nombre a la valeur initiale de 0.
	 * La valeur max doit être strictement positive.
	 *
	 * @param limite valeur max du nombre (qui ne sera jamais atteinte)
	 * @param suivant nombre auquel il doit être relié
	 */
	public Nombre(int limite, Nombre suivant) {
		this(0, limite, suivant);
	}
	
	/**
	 * Constructeur.
	 * La valeur initiale doit être positive.
	 * La valeur max doit être strictement positive.
	 *
	 * @param valeur valeur initiale du nombre
	 * @param limite valeur max du nombre (qui ne sera jamais atteinte)
	 * @param suivant nombre auquel il doit être relié
	 */
	public Nombre(int valeur, int limite, Nombre suivant) {
		if (valeur < 0 || limite <= 0 || valeur >= limite)
			throw new IllegalArgumentException();
		this.valeur = valeur;
		this.limite = limite;
		this.suivant = suivant;
	}

	/**
	 * Accesseur.
	 *
	 * @return valeur du nombre
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * Accesseur.
	 *
	 * @return la valeur max du nombre
	 */
	public int getLimite() {
		return limite;
	}
	
	/**
	 * Incrémente le nombre.
	 * Cette méthode le nombre remet à zéro et incrémente le nombre suivant si
	 * nécessaire.
	 */
	public void incrementer() {
		valeur++;
		if (valeur >= limite) {
			valeur = 0;
			if (suivant != null)
				suivant.incrementer();
		}
	}
	
}
