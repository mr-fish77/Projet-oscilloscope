import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JPanel;

/**
 * Bouton a deux position : ON et OFF. Son constructeur permet de parametrer ses
 * couleurs. Par defaut, la valeur est false. 
 * Pour l'utiliser, on l'initialise avec ses couleurs d'arriere et d'avant-plan.
 * Il faut aussi lui definir un Listener et une actionCommand avec la methode 
 * setListener(Bouton_OnOff_Listener l, String actionCommand). Lors d'un clic, 
 * le Bouton_OnOff change sa valeur, sa couleur, et envoie un Bouton_OnOff_Event.
 * On peut aussi le forcer a adopter une certaine position.
 * Un Bouton_OnOff ne peut accepter qu'un unique objet Bouton_OnOff_Listener.
 * Visuellement, la couleur du bouton indique sa valeur. 
 * Il herite de JPanel donc toutes les methodes inherentes a JPanel sont possibles. 
 */
public class Bouton_OnOff extends JPanel implements MouseListener {

	private static final long serialVersionUID = -4852536528429542058L;

	/** Valeur associee a la couleur du bouton. */
	private boolean valeur = false;

	/** Couleurs du bouton, non modifiables. */
	public final Color COLOR_ON, COLOR_OFF;

	/** Liste de Listener. */
	private LinkedList<Bouton_OnOff_Listener> listeners = new LinkedList<Bouton_OnOff_Listener>();;

	/** Texte qui decrit l'action du Listener. */
	private String actionCommand = "Bouton_OnOff: " + this.hashCode();
	
	/** Carre dans lequel est peint l'objet, relatif a sa taille. 
	 * Son contenu est organise de la meme facon qu'un setBounds. */
	private int[] pb;
	
	/** Definit si un clic sur l'arriere-plan declenche un evenement. */
	private boolean backgroundClicWorks = false;

	/**
	 * Constructeur par defaut. Cree un bouton de taille 200*30 mais affiche 
	 * un cercle de rayon 30 au centre. Il est possible de modifier cette valeur.
	 * Il est recommande d'ajouter un Listener a cet objet.
	 * 
	 * @param colorOn
	 *            La couleur correspondant a la position ON.
	 * @param colorOff
	 *            La couleur correspondant a la position OFF
	 */
	public Bouton_OnOff(Color colorOn, Color colorOff) {
		super();
		setSize(200, 30);
		setBackground(null);
		setOpaque(true);
		COLOR_ON = colorOn;
		COLOR_OFF = colorOff;
	}

	/** Colorie le bouton d'apres la couleur correspondant a l'etat du Signal.
	 * @param g L'element graphique tant appreciable.
	 */
	public void paint(Graphics g) {
		// On recupere les dimensions de l'objet.
		int largeur = this.getWidth(), hauteur = this.getHeight();

		// On colorie l'arriere-plan.
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, largeur, hauteur);
		
		// On re-calcule la valeur de paintedBounds.
		if (largeur > hauteur) {
			pb = new int []{(largeur - hauteur) / 2, 1, hauteur - 2, hauteur - 2};
		} else {
			pb = new int [] {1, (hauteur - largeur) / 2, largeur - 2, largeur - 2};
		}

		// On definit la couleur.
		if (valeur == true) {
			g.setColor(COLOR_ON);
		} else {
			g.setColor(COLOR_OFF);
		}

		// On dessine l'interieur de l'objet.
		g.fillOval(pb[0], pb[1], pb[2], pb[3]);
		g.setColor(Color.WHITE);
		if (largeur > hauteur) {
			g.drawLine(largeur / 2 - 1, hauteur / 4, largeur / 2 - 1, hauteur / 2);
			g.drawArc((largeur / 2 - hauteur / 4) - 1, hauteur / 4, hauteur / 2, hauteur / 2, 180, 180);
		} else {
			g.drawLine(largeur / 4 - 1, hauteur / 2, largeur / 2 - 1, hauteur / 2);
			g.drawArc(largeur / 4 - 1, (hauteur / 2 - largeur / 4), hauteur / 2, hauteur / 2, 180, 180);
		}
	}
	
	/** @return la valeur. */
	public boolean valeur() {
		return valeur;
	}

	/** Inverse la valeur. */
	public void changeValeur() {
		valeur = !valeur;
		repaint();
	}

	/** Modifie la valeur.
	 * @param b La valeur a appliquer. */
	public void setValeur(boolean b) {
		valeur = b;
		repaint();
	}

	/** En cas de clic, cree un Bouton_OnOff_Event.
	 * Le parametre backgroundClicWorks peut bloquer ce clic en fonction de la position de la souris.
	 * Si aucun listener n'est declare, rien ne se produit.
	 * @param e
	 *            L'evenement.
	 */
	public void mouseClicked(MouseEvent e) {
		if (listeners.size() > 0) {
			int x = e.getX(), y = e.getY();
			if 		((backgroundClicWorks)					// Si on peut cliquer sur l'arriere-plan c'est bon.
					|| ((x > pb[0]) && (x < pb[0] + pb[2])	// Sinon, on verifie la position selon x
					&& (y > pb[1]) && (y < pb[1] + pb[3])))	// et la position selon y.
			{
				Component src = e.getComponent(); // Objet source.
				Bouton_OnOff_Event evt = new Bouton_OnOff_Event(src, actionCommand);
				for (Bouton_OnOff_Listener l : listeners) {
					l.btnClicked(evt);
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	/** Ajoute un Listener au Bouton_OnOff.
	 * @param l Objet qui implemente Bouton_OnOff_Listener.
	 */
	public void addListener(Bouton_OnOff_Listener l) {
		this.addMouseListener(this);
		listeners.add(l);
	}
	
	/** Modifie l'actionCommand du bouton.
	 * @param actionC L'actionCommand souhaite.
	 */
	public void setActionCommand (String actionC) {
		this.actionCommand = actionC;
	}
	
	/** @return l'actionCommand. */
	public String getActionCommand() {
		return this.actionCommand;
	}
	
	/** Modifie la valeur de la variable qui indique si
	 * un clic sur l'arriere-plan est considere comme un clic ou non.
	 * @param b Valeur souhaitee.
	 */
	public void setBackgroundClicWorks(boolean b) {
		this.backgroundClicWorks = b;
	}
	
	/** @return true si un clic sur l'arriere-plan est considere comme un clic, 
	 * 			false sinon.
	 */
	public boolean getBackgroundClicWorks () {
		return this.backgroundClicWorks;
	}
}
