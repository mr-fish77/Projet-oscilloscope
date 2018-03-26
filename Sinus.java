import java.awt.Color;

/**
 Classe qui permet de generer un signal sinusoidal.
 */
public class Sinus extends Signal {
   
    /** Constructeur par signal sinusoidal. */
    public Sinus(int n){
        super(n);
    }
    
	/** Constructeur qui cree un nouveau signal par recopie.
	 * @param s Signal a copier. */
    public Sinus(Signal s) {
    	super(s);
    }

    
    /** Methode generale pour les signaux de calcul d'une valeur en un point
     * @param double x : le point de calcul de la fonction
     * @return double : la valeur en x
     */
    public double fonction(double x) {
    	return Math.sin( 2.*Math.PI*x);	//on retourne juste un sinus de periode 1 (pour correspondre a la normalisation)
    }
    

    /** Methode qui recalcule les points des signaux en fonction de la taille de la fenetre...
     */
    public void calculPoint(){ //calcul des points
       super.calculPoint();
    }
    
    /** @return la forme du Signal, ici : SIN. */
    public String getForme() {
	    return SIGNAL_TYPES[0];
    }
}
