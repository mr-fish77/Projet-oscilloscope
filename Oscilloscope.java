import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe qui gere l'affichage de l'oscilloscope a l'ecran
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
	private BoutonTexte sauvRap, mesures, acquisition, autoset, utilitaire, curseurs, affichage, recopie, runStop, maths;
	private AbstractMenu menuCurseur, menuMaths;
	
	/** Genere la fenetre principale de l'oscilloscope.
	 * @param Signal[] signaux : le tableau des signaux
	 * @param Generateur generateur : le generateur (pour gerer les repaints au besoin)
	 */
	public Oscilloscope(Signal[] signaux, Generateur generateur){
		super("Oscilloscope");
		setIconImage(new ImageIcon("icone.png").getImage());	//icone de la fenetre
		setSize(1200, 600);
		setMinimumSize(new Dimension(600, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.generateur = generateur;

		JPanel conteneurPrincipal = new JPanel();		//pour ne pas avoir de probleme avec les marges des fenetres
		conteneurPrincipal.setLayout(new GridLayout());
		setContentPane(conteneurPrincipal);
		
		//Definition des menus
		menuCurseur = new MenuCurseur(signaux, this);
		menuMaths = new MenuMath(signaux, this);
		
		
		/* Conteneur de gauche, contient l'ecran et les boutons interactions menus. */
		JPanel conteneurEcran = new JPanel();
		conteneurEcran.setLayout(new GridLayout(1,1));
		add(conteneurEcran);
		
		/* Ecran de l'oscilloscope. */
		ecran = new Ecran(signaux, this);
		generateur.setEcran(ecran);	//pour gerer les repaints lorsqu'un signal est change dans le generateur
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
		setVisible(true);
	}

	
	/**
	 * Methode deportee pour ajouter tous les boutons d'affichage de menus
	 * @param JPanel boutonsAffichageMenus : le conteneur des menus
	 */
	public void ajouterAffichageMenus(JPanel boutonsAffichageMenus) {
		sauvRap = new BoutonTexte("Sauv/Rap");
		mesures = new BoutonTexte("Mesures", this);
		acquisition = new BoutonTexte("Acquisition");
		autoset = new BoutonTexte("AutoSet", this);
		utilitaire = new BoutonTexte("Utilitaire");
		curseurs = new BoutonTexte("Curseurs", this);
		affichage = new BoutonTexte("Affichage");
		recopie = new BoutonTexte("Recopie", this);
		runStop = new BoutonTexte("Run/Stop");
		maths = new BoutonTexte("Math Menu", this);
		
		boutonsAffichageMenus.add(sauvRap);
		boutonsAffichageMenus.add(mesures);
		boutonsAffichageMenus.add(acquisition);
		boutonsAffichageMenus.add(autoset);
		boutonsAffichageMenus.add(utilitaire);
		boutonsAffichageMenus.add(curseurs);
		boutonsAffichageMenus.add(affichage);
		boutonsAffichageMenus.add(recopie);
		boutonsAffichageMenus.add(runStop);
		boutonsAffichageMenus.add(maths);
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
	 * Methode qui prend en charge la desactivation du menu curseur.
	 * Notamment, il faut les retirer de l'ecran et re-allouer 
	 * les potentiometres aux bons objets pour leur Listener.
	 */
	public void desactiveCurseur() {
		//on reatribue les listener aux potentiometres comme il faut
		ch1.potPos.setPotentiometreListener(ch1);
		ch2.potPos.setPotentiometreListener(ch2);
	}
	
	/**
	 * Methode qui fait une capture de ce qui est imprime a l'ecran
	 */
	public void imprimeImage() {
		//on cree l'image a la bonne taille
		BufferedImage image = new BufferedImage(ecran.getWidth(), ecran.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();	//on cree un objet graphique qui va contenir l'information de l'ecran
		ecran.paint(g2);	//on peint l'ecran dans cet objet graphique : on a une image
		
		//pour choisir ou non d'enregistrer
		JFrame affichageImage = new JFrame("Recopie");
		affichageImage.setIconImage(new ImageIcon("icone.png").getImage());	//icone de la fenetre
		affichageImage.setLayout(new BorderLayout());
		
		//affichage de l'image
		JLabel affImage = new JLabel(new ImageIcon(image));
		affImage.setBackground(Color.BLACK);	//on met un fond noir
		affImage.setOpaque(true);
		affichageImage.add(affImage, BorderLayout.CENTER);
		
		//bouton pour choisir ou non la sauvegarde
		JButton sauvegarde = new JButton("Sauvegarder ! (dans le fichier \"capture.png\")");
		affichageImage.add(sauvegarde, BorderLayout.SOUTH);
		sauvegarde.addActionListener(new ActionListener() {	//on definit direct l'actionListener
			
			public void actionPerformed(ActionEvent e) {
				try {	//sauvegarde de l'image
					ImageIO.write(image, "png", new File("capture.png"));
				}catch(IOException o) {
					o.printStackTrace();
				}
				affichageImage.setVisible(false);	//et du coup fermeture de la fenetre
				affichageImage.dispose();
			}
		});
		
		//on ajuste la taille de la fenetre au minimum et on affiche
		affichageImage.pack();
		affichageImage.setVisible(true);
	}
	
	/**
	 * Methode qui gere l'appui sur un bouton
	 * @param ActionEvent e : l'actionEvent obligatoire :(
	 */
	public void actionPerformed(ActionEvent e) {
		if		(e.getSource() == curseurs.getJButton()) {
			ecran.changerMenu(menuCurseur);
		}else if(e.getSource() == mesures.getJButton()) {
			
			
		}else if(e.getSource() == ch1.getButton().getJButton()) {
			
			
		}else if(e.getSource() == ch2.getButton().getJButton()) {
			
			
		}else if(e.getSource() == maths.getJButton()) {
			ecran.changerMenu(menuMaths);
		}else if(e.getSource() == autoset.getJButton()) {
			gestionTemps.autoset();	//pour regler l'echelle de temps
			ch1.autoset();	//pour regler chaque signal (en volt)
			ch2.autoset();
		}else if(e.getSource() == recopie.getJButton()) {
			imprimeImage();
		}
	}
}
