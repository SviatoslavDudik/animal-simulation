package model;
import java.awt.Color;
import java.util.Random;

public abstract class Animal {
	
	protected Position pos;
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
	protected int mort;
	protected boolean estMange;
	protected static Random r = new Random();
	
	public Animal(Position c, Color col, double pdet, double prep, int batk, int a, int d, int vd, int ea, int ev) {
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
		mort = 0;
		estMange = false;
	}
	
	public boolean detecterProie(Animal a) {
		double v = r.nextDouble();
		if (v <= pdetection) 
			return true;
		else
			return false;
	}
	
	public boolean attaquer(Animal a) {
		if (a.estMort())
			return true;
		int bAtk = r.nextInt(bonusAtk+1);
		int atk = this.atk + bAtk;
		if (atk > a.def) {
			return true;
		} else {
			return false;
		}
	}

	public void manger(Animal a) {
		faim = 0;
		a.mort = 1;
		a.estMange = true;
	}
	
	public void fuir() {
		try {
			allerPositionVoisine();
		} catch (PositionLibreInexistanteException e) {}
	}

	public void seDeplacer() {
		try {
			for (int i = 0; i<vitesseDeplacement; i++)
				allerPositionVoisine();
		} catch (PositionLibreInexistanteException e) {}
	}
	
	public boolean seReproduire() {
		if (r.nextDouble() <= preproduction)
			return true;
		else
			return false;
	}

	public boolean estMort() {
		return mort>0;
	}
	
	public int joursDepuisMort() {
		return mort;
	}
	
	public boolean estMange() {
		return estMange;
	}
	
	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public Color getCouleur() {
		return couleur;
	}
	
	public abstract EspeceAnimal getEspece();

	public void incrementerFaim() {
		faim++;
		if (!estMort() && faim>=enduranceAlimentaire)
			mort++;
	}
	
	public void incrementerAge() {
		age++;
		if (estMort() || age>=esperanceVie)
			mort++;
	}
	
	private void allerPositionVoisine() throws PositionLibreInexistanteException {
		Position p = getPos();
		Position newP = p.getPositionLibreAleatoire();
		p.liberer();
		newP.setAnimal(this);
		setPos(newP);
	}

}
