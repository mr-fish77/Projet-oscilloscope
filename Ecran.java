import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Classe qui permet de creer un ecran d'oscilloscope
 * @author Pierre-Yves
 *
 */
public class Ecran extends JPanel{
	private Color arrierePlan = Color.BLACK;	//on peut changer les couleurs de l'ecran
	private Color avantPlan = Color.WHITE;
	
	/**
	 * Constructeur par defaut de la classe
	 */
	public Ecran() {
		super();
		setBackground(arrierePlan);
	}
	
	
	/**
	 * Methode qui gere l'affichage de l'element
	 * @param Graphics g : l'objet graphique
	 */
	public void paint(Graphics g) {
		super.paint(g);	//on appelle la methode mere (pour l'arriere plan du jpanel)
		
		afficheQuadrillage(g);
	}
	
	
	/**
	 * Methode qui permet d'afficher la grille adaptee a la taille de l'ecran
	 * @param Graphics g : l'objet graphique
	 */
	public void afficheQuadrillage(Graphics g) {
		int hauteur = getHeight();	//on recupere la taille du jpanel et donc de l'ecran
		int largeur = getWidth();
		
		int dHauteur = hauteur/2;	//millieu de l'ecran
		int dLargeur = largeur/2;
		
		int hGrandeGrad = hauteur/8;//espace entre carreaux du grand quadrillage
		int lGrandeGrad = largeur/8;
		
		int hPetiteGrad = hauteur/40;//espace entre les petites graduations
		int lPetiteGrad = largeur/40;
		
		g.setColor(avantPlan);
		
		for(int i = 0; i<8; i++) {
			g.drawLine(0, i*hGrandeGrad, largeur, i*hGrandeGrad);	//quadrillage vertical
			g.drawLine(i*lGrandeGrad, 0, i*lGrandeGrad, hauteur);	//quadrillage horizontal
			
			//on est oblige de faire cette boucle dans la precedente, a cause des pixels qui sont des int, on finit par se decaler beaucoup du quadrillage initial
			for(int j = 1; j < 5; j++) {
				g.drawLine(i*lGrandeGrad + j*lPetiteGrad, dHauteur - hPetiteGrad/2, i*lGrandeGrad + j*lPetiteGrad, dHauteur + hPetiteGrad/2);	//graduation axe horizontal
				g.drawLine(dLargeur - lPetiteGrad/2, i*hGrandeGrad + j*hPetiteGrad, dLargeur + lPetiteGrad/2, i*hGrandeGrad + j*hPetiteGrad);	//graduation axe vertical
			}
		}
		
	}
	
}
