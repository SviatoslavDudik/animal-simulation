package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Animal;
import model.Couple;
import model.EspeceAnimal;
import model.Grille;
import model.Position;
import model.PositionLibreInexistanteException;
import model.Renard;
import view.AffichageCercle;
import view.AffichageRectangle;
import view.Case;
import view.ChampGraphique;
import view.GrilleView;
import view.SimulationView;

public class Simulation implements Runnable {

	private List<Animal> animaux;
	private AccesPosition2D<Animal> positions;
	private int largeur, hauteur;
	private Acces2D<Case, Color> cases;
	private ChampGraphique cont;
	private SimulationView sim;
	private JLabel saison, error;
	private JButton start;
	private JTextField fieldRenard, fieldPoule, fieldVipere;
	private Thread t;
	private final Object obj = new Object();
	private boolean enCours;
	private List<Animal> aSupprimer, tourInterdit;
	private List<EspeceAnimal> aAjouter;

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

	private void initialisationModele() {
		animaux = new ArrayList<Animal>();
		positions = new Grille(largeur, hauteur);
		try {
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
		} catch (NumberFormatException e) {
			error.setForeground(Color.RED);
			error.setText("wrong values");
		} catch (PositionLibreInexistanteException e) {
			error.setForeground(Color.RED);
			error.setText("too many animals");
		} finally {
			cont.repaint();
		}
	}

	private void initialisationView() {
		cont = new ChampGraphique(largeur * 10 + 50, 2 * hauteur * 10 + 50);
		sim = new SimulationView(cases, new AffichageRectangle());
		sim.setPreferredSize(new Dimension(largeur * 10 + 2, hauteur * 10 + 2));
		cont.addCenter(sim);

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
				switch (sim.getAffichage()) {
				case CERCLE:
					sim.setAffichage(new AffichageRectangle());
					break;
				case RECTANGLE:
					sim.setAffichage(new AffichageCercle());
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

	@Override
	public void run() {
		while (true) {
			cases = new GrilleView(largeur, hauteur, 0, 0, 10, 10);
			sim.setCases(cases);
			synchronized (obj) {
				enCours = true;
			}
			initialisationModele();
			simulation();
		}

	}

	public void lancer() {
		initialisationView();
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (t.getState() == Thread.State.NEW) {
					t.start();
				} else {
					synchronized (obj) {
						enCours = false;
					}
				}
			}

		});
	}

	private void simulation() {
		boolean running;
		synchronized (obj) {
			running = this.enCours;
		}

		while (running) {
			aAjouter.clear();
			aSupprimer.clear();
			tourInterdit.clear();
			cont.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (Animal a : animaux) {
				if (a.estMort()) {
					if (a.joursDepuisMort() > 10) {
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
			animaux.removeAll(aSupprimer);
			for (EspeceAnimal e: aAjouter) {
				try {
					addAnimal(e);
				} catch (PositionLibreInexistanteException e1) {}
			}

			synchronized (obj) {
				running = this.enCours;
			}
		}
	}

	private void addAnimal(EspeceAnimal espece) throws PositionLibreInexistanteException {
		Position p = positions.getPositionLibreAleatoire();
		Animal a = AnimalAbstractFactory.getFactory(espece).getAnimal(p);
		animaux.add(a);
		Couple<Integer, Integer> couple = positions.getCoordinates(p.getId());
		positions.setElementContent(a, couple.a, couple.b);
		cases.setElementContent(a.getCouleur(), couple.a, couple.b);
	}

	private void faireTour(Animal a) {
		List<Position> voisines = a.getPos().getPositionVoisines();
		for (Position p : voisines) {
			if (p.estLibre() == false) {
				Animal b = p.getAnimal();
				if (attaquer(a, b))
					return;
				// TODO season check
				if (b.getClass().equals(a.getClass())) {
					if (seReproduire(a, b))
						tourInterdit.add(b);
					return;
				}
			}
		}
		Couple<Integer, Integer> couple = positions.getCoordinates(a.getPos().getId());
		a.seDeplacer();
		majPositionAnimal(couple, a);
		a.incrementerAge();
		a.incrementerFaim();
	}

	private boolean seReproduire(Animal a, Animal animal) {
		boolean res = a.seReproduire();
		if (res == true)
			aAjouter.add(0, a.getEspece());
		return res;
	}

	private boolean attaquer(Animal a, Animal proie) {
		Couple<Integer, Integer> couple = positions.getCoordinates(proie.getPos().getId());
		if (proie.estMange())
			return false;
		if ((proie.estMort() || !proie.getClass().equals(a.getClass())) && a.detecterProie(proie)) {
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

	private void majPositionAnimal(Couple<Integer, Integer> couple, Animal a) {
		cases.setElementContent(null, couple.a, couple.b);
		couple = positions.getCoordinates(a.getPos().getId());
		if (a.estMort())
			throw new RuntimeException();
		cases.setElementContent(a.getCouleur(), couple.a, couple.b);
	}

}
