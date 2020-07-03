package view;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


//Merci à StackOverflow pour sa précieuse contribution !


public class ChampGraphique extends JPanel {

	private int largeur, hauteur;
	private JFrame window;
	private JPanel top, center, bottom;

	/**
	 * Constructeur.
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 */
	public ChampGraphique(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.setLayout(new BorderLayout(10,10));
		top = new JPanel();
		center = new JPanel();
		bottom = new JPanel();
		add(top, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);

		window = new JFrame();
		window.setSize(largeur, hauteur);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO delete
		window.setUndecorated(true);

		window.add(this);
	}
	
	public void showWindow() {
		window.pack();
		window.setVisible(true);
	}
	
	public void addTop(Component c) {
		top.add(c);
	}

	public void addCenter(Component c) {
		center.add(c);
	}

	public void addBottom(Component c) {
		bottom.add(c);
	}

	/**
	 * Accesseur.
	 * @return Renvoie la largeur de la grille
	 */
	public int getLargeur() {
		return largeur;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la hauteur de la grille
	 */
	public int getHauteur() {
		return hauteur;
	}

}
