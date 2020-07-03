package model;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Représentation d'un animal.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public abstract class Animal {
	
	/** Position de l'animal. */
	protected Position pos;
	/** Couleur attribuée à l'animal */
	protected Color couleur;
	/** Liste d'espèces que cet animal peut manger */
	protected List<EspeceAnimal> proies;
	/** Probabilité de détection d'une proie. */
	protected double pdetection;
	/** Probabilité de reproduction. */
	protected double preproduction;
	/** Bonus d'attaque. */
	protected int bonusAtk;
	/** Capacité d'attaque. */
	protected int atk;
	/** Capacité de défense. */
	protected int def;
	/** Vitesse de déplacement. */
	protected int vitesseDeplacement;
	/** Endurance alimentaire. */
	protected int enduranceAlimentaire;
	/** Espérance de vie */
	protected int esperanceVie;
	/** Nombre de jours sans nourriture. */
	protected int faim;
	/** Age de l'animal (en jours). */
	protected int age;
	/** Nombre de jours depuis la mort. */
	protected int mort;
	/** Valeur indiquant si l'animal a été mangé. */
	protected boolean estMange;
	/** Générateur de nombres aléatoires */
	protected static Random r = new Random();
	
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
	public Animal(Position p, Color col, double pdet, double prep, int batk, int a, int d, int vd, int ea, int ev) {
		if (p.estLibre() == false)
			throw new IllegalArgumentException();
		pos = p;
		couleur = col;
		proies = new LinkedList<EspeceAnimal>();
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
	
	/**
	 * Détecter une proie.
	 *
	 * @param a proie
	 * @return true si l'animal a réussi de détecter la proie, false sinon
	 */
	public boolean detecterProie(Animal a) {
		double v = r.nextDouble();
		if (v <= pdetection) 
			return true;
		else
			return false;
	}
	
	/**
	 * Attaquer une proie
	 *
	 * @param a proie
	 * @return true si l'animal a réussi d'attaquer, false s'il a échoué
	 */
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

	/**
	 * Manger un animal.
	 * Remet à zéro le nombre de jours sans nourriture et marque l'animal comme
	 * étant mangé.
	 * @param a animal à manger
	 */
	public void manger(Animal a) {
		faim = 0;
		a.mort = 1;
		a.estMange = true;
	}
	
	/**
	 * Fuir d'un autre animal.
	 * L'animal se déplace à une position voisine aléatoire (s'il y en a une).
	 */
	public void fuir() {
		try {
			allerPositionVoisine();
		} catch (PositionLibreInexistanteException e) {}
	}

	/**
	 * Se déplacer en fonction de sa vitesse de déplacement.
	 * L'animal se déplace {@link vitesseDeplacement} fois à une position
	 * voisine aléatoire (s'il y en a une).
	 */
	public void seDeplacer() {
		try {
			for (int i = 0; i<vitesseDeplacement; i++)
				allerPositionVoisine();
		} catch (PositionLibreInexistanteException e) {}
	}
	
	/**
	 * Essayer de se reproduire.
	 * @return true si la reproduction réussi, false sinon
	 */
	public boolean seReproduire() {
		if (r.nextDouble() <= preproduction)
			return true;
		else
			return false;
	}

	/**
	 * Vérifie si l'animal est mort.
	 *
	 * @return true si l'animal est mort, false sinon.
	 */
	public boolean estMort() {
		return mort>0;
	}
	
	/**
	 * Indique le nombre de jour depuis la mort.
	 *
	 * @return le nombre de jour depuis la mort, ou 0 si l'animal est vivant
	 */
	public int joursDepuisMort() {
		return mort;
	}
	
	/**
	 * Vérifie si l'animal a été mangé par un autre animal.
	 *
	 * @return true si un autre animal l'a mangé, false sinon.
	 */
	public boolean estMange() {
		return estMange;
	}
	
	/**
	 * Accesseur.
	 *
	 * @return position de l'animal
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * Accesseur.
	 * @param pos nouvelle position de l'animal
	 */
	public void setPos(Position pos) {
		this.pos = pos;
	}

	/**
	 * Accesseur.
	 *
	 * @return couleur
	 */
	public Color getCouleur() {
		return couleur;
	}
	
	/**
	 * Accesseur.
	 *
	 * @return espèce de l'animal
	 */
	public abstract EspeceAnimal getEspece();
	
	/**
	 * Vérifie si l'animal peut manger une proie.
	 *
	 * @param a proie
	 * @return true s'il peut la manger, false sinon
	 */
	public boolean peutManger(Animal a) {
		return proies.contains(a.getEspece());
	}
	
	/**
	 * Ajoute un espèce à la liste de proies.
	 *
	 * @param espece espèce à ajouter
	 */
	public void addProie(EspeceAnimal espece) {
		proies.add(espece);
	}

	/**
	 * Incrémente le nombre de jours sans nourriture.
	 * Si ce nombre dépasse l'endurance alimentaire, alors marque l'animal comme
	 * mort.
	 */
	public void incrementerFaim() {
		faim++;
		if (!estMort() && faim>=enduranceAlimentaire)
			mort++;
	}
	
	/**
	 * Incrémente l'age.
	 * Si son age dépasse l'espérance de vie, alors marque l'animal comme mort.
	 * Si l'animal est déjà mort, alors incrémente le nombre de jours depuis la
	 * mort.
	 */
	public void incrementerAge() {
		age++;
		if (estMort() || age>=esperanceVie)
			mort++;
	}
	
	/**
	 * Déplace animal à une position voisine (s'il en existe une).
	 *
	 * @throws PositionLibreInexistanteException s'il n'y a pas de position
	 * voisine libre
	 */
	private void allerPositionVoisine() throws PositionLibreInexistanteException {
		Position p = getPos();
		Position newP = p.getPositionLibreAleatoire();
		p.liberer();
		newP.setAnimal(this);
		setPos(newP);
	}

}
