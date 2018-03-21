import java.awt.Color;

/**
<<<<<<< HEAD
 * Classe qui permet de generer un signal sinusoidale   
=======
 * Classe qui permet de gerer un signal sinusoidale   ===> pb de mise a l'echelle
>>>>>>> e9ca9f035a2a78fae9fb8e6ffdf33e3e39b95a03
 * @author Mathieu
 *
 */
public class Sinus extends Signal {
   
   

    /*public static void main (String[] args){
		Sinus essai = new Sinus(1);
        essai.calculPoint();
	  }*/
    
    /**
	 * Constructeur par signal sinusoidale.
	 */
    public Sinus(int n){
        super(n);
    }
    
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
    
    
    
    /** Renvoie la forme du Signal, ici : SIN. */
    public String getForme() {return SIGNAL_TYPES[0];}
}
