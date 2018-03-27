import java.awt.Graphics;

/**
 * Classe mere qui gere tous les curseurs
 */
public abstract class Curseur{
	/** Positions reelles a l'ecran */
	protected int posCurseur1;
	protected int posCurseur2;
	/** Conversion graduation-pixels */
	protected double nbPixel;
	
	/** Valeurs en graduation des curseurs */
	protected double echelle;
	protected double vraiVal1;
	protected double vraiVal2;
	public double difference;
	
	/** Tableau des signaux */
	protected Signal[] s;
	
	/**
	 * Constructeur par defaut de la classe
	 * @param Signal[] s : le tableau des signaux
	 */
	public Curseur(Signal[] s) {
		this.s = s;
	}

	/**
	 * Methode qui permet d'afficher les curseurs a l'ecran
	 * @param Graphics g : l'objet graphique bien pratique
	 * @param int hauteur : la hauteur de la grille
	 * @param int largeur : la largeur de la grille
	 */
	public abstract void paint(Graphics g, int hauteur, int largeur);
	
	
	/**
	 * Methode appelee pour mettre a jour les valeurs des curseurs
	 * @param int evolCran1 : l'evolution pour le curseur1
	 * @param int evolCran2 : l'evolution pour le curseur2
	 */
	public void mAJCran(int evolCran1, int evolCran2) {
		vraiVal1 += evolCran1*0.04;	//on progresse par 4% (ca semble a peu pres bon)
		vraiVal2 += evolCran2*0.04;
		difference = (vraiVal2-vraiVal1);
		
		//on met a jour l'echelle
		mAJEchelle();
	}
	
	/** Methode correcte pour les puissances de 10 (ne renvoie pas de 0 pour 10^0)
	 * @param double val : la puissance a calculer
	 * @return double : la valeur de la puissance
	 */
	public double pow(double val) {
		return((Math.pow(10,  val)>0) ? Math.pow(10,  val) : 1);
	}
	
	/**
	 * Met a jour l'echelle des curseurs
	 */
	public void mAJEchelle() {}
	
	/**
	 * Donne la difference en String
	 * @return String : la difference
	 */
	public String getDifference() {
		int puissanceUnite = (Math.log10(Math.abs(difference*echelle)) > -100)?(int)Math.log10(Math.abs(difference*echelle)):0;
		return (String.format("%.2f", difference*echelle/pow(puissanceUnite)) + "E" + puissanceUnite + " V");
	}
	
	/**
	 * Donne la valeur 1 en String
	 * @return String : la valeur
	 */
	public String getVraiVal1() {
		int puissanceUnite = (Math.log10(Math.abs(vraiVal1*echelle)) > -100)?(int)Math.log10(Math.abs(difference*echelle)):0;
		return(String.format("%.2f", vraiVal1*echelle/pow(puissanceUnite)) + "E" + puissanceUnite + " V");
	}
	
	/**
	 * Donne la valeur 2 en String
	 * @return String : la valeur
	 */
	public String getVraiVal2() {
		int puissanceUnite = (Math.log10(Math.abs(vraiVal2*echelle)) > -100)?(int)Math.log10(Math.abs(difference*echelle)):0;
		return(String.format("%.2f", vraiVal2*echelle/pow(puissanceUnite)) + "E" + puissanceUnite + " V");
	}
}
