import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/** Bouton a deux position : ON et OFF.
 * Son constructeur permet de parametrer ses couleurs.
 * Par defaut, la valeur est false. 
 * Lors d'une modification de la valeur, il faut  
 * Sa couleur indique l'etat (actif ou non) du Signal. */
public class Bouton_OnOff extends JPanel {
	
	/** true si signal actif, false si signal inactif. */
	private boolean valeur = false;
	
	/** Couleurs du bouton, non modifiables. */
	public final Color COLOR_ON, COLOR_OFF;
	
	/** Constructeur par defaut. 
	 * Cree un bouton de taille 200*30 mais affiche comme un cercle de rayon 30 au centre.
	 * @param colorOn La couleur correspondant a la position ON.
	 * @param colorOff La couleur correspondant a la position OFF*/
	public Bouton_OnOff(Color colorOn, Color colorOff) {
		super();
		setSize(200, 30);
		setBackground(null);
		setOpaque(true);
		COLOR_ON = colorOn;
		COLOR_OFF = colorOff;
	}
	
	/** Colorie le bouton d'apres la couleur correspondant a l'etat du Signal. 
	 * @param g L'element graphique tant appreciable. */
	public void paint (Graphics g) {
		// On recupere les dimensions de l'objet.
		int largeur = this.getWidth(), hauteur = this.getHeight();
		
		// On colorie l'arriere-plan.
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, largeur, hauteur);
		
		// On definit la couleur.
		if (valeur == true) {
			g.setColor(COLOR_ON);
		} else {
			g.setColor(COLOR_OFF);
		}
		
		// On dessine vraiment l'objet.
		if (largeur > hauteur) {
			g.fillOval((largeur - hauteur)/2, 1, hauteur - 2, hauteur - 2);
			g.setColor(Color.WHITE);
			g.drawLine(largeur/2 - 1, hauteur/4, largeur/2 - 1, hauteur/2);
			g.drawArc((largeur/2 - hauteur/4) - 1, hauteur/4, hauteur/2, hauteur/2, 180, 180);
		} else {
			g.fillOval(1, (hauteur - largeur)/2, largeur - 2, largeur - 2);
			g.setColor(Color.WHITE);
			g.drawLine(largeur/4 - 1, hauteur/2, largeur/2 - 1, hauteur/2);
			g.drawArc(largeur/4 - 1, (hauteur/2 - largeur/4), hauteur/2, hauteur/2, 180, 180);
		}
	}
	
	/** @return la valeur. */
	public boolean valeur() {
		return valeur;
	}
	
	/** Inverse la valeur. Implique de repeindre l'objet. */
	public void changeValeur() {
		valeur = !valeur;
		repaint();
	}
	
	/** @param b La valeur a appliquer. */
	public void setValeur(boolean b) {
		valeur = b;
		repaint();
	}
}