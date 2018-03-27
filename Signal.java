import java.awt.Color;
import java.awt.Graphics;

/** Classe mere des differents signaux. 
 * Tous les signaux du projet heritent de Signal. 
 * Cette methode gere l'initialisation, la modification et 
 * la reinitialisation des signaux, mais aussi elle genere 
 * un nuage de points permettant l'affichage ulterieur a l'oscilloscope. */

public abstract class Signal {
	
	/** Amplitude du signal en volts. */
	protected double amplitude;
	/** Frequence du signal en Hz. */
	protected double freq;
	/** Dephasage en radians.*/
	protected double dephasage;
	/** Numero du signal : 1 ou 2. */
	public final int NUMERO;
	
	/** Valeur maximale de l'amplitude d'un signal : 10 V */
	protected final double MAX_AMP = 10.;
	/** Valeur maximale de la frequence d'un signal : 2 Ghz. */
	protected final double MAX_FREQ =  (2.*Math.pow(10, 9)); 
	
	/** Valeurs possibles des unites ou formes de signal. */
	public static final String [] SIGNAL_TYPES = {"SIN", "TRI", "REC"}, FREQ_UNITES = {"Hz", "kHz", "MHz", "GHz"},  AMPL_UNITES = {"mV", "V"};

	/** Valeurs par defaut lors de la creation d'un signal. */
	public static final double DEF_AMP = 5., DEF_FREQ = 50.;
	
	/** true si le Signal est actif, false sinon. Un signal inactif est nul. */
	protected boolean active;
	
	/** Points a afficher. */
	public double[][] nuagePoint;
	
	/** Couleur de la courbe */
	protected Color couleur;
	/** Tableau de couleur par signal */
	protected static final Color[] COULEURS_SIGNAUX = {Color.BLUE, Color.ORANGE};
    
    /**nb de point par graduation*/
    public final int NB_POINTS = 1000; 
    /**nb de pixels par graduation X*/
    public  int nbPixelX; 
    /**nb de pixels par graduation Y*/
    public  int nbPixelY; 
    /** origine X*/
    public  int ox; 
    /** origine Y*/
    public  int oy; 
    /**nb de graduation selon X*/
    public  final int CASE_X = 8;
    /**nb de graduation selon Y*/
    public  final int CASE_Y = 8; 
    /**valeur d'une graduation selon X en s*/
    public double echelleX; 
    /**valeur d'une graduation selon Y en V*/
    public double echelleY; 
    /**decalage suivant X en %/div */
    public double decalageX = 0;
    /**decalage suivant Y en %/div */
    public double decalageY = 0;
    
    
    /**détermine si le sigal doit être afficher ou non*/
    public boolean affiche;

    

	/**
	 * Constructeur par defaut.
	 */
	public Signal(int n) {
		amplitude = DEF_AMP;
		freq = DEF_FREQ;
		active = false;
		NUMERO = n;
		couleur = COULEURS_SIGNAUX[n-1];
		dephasage = 0;
		
		decalageX = 0;
		decalageY = 0;
		
    	this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
        affiche = true;
	}
	
	/**
	 * Constructeur pour reconstruction
	 */
	public Signal(Signal s) {
		amplitude = s.amplitude;
		freq = s.freq;
		active = s.active;
		affiche = s.affiche;
		NUMERO = s.NUMERO;
		couleur = s.couleur;
		
		nbPixelX = s.nbPixelX;
		nbPixelY = s.nbPixelY;
		ox = s.ox;
		oy = s.oy;
		echelleX = s.echelleX;
		echelleY = s.echelleY;
		decalageX = s.decalageX;
		decalageY = s.decalageY;
		
    	this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
	}
	
	/**
	 * @return La forme du signal sous un court String en capitales.
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
	 * @return Le dephasage en radians. 
	 */
	public double getDephasage(){
		return dephasage;
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

	/** Modifie la valeur de la frequence.
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
	
	/** Modifie la valeur du dephasage.
	 * @param p Phase à appliquer, sa valeur est entre -pi et pi.
	 */
	public void setDephasage(double p){
		dephasage = p % (2*Math.PI);
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
	
	/** Indique si les signaux sont les memes. La verification se fait sur leurs numeros. 
	 * @param sig Le signal a verifier.
	 * @return true si leurs numeros sont identiques.
	 */
	public boolean equals (Signal sig) {
		return sig.NUMERO == this.NUMERO;
	}
	
    
    /** met a l'echelle le signal 
	 * @param taille affichage x
     * @param taille affichage y
	 */
    public void miseAEchelle(int x, int y){
        nbPixelX = x / CASE_X;	//nombre de pixels par graduation
        nbPixelY = y / CASE_Y;
        
        ox = x / 2;	//position des axes, pour obtenir le centre du repere
        oy = y / 2;
    }
    
    
    /** Methode qui recalcule les points des signaux en fonction de la taille de la fenetre...
     */
    public void calculPoint(){
    	this.nuagePoint = new double[NB_POINTS * CASE_X ][2];
    	for(int i = (int) (-((NB_POINTS * CASE_X)/2)) ; i < (nuagePoint.length /2) ; i++){ //soustraction pour remplir les nb negatifs
            nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] = (i * echelleX/ NB_POINTS) * nbPixelX / this.echelleX   + ox; //mise à l'echlle des x
			nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] =  - fonction(freq * ((i * echelleX / NB_POINTS) - decalageX * echelleX) - dephasage/(Math.PI*2)) * (amplitude / echelleY) * nbPixelY + oy;                
    	}
    }
    
    
    /** Methode generale pour les signaux de calcul d'une valeur en un point
     * @param double x : le point de calcul de la fonction
     * @return double : la valeur en x
     */
    public abstract double fonction(double x);
    
    
    /** Dessine la courbe si elle doit etre affichee
     * @param Graphics g : pour peindre la courbe
     */
    public void dessineCourbe(Graphics g) {
    	
        if(affiche == true){//on regarde si l'oscillo doit afficher un signal
            
            if(active) {		//on regarde si le signal doit etre affiche
                g.setColor(couleur);
                calculPoint();	//on recalcule les point
                
                int a = 0;
                while(a < (nuagePoint.length-1)){
                    g.drawLine((int) (nuagePoint[a][0]), (int) (nuagePoint[a][1] + decalageY*nbPixelY),(int) (nuagePoint[a+1][0]),(int) (nuagePoint[a+1][1]+ decalageY*nbPixelY));
                    a++;
                }
            
            }
	    }	
    }
    
    
    
    public void dessineCentre(Graphics g, int x, int y){
        
        if(affiche == true){//on regarde si l'oscillo doit afficher un signal
            
            if(active) {
                g.setColor(couleur);
                //g.fillRect( (x-8) , (int) ( y/2 + decalageY*nbPixelY) , 8  ,  2 );
                drawArrowLine(g, x, (int) ( y/2 + decalageY*nbPixelY) , (x-10), (int) ( y/2 + decalageY*nbPixelY), 5 , 5);
            }
        }
                
        
    }
    
    
        /**
     * desine une fleche entre deux point 
     * @param g 
     * @param x1 premier point.
     * @param y1 premier point
     * @param x2 deuxieme point.
     * @param y2 deuxieme  point.
     * @param d  longueur de la fleche
     * @param h  hauteur de la fleche
     */
    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }
    
    
    
    
    
    
    
    
}
