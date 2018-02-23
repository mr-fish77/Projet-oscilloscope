
public class Signal {
	/**
	 * Indique la forme du signal. Ne peut prendre que des valeurs pr�d�finies.
	 */
	private String forme;
	/**
	 * Indique l'amplitude cr�te-�-cr�te du signal. A une valeur comprise entre 0 et
	 * 20V.
	 */
	private double amplitude;
	/**
	 * Indique la fr�quence du signal. Pour �viter les erreurs avec la taille des
	 * int, on limite la valeur � 1Ghz.
	 */
	private int freq;
	/**
	 * Indique le bruit en pourcentage. Valeur comprise entre 0 et 100.
	 */
	private double bruit;

	private final double MAX_AMP = 20., MAX_BRUIT = 100;
	private final int MAX_FREQ = (int) Math.pow(10., 9);

	/**
	 * Constructeur � vide. Ces valeurs servent simplement � initialiser l'objet. On
	 * a choisi une forme sinuso�dale � 50 Hz comme le r�seau EDF, et une amplitude
	 * de 20V cr�te-�-cr�te car c'est la valeur limite des oscilloscopes des TP.
	 */
	public Signal() {
		forme = "sinus";
		amplitude = 5.;
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
	 * @return La fr�quence du signal.
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
	 *            La forme � modifier.
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
	 *            the amplitude to set
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
	 *            the freq to set
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
	 *            the bruit to set
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
