import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe qui permet de gerer l'echelle et la position au niveau du temps
 */
public class GestionTemps extends JPanel implements PotentiometreListener{
	/** Le tableau de signal */
	private Signal[] signaux;		//tableau de signal (on est oblige car on cree souvent de nouveaux signaux)
	
	/** Les boutons d'interactions */
	private Potentiometre potPos;	//potentiometre position verticale
	private Potentiometre potDiv;	//potentiometre volts/div
	private BoutonTexte chMenu;		//bouton affichage menu du signal
	
	/** L'ecran pour gerer les repaints */
	private Ecran ecran;			//Ecran de l'oscillo pour gerer les repaint() et le bas de l'ecran
	
	/** Les echelles et decalages */
	private double decalage;		//decalage des signaux en temps
	//echelles en chiffres et lettres
	private static final double[] ECHELLES = {1, 0.5, 0.2, 0.1, 0.05, 0.01, 0.005, 0.001, 0.0005, 0.0001, 0.00005, 0.00001, 0.000005, 0.000001, 0.0000005, 0.0000001};
	private static final String[] STR_ECHELLES = {"1 s", "0.5 s", "0.2 s", "0.1 s", "50 ms", "20 ms", "5 ms", "1 ms", "0.5 ms", "0.1 ms", "50 micro s", "10 micro s", "5 micro s", "1 micro s", "0.5 micro s", "0.1 micro s"};
	private int compteurEchelle = 0;	//pour connaitre la position dans le tableau
	
	
	/**
	 * Constructeur qui cree l'interface graphique du channel
	 * @param Signal[] signaux : les signaux
	 * @param Ecran ecran : l'ecran pour gerer les repaint()
	 */
	public GestionTemps(Signal[] signaux, Ecran ecran){
		this.signaux = signaux;
		this.ecran = ecran;
		
		//on attribue les valeurs par defauts
		this.signaux[0].echelleX = ECHELLES[compteurEchelle];
		this.signaux[1].echelleX = ECHELLES[compteurEchelle];
		ecran.bas.setTemps("Temps : " + STR_ECHELLES[compteurEchelle] + "/div");	//et les textes affiches par defaut dans les notifications
		ecran.bas.setText("Decalage temps : " + String.format("%.2f", (double)(Math.round(decalage*100))/100) + " div");
		
		setLayout(new GridBagLayout());//Layout plus complique mais permet de gerer a peu pres bien
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.gridx = 0;
		contraintes.weightx = 1;
		contraintes.fill = GridBagConstraints.BOTH;
		
		//Nom du channel
		JLabel titreTemps = new JLabel("Temps");
		contraintes.weighty = 0.5;
		contraintes.gridy++;
		add(titreTemps, contraintes);
				
		//texte au dessus du potentiometre de position
		JLabel descriptionPotPos = new JLabel("Position");
		contraintes.gridy++;
		contraintes.weighty = 0.2;
		add(descriptionPotPos, contraintes);
		
		//potentiometre de position
		potPos = new Potentiometre();
		potPos.addPotentiometreListener(this);
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
		contraintes.gridy++;
		add(descriptionPotDiv, contraintes);
		
		//potentiometre de volts/div
		potDiv = new Potentiometre();
		potDiv.addPotentiometreListener(this);
		contraintes.weighty = 1;
		contraintes.gridy++;
		add(potDiv, contraintes);
	}
	
	
	/** Permet l'interaction avec un potentiometre
	 * @param Potentiometre potentiometre : la source de l'evenement
	 * @param int evolutionCran : + ou -1
	 */
	public void potentiometrePerformed(Potentiometre potentiometre, int evolutionCran) {
		if(potentiometre.equals(potPos)) {	//si c'est le potentiometre de position
			decalage += 0.04*evolutionCran;	//on implemente/reduit le decalage (0.04 correspond au reglage sur le vrai oscillo)
			//on l'attribue a chacun des signaux
			signaux[0].decalageX = decalage;
			signaux[1].decalageX = decalage;
			ecran.bas.setText("Decalage temps : " + String.format("%.2f", (double)(Math.round(decalage*100))/100) + " div");
			ecran.grille.repaint();
			
		}else if(potentiometre.equals(potDiv)){	//reglage echele
			if((compteurEchelle >= 0 && compteurEchelle < (ECHELLES.length - 1) && evolutionCran > 0) || (compteurEchelle < (ECHELLES.length) && compteurEchelle > 0 && evolutionCran < 0)) {	//on regarde qu'on est toujours dans les clous du tableau
				compteurEchelle += evolutionCran;
				//attribution au 2 signaux
				signaux[0].echelleX = ECHELLES[compteurEchelle];
				signaux[1].echelleX = ECHELLES[compteurEchelle];
				ecran.bas.setTemps("Temps : " + STR_ECHELLES[compteurEchelle] + "/div");
				ecran.grille.repaint();
			}
		}
	}
}
