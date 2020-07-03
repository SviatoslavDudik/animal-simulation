package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Animal;
import model.Couple;
import model.DateSimulation;
import model.EspeceAnimal;
import model.Grille;
import model.Position;
import model.PositionLibreInexistanteException;
import view.AffichageCercle;
import view.AffichageRectangle;
import view.Case;
import view.ChampGraphique;
import view.GrilleView;
import view.SimulationView;

/**
 * Contrôleur de la simulation.
 * Cette classe sert à initialiser et à exécuter la simulation.
 * Elle contient également la méthode main.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class Simulation implements Runnable {

	private AccesPosition2D<Animal> positions;
	private DateSimulation date;
	private int largeur, hauteur;
	private Acces2D<Case, Color> cases;
	private ChampGraphique cont;
	private SimulationView simulationView;
	private JLabel saison, error;
	private JButton start;
	private JTextField fieldRenard, fieldPoule, fieldVipere;
	private boolean enCours;
	/**
	 * Liste de tous les animaux vivants.
	 */
	private List<Animal> animaux;
	/**
	 * Liste des animaux qui doivent être supprimés avant le prochain tour.
	 */
	private List<Animal> aSupprimer;
	/**
	 * Liste d'animaux qui doivent sauter ce tour.
	 */
	private List<Animal> tourInterdit;
	/**
	 * Liste d'espèces d'animaux qu'il faut créer avant le prochain tour.
	 */
	private List<EspeceAnimal> aAjouter;
	/**
	 * Thread qui exécute la simulation.
	 */
	private Thread t;
	/**
	 * Objet utilisé pour la synchronisation entre les threads
	 */
	private final Object obj = new Object();

	/**
	 * Le point d'entrée du programme.
	 * Crée une instance de {@link Simulation} et lance la simulation.
	 *
	 * @param args les arguments du programme (ne sont pas utilisés)
	 */
	public static void main(String[] args) {
		Simulation sim = new Simulation(60, 40);
		sim.lancer();
	}

	/**
	 * Constructeur.
	 * Construit le contrôleur d'une simulation.
	 *
	 * @param largeur largeur en nombre de cases
	 * @param hauteur hauteur en nombre de cases
	 */
	public Simulation(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		t = new Thread(this);
		cases = new GrilleView(largeur, hauteur, 0, 0, 10, 10);
		enCours = false;
		aAjouter = new ArrayList<EspeceAnimal>();
		aSupprimer = new ArrayList<Animal>();
		tourInterdit = new ArrayList<Animal>();
	}

	/**
	 * Initialise le modèle en prenant les valeurs données par l'utilisateur.
	 * La méthode crée les animaux en quantités données par l'utilisateur et
	 * refait la grille des animaux.
	 *
	 * @throws PositionLibreInexistanteException s'il y a moins de positions que
	 * des animaux.
	 */
	private void initialisationModele() throws PositionLibreInexistanteException {
		animaux = new ArrayList<Animal>();
		positions = new Grille(largeur, hauteur);
		date = new DateSimulation();
		int n;
		n = Integer.parseUnsignedInt(fieldPoule.getText());
		for (int i = 0; i < n; i++)
			addAnimal(EspeceAnimal.POULE);
		n = Integer.parseUnsignedInt(fieldRenard.getText());
		for (int i = 0; i < n; i++)
			addAnimal(EspeceAnimal.RENARD);
		n = Integer.parseUnsignedInt(fieldVipere.getText());
		for (int i = 0; i < n; i++)
			addAnimal(EspeceAnimal.VIPERE);
		error.setText("");
	}

	/**
	 * Initialise la fenêtre graphique.
	 * La fonction crée la fenêtre et y ajoute tous les éléments graphiques.
	 */
	private void initialisationView() {
		cont = new ChampGraphique(largeur * 10 + 50, 2 * hauteur * 10 + 50);
		simulationView = new SimulationView(cases, new AffichageRectangle());
		simulationView.setPreferredSize(new Dimension(largeur * 10 + 2, hauteur * 10 + 2));
		cont.addCenter(simulationView);

		fieldPoule = new JTextField(3);
		fieldRenard = new JTextField(3);
		fieldVipere = new JTextField(3);
		cont.addTop(new JLabel("Poules:"));
		cont.addTop(fieldPoule);
		cont.addTop(new JLabel("Renards:"));
		cont.addTop(fieldRenard);
		cont.addTop(new JLabel("Viperes:"));
		cont.addTop(fieldVipere);
		start = new JButton("Lancer");
		cont.addTop(start);
		JButton strategy = new JButton("Changer affichage");
		strategy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch (simulationView.getAffichage()) {
				case CERCLE:
					simulationView.setAffichage(new AffichageRectangle());
					break;
				case RECTANGLE:
					simulationView.setAffichage(new AffichageCercle());
					break;
				}
				cont.repaint();
			}
		});
		cont.addTop(strategy);

		saison = new JLabel();
		error = new JLabel();
		cont.addBottom(new JLabel("Saison:"));
		cont.addBottom(saison);
		cont.addBottom(error);

		cont.showWindow();
	}

	/**
	 * Initialise la simulation.
	 * Cette méthode crée l'interface graphique et ajoute un listener pour
	 * que l'utilisateur puisse démarrer la simulation.
	 */
	public void lancer() {
		initialisationView();
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				demarrerSimulation();
			}
		});
	}

	/**
	 * Démarre la simulation en exécutant le thread {@link t}.
	 */
	private void demarrerSimulation() {
		try {
			verifierValeursEntrees();
		if (t.getState() == Thread.State.NEW) {
			// si noveau thread, lancer
			t.start();
		} else {
			// si est en train de s'executer alors redemarrer
			synchronized (obj) {
				enCours = false;
			}
		}
		} catch (NumberFormatException e) {
			error.setForeground(Color.RED);
			error.setText("wrong values");
		}
	}
	
	/**
	 * Vérifie les valeurs entrées par l'utilisateur.
	 * @throws NumberFormatException si un des nombres n'est pas entier
	 */
	private void verifierValeursEntrees() throws NumberFormatException {
		Integer.parseUnsignedInt(fieldPoule.getText());
		Integer.parseUnsignedInt(fieldRenard.getText());
		Integer.parseUnsignedInt(fieldVipere.getText());
	}

	/**
	 * La méthode appelée au démarrage du thread.
	 * Elle initialise le modèle et commence la simulation.
	 */
	@Override
	public void run() {
		while (true) {
			cases = new GrilleView(largeur, hauteur, 0, 0, 10, 10);
			simulationView.setCases(cases);
			synchronized (obj) {
				enCours = true;
			}
			try {
				initialisationModele();
				cont.repaint();
				simulation();
			} catch (PositionLibreInexistanteException e) {
				error.setForeground(Color.RED);
				error.setText("too many animals");
			}
		}
	}

	/**
	 * Simule les interactions entre les animaux.
	 * La méthode exécute les tours des animaux, ajoute et supprime les animaux
	 * si nécessaire. Elle continue ainsi tant que {@link enCours} reste vrai.
	 * Quand cette variable passe à faux la simulation se termine.
	 */
	private void simulation() {
		boolean running;
		synchronized (obj) {
			running = this.enCours;
		}

		while (running) {
			aAjouter.clear();
			aSupprimer.clear();
			tourInterdit.clear();
			saison.setText(date.toString());
			cont.repaint();
			// attendre un peu
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			executerJour();

			animaux.removeAll(aSupprimer);
			// mélanger les animaux à ajouter pour éviter de donner
			// un avantage lier à l'ordre de réproduction
			// surtout nécessaire le premier printemps de la simulation
			Collections.shuffle(aAjouter);
			for (EspeceAnimal e : aAjouter) {
				try {
					addAnimal(e);
				} catch (PositionLibreInexistanteException e1) {
				}
			}
			date.incrementer();

			synchronized (obj) {
				running = this.enCours;
			}
		}
	}

	/**
	 * Exécute un jour de la simulation.
	 * La méthode fait les traitements nécessaires pour simuler un tour de
	 * chaque animal ce qui est équivalent à un jour.
	 */
	private void executerJour() {
		for (Animal a : animaux) {
			if (a.estMort()) {
				if (a.joursDepuisMort() > 10) {
					// enlever l'animal mort
					Couple<Integer, Integer> c = positions.getCoordinates(a.getPos().getId());
					positions.libererPosition(c.a, c.b);
					cases.setElementContent(null, c.a, c.b);
					aSupprimer.add(a);
				}
				a.incrementerAge();
				continue;
			}
			if (tourInterdit.contains(a))
				continue;

			faireTour(a);
		}
	}

	/**
	 * Ajoute un nouvel animal à la simulation.
	 * L'animal est créé et est ajouté à la représentation logique ainsi qu'à la
	 * grille d'affichage.
	 *
	 * @param espece espèce d'animal à ajouter
	 * @throws PositionLibreInexistanteException s'il n'y a pas de place pour
	 * un nouvel animal
	 */
	private void addAnimal(EspeceAnimal espece) throws PositionLibreInexistanteException {
		Position p = positions.getPositionLibreAleatoire();
		Animal a = AnimalAbstractFactory.getFactory(espece).getAnimal(p);
		animaux.add(a);
		Couple<Integer, Integer> couple = positions.getCoordinates(p.getId());
		positions.setElementContent(a, couple.a, couple.b);
		cases.setElementContent(a.getCouleur(), couple.a, couple.b);
	}

	/**
	 * Simule un tour de l'animal.
	 * Cette méthode essayera de détecter une proie à côté et de l'attaquer. Si
	 * cela échoue elle essayera de se reproduire avec un représentant de son
	 * espèce (la reproduction n'est possible qu'au printemps). S'il n'y a pas
	 * de partenaire ou si il est un autre saison, l'animal se déplacera.
	 * La fonction se charge d'incrémenter l'age de l'animal et le nombre de
	 * jour qu'il n'a pas mangé.
	 *
	 * Si l'animal meurt pendant son tour, sa représentation graphique change.
	 *
	 * Avant d'appeler cette fonction il faut s'assurer que l'animal peut faire
	 * un tour.
	 *
	 * @param a animal qui fait son tour
	 */
	private void faireTour(Animal a) {
		List<Position> voisines = a.getPos().getPositionVoisines();
		for (Position p : voisines) {
			if (p.estLibre() == false) {
				Animal b = p.getAnimal();
				if (attaquer(a, b))
					return;
				if (seReproduire(a, b))
					return;
			}
		}
		Couple<Integer, Integer> couple = positions.getCoordinates(a.getPos().getId());
		a.seDeplacer();
		majPositionAnimal(couple, a);
		a.incrementerAge();
		a.incrementerFaim();
		if (a.estMort()) {
			couple = positions.getCoordinates(a.getPos().getId());
			cases.setElementContent(Color.GRAY, couple.a, couple.b);
		}
	}

	/**
	 * Essayer de reproduire les animaux.
	 * La fonction vérifie la saison et les espèces des animaux en questions.
	 * En cas de succès cette fonction crée le nouvel animal et force l'animal b sauter son tour.
	 *
	 * @param a animal qui initie la reproduction
	 * @param b partenaire potentiel
	 * @return true si l'animal initiant la reproduction doit finir son tour, faux s'il peux continuer
	 */
	private boolean seReproduire(Animal a, Animal b) {
		if (date.getNomSaison().equalsIgnoreCase("PRINTEMPS") && b.getClass().equals(a.getClass())) {
			if (a.seReproduire()) {
				aAjouter.add(a.getEspece());
				tourInterdit.add(b);
			}
			return true;
		}
		return false;
	}

	/**
	 * Essayer d'attaquer un animal.
	 * La méthode vérifie si l'attaquant peut attaquer la proie. Elle exécute
	 * aussi les méthodes correspondantes au succès et à l'échec.
	 *
	 * @param a attaquant
	 * @param proie proie
	 * @return true si l'attaquant doit finir son tour, false s'il peux continuer
	 */
	private boolean attaquer(Animal a, Animal proie) {
		Couple<Integer, Integer> couple = positions.getCoordinates(proie.getPos().getId());
		if (proie.estMange())
			return false;
		if ((proie.estMort() || a.peutManger(proie)) && a.detecterProie(proie)) {
			if (a.attaquer(proie)) {
				a.manger(proie);
				positions.libererPosition(couple.a, couple.b);
				cases.setElementContent(null, couple.a, couple.b);
				aSupprimer.add(proie);
			} else {
				proie.fuir();
				majPositionAnimal(couple, proie);
			}
			return true;
		}
		return false;
	}


	/**
	 * Met à jour l'affichage de la position d'un animal.
	 * Efface la case (graphique) correspondant à l'ancienne position de l'animal
	 * et colorie la case de son emplacement actuel.
	 * Cette fonction doit être appelée après chaque déplacement d'un animal.
	 * L'animal doit être vivant.
	 *
	 * @param couple ancienne position de l'animal
	 * @param a animal
	 */
	private void majPositionAnimal(Couple<Integer, Integer> couple, Animal a) {
		cases.setElementContent(null, couple.a, couple.b);
		couple = positions.getCoordinates(a.getPos().getId());
		if (a.estMort())
			throw new RuntimeException();
		cases.setElementContent(a.getCouleur(), couple.a, couple.b);
	}

}
