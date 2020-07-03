package controller;
import java.awt.Color;

import model.Animal;
import model.EspeceAnimal;
import model.Position;
import model.Poule;

/**
 * Usine permettant de construire un animal de classe {@link Poule}.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class PouleFactory extends AnimalAbstractFactory {
	
	/**
	 * Constructeur.
	 * Construit une usine avec les paramètres par défaut
	 */
	public PouleFactory() {
		setCouleur(Color.YELLOW);
		setPdetection(0.10);
		setPreproduction(0.40);
		setBonusAtk(6);
		setAtk(15);
		setDef(23);
		setVitesseDeplacement(2);
		setEnduranceAlimentaire(20);
		setEsperanceVie(150);
	}

	@Override
	public Animal getAnimal(Position c) {
		Color col = getCouleur();
		double pdet = getPdetection();
		double prep = getPreproduction();
		int ba = getBonusAtk();
		int a = getAtk();
		int d = getDef();
		int vd = getVitesseDeplacement();
		int ea = getEnduranceAlimentaire();
		int ev = getEsperanceVie();
		Poule p =  new Poule(c, col, pdet, prep, ba, a, d, vd, ea, ev);
		p.addProie(EspeceAnimal.VIPERE);
		return p;
	}

}
