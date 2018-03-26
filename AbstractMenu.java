import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractMenu extends JPanel implements ActionListener{
	
	/** Les signaux. */
	protected Signal[] signaux;
	
	/** String indiquant le menu actuellement a l'ecran.
	 * Il est affiche dans le Terminal. */
	protected static String menuActuel = "NoMenu";
	
	/** Les 5 menus. */
	protected LinkedList<JButton> boutons = new LinkedList<JButton>();
	
	/** Cree un Menu.
	 * @param s Les signaux, en acces protected.
	 * @param menuActuel Chaine de caracteres qui indiquent le menu actuellement a l'ecran.
	 * Utilise pour le deboggage, sera desactive lors de la finalisation du projet.
	 */
	protected AbstractMenu(Signal[] s, String menuActuel) {
		super();
		signaux = s;
		
		this.menuActuel = menuActuel.toUpperCase(); // Mise en majuscules.
		System.out.println("MENU " + menuActuel);
		
		setLayout(new GridLayout(5,1));	//fenetre separee en 5 cases de meme taille
		for (int i = 0; i < 5; i++) {
			JButton b = new JButton();
			b.setActionCommand(Integer.toString(i));
			boutons.add(b);
			this.add(b);
		}
		addActionListener(this);
	}
	
	
	/** Ecrit le titre et affiche les 5 boutons. 
	 * @param g L'element graphique fort appreciable. */
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	
	/** Permet de peindre sur la grille
	 * @param g : l'element graphique qui est bien
	 */
	public void paintGrille(Graphics g) {
		
	}
	
	/** Methode qui ajoute un actionListener a tous les boutons a la fois
	 * @param ActionListener al : l'actionlistener a rajouter a tous les objets
	 */
	public void addActionListener(ActionListener al) {
		for (JButton b : boutons) {
			b.addActionListener(al);
		}
	}
	
	
	/**
	 * Methode qui desactive le menu : a implementer au besoin
	 */
	public void desactiverMenu() {}
	
	/**
	 * Methode qui met en route le menu
	 */
	public void miseEnRoute() {}
	
	
	/** Methode qui prend en charge l'action sur les boutons
	 * @param ActionEvent e : l'action event habituel
	 */
	public void actionPerformed (ActionEvent e) {
		if(e.getSource().equals(boutons.get(0))) {
			actionBouton1(e);
		}else if(e.getSource().equals(boutons.get(1))) {
			actionBouton2(e);
		}else if(e.getSource().equals(boutons.get(2))) {
			actionBouton3(e);
		}else if(e.getSource().equals(boutons.get(3))) {
			actionBouton4(e);
		}else if(e.getSource().equals(boutons.get(4))) {
			actionBouton5(e);
		}
	}
	
	/** Methode qui est appelee lorsque le 1er bouton est clique
	 * @param ActionEvent e : l'action event habituel
	 */
	public void actionBouton1(ActionEvent e) {}
	
	/** Methode qui est appelee lorsque le 2e bouton est clique
	 * @param ActionEvent e : l'action event habituel
	 */
	public void actionBouton2(ActionEvent e) {}
	
	
	/** Methode qui est appelee lorsque le 3e bouton est clique
	 * @param ActionEvent e : l'action event habituel
	 */
	public void actionBouton3(ActionEvent e) {}
	
	
	/** Methode qui est appelee lorsque le 4e bouton est clique
	 * @param ActionEvent e : l'action event habituel
	 */
	public void actionBouton4(ActionEvent e) {}
	
	
	/** Methode qui est appelee lorsque le 5e bouton est clique
	 * @param ActionEvent e : l'action event habituel
	 */
	public void actionBouton5(ActionEvent e) {}
}
