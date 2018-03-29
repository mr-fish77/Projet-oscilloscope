public class MenuMesures extends AbstractMenu {
	/** Index de la source actuelle des mesures. */
	private int src = 0;
	
	public MenuMesures (Signal[] s) {
		super(s);
		refreshItems();
	}
	
	public void refreshItems() {
		if (this.signaux[src].getActive()) {
			String[] freq = this.signaux[src].getFreqAsString();
			String[] ampl = this.signaux[src].getAmplAsString();
			super.setAllTexts(new String[] {
					"Source : " + (src+1),
					"<html>Periode : <br />" + this.calculePeriode() + "</html>",
					"<html>Frequence : <br />" + freq[0] + " " + freq[1] + "</html>",
					"<html>Amplitude : " + ampl[0] + " " + ampl[1] + "</html>",
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
		this.src = (src + 1) % 2;
		System.out.println(src);
		refreshItems();
	}
}
