import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**
 * Classe qui permet de creer un ecran d'oscilloscope
 * @author Pierre-Yves
 *
 */
public class Ecran extends JPanel{
	/** Couleur d'arriere-plan ou d'avant-plan. */
	public final static Color arrierePlan = Color.WHITE, avantPlan = Color.BLACK;
	/** Objets graphiques utilises. */
	public Grille grille; public AbstractMenu menus; 
    public MenuDuBas bas;
	/** Les signaux. */
	private Signal[] signaux;
	
	/**
	 * Constructeur par defaut de la classe
	 */
	public Ecran(Signal[]signaux, Oscilloscope oscillo) {
		super();
		this.signaux = signaux;
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.BOTH;
        
        grille = new Grille(signaux);
        contraintes.gridx = 0;
        contraintes.gridy=0;
        contraintes.weightx = 1;
        contraintes.weighty = 0.95;
        add(grille, contraintes);
        
        //menus = new MenuMesures(signaux);
        menus = new MenuCurseur(signaux, oscillo);
        contraintes.gridy = 0;
        contraintes.gridx = 1;
        contraintes.weighty = 1;
        contraintes.weightx = 0.2;
        add(menus, contraintes);
        
        bas = new MenuDuBas(signaux);
        contraintes.gridy = 1;
        contraintes.gridx = 0;
        contraintes.weightx = 1;
        contraintes.weighty = 0.05;
        contraintes.gridwidth=2;
        add(bas, contraintes);
        

	}
	
	/** Actualise l'affichage de l'ecran.
	 * @param g L'element graphique toujours le bienvenu.
	 */
	public void paint (Graphics g) {
		super.paint(g);
		
	}
}
