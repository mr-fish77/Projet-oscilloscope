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
	/** Les signaux. Un seul est utilise 
	 * mais il faut un tableau pour conserver un pointeur memoire correct. */
	private Signal[] signaux;
	/** Indice du signal dans le tableau de signaux. */
	private int n;
	/** Nom du Channel. */
	private String nomChannel;
	/** Ecran (pour gerer les repaint(). */
	private Ecran ecran;
	
	/** Potentiometre de position verticale. */
	public Potentiometre potPos;
	/** Potentiometre de Volts par division (horizontal). */
	private Potentiometre potDiv;
	/** Bouton qui affiche le menu du Signal concerne. */
	private BoutonTexte chMenu;
	/** Decalage en hauteur du signal. */
	private double decalage = 0.0;
	/** Echelles des ordonnees du signal. */
	private static final double[] ECHELLES = {10.0, 5.0, 2.0, 1.0, 0.5, 0.2, 0.1, 0.05};
	/** Position dans le tableau des echelles. */
	private int compteurEchelle = 1;
	
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
	
	/** Permet d'acceder au bouton
	 * @return BoutonTexte le bouton
	 */
	public BoutonTexte getButton() {
		return chMenu;
	}
}
