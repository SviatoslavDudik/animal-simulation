package controller;
import java.awt.Color;

import model.Animal;
import model.Position;
import model.Renard;

public class RenardFactory extends AnimalAbstractFactory {

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

		return new Renard(c, col, pdet, prep, ba, a, d, vd, ea, ev);
	}

}
