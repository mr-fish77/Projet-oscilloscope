import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe qui permet de gerer un curseur vertical (volts)
 * @author Pierre-Yves
 *
 */
public class CurseurVertical extends Curseur{
	protected int n;
	
	public CurseurVertical(Signal[] s, int n) {
		super(s);
		this.n = n;
		
	}
	
	/**
	 * Methode qui gere l'affichage des curseurs a l'ecran
	 * @param Graphics g :l'objet graphique qui fait zizir
	 */
	public void paint(Graphics g, int hauteur, int largeur) {
		g.setColor(Color.RED);
		
		echelle = s[n].echelleY;
		nbPixel = s[n].nbPixelY;
		posCurseur1 = -(int)(valCurseur1*nbPixel) + hauteur/2;
		posCurseur2 = -(int)(valCurseur2*nbPixel) + hauteur/2;
		
		g.drawLine(0, posCurseur1, largeur, posCurseur1);	//1er curseur
		g.drawLine(0, posCurseur2, largeur, posCurseur2);	//2e curseur
	}

	
}
