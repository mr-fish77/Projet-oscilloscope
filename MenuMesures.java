import java.awt.Graphics;

public class MenuMesures extends AbstractMenu {
	/** Index de la source actuelle des mesures. */
	private int src = 0;
	
	/** Cree un MenuMesures.
	 * @param s Les signaux.
	 */
	public MenuMesures () {
		super();
		refreshItems();
	}
	
	/** Actualise les boutons. */
	public void refreshItems() {
		if (signaux[src].getActive()) {
			String[] freq = signaux[src].getFreqAsString();
			String[] ampl = signaux[src].getAmplAsString();
			super.setAllTexts(new String[] {
					"Source : " + (src+1),
					linesToHtml(new String[] {"Periode : ",   this.calculePeriode()  }),
					linesToHtml(new String[] {"Frequence : ", freq[0] + " " + freq[1]}),
					linesToHtml(new String[] {"Amplitude : ", ampl[0] + " " + ampl[1]}),
					" "});
		} else {
			super.setAllTexts(new String[] {
					"Source : " + (src+1),
					linesToHtml(new String[] {"Periode : ", "???"}),
					linesToHtml(new String[] {"Frequence : ", "???"}),
					linesToHtml(new String[] {"Amplitude : ", "???"}),
					" "	
			});
		}
	}
	
	/** Calcule la periode liee au signal.
	 * @return La periode avec son unite.
	 */
	private String calculePeriode() {
		double p = 1./this.signaux[src].getFreq();
		if (p > .001) {
			return Double.toString(p*1000.) +" ms";
		} else if (p > .000001) {
			return Double.toString(p*1000000.) +" µs";
		} else {
			return Double.toString(p*1000000000.) +" ns";
		}
	}
	
	
	/** Methode normalement appelee pour afficher quelque chose a la grille
	 * Sert ici pour mettre a jour l'affichage des boutons
	 * @param Graphics g : l'objet graphique
	 * @param int hauteur : la hauteur de la grille
	 * @param int largeur : la largeur de la grille
	 */
	public void paintGrille(Graphics g, int hauteur, int largeur) {
		refreshItems();
	}
	
	
	/** Action liee au bouton 1.
	 * Change le Signal a l'ecran et 
	 */
	public void actionBouton1 () {
		this.src = (src + 1) % 2;
		refreshItems();
	}
	
	public void actionBouton2() {};
	public void actionBouton3() {};
	public void actionBouton4() {};
	public void actionBouton5() {};
}
