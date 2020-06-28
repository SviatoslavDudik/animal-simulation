import java.awt.Color;

public class VipereFactory extends AnimalAbstractFactory {
	
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
	public Animal getCreatue(Case c) {
		Color col = getCouleur();
		double pdet = getPdetection();
		double prep = getPreproduction();
		int ba = getBonusAtk();
		int a = getAtk();
		int d = getDef();
		int vd = getVitesseDeplacement();
		int ea = getEnduranceAlimentaire();
		int ev = getEsperanceVie();

		return new Vipere(c, col, pdet, prep, ba, a, d, vd, ea, ev);
	}

}
