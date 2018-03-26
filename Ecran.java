import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**
 * Classe qui permet de creer un ecran d'oscilloscope
 */
public class Ecran extends JPanel{
	/** Couleur d'arriere-plan ou d'avant-plan. */
	public final static Color arrierePlan = Color.WHITE, avantPlan = Color.BLACK;
	/** Objets graphiques utilises. */
	public Grille grille; public AbstractMenu menus; 
    public MenuDuBas bas;
    private GridBagConstraints contraintes;	//pour utiliser un gridbaglayout
	/** Les signaux. */
	private Signal[] signaux;
	
	/**
	 * Constructeur par defaut de la classe
	 */
	public Ecran(Signal[]signaux, Oscilloscope oscillo) {
		super();
		this.signaux = signaux;
		
		//GridBAgLayout (arrangement en lignes/colonnes)
		setLayout(new GridBagLayout());
		contraintes = new GridBagConstraints();	//les contraintes associees
		contraintes.fill = GridBagConstraints.BOTH;
        
		//la grille d'affichage des signaux
        grille = new Grille(signaux, this);
        contraintes.gridx = 0;
        contraintes.gridy=0;
        contraintes.weightx = 1;
        contraintes.weighty = 0.95;
        add(grille, contraintes);
        
        // la zone de notification en bas de l'ecran
        bas = new MenuDuBas(signaux);
        contraintes.gridy = 1;
        contraintes.gridx = 0;
        contraintes.weightx = 1;
        contraintes.weighty = 0.05;
        add(bas, contraintes);
	}
	
	
	/** Actualise l'affichage de l'ecran.
	 * @param g L'element graphique toujours le bienvenu.
	 */
	public void paint (Graphics g) {
		super.paint(g);
	}
	
	
	/**
	 * Permet de basculer d'un menu a un autre
	 * @param Menu menu : le nouveau menu a afficher
	 */
	public void changerMenu(AbstractMenu menu) {
		if(menus != null) {	//au lancement il n'y a pas de menu
			menus.desactiverMenu();
			remove(menus);
		}
		
		// on change le menu
		menus = menu;
		menus.miseEnRoute();
		contraintes.gridy = 0;
	    contraintes.gridx = 1;
	    contraintes.weighty = 1;
	    contraintes.weightx = 0.2;
	    add(menus, contraintes);
		
	    //les deux commandes sont obligatoire pour voir un chgt a l'ecran
		revalidate();
        repaint();
	}
}
