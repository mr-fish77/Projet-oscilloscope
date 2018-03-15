import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe qui gere l'affichage de l'oscilloscope a l'ecran
 * @author Pierre-Yves
 *
 */
public class Oscilloscope extends JFrame{
	private Channel ch1;		//channel du premier signal
	private Channel ch2;		//channel du deuxieme signal
	private GestionTemps gestionTemps;	//Permet de gerer le temps (position et echelle)
	
	private Generateur generateur;	//Pour pouvoir interagir avec le generateur
	
	private Ecran ecran;		//ecran d'affichage de l'oscilloscope
	
	//boutons du menu du haut de l'oscilloscope
	private BoutonTexte sauvRap = new BoutonTexte("Sauv/Rap");
	private BoutonTexte mesures = new BoutonTexte("Mesures");
	private BoutonTexte acquisition = new BoutonTexte("Acquisition");
	private BoutonTexte autoset = new BoutonTexte("AutoSet");
	private BoutonTexte utilitaire = new BoutonTexte("Utilitaire");
	private BoutonTexte curseurs = new BoutonTexte("Curseurs");
	private BoutonTexte affichage = new BoutonTexte("Affichage");
	private BoutonTexte recopie = new BoutonTexte("Recopie");
	private BoutonTexte runStop = new BoutonTexte("Run/Stop");
	

	public Oscilloscope(Signal[] signaux, Generateur generateur){
		super("Oscilloscope");      //constructeur par defaut de la classe JFrame
		setSize(1200, 600);
		setMinimumSize(new Dimension(600, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.generateur = generateur;			//Genrateur de signaux

		JPanel conteneurPrincipal = new JPanel();		//pour ne pas avoir de probleme avec les marges des fenetres
		conteneurPrincipal.setLayout(new GridLayout());
		setContentPane(conteneurPrincipal);
		
		
		//Conteneur de gauche, contient l'ecran et les boutons interactions menus
		JPanel conteneurEcran = new JPanel();
		conteneurEcran.setLayout(new GridLayout(1,1));
		add(conteneurEcran);
		
		ecran = new Ecran(signaux);	//ecran de l'oscilloscope
		generateur.setEcran(ecran);
		conteneurEcran.add(ecran);
		
		
		//Conteneur de droite, contient les boutons pour afficher les menus 
		JPanel conteneurGestion = new JPanel();
		add(conteneurGestion);
		conteneurGestion.setLayout(new GridBagLayout());
		
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 1;
		contraintes.gridx = 0;
		
		//Conteneur en haut, contient tous les boutons d'affichage de menu
		JPanel boutonsAffichageMenus = new JPanel();
		boutonsAffichageMenus.setLayout(new GridLayout(2,5));
		ajouterAffichageMenus(boutonsAffichageMenus);	//methode deportee pour ajouter les elements
		contraintes.gridy = 0;
		contraintes.weighty = 0.5;
		conteneurGestion.add(boutonsAffichageMenus, contraintes);
		
		//conteneur du milieu, contient la gestion des channels, du temps et du trigger
		ch1 = new Channel(signaux, 1, "CH1", ecran);	//channels associes au signaux
		ch2 = new Channel(signaux, 2, "CH2", ecran);
		gestionTemps = new GestionTemps(signaux, ecran);
		
		JPanel affichageGestionChannels = new JPanel();
		affichageGestionChannels.setLayout(new GridLayout(1, 4));
		affichageGestionChannels.setBackground(Color.GREEN);
		ajouterGestionChannels(affichageGestionChannels);	//methode deportee pour ajouter les elements
		contraintes.gridy = 1;
		contraintes.weighty = 1;
		conteneurGestion.add(affichageGestionChannels, contraintes);
		
		//conteneur en bas, pour activer/desactiver les channels
		JPanel branchementChannels = new JPanel();
		branchementChannels.setBackground(Color.BLUE);
		contraintes.gridy = 2;
		contraintes.weighty = 0.5;
		conteneurGestion.add(branchementChannels, contraintes);
		
		setVisible(true);
	}

	
	/**
	 * Methode deportee pour ajouter tous les boutons d'affichage de menus
	 * @param JPanel boutonsAffichageMenus : le conteneur des menus
	 */
	public void ajouterAffichageMenus(JPanel boutonsAffichageMenus) {
		boutonsAffichageMenus.add(sauvRap);	//on les ajoute un par un
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
		affichageGestionChannels.add(ch1);			//Channels
		affichageGestionChannels.add(ch2);
		affichageGestionChannels.add(gestionTemps);	//Gestion du temps
		
	}
}
