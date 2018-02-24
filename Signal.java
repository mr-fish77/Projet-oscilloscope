
public class Signal {
	/** Indique la forme du signal. */
	private String forme;
	/** Indique l'amplitude crête-à-crête du signal en volts. */
	private double amplitude;
	/** Indique la fréquence du signal en Hz. */
	private int freq;
	/** Indique le bruit en pourcentage. */
	private double bruit;
	
	/** Valeurs maximales des propriétés du signal. */
	private final double MAX_AMP = 20., MAX_BRUIT = 100.; // 20V crête-à-crête, 100% de bruit.
	private final int MAX_FREQ = (int) Math.pow(10, 9); // 1GHz.

	/**
	 * Constructeur à vide. Ces valeurs servent simplement à initialiser l'objet. On
	 * a choisi une forme sinusoïdale à 50 Hz comme le réseau EDF, et une amplitude
	 * de 20V crête-à-crête car c'est la valeur limite des oscilloscopes des TP.
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
	public String getForme() {
		return forme;
	}

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
	 * @return Le bruit du signal.
	 */
	public double getBruit() {
		return bruit;
	}

	/**
	 * @param forme
	 *            La forme à modifier.
	 * @throws IllegalArgumentException
	 *             si la valeur est incorrecte.
	 */
	public void setForme(String forme) {
		if (forme.equals("sinus") || forme.equals("rect") || forme.equals("triangle"))
			this.forme = forme;
		else
			throw new IllegalArgumentException();
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

	/**
	 * @param bruit
	 *            Le bruit à appliquer.
	 * 	      Valeur comprise entre 0 et MAX_BRUIT.
	 */
	public void setBruit(double bruit) {
		if (bruit > MAX_BRUIT) {
			this.bruit = MAX_BRUIT;
		} else if (bruit < 0) {
			this.bruit = 0;
		} else {
			this.bruit = bruit;
		}
	}
}
