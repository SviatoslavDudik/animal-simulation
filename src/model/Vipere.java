package model;
import java.awt.Color;

/**
 * Classe représentant une vipère.
 */
public class Vipere extends Animal {

	/**
	 * Constructeur.
	 *
	 * @param p position
	 * @param col couleur
	 * @param pdet probabilité de détection
	 * @param prep probabilité de reproduction
	 * @param batk bonus d'attaque
	 * @param a capacité d'attaque
	 * @param d capacité de défense
	 * @param vd vitesse de déplacement
	 * @param ea endurance alimentaire (en jours)
	 * @param ev espérance de vie (en jours)
	 */
	public Vipere(Position c, Color col, double pdet, double prep, int batk, int a, int d, int vd, int ea, int ev) {
		super(c, col, pdet, prep, batk, a, d, vd, ea, ev);
	}

	@Override
	public EspeceAnimal getEspece() {
		return EspeceAnimal.VIPERE;
	}


}
