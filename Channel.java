import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe qui permet de gerer un channel
 * @author Pierre-Yves
 *
 */
public class Channel extends JPanel implements PotentiometreListener{
	private Signal[] signaux;		//tableau de signal (on est oblige car on cree souvent de nouveaux signaux)
	private int n;					//indice du signal
	private String nomChannel;		//nom du channel
	
	private Ecran ecran;	//l'ecran pour gerer les repaint()
	
	public Potentiometre potPos;	//potentiometre position verticale
	private Potentiometre potDiv;	//potentiometre volts/div
	private BoutonTexte chMenu;		//bouton affichage menu du signal
	
	private double decalage = 0.0;		//decalage en hauteur du signal
	
	//Echelles des ordonnees du signal
	private static final double[] ECHELLES = {10.0, 5.0, 2.0, 1.0, 0.5, 0.2, 0.1, 0.05};
	private int compteurEchelle = 1;	//position dans le tableau du dessus
	
	
	
	
	/**
	 * Constructeur qui cree l'interface graphique du channel
	 * @param Signal signal : le signal associe au channel
	 * @param String nomChannel : le nom du channel
	 */
	public Channel(Signal[] signaux, int n,  String nomChannel, Ecran ecran){
		this.signaux = signaux;
		this.n = n-1;
		this.ecran = ecran;
		this.nomChannel = nomChannel;
		
		this.signaux[this.n].echelleY = ECHELLES[compteurEchelle];
		ecran.bas.setCh(nomChannel + " : " + ECHELLES[compteurEchelle] + " Volts/div", this.n);
		
		setLayout(new GridBagLayout());//Layout plus complique mais permet de gerer a peu pres bien
		GridBagConstraints contraintes = new GridBagConstraints();
		contraintes.gridx = 0;
		contraintes.weightx = 1;
		contraintes.fill = GridBagConstraints.BOTH;
		
		//Nom du channel
		JLabel titreChannel = new JLabel(nomChannel);
		contraintes.weighty = 0.5;
		contraintes.gridy++;
		add(titreChannel, contraintes);
		
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
		chMenu = new BoutonTexte(nomChannel + " Menu");
		contraintes.weighty = 0.5;
		contraintes.gridy++;
		add(chMenu, contraintes);
		
		//texte au dessus du potentiometre de volts/div
		JLabel descriptionPotDiv = new JLabel("Volts/Div");
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
		if(potentiometre.equals(potPos)) {	//reglage du deltaY
			decalage += 0.04*evolutionCran;	//reglage par defaut de l'oscillo
			signaux[n].decalageY = decalage;
			ecran.bas.setText("Decalage " + nomChannel + " : " + String.format("%.2f", (double)(Math.round(decalage*100))/100) + " div");
			ecran.repaint();
			
		}else if(potentiometre.equals(potDiv)) {	//reglage de l'echelle
			if((compteurEchelle >= 0 && compteurEchelle < (ECHELLES.length - 1) && evolutionCran > 0) || (compteurEchelle < (ECHELLES.length) && compteurEchelle > 0 && evolutionCran < 0)) {	//on regarde qu'on est toujours dans les clous du tableau
				compteurEchelle += evolutionCran;
				signaux[n].echelleY = ECHELLES[compteurEchelle];
				ecran.bas.setCh(nomChannel + " : " + ECHELLES[compteurEchelle] + " Volts/div", n);
				ecran.repaint();
			}
			
		}
		
	}
}
