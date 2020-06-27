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
	private int largeur, hauteur;
	
	private List<Point> casesAColorier;

	/**
	 * Constructeur.
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 */
	public ChampGraphique(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		casesAColorier = new ArrayList<>(25);

		JFrame window = new JFrame();
		window.setSize(largeur*10+50, hauteur*10+50);
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
		for (Point fillCell : casesAColorier) {
			int cellX = 10 + (fillCell.x * 10);
			int cellY = 10 + (fillCell.y * 10);
			g.setColor(Color.RED);
			g.fillRect(cellX, cellY, 10, 10);
		}
		
		g.setColor(Color.BLACK);
		g.drawRect(10, 10, largeur*10, hauteur*10);

		for (int i = 10; i <= largeur*10; i += 10) {
			g.drawLine(i, 10, i, hauteur*10+10);
		}

		for (int i = 10; i <= hauteur*10; i += 10) {
			g.drawLine(10, i, largeur*10+10, i);
		}
	}

	/**
	 * Fonction permettant de colorier, en rouge, une case de la grille
	 * @param x Abscisse de la case à colorier (entre 0 et largeur de grille - 1).
	 * @param y Ordonnée de la case à colorier (entre 0 et hauteur de grille - 1).
	 */
	public void colorierCase(int x, int y) {
		casesAColorier.add(new Point(x, y));
		repaint();
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
