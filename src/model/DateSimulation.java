package model;

/**
 * Représentation d'une date pour une simulation.
 * Une date est composée de la saison et du jour dans cette saison.
 * Il y a 4 saisons au total et chaque saison compte 30 jours.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class DateSimulation {
	
	private Nombre saison;
	private Nombre jour;
	private static int jourParSaison = 30;
	private static String[] nomSaison = {"Hiver", "Printemps", "Été", "Automne"};
	
	/**
	 * Constructeur.
	 * Construit la date par défaut (premier jour de l'hiver).
	 */
	public DateSimulation() {
		saison = new Nombre(4);
		jour = new Nombre(jourParSaison, saison);
	}
	
	/**
	 * Incrémente la date.
	 * Incrémente le compteur de jours et si nécessaire le compteur de saisons.
	 */
	public void incrementer() {
		jour.incrementer();
	}
	
	/**
	 * Donne le nom de la saison en cours.
	 *
	 * @return nom de la saison en cours.
	 */
	public String getNomSaison() {
		return nomSaison[saison.getValeur()];
	}
	
	/**
	 * Convertit la date en chaine de caractères.
	 *
	 * @return la chaine de caractères représentant la date.
	 */
	@Override
	public String toString() {
		return nomSaison[saison.getValeur()] + " " + (jour.getValeur()+1);
	}

}
