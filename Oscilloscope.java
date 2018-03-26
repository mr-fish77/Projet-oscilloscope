import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe qui gere l'affichage de l'oscilloscope a l'ecran
 * @author Pierre-Yves
 *
 */
public class Oscilloscope extends JFrame implements ActionListener{
	/** Les Channel correspondant a chaque Signal. */
	public Channel ch1, ch2;
	/** Objet permettant de gerer la position et l'echelle temporels. */
	private GestionTemps gestionTemps;
	/** Generateur associe, on y recupere le signal lors d'une modification. */
	private Generateur generateur;
	/** Ecran d'affichage de l'oscilloscope. */
	public Ecran ecran;
	
	/** Boutons en haut a droite de l'interface de l'oscilloscope. */
	private BoutonTexte sauvRap, mesures, acquisition, autoset, utilitaire, curseurs, affichage, recopie, runStop;
	private AbstractMenu menuCurseur;
	
	/** Genere la fenetre principale de l'oscilloscope.*/
	public Oscilloscope(Signal[] signaux, Generateur generateur){
		super("Oscilloscope");
		setSize(1200, 600);
		setMinimumSize(new Dimension(600, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.generateur = generateur;

		JPanel conteneurPrincipal = new JPanel();		//pour ne pas avoir de probleme avec les marges des fenetres
		conteneurPrincipal.setLayout(new GridLayout());
		setContentPane(conteneurPrincipal);
		
		//Definition des menus
		menuCurseur = new MenuCurseur(signaux, this);
		
		
		/* Conteneur de gauche, contient l'ecran et les boutons interactions menus. */
		JPanel conteneurEcran = new JPanel();
		conteneurEcran.setLayout(new GridLayout(1,1));
		add(conteneurEcran);
		
		/* Ecran de l'oscilloscope. */
		ecran = new Ecran(signaux, this);
		generateur.setEcran(ecran);
		conteneurEcran.add(ecran);
		
		
		/* Conteneur de droite, contient les boutons pour afficher les menus. */
		JPanel conteneurGestion = new JPanel();
		add(conteneurGestion);
		conteneurGestion.setLayout(new GridBagLayout());
		
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 1;
		contraintes.gridx = 0;
		
		/* Conteneur en haut, contient tous les boutons d'affichage de menu. */
		JPanel boutonsAffichageMenus = new JPanel();
		boutonsAffichageMenus.setLayout(new GridLayout(2,5));
		ajouterAffichageMenus(boutonsAffichageMenus);
		contraintes.gridy = 0;
		contraintes.weighty = 0.5;
		conteneurGestion.add(boutonsAffichageMenus, contraintes);
		
		/* Conteneur du milieu, contient la gestion des channels, du temps et du trigger. */
		ch1 = new Channel(signaux, 1, "CH1", ecran);
		ch2 = new Channel(signaux, 2, "CH2", ecran);
		gestionTemps = new GestionTemps(signaux, ecran);
		
		JPanel affichageGestionChannels = new JPanel();
		affichageGestionChannels.setLayout(new GridLayout(1, 4));
		ajouterGestionChannels(affichageGestionChannels);
		contraintes.gridy = 1;
		contraintes.weighty = 1;
		conteneurGestion.add(affichageGestionChannels, contraintes);
		
		/* Derniers preparatifs puis affichage. */
		ecran.repaint();
		ecran.menus.miseEnRoute();
		setVisible(true);
	}

	
	/**
	 * Methode deportee pour ajouter tous les boutons d'affichage de menus
	 * @param JPanel boutonsAffichageMenus : le conteneur des menus
	 */
	public void ajouterAffichageMenus(JPanel boutonsAffichageMenus) {
		sauvRap = new BoutonTexte("Sauv/Rap", this);
		mesures = new BoutonTexte("Mesures", this);
		acquisition = new BoutonTexte("Acquisition", this);
		autoset = new BoutonTexte("AutoSet", this);
		utilitaire = new BoutonTexte("Utilitaire", this);
		curseurs = new BoutonTexte("Curseurs", this);
		affichage = new BoutonTexte("Affichage", this);
		recopie = new BoutonTexte("Recopie", this);
		runStop = new BoutonTexte("Run/Stop", this);
		
		boutonsAffichageMenus.add(sauvRap);
		boutonsAffichageMenus.add(mesures);
		boutonsAffichageMenus.add(acquisition);
		boutonsAffichageMenus.add(autoset);
		boutonsAffichageMenus.add(utilitaire);
		boutonsAffichageMenus.add(curseurs);
		boutonsAffichageMenus.add(affichage);
		boutonsAffichageMenus.add(recopie);
		boutonsAffichageMenus.add(runStop);
	}
	
	/**
	 * Methode deportee pour ajouter tous les elements a l'espace central : channel, temps, trigger...
	 * @param JPanel affichageGestionChannels : le conteneur central
	 */
	public void ajouterGestionChannels(JPanel affichageGestionChannels) {
		affichageGestionChannels.add(ch1);	
		affichageGestionChannels.add(ch2);
		affichageGestionChannels.add(gestionTemps);
	}
	
	/**
	 * Methode qui prend en charge la desactivation du menu curseur
	 */
	public void desactiveCurseur() {
		ch1.potPos.addPotentiometreListener(ch1);
		ch2.potPos.addPotentiometreListener(ch2);
		
		ecran.grille.affCurseur = false;
		ecran.grille.repaint();
	}
	
	/**
	 * Methode qui gere l'appui sur un bouton
	 * @param ActionEvent e : l'actionEvent obligatoire :(
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == curseurs.getJButton()) {
			ecran.changerMenu(menuCurseur);
			
		}else if(e.getSource() == mesures.getJButton()) {
			
			
		}else if(e.getSource() == ch1.chMenu.getJButton()) {
			
			
		}else if(e.getSource() == ch2.chMenu.getJButton()) {
			
			
		}else if(e.getSource() == autoset.getJButton()) {
			
			
		}else if(e.getSource() == sauvRap.getJButton()) {
			
			
		}else if(e.getSource() == acquisition.getJButton()) {
			
			
		}else if(e.getSource() == utilitaire.getJButton()) {
			
			
		}else if(e.getSource() == affichage.getJButton()) {
			
			
		}else if(e.getSource() == recopie.getJButton()) {
			
			
		}else if(e.getSource() == runStop.getJButton()) {
			
			
		}
	}
}
