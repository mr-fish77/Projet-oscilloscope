import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe qui permet de gerer un curseur vertical (volts)
 */
public class CurseurHorizontal extends Curseur {
	
	/**
	 * Constructeur par defaut de la classe
	 * @param s : le tableau des signaux
	 */
	public CurseurHorizontal(Signal[] s) {
		super(s);
	}
	
	
	/**
	 * Methode qui gere l'affichage des curseurs a l'ecran
	 * @param g :l'objet graphique qui fait zizir
	 * @param hauteur : la hauteur de la grille
	 * @param largeur : la largeur de la grille
	 */
	public void paint(Graphics g, int hauteur, int largeur) {
		g.setColor(Color.RED);	//affichage des curseurs dans la bonne couleur
		
		echelle = s[0].echelleX;//on recupere l'echelle de temps et le nombre de pixels associe
		nbPixel = s[0].nbPixelX;
		
		//on calcule les positions
		posCurseur1 = (int)(vraiVal1*nbPixel) + largeur/2;	//cete fois-ci pas besoin de signe -, on est deja dans le sens logique
		posCurseur2 = (int)(vraiVal2*nbPixel) + largeur/2;
		
		g.drawLine(posCurseur1, 0, posCurseur1, hauteur);	//1er curseur
		g.drawLine(posCurseur2, 0, posCurseur2, hauteur);	//2e curseur
	}
	
	/**
	 * Met a jour l'echelle des curseurs
	 */
	public void mAJEchelle() {
		echelle = s[0].echelleX;
	}
	
	
	/**
	 * Donne la difference en String
	 * @return String : la difference
	 */
	public String getDifference() {
		return (super.getDifference() + " s");	//on affiche a l'ecran
	}
	
	/**
	 * Donne la valeur 1 en String
	 * @return String : la valeur
	 */
	public String getVraiVal1() {//voir avant
		return (super.getVraiVal1() + " s");	//on affiche a l'ecran
	}
	
	/**
	 * Donne la valeur 2 en String
	 * @return String : la valeur
	 */
	public String getVraiVal2() {//voir avant
		return (super.getVraiVal2() + " s");	//on affiche a l'ecran
	}
}
