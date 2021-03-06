/**
 Classe qui permet de generer un signal sinusoidal.
 */
public class Sinus extends Signal {
   
    /** Constructeur par signal sinusoidal.
     * @param n : le numero du signal
     */
    public Sinus(int n){
        super(n);
    }
    
	/** Constructeur qui cree un nouveau signal par recopie.
	 * @param s Signal a copier. */
    public Sinus(Signal s) {
    	super(s);
    }

    
    /** Methode generale pour les signaux de calcul d'une valeur en un point
     * @param x : le point de calcul de la fonction
     * @return double : la valeur en x
     */
    public double fonction(double x) {
    	return Math.sin( 2.*Math.PI*x);	//on retourne juste un sinus de periode 1 (pour correspondre a la normalisation)
    }
    
    /** @return la forme du Signal, ici : SIN. */
    public String getForme() {
	    return SIGNAL_TYPES[0];
    }
}
