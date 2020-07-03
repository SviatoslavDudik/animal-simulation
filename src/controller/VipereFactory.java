package controller;
import java.awt.Color;

import model.Animal;
import model.EspeceAnimal;
import model.Position;
import model.Vipere;

/**
 * Usine permettant de construire un animal de classe {@link Vipere}.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class VipereFactory extends AnimalAbstractFactory {

	/**
	 * Constructeur.
	 * Construit une usine avec les paramètres par défaut
	 */
	public VipereFactory() {
		setCouleur(Color.GREEN);
		setPdetection(0.50);
		setPreproduction(0.50);
		setBonusAtk(8);
		setAtk(5);
		setDef(17);
		setVitesseDeplacement(1);
		setEnduranceAlimentaire(35);
		setEsperanceVie(120);
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
		Vipere v = new Vipere(c, col, pdet, prep, ba, a, d, vd, ea, ev);
		v.addProie(EspeceAnimal.RENARD);
		return v;
		
	}

}
