package controller;
import java.awt.Color;

import model.Animal;
import model.EspeceAnimal;
import model.Position;

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
	
	public abstract Animal getAnimal(Position c);

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public double getPdetection() {
		return pdetection;
	}

	public void setPdetection(double pdetection) {
		this.pdetection = pdetection;
	}

	public double getPreproduction() {
		return preproduction;
	}

	public void setPreproduction(double preproduction) {
		this.preproduction = preproduction;
	}

	public int getBonusAtk() {
		return bonusAtk;
	}

	public void setBonusAtk(int bonusAtk) {
		this.bonusAtk = bonusAtk;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getEnduranceAlimentaire() {
		return enduranceAlimentaire;
	}

	public void setEnduranceAlimentaire(int enduranceAlimentaire) {
		this.enduranceAlimentaire = enduranceAlimentaire;
	}

	public int getEsperanceVie() {
		return esperanceVie;
	}

	public void setEsperanceVie(int esperanceVie) {
		this.esperanceVie = esperanceVie;
	}

	public int getVitesseDeplacement() {
		return vitesseDeplacement;
	}

	public void setVitesseDeplacement(int vitesseDeplacement) {
		this.vitesseDeplacement = vitesseDeplacement;
	}
	
	

}
