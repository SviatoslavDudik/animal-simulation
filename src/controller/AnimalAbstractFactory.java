package controller;
import java.awt.Color;

import model.Animal;
import model.EspeceAnimal;
import model.Position;

/**
 * Usine abstraite des animaux.
 * Créer les animaux avec la fonction {@link AnimalAbstractFactory#getAnimal}.
 * Permet de configurer l'animal avant sa création.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public abstract class AnimalAbstractFactory {
	
	private static final RenardFactory RENARD_FACTORY = new RenardFactory();
	private static final PouleFactory POULE_FACTORY = new PouleFactory();
	private static final VipereFactory VIPERE_FACTORY = new VipereFactory();

	private Color couleur;
	private double pdetection;
	private double preproduction;
	private int bonusAtk;
	private int atk;
	private int def;
	private int enduranceAlimentaire;
	private int esperanceVie;
	private int vitesseDeplacement;

	/**
	 * Renvoie l'usine correspondante à l'espèce spécifiée en paramètre.
	 *
	 * @param type espèce d'animal
	 * @return usine de l'espèce
	 */
	public static AnimalAbstractFactory getFactory(EspeceAnimal type) {
		if (type.equals(EspeceAnimal.RENARD))
			return RENARD_FACTORY;
		else if (type.equals(EspeceAnimal.POULE))
			return POULE_FACTORY;
		else if (type.equals(EspeceAnimal.VIPERE))
			return VIPERE_FACTORY;
		else 
			return null;
	}
	
	/**
	 * Crée un animal avec les attributs choisis auparavant.
	 *
	 * @param c position où l'animal créé doit être placé
	 * @return animal créé
	 */
	public abstract Animal getAnimal(Position c);

	/**
	 * Accesseur.
	 *
	 * @return couleur choisie pour l'animal
	 */
	public Color getCouleur() {
		return couleur;
	}

	/**
	 * Accesseur.
	 *
	 * @param couleur nouveau couleur de l'animal
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	/**
	 * Accesseur.
	 *
	 * @return probabilité de détection choisie
	 */
	public double getPdetection() {
		return pdetection;
	}

	/**
	 * Accesseur.
	 *
	 * @param pdetection nouvelle probabilité de détection
	 */
	public void setPdetection(double pdetection) {
		this.pdetection = pdetection;
	}

	/**
	 * Accesseur.
	 *
	 * @return probabilité de reproduction choisie
	 */
	public double getPreproduction() {
		return preproduction;
	}

	/**
	 * Accesseur.
	 *
	 * @param preproduction nouvelle probabilité de reproduction
	 */
	public void setPreproduction(double preproduction) {
		this.preproduction = preproduction;
	}

	/**
	 * Accesseur.
	 *
	 * @return bonus d'attaque choisi pour l'animal
	 */
	public int getBonusAtk() {
		return bonusAtk;
	}

	/**
	 * Accesseur.
	 *
	 * @param bonusAtk nouveau bonus d'attaque
	 */
	public void setBonusAtk(int bonusAtk) {
		this.bonusAtk = bonusAtk;
	}

	/**
	 * Accesseur.
	 *
	 * @return attaque choisie
	 */
	public int getAtk() {
		return atk;
	}

	/**
	 * Accesseur.
	 *
	 * @param atk nouvelle attaque de l'animal
	 */
	public void setAtk(int atk) {
		this.atk = atk;
	}

	/**
	 * Accesseur.
	 *
	 * @return capacité de défense choisie pour l'animal
	 */
	public int getDef() {
		return def;
	}

	/**
	 * Accesseur.
	 *
	 * @param def nouvelle capacité de défense
	 */
	public void setDef(int def) {
		this.def = def;
	}

	/**
	 * Accesseur.
	 *
	 * @return endurance alimentaire de l'animal
	 */
	public int getEnduranceAlimentaire() {
		return enduranceAlimentaire;
	}

	/**
	 * Accesseur.
	 *
	 * @param enduranceAlimentaire nouvelle endurance alimentaire de l'animal
	 */
	public void setEnduranceAlimentaire(int enduranceAlimentaire) {
		this.enduranceAlimentaire = enduranceAlimentaire;
	}

	/**
	 * Accesseur.
	 *
	 * @return espérance de vie choisie pour l'animal
	 */
	public int getEsperanceVie() {
		return esperanceVie;
	}

	/**
	 * Accesseur.
	 *
	 * @param esperanceVie nouvelle espérance de vie
	 */
	public void setEsperanceVie(int esperanceVie) {
		this.esperanceVie = esperanceVie;
	}

	/**
	 * Accesseur.
	 *
	 * @return vitesse de déplacement choisie
	 */
	public int getVitesseDeplacement() {
		return vitesseDeplacement;
	}

	/**
	 * Accesseur.
	 *
	 * @param vitesseDeplacement nouvelle vitesse de déplacement
	 */
	public void setVitesseDeplacement(int vitesseDeplacement) {
		this.vitesseDeplacement = vitesseDeplacement;
	}

}
