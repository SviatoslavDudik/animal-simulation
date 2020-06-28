import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//Merci à StackOverflow pour sa précieuse contribution !


public class ChampGraphique extends JPanel {
	private int x, y;
	private int largeur, hauteur;
	private int wlargeur, whauteur;
	private AffichageStrategy affichage;
	private Iterable<Case> cases;

	/**
	 * Constructeur.
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 */
	public ChampGraphique(int largeur, int hauteur) {
		x = 0;
		y = 0;
		this.largeur = largeur;
		this.hauteur = hauteur;
		wlargeur = largeur;
		whauteur = hauteur;

		JFrame window = new JFrame();
		window.setSize(wlargeur, whauteur);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO delete
		window.setUndecorated(true);

		window.add(this);
		window.setVisible(true);
	}

	@Override
	//Fonction d'affichage de la grille.
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Case c: cases)
			affichage.afficher(g, c);
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, largeur, hauteur);

	}

	public void setFrame(int x, int y, int l, int h) {
		this.x = x;
		this.y = y;
		largeur = l;
		hauteur = h;
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

	public void setAffichage(AffichageStrategy s) {
		affichage = s;
	}

	public void setCases(Iterable<Case> grille) {
		cases = grille;
	}
}
