import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * classe qui permet d'afficher le quadrillage, les signaux et les affichages de menus a l'ecran
 */
public class Grille extends JPanel {
	
	/** Les signaux. */
	Signal[] signaux;
	
	/**fonctionMath**/
	FonctionMath smaths;
	
	/** Couleur d'arriere-plan ou d'avant-plan. */
	Color arrierePlan = Ecran.arrierePlan, avantPlan = Ecran.avantPlan;
	
	/** affichage des menus */
	private Ecran ecran;
	
	/** Cree la grille.
	 * @param s Les signaux
	 */
	public Grille(Signal[] s, Ecran ecran) {
		super();
		setLayout(null);
		
		this.ecran = ecran;
		signaux = s;
		
		//on donne des couleurs
		setBackground(arrierePlan);
		setForeground(avantPlan);
	}
	
	
	/** Dessine le quadrillage et les courbes. 
	 * @param g L'element graphique fort sympathique. */
	public void paint (Graphics g) {
		super.paint(g);
		afficheQuadrillage(g);	//affichage de la grille a l'ecran
		
		//on met a jour les donnes et repeint les signaux
		signaux[0].miseAEchelle(getWidth(), getHeight());
		signaux[0].dessineCourbe(g);
		signaux[1].miseAEchelle(getWidth(), getHeight());
		signaux[1].dessineCourbe(g);
        
		//on s'occupe de l'affichage a l'ecran des menus au besoin
		if(ecran.menus != null) {
			ecran.menus.paintGrille(g,  getHeight(), getWidth());
		}
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
