import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/** Bouton pour allumer ou eteindre un Signal. 
 * Sa couleur indique l'etat (actif ou non) du Signal. */
public class Button_OnOff extends JPanel {
	
	/** true si signal actif, false si signal inactif. */
	public boolean valeur = false;
	
	/** Couleurs du bouton. */
	public final Color COLOR_ON, COLOR_OFF;
	
	/** Constructeur par défaut. */
	public Button_OnOff(Color on, Color off) {
		super();
		setSize(200, 30);
		setBackground(null);
		setOpaque(true);
		COLOR_ON = on;
		COLOR_OFF = off;
	}
	
	/** Colorie le bouton d'apres la couleur correspondant a l'etat du Signal. */
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
	
	
}