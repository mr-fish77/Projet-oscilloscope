
public class Signal {
	/** Indique l'amplitude du signal en volts. */
	private double amplitude;
	/** Indique la frÃ©quence du signal en Hz. */
	private int freq;
	
	/** Valeurs maximales des proprietes du signal. */
	private final double MAX_AMP = 10.; // 20V crete-a-crete.
	private final int MAX_FREQ = (int) Math.pow(10, 9); // 1GHz.
	
	/** Actif ou non. */
	private boolean active;
	
	/**
	 * Constructeur par defaut.
	 */
	public Signal() {
		amplitude = MAX_AMP;
		freq = 50;
		active = false;
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
}
