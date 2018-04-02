import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe qui permet de gerer l'echelle et la position au niveau du temps
 */
public class GestionTemps extends JPanel implements PotentiometreListener{
	
	private static final long serialVersionUID = 3745820913624486058L;

	/** Le tableau de signal */
	private Signal[] signaux;
	
	/** Objet interactif : potentiometre de position verticale. */
	private Potentiometre potPos;
	/** Objet interactif : potentiometre de volts/division. */
	private Potentiometre potDiv;
	/** Objet interactif : bouton d'affichage du menu du signal. */
	private BoutonTexte chMenu;
	
	/** L'ecran pour gerer les repaints */
	private Ecran ecran;
	
	/** Decallage des signaux en temps. */
	private double decalage;
	/** Echelle en chiffres. */
	private static final double[] ECHELLES = {1, 0.5, 0.2, 0.1, 0.05, 0.01, 0.005, 0.001, 0.0005, 0.0001, 0.00005, 0.00001, 0.000005, 0.000001, 0.0000005, 0.0000001};
	/** Echelle en toutes lettres. */
	private static final String[] STR_ECHELLES = {"1 s", "0.5 s", "0.2 s", "0.1 s", "50 ms", "10 ms", "5 ms", "1 ms", "0.5 ms", "0.1 ms", "50 micro s", "10 micro s", "5 micro s", "1 micro s", "0.5 micro s", "0.1 micro s"};
	/** Index actuel dans le tableau des echelles. */
	private int compteurEchelle = 0;
	
	
	/**
	 * Constructeur qui cree l'interface graphique du channel
	 * @param signaux : les signaux
	 * @param ecran : l'ecran pour gerer les repaint()
	 */
	public GestionTemps(Signal[] signaux, Ecran ecran){
		this.signaux = signaux;
		this.ecran = ecran;
		
		//on attribue les valeurs par defauts
		this.signaux[0].echelleX = ECHELLES[compteurEchelle];
		this.signaux[1].echelleX = ECHELLES[compteurEchelle];
		ecran.bas.setTemps("Temps : " + STR_ECHELLES[compteurEchelle] + "/div");	//et les textes affiches par defaut dans les notifications
		ecran.bas.setText("Decalage temps : " + String.format("%.2f", (double)(Math.round(decalage*100))/100) + " div");
		
		setOpaque(false);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));	//on rajoute des bordures
		setLayout(new GridBagLayout());//Layout plus complique mais permet de gerer a peu pres bien
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.gridx = 0;
		contraintes.weightx = 1;
		contraintes.fill = GridBagConstraints.BOTH;
		
		//Nom du channel
		JLabel titreTemps = new JLabel("Temps");
		titreTemps.setHorizontalAlignment(JLabel.CENTER);		//on met le texte au centre
		titreTemps.setFont(new Font("Calibri", Font.BOLD, 20));	//on change la police
		contraintes.weighty = 0.5;
		contraintes.gridy++;
		add(titreTemps, contraintes);
				
		//texte au dessus du potentiometre de position
		JLabel descriptionPotPos = new JLabel("Position");
		descriptionPotPos.setHorizontalAlignment(JLabel.CENTER);
		descriptionPotPos.setVerticalAlignment(JLabel.BOTTOM);
		contraintes.gridy++;
		contraintes.weighty = 0.2;
		add(descriptionPotPos, contraintes);
		
		//potentiometre de position
		potPos = new Potentiometre();
		potPos.setPotentiometreListener(this);
		contraintes.gridy++;
		contraintes.weighty = 0.5;
		add(potPos, contraintes);
		
		//bouton d'affichage du menu du channel
		chMenu = new BoutonTexte("HORIZONTAL Menu");
		contraintes.weighty = 0.5;
		contraintes.gridy++;
		add(chMenu, contraintes);
		
		//texte au dessus du potentiometre de volts/div
		JLabel descriptionPotDiv = new JLabel("Sec/Div");
		descriptionPotDiv.setHorizontalAlignment(JLabel.CENTER);
		descriptionPotDiv.setVerticalAlignment(JLabel.BOTTOM);
		contraintes.gridy++;
		add(descriptionPotDiv, contraintes);
		
		//potentiometre de volts/div
		potDiv = new Potentiometre();
		potDiv.setPotentiometreListener(this);
		contraintes.weighty = 1;
		contraintes.gridy++;
		add(potDiv, contraintes);
	}
	
	/**
	 * Permet de gerer la fonction autoset au niveau du temps
	 */
	public void autoset() {
		int compteur = 0;
		
		//on diminue l'echelle jusqu'a voir 4 periodes
		while(
				compteur < ECHELLES.length && 
				ECHELLES[compteur]*Signal.CASE_X*signaux[0].getFreq() > 4) 
		{
			compteur ++;
		}
		
		compteurEchelle = compteur;
		mAJEchelle();
	}
	
	
	/** Methode unifiee de mise a l'echelle.
	 * Concretement, mise a jour des donnees puis affichage.
	 */
	public void mAJEchelle() {
		signaux[0].echelleX = ECHELLES[compteurEchelle];
		signaux[1].echelleX = ECHELLES[compteurEchelle];
		ecran.bas.setTemps("Temps : " + STR_ECHELLES[compteurEchelle] + "/div");
		ecran.grille.repaint();
	}
	
	
	/** Permet l'interaction avec un potentiometre
	 * @param potentiometre : la source de l'evenement
	 * @param evolutionCran : + ou -1
	 */
	public void potentiometrePerformed(Potentiometre potentiometre, int evolutionCran) {
		if(potentiometre.equals(potPos)) {
			decalage += .04*evolutionCran;	//on implemente/reduit le decalage (0.04 correspond au reglage sur le vrai oscillo)
			signaux[0].decalageX = decalage;
			signaux[1].decalageX = decalage;
			ecran.bas.setText("Decalage temps : " + String.format("%.2f", (double)(Math.round(decalage*100))/100) + " div");
			ecran.grille.repaint();
			
		} else if(potentiometre.equals(potDiv)){
			if ( //on regarde qu'on est toujours dans les clous du tableau
					(compteurEchelle >= 0 && compteurEchelle < (ECHELLES.length - 1) && evolutionCran > 0) || 
					(compteurEchelle < ECHELLES.length && compteurEchelle > 0 && evolutionCran < 0)) {
				compteurEchelle += evolutionCran;
				//attribution au 2 signaux
				mAJEchelle();
			}
		}
	}
}
