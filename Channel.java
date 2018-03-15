import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe qui permet de gerer un channel
 * @author Pierre-Yves
 *
 */
public class Channel extends JPanel{
	private Signal[] signaux;		//tableau de signal (on est oblige car on cree souvent de nouveaux signaux)
	private int n;					//indice du signal
	
	private Potentiometre potPos = new Potentiometre();	//potentiometre position verticale
	private Potentiometre potDiv = new Potentiometre();	//potentiometre volts/div
	private BoutonTexte chMenu;		//bouton affichage menu du signal
	
	
	/**
	 * Constructeur qui cree l'interface graphique du channel
	 * @param Signal signal : le signal associe au channel
	 * @param String nomChannel : le nom du channel
	 */
	public Channel(Signal[] signaux, int n,  String nomChannel){
		this.signaux = signaux;
		this.n = n-1;
		
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
		contraintes.weighty = 1;
		contraintes.gridy++;
		add(potDiv, contraintes);
		
	}
}
