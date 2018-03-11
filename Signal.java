public abstract class Signal {
	
	/** Amplitude du signal en volts. */
	private double amplitude;
	/** Frequence du signal en Hz. */
	private double freq;
	/** Numéro du signal : 1 ou 2. */
	public final int NUMERO;
	
	/** Valeur maximale de l'amplitude d'un signal : 10 V */
	private final double MAX_AMP = 10.;
	/** Valeur maximale de la fréquence d'un signal : 2 Ghz. */
	private final double MAX_FREQ =  (2.*Math.pow(10, 9)); 
	
	/** Valeurs possibles des unités ou formes de signal. */
	public static final String [] SIGNAL_TYPES = {"SIN", "TRI", "REC"}, FREQ_UNITES = {"Hz", "kHz", "MHz", "GHz"},  AMPL_UNITES = {"mV", "V"};

	/** Actif ou non, affiché ou non. 
	 * Un signal actif est nul.
	 * */
	private boolean active;
	
	/** Points a afficher. */
	public double[][] nuagePoint;

	/**
	 * Constructeur par defaut.
	 */
	public Signal(int n) {
		amplitude = 5;
		freq = 100.;
		active = false;
		NUMERO = n;
	}
	
	/** Met les attributs du signal aux valeurs renseignées.
	 * C'est une sorte de constructeur qui ne reinitialise pas. 
	 * @param amp L'amplitude desiree.
	 * @param f La frequence souhaitee.
	 */
	public void setSignal(double amp, double f) {
		this.setAmplitude(amp);
		this.setFreq(f);
	}

	/**
	 * @return La forme du signal.
	 */
	public abstract String getForme();

	/**
	 * @return L'amplitude du signal.
	 */
	public double getAmplitude() {
		return amplitude;
	}

	/**
	 * @return La frequence du signal.
	 */
	public double getFreq() {
		return freq;
	}

	/** 
	 * @return L'amplitude a 4 chiffres significatifs, et son unite.
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
	 * @return La frequence a 3 chiffres significatifs, et son unite.
	 */
	public String [] getFreqAsString() {
		String[] s = new String[2];
		if (freq < 1000) {
			s[0] = Double.toString(freq);
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
	 *            L'amplitude a modifier.
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
	 *            La frequence a appliquer.
	 * 	      Valeur comprise entre 1 Hz et MAX_FREQ.
	 */
	public void setFreq(double freq) {
		if (freq > MAX_FREQ) {
			this.freq = MAX_FREQ;
		} else if (freq < 1.) {
			this.freq = 1.;
		} else {
			this.freq = freq;
		}
	}
	
	/** Active un signal.
	 * @param b true pour activer, false pour desactiver.
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
	
	/** Indique si les signaux sont les memes. 
	 * @param signal1 Le signal a verifier.
	 * @return true si ce sont les memes, false sinon.
	 */
	public boolean equals (Signal signal1) {
		return signal1.NUMERO == this.NUMERO;
	}
}
