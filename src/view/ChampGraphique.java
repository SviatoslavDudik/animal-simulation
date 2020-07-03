package view;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Merci à StackOverflow pour sa précieuse contribution !

/**
 * Élément graphique occupant toute la fenêtre.
 *
 * @author Ali Aboutarik
 * @author Sviatoslav Dudik
 */
public class ChampGraphique extends JPanel {

	/** Largeur de la fenêtre en pixels. */
	private int largeur;
	/** Hauteur de la fenêtre en pixels. */
	private int hauteur;
	/** Fenêtre. */
	private JFrame window;
	/** Conteneur en haut de la fenêtre. */
	private JPanel top;
	/** Conteneur au centre de la fenêtre. */
	private JPanel center;
	/** Conteneur en bas de la fenêtre. */
	private JPanel bottom;

	/**
	 * Constructeur.
	 * 
	 * @param largeur largeur (en pixels) de la fenêtre
	 * @param hauteur hauteur (en pixels) de la fenêtre
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

		window.add(this);
	}
	
	/**
	 * Affiche la fenêtre.
	 * La méthode doit être appeler après l'ajout de tous les éléments.
	 */
	public void showWindow() {
		window.pack();
		window.setVisible(true);
	}
	
	/**
	 * Ajoute un élément en haut.
	 *
	 * @param c élément à ajouter
	 */
	public void addTop(Component c) {
		top.add(c);
	}

	/**
	 * Ajoute un élément au centre.
	 *
	 * @param c élément à ajouter
	 */
	public void addCenter(Component c) {
		center.add(c);
	}

	/**
	 * Ajoute un élément en bas.
	 *
	 * @param c élément à ajouter
	 */
	public void addBottom(Component c) {
		bottom.add(c);
	}

	/**
	 * Accesseur.
	 *
	 * @return largeur de la fenêtre
	 */
	public int getLargeur() {
		return largeur;
	}
	
	/**
	 * Accesseur.
	 *
	 * @return hauteur de la fenêtre
	 */
	public int getHauteur() {
		return hauteur;
	}

}
