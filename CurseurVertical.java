import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe qui permet de gerer un curseur vertical (volts)
 */
public class CurseurVertical extends Curseur{
	/** Numero du signal associe au curseur */
	protected int n;
	
	/**
	 * Constructeur par defaut de la classe
	 * @param Signal[] s : le tableau de signaux
	 * @param int n : le numero du signal associe au curseur
	 */
	public CurseurVertical(Signal[] s, int n) {
		super(s);
		this.n = n;
	}
	
	/**
	 * Methode qui gere l'affichage des curseurs a l'ecran
	 * @param Graphics g :l'objet graphique qui fait zizir
	 * @param int hauteur : la hauteur de la grille
	 * @param int largeur : la largeur de la grille
	 */
	public void paint(Graphics g, int hauteur, int largeur) {
		g.setColor(Color.RED);
		
		echelle = s[n].echelleY;//on recupere les echelles du signal associe
		nbPixel = s[n].nbPixelY;
		
		//on calcule les positions
		posCurseur1 = -(int)(valCurseur1*nbPixel) + hauteur/2;	//on met un signe - pour etre coherent avec le sens de rotation des potentiometres
		posCurseur2 = -(int)(valCurseur2*nbPixel) + hauteur/2;
		
		g.drawLine(0, posCurseur1, largeur, posCurseur1);	//1er curseur
		g.drawLine(0, posCurseur2, largeur, posCurseur2);	//2e curseur
	}

	
}
