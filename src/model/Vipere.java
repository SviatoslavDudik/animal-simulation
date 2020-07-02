package model;
import java.awt.Color;

public class Vipere extends Animal {

	public Vipere(Position c, Color col, double pdet, double prep, int batk, int a, int d, int vd, int ea, int ev) {
		super(c, col, pdet, prep, batk, a, d, vd, ea, ev);
	}

	@Override
	public EspeceAnimal getEspece() {
		return EspeceAnimal.VIPERE;
	}


}
