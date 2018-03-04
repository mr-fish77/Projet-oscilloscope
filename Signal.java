
public class Signal {
	
	/** Amplitude du signal en volts. */
	private double amplitude;
	/** Frequence du signal en Hz. */
	private int freq;
	
	/** Valeur maximale de l'amplitude d'un signal : 10 V */
	private final double MAX_AMP = 10.;
	/** Valeur maximale de la fréquence d'un signal : 2 Ghz. */
	private final int MAX_FREQ = (int) 2.*Math.pow(10, 9); 
	
	/** Valeurs possibles des unités ou formes de signal. */
	public static final String [] SIGNAL_TYPES = {"SIN", "TRI", "REC"}, FREQ_UNITES = {"Hz", "kHz", "MHz", "GHz"},  AMPL_UNITES = {"mV", "V"};

	/** Actif ou non, affiché ou non. 
	 * Un signal actif est nul.
	 * Le paramètre displayed devrait être dans Channel plutot que Signal non ?
	 * */
	private boolean active, displayed;
	
	/**
	 * Constructeur par defaut.
	 */
	public Signal() {
		resetSignal();
		active = false;
		displayed = false;
	}
	
	/** Met les attributs du signal à leur valeur par défaut. */
	public void resetSignal() {
		amplitude = MAX_AMP;
		freq = 50;
	}
	
	/** Met les attributs du signal aux valeurs renseignées.
	 * C'est une sorte de constructeur qui ne réinitialise pas. 
	 * @param amp L'amplitude désirée.
	 * @param f La fréquence souhaitée.
	 */
	public void setSignal(double amp, int f) {
		amplitude = this.setAmplitude(amp);
		freq = this.setFreq(f);
	}

	/**
	 * @return La forme du signal.
	 */
	public String getForme() {return "SIN";}

	/**
	 * @return L'amplitude du signal.
	 */
	public double getAmplitude() {
		return amplitude;
	}

	/**
	 * @return La frÃ©quence du signal.
	 */
	public int getFreq() {
		return freq;
	}

	/** 
	 * @return L'amplitude à 3 chiffres significatifs, et son unité.
	 */
	public String[] getAmplAsString() {
		String[] s = new String[2];
		if(amplitude > 1) {
			s[0] = Double.toString(amplitude);
			s[1] = "V";
			return s;
		} else {
			s[0] = Double.toString(amplitude * 1000);
			s[1] = "mV";
			return s;
		}
	}
	
	/** 
	 * @return La fréquence à 3 chiffres significatifs, et son unité.
	 */
	public String [] getFreqAsString() {
		String[] s = new String[2];
		if (freq < 1000) {
			s[0] = Integer.toString(freq);
			s[1] = "Hz";
			return  s;
		} else if (freq < 1000000) {
			s[0] = Double.toString(freq / 1000.);
			s[1] = "kHz";
			return s;
		} else if (freq < 1000000000) {
			s[0] = Double.toString(freq / 1000000.);
			s[1] = "MHz";
			return s;
		} else {
			s[0] = Double.toString(freq / 1000000000.);
			s[1] = "GHz";
			return s;
		}
	}

	/**
	 * @param amplitude
	 *            L'amplitude Ã  modifier.
	 * 	      Valeur comprise entre 0.1 et MAX_AMP compris.
	 */
	public void setAmplitude(double amplitude) {
		if (amplitude > MAX_AMP) {
			this.amplitude = MAX_AMP;
		} else if (amplitude < 0) {
			this.amplitude = .1;
		} else {
			this.amplitude = amplitude;
		}
	}

	/**
	 * @param freq
	 *            La frÃ©quence Ã  appliquer.
	 * 	      Valeur comprise entre 1 Hz et MAX_FREQ.
	 */
	public void setFreq(int freq) {
		if (freq > MAX_FREQ) {
			this.freq = MAX_FREQ;
		} else if (freq < 1) {
			this.freq = 1;
		} else {
			this.freq = freq;
		}
	}
	
	/** Active un signal.
	 * @param b true pour activer, false pour désactiver.
	 */
	public void setActive(boolean b) {
		this.active = b;
	}
	
	/** Indique si un signal est actif ou non.
	 * @return true si actif, false si inactif.
	 */
	public boolean getActive() {
		return this.active;
	}
}
