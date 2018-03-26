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
	protected JButton bouton1, bouton2, bouton3, bouton4, bouton5;
	
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
		for (java.awt.Component c : this.getComponents()){
			JButton b = (JButton) c;
			b.addActionListener(this);
		}
	}
	
	
	/** Ecrit le titre et affiche les 5 boutons. 
	 * @param g L'element graphique fort appreciable. */
	public void paint(Graphics g) {
		super.paint(g);
	}
	
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
	 * @param String s Tableau avec tous les textes a mettre. */
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
	
	
	/** Methode qui prend en charge l'action sur les boutons
	 * @param ActionEvent e : l'action event habituel
	 */
	public void actionPerformed (ActionEvent e) {
		if      (e.getSource().equals(bouton1)) {
			actionBouton1(e);
		}else if(e.getSource().equals(bouton2)) {
			actionBouton2(e);
		}else if(e.getSource().equals(bouton3)) {
			actionBouton3(e);
		}else if(e.getSource().equals(bouton4)) {
			actionBouton4(e);
		}else if(e.getSource().equals(bouton5)) {
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
