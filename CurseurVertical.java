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
	 * @param s : le tableau de signaux
	 * @param n : le numero du signal associe au curseur
	 */
	public CurseurVertical(Signal[] s, int n) {
		super(s);
		this.n = n;
	}
	
	/**
	 * Methode qui gere l'affichage des curseurs a l'ecran
	 * @param g :l'objet graphique qui fait zizir
	 * @param hauteur : la hauteur de la grille
	 * @param largeur : la largeur de la grille
	 */
	public void paint(Graphics g, int hauteur, int largeur) {
		g.setColor(Color.RED);
		
		echelle = s[n].echelleY;//on recupere les echelles du signal associe
		nbPixel = s[n].nbPixelY;
		
		//on calcule les positions
		posCurseur1 = -(int)(vraiVal1*nbPixel) + hauteur/2;	//on met un signe - pour etre coherent avec le sens de rotation des potentiometres
		posCurseur2 = -(int)(vraiVal2*nbPixel) + hauteur/2;
		
		g.drawLine(0, posCurseur1, largeur, posCurseur1);	//1er curseur
		g.drawLine(0, posCurseur2, largeur, posCurseur2);	//2e curseur
	}
	
	
	/**
	 * Met a jour l'echelle des curseurs
	 */
	public void mAJEchelle() {
		echelle = s[n].echelleY;
	}
	
	/**
	 * Donne la difference en String
	 * @return String : la difference
	 */
	public String getDifference() {
		return (super.getDifference() + " V");	//on affiche a l'ecran
	}
	
	/**
	 * Donne la valeur 1 en String
	 * @return String : la valeur
	 */
	public String getVraiVal1() {//voir avant
		return (super.getVraiVal1() + " V");	//on affiche a l'ecran
	}
	
	/**
	 * Donne la valeur 2 en String
	 * @return String : la valeur
	 */
	public String getVraiVal2() {//voir avant
		return (super.getVraiVal2() + " V");	//on affiche a l'ecran
	}
}
