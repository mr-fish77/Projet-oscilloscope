public abstract class  Signal {
	
	/** Amplitude du signal en volts. */
	private double amplitude;
	/** Frequence du signal en Hz. */
	private double freq;
	/** Numéro du signal : 1 ou 2. */
	public final int numero;
	
	/** Valeur maximale de l'amplitude d'un signal : 10 V */
	private final double MAX_AMP = 10.;
	/** Valeur maximale de la frÃ©quence d'un signal : 2 Ghz. */
	private final double MAX_FREQ =  (2.*Math.pow(10, 9)); 
	
	/** Valeurs possibles des unitÃ©s ou formes de signal. */
	public static final String [] SIGNAL_TYPES = {"SIN", "TRI", "REC"}, FREQ_UNITES = {"Hz", "kHz", "MHz", "GHz"},  AMPL_UNITES = {"mV", "V"};

	/** Actif ou non, affichÃ© ou non. 
	 * Un signal actif est nul.
	 * */
	private boolean active;
    
    
    public double[][] nuagePoint; //stokage des points à afficher
    
	
	/**
	 * Constructeur par defaut.
	 */
	public Signal(int n) {
		amplitude = 5;
		freq = 100;
		active = false;
		numero = n;
	}
	
	/** Met les attributs du signal aux valeurs renseignÃ©es.
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
	public String getForme() {return "";}

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
		} else if (freq < 1) {
			this.freq = 1;
		} else {
			this.freq = freq;
		}
	}
    
}
