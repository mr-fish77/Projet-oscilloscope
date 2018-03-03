
public class Signal {
	
	/** Amplitude crete-a-crete du signal en volts. */
	private double amplitude;
	/** Frequence du signal en Hz. */
	private int freq;
	
	/** Valeurs maximales des proprietes du signal. */
	private final double MAX_AMP = 20.; // 20V crÃªte-Ã -crÃªte.
	private final int MAX_FREQ = (int) Math.pow(10, 9); // 1GHz.
	
	public static final String [] SIGNAL_TYPES = {"SIN", "TRI", "REC"};
	public static final String [] FREQ_UNITES = {"Hz", "kHz", "MHz", "GHz"};
	public static final String [] AMPL_UNITES = {"mV", "V"};

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
	
	public void resetSignal() {
		amplitude = 10.;
		freq = 50;
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
	
	public void setActive(boolean b) {
		this.active = b;
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	public void setDisplayed(boolean b) {
		this.displayed = b;
	}
	
	public boolean getDisplayed() {
		return displayed;
	}
}
