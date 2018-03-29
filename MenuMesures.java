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
	}
	
	public void refreshItems() {
		super.setAllTexts(new String[] {
				"Source : " + (src+1),
				"Periode : " + this.signaux[src],
				" "});
	}
}
