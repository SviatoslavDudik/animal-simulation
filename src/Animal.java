import java.awt.Color;
import java.util.Random;

public abstract class Animal {
	
	protected Case pos;
	protected Color couleur;
	protected double pdetection;
	protected double preproduction;
	protected int bonusAtk;
	protected int atk;
	protected int def;
	protected int vitesseDeplacement;
	protected int enduranceAlimentaire;
	protected int esperanceVie;
	protected int faim;
	protected int age;
	protected boolean mort;
	protected static Random r = new Random();
	
	public Animal(Case c, Color col, double pdet, double prep, int batk, int a, int d, int vd, int ea, int ev) {
		pos = c;
		couleur = col;
		pdetection = pdet;
		preproduction = prep;
		bonusAtk = batk;
		atk = a;
		def = d;
		vitesseDeplacement = vd;
		enduranceAlimentaire = ea;
		esperanceVie = ev;
		faim = 0;
		age = 0;
		mort = false;
	}
	
	public boolean detecterProie() {
		double v = r.nextDouble();
		if (v <= pdetection) 
			return true;
		else
			return false;
	}
	
	public boolean attaquer(Animal a) {
		int bAtk = r.nextInt(bonusAtk+1);
		int atk = this.atk + bAtk;
		if (atk > a.def)
			return true;
		else 
			return false;
	}

	public void manger() {
		faim = 0;
	}

	public boolean estMort() {
		return mort;
	}
	
	public Case getPos() {
		return pos;
	}

	public void setPos(Case pos) {
		this.pos = pos;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void incrementerFaim() {
		faim++;
		if (faim>=enduranceAlimentaire)
			mort = true;
	}
	
	public void incrementerAge() {
		age++;
		if (age>=esperanceVie)
			mort = true;
	}

}
