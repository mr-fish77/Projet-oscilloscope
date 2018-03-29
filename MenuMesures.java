import java.awt.*;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.*;


public class MenuMesures extends AbstractMenu {
	/** Liste des mesures possibles. A supprimer eventuellement plus tard. */
	private final static String[] MESURES_POSSIBLES = {"Periode", "Frequence", "Amplitude"};
	/** Index de la source actuelle des mesures. */
	private int src = 0;
	
	public MenuMesures (Signal[] s) {
		super(s);
		refreshItems();
	}
	
	public void refreshItems() {
		String[] freq = this.signaux[src].getFreqAsString();
		String[] ampl = this.signaux[src].getAmplAsString();
		super.setAllTexts(new String[] {
				"Source : " + (src+1),
				"Periode : " + this.calculePeriode(),
				"Frequence : " + freq[0] + " " + freq[1],
				"Amplitude : " + ampl[0] + " " + ampl[1],
				" "});
	}
	
	public String calculePeriode() {
		double p = 1./this.signaux[src].getFreq();
		if (p > .001) {
			return Double.toString(p*1000.) +"ms";
		} else if (p > .000001) {
			return Double.toString(p*1000000.) +"µs";
		} else {
			return Double.toString(p*1000000000.) +"ns";
		}
	}
	
	public void actionBouton1 () {
		this.src = (src + 1) % 1;
		System.out.println(src);
	}
}
