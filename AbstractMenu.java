import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** Classe mere des differents menus. Elle gere principalement
 * les 5 boutons de chaque menu, a savoir leur creation, 
 * le renommage des boutons, ainsi que 
 * la simplification de la gestion des actionPerformed. 
 * C'est une classe abstract. */

public abstract class AbstractMenu extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 8535664640439971855L;

	/** Oscillo pour acceder aux variable utiles */
	protected static Oscilloscope oscillo;
	
	/** Les signaux. */
	protected static Signal[] signaux;
	
	/** Les 5 menus. */
	protected JButton bouton1, bouton2, bouton3, bouton4, bouton5;
	
	/** Cree un Menu.
	 * @param s Les signaux, en acces protected.
	 */
	protected AbstractMenu() {
		super();
		
		/* Generation du contenu avec les boutons. */
		setLayout(new GridLayout(5,1));
		bouton1 = new JButton("");
		bouton2 = new JButton("");
		bouton3 = new JButton("");
		bouton4 = new JButton("");
		bouton5 = new JButton("");
		this.add(bouton1);
		this.add(bouton2);
		this.add(bouton3);
		this.add(bouton4);
		this.add(bouton5);
		for (Component c : this.getComponents()){
			JButton b = (JButton) c;
			b.addActionListener(this);
			b.setHorizontalAlignment(JButton.CENTER);
		}
	}
	
	
	/** Ecrit le titre et affiche les 5 boutons. 
	 * @param g L'element graphique fort appreciable. */
	public void paint(Graphics g) {
		super.paint(g);
	}
	

	
	/**
	 * Methode qui permet de peindre sur l'ecran
	 * @param Graphics g : l'objet graphique bien pratique
	 * @param int hauteur : la hauteur de la grille
	 * @param int largeur : la largeur de la grille
	 */
	public void paintGrille(Graphics g, int hauteur, int largeur) {}
	
	
	/** Modifie le texte du bouton numero n.
	 * @param s String a mettre dans le JButton.
	 * @param n Numero du bouton. */
	public void setText(String s, int n){
		switch (n){
			case 1:
			bouton1.setText(s);
			break;
			case 2:
			bouton2.setText(s);
			break;
			case 3:
			bouton3.setText(s);
			break;
			case 4:
			bouton4.setText(s);
			break;
			case 5:
			bouton5.setText(s);
			break;
		}
	}
	
	/** Modifie le texte de tous les boutons a la fois.
	 * @param String s Tableau avec tous les textes a mettre dans l'ordre. 
	 * Pour faire ca facilement : new String[]{"xxx","xxx"...} a 5 cases.*/
	public void setAllTexts(String[] s){
		bouton1.setText(s[0]);
		bouton2.setText(s[1]);
		bouton3.setText(s[2]);
		bouton4.setText(s[3]);
		bouton5.setText(s[4]);
	}
	
	/**
	 * Methode qui desactive le menu : a implementer au besoin
	 */
	public void desactiverMenu() {}
	
	/**
	 * Methode qui met en route le menu
	 */
	public void miseEnRoute() {}
	
	
	/** Methode qui prend en charge l'action sur les boutons.
	 * N'est pas utilise par les sous-classes, 
	 * les methodes de chaque bouton est deportee individuellement.
	 * @param ActionEvent e : l'action event habituel
	 * 		  qui ne sera pas envoye aux methodes deportees.
	 */
	public void actionPerformed (ActionEvent e) {
		if      (e.getSource().equals(bouton1)) {
			actionBouton1();
		}else if(e.getSource().equals(bouton2)) {
			actionBouton2();
		}else if(e.getSource().equals(bouton3)) {
			actionBouton3();
		}else if(e.getSource().equals(bouton4)) {
			actionBouton4();
		}else if(e.getSource().equals(bouton5)) {
			actionBouton5();
		}
	}
	
	/** Methode qui est appelee lorsque le 1er bouton est clique
	 */
	public abstract void actionBouton1();
	
	/** Methode qui est appelee lorsque le 2e bouton est clique
	 */
	public abstract void actionBouton2();
	
	
	/** Methode qui est appelee lorsque le 3e bouton est clique
	 */
	public abstract void actionBouton3();
	
	
	/** Methode qui est appelee lorsque le 4e bouton est clique
	 */
	public abstract void actionBouton4();
	
	
	/** Methode qui est appelee lorsque le 5e bouton est clique
	 */
	public abstract void actionBouton5();
	
	/** Assigne un oscilloscope et un tableau de signaux en attribut.
	 * @param o Oscilloscope.
	 * @param s Les signaux.
	 */
	public void setStatics(Oscilloscope o, Signal[] s) {
		oscillo = o;
		signaux = s;
	}
}
