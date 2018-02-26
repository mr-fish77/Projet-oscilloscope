
public abstract class Signal {
	/** Indique l'amplitude crête-à-crête du signal en volts. */
	private double amplitude;
	/** Indique la fréquence du signal en Hz. */
	private int freq;
	
	/** Valeurs maximales des propriétés du signal. */
	private final double MAX_AMP = 20.; // 20V crête-à-crête.
	private final int MAX_FREQ = (int) Math.pow(10, 9); // 1GHz.

	/**
	 * Constructeur par défaut.
	 */
	public Signal() {
		forme = "sinus";
		amplitude = 20.;
		freq = 50;
		bruit = 0.;
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
	 * @return La fréquence du signal.
	 */
	public int getFreq() {
		return freq;
	}

	/**
	 * @param amplitude
	 *            L'amplitude à modifier.
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
	 *            La fréquence à appliquer.
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
}
