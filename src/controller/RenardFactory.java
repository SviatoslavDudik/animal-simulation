package controller;
import java.awt.Color;

import model.Animal;
import model.EspeceAnimal;
import model.Position;
import model.Renard;

/**
 * Usine permettant de construire un animal de classe {@link Renard}.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class RenardFactory extends AnimalAbstractFactory {

	/**
	 * Constructeur.
	 * Construit une usine avec les paramètres par défaut
	 */
	public RenardFactory() {
		setCouleur(Color.ORANGE);
		setPdetection(0.20);
		setPreproduction(0.14);
		setBonusAtk(12);
		setAtk(20);
		setDef(7);
		setVitesseDeplacement(3);
		setEnduranceAlimentaire(8);
		setEsperanceVie(250);
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
		Renard r = new Renard(c, col, pdet, prep, ba, a, d, vd, ea, ev);
		r.addProie(EspeceAnimal.POULE);
		return r;
	}

}
