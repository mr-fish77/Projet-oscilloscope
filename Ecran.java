import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Classe qui permet de creer un ecran d'oscilloscope
 * @author Pierre-Yves
 *
 */
public class Ecran extends JPanel{
	/** Couleur d'arriere-plan ou d'avant-plan. */
	public final static Color arrierePlan = Color.WHITE, avantPlan = Color.BLACK;
	/** Objets graphiques utilisés. */
	public Grille grille; public MenuManager menus; private MenuDuBas bas;
	/** Les signaux. */
	private Signal[] signaux;
	
	/**
	 * Constructeur par defaut de la classe
	 */
	public Ecran(Signal[]signaux) {
		super();
		setBackground(arrierePlan);
		setLayout(null);
		
        this.signaux = signaux;
        grille = new Grille(signaux);
        menus = new MenuManager(signaux);
        bas = new MenuDuBas(signaux);
        add(grille);
        add(menus);
        add(bas);
	}
	
	public void paint (Graphics g) {
		// On recupere la taille de la fenetre.
		double actualWidth = this.getWidth();
		double actualHeight = this.getHeight();
		
		// On modifie la taille des JPanel.
		grille.setBounds(0, 0, (int)(.75*actualWidth), (int)(.95*actualHeight));
		menus.setBounds((int)(.75*actualWidth), 0, (int)(.25*actualWidth), (int)(.95*actualHeight));
		bas.setBounds(0, (int)(.95*actualHeight), (int)actualWidth, (int)(.05*actualHeight));
		grille.repaint(); menus.repaint(); bas.repaint();
	}

}
