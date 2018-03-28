import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

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
	private BoutonTexte cacher, mesures, acquisition, autoset, utilitaire, curseurs, affichage, recopie, runStop, maths;
	private AbstractMenu menuCurseur, menuMaths;
	
	/** Surprise ^^ */
	private Color colorDefaut;	//couleur par defaut de l'oscillo
	private boolean easter = false;	//activation de l'easter egg
	private int posX = -80, posY = -48, largX = 50, largY = 31, deltaX, deltaY, compteurEgg = 0;	//coordonnees
	private Timer dureeEaster;	//timer de l'easter egg
	private Image im;	//la petite surprise qui apparait
	private Clip clip;	//la musique qui va avec
	
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
		colorDefaut = getBackground();

		this.generateur = generateur;

		JPanel conteneurPrincipal = new JPanel();		//pour ne pas avoir de probleme avec les marges des fenetres
		conteneurPrincipal.setLayout(new GridLayout());
		conteneurPrincipal.setOpaque(false);
		conteneurPrincipal.setBackground(Color.RED);
		setContentPane(conteneurPrincipal);
		
		//Definition des menus
		menuCurseur = new MenuCurseur(signaux, this);
		menuMaths = new MenuMath(signaux, this);
		
		
		/* Conteneur de gauche, contient l'ecran et les boutons interactions menus. */
		JPanel conteneurEcran = new JPanel();
		conteneurEcran.setOpaque(false);
		conteneurEcran.setLayout(new GridLayout(1,1));
		add(conteneurEcran);
		
		/* Ecran de l'oscilloscope. */
		ecran = new Ecran(signaux, this);
		generateur.setEcran(ecran);	//pour gerer les repaints lorsqu'un signal est change dans le generateur
		conteneurEcran.add(ecran);
		
		
		/* Conteneur de droite, contient les boutons pour afficher les menus. */
		JPanel conteneurGestion = new JPanel();
		add(conteneurGestion);
		conteneurGestion.setOpaque(false);
		conteneurGestion.setLayout(new GridBagLayout());
		
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 1;
		contraintes.gridx = 0;
		
		/* Conteneur en haut, contient tous les boutons d'affichage de menu. */
		JPanel boutonsAffichageMenus = new JPanel();
		boutonsAffichageMenus.setLayout(new GridLayout(2,5));
		boutonsAffichageMenus.setOpaque(false);
		ajouterAffichageMenus(boutonsAffichageMenus);
		contraintes.gridy = 0;
		contraintes.weighty = 0.5;
		conteneurGestion.add(boutonsAffichageMenus, contraintes);
		
		/* Conteneur du milieu, contient la gestion des channels, du temps et du trigger. */
		ch1 = new Channel(signaux, 1, "CH1", ecran);
		ch2 = new Channel(signaux, 2, "CH2", ecran);
		gestionTemps = new GestionTemps(signaux, ecran);
		
		JPanel affichageGestionChannels = new JPanel();
		affichageGestionChannels.setOpaque(false);
		affichageGestionChannels.setLayout(new GridLayout(1, 4));
		ajouterGestionChannels(affichageGestionChannels);
		contraintes.gridy = 1;
		contraintes.weighty = 1;
		conteneurGestion.add(affichageGestionChannels, contraintes);
		
		/* Derniers preparatifs puis affichage. */
		ecran.repaint();
		setVisible(true);
		
		//a voir plus tard ^^
		try {
			im = ImageIO.read(new File("im.png"));
		}catch(IOException o) {o.printStackTrace();}
		
		try{
	        clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("musique.wav")));
	    }catch (Exception e){e.printStackTrace();}
	}

	
	/**
	 * Methode deportee pour ajouter tous les boutons d'affichage de menus
	 * @param JPanel boutonsAffichageMenus : le conteneur des menus
	 */
	public void ajouterAffichageMenus(JPanel boutonsAffichageMenus) {
		cacher = new BoutonTexte("Cacher Menus", this);
		mesures = new BoutonTexte("Mesures", this);
		acquisition = new BoutonTexte("Acquisition");
		autoset = new BoutonTexte("AutoSet", this);
		utilitaire = new BoutonTexte("Utilitaire");
		curseurs = new BoutonTexte("Curseurs", this);
		affichage = new BoutonTexte("Affichage");
		recopie = new BoutonTexte("Recopie", this);
		runStop = new BoutonTexte("Run/Stop");
		maths = new BoutonTexte("Math Menu", this);
		
		boutonsAffichageMenus.add(cacher);
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
		//pour afficher l'arriere plan
		g2.setColor(colorDefaut);
		g2.fillRect(0, 0, ecran.getWidth(), ecran.getHeight());
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
	 * MEthode qui prend en charge l'easter egg (si vous l'avez trouve)
	 */
	public void easterEgg() {
		//belle couleur bleu nuit
		setBackground(Color.decode("#18219D"));
		
		
		dureeEaster = new Timer(27000, this);	//duree de l'easter egg
		easter = true;
		deltaX = (int)(Math.round(30*Math.random()))+10;
		deltaY = (int)(Math.round(30*Math.random()))+10;
		
		//on recommence la musique
		clip.setFramePosition(0);
        clip.start();
        //on attend la fin de l'intro
        try {
			Thread.sleep(3400);
		}catch(InterruptedException e) {e.printStackTrace();}
        //on lance le time
		dureeEaster.start();
	}
	
	
	/**
	 * Methode qui gere l'affichage graphique
	 * @param Graphics g : l'object graphique interessant
	 */
	public void paint(Graphics g) {
		super.paint(g);
		
		//si easter egg
		if(easter) {
			g.drawImage(im, posX, posY, null);
			
			//on calcule les rebonds
			if(posX<0) {
				deltaX = (int)(Math.round(30*Math.random()))+10;	//on met des vitesses rapides
			}else if(posX>getWidth()-80) {
				deltaX = -(int)(Math.round(30*Math.random()))-10;
			}
			
			if(posY<0) {
				deltaY = (int)(Math.round(10*Math.random()))+10;
			}else if(posY>getHeight()-48) {
				deltaY = -(int)(Math.round(10*Math.random()))-10;
			}
			
			posX +=deltaX;	//on met a jour les positions
			posY +=deltaY;
			
			//on attend 15 ms pour avoir un frame-rate de ~60fps
			try {
				Thread.sleep(15);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			//on recommence la boucle
			repaint();
		}
	}
	
	/**
	 * Methode qui gere l'appui sur un bouton
	 * @param ActionEvent e : l'actionEvent obligatoire :(
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == dureeEaster) {	//prise en charge timer easter egg
			dureeEaster.stop();
			easter = false;
			setBackground(colorDefaut);
			clip.stop();
		}else if (e.getSource() == curseurs.getJButton()) {	//menu curseur
			ecran.changerMenu(menuCurseur);
		}else if(e.getSource() == mesures.getJButton()) {
			
			
		}else if(e.getSource() == ch1.getButton().getJButton()) {
			
			
		}else if(e.getSource() == ch2.getButton().getJButton()) {
			
			
		}else if(e.getSource() == maths.getJButton()) {	//fonction maths
			ecran.changerMenu(menuMaths);
		}else if(e.getSource() == autoset.getJButton()) {	//autoset
			gestionTemps.autoset();	//pour regler l'echelle de temps
			ch1.autoset();	//pour regler chaque signal (en volt)
			ch2.autoset();
			
			//partie easter egg
			if(!easter) {	//on autorise la progression de l'activation que si l'easter egg n'est pas lance
				compteurEgg++;
			}
			
			//Activation si on a clique 8 fois
			if(compteurEgg>8) {
				easterEgg();
				compteurEgg = 0;
			}
		}else if(e.getSource() == recopie.getJButton()) {
			imprimeImage();
		}else if(e.getSource() == cacher.getJButton()) {
			ecran.enleverMenus();
		}
	}
}
