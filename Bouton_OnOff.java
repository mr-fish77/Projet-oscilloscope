import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/** Bouton à deux position : ON et OFF.
 * Son constructeur permet de paramétrer ses couleurs.
 * Par défaut, la valeur est false. 
 * Lors d'une modification de la valeur, il faut  
 * Sa couleur indique l'etat (actif ou non) du Signal. */
public class Bouton_OnOff extends JPanel {
	
	/** true si signal actif, false si signal inactif. */
	private boolean valeur = false;
	
	/** Couleurs du bouton, non modifiables. */
	public final Color COLOR_ON, COLOR_OFF;
	
	/** Constructeur par défaut. 
	 * Crée un bouton de taille 200*30 mais affiché comme un cercle de rayon 30 au centre.
	 * @param colorOn La couleur correspondant à la position ON.
	 * @param colorOff La couleur correspondant à la position OFF*/
	public Bouton_OnOff(Color colorOn, Color colorOff) {
		super();
		setSize(200, 30);
		setBackground(null);
		setOpaque(true);
		COLOR_ON = colorOn;
		COLOR_OFF = colorOff;
	}
	
	/** Colorie le bouton d'apres la couleur correspondant a l'etat du Signal. 
	 * @param g L'élément graphique tant appréciable. */
	public void paint (Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawOval(85, 0, 30, 30);
		if (valeur == true) {
			g.setColor(COLOR_ON);
		} else {
			g.setColor(COLOR_OFF);
		}
		g.fillOval(86, 1, 28, 28);
		g.setColor(Color.WHITE);
		g.drawLine(100, 7, 100, 15);
		g.drawArc(93, 8, 15, 15, 180, 180);
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
}