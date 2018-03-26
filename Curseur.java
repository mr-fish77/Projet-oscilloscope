import java.awt.Graphics;

/**
 * Classe mere qui gere tous les curseurs
 * @author Pierre-Yves
 */
public abstract class Curseur{
	/** Positions en echelle de graduations */
	protected double valCurseur1;
	protected double valCurseur2;
	
	/** Positions reelles a l'ecran */
	protected int posCurseur1;
	protected int posCurseur2;
	/** Conversion graduation-pixels */
	protected double nbPixel;
	
	/** Valeurs en volts/sec des curseurs */
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
		valCurseur1 += evolCran1*0.04;
		valCurseur2 += evolCran2*0.04;
		
		vraiVal1 = Math.round(valCurseur1*echelle*100)/100;
		vraiVal2 = Math.round(valCurseur2*echelle*100)/100;
		difference = Math.round((valCurseur2-valCurseur1)*echelle*100)/100;
	}
}
