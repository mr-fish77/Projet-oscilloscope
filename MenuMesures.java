import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;


public class MenuMesures extends AbstractMenu {
	private JButton source = new JButton ("Source : CH1");
	private JButton periode = new JButton ("Periode");
	private JButton frequence = new JButton ("Frequence");
	private JButton amplitude = new JButton ("Amplitude");
	
	/** Liste des mesures possibles. A supprimer eventuellement plus tard. */
	private final static String[] MESURES_POSSIBLES = {"Periode", "Frequence", "Amplitude"};
	/** Index de la source actuelle des mesures. */
	private int src = 0;
	
	public MenuMesures (Signal[] s) {
		super(s);
	}
	
	public void actionPerformed (ActionEvent e){
		int indexBouton = Integer.parseInt(e.getActionCommand());
		
		if (indexBouton == 0) { 		// Changement de la source
			if (src == 0){		// On passe de 1 a 2
				source.setText("Source : CH2");
			} else {			// On passe de 2 a 1
				source.setText("Source : CH1");
			}
		}
		else if (indexBouton == 1) {	// Mesure de la periode
			
		}
	}
	
	/** Rafraichit les textes des boutons. */
	private void refresh() {
		// Source actuelle
		bouton1.setText("Source : " + (src + 1));
		
		// 
	}
}
