public class MenuMesures extends AbstractMenu {
	/** Index de la source actuelle des mesures. */
	private int src = 0;
	
	/** Cree un MenuMesures.
	 * @param s Les signaux.
	 */
	public MenuMesures (Signal[] s) {
		super(s);
		refreshItems();
	}
	
	/** Actualise les boutons. */
	public void refreshItems() {
		if (this.signaux[src].getActive()) {
			String[] freq = this.signaux[src].getFreqAsString();
			String[] ampl = this.signaux[src].getAmplAsString();
			super.setAllTexts(new String[] {
					"Source : " + (src+1),
					"<html>Periode : <br />" + this.calculePeriode() + "</html>",
					"<html>Frequence : <br />" + freq[0] + " " + freq[1] + "</html>",
					"<html>Amplitude : <br />" + ampl[0] + " " + ampl[1] + "</html>",
					" "});
		} else {
			super.setAllTexts(new String[] {
					"Source : " + (src+1),
					"<html>Periode : <br /> ??? </html>",
					"<html>Frequence : <br /> ??? </html>",
					"<html>Amplitude : <br /> ??? </html>",
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
	
	/** Action liee au bouton 1.
	 * Change le Signal a l'ecran et 
	 */
	public void actionBouton1 () {
		this.src = (src + 1) % 2;
		System.out.println(src);
		refreshItems();
	}
}
