import java.awt.Color;

/**
 * Classe qui permet de generer un signal triangle
 * @author Mathieu
 *
 */
public class Triangle extends Signal {
 
    /**
	 * Constructeur par signal trinagle.
	 */
    public Triangle(int n){
        super(n);
    }
    
    public Triangle(Signal s) {
    	super(s);
    }
    
    
    /** Methode generale pour les signaux de calcul d'une valeur en un point
     * @param double x : le point de calcul de la fonction
     * @return double : la valeur en x
     */
    public double fonction(double x){
    	//on a un signal de periode 1, on applique un modulo pour enlever tous les 1 en trop
    	x = (x % 1 + 1) % 1;// java produit des modulos negatifs avec des nombre negatifs, on le ramene en positif
		
    	//formule en 3 morceaux car il faut avoir une valeur nulle en 0
		if(x<=0.25){		//pente positive montante
			return(4*x);
		}else if(x<=0.75){	//pente negative descendante
			return(2 - 4*x);
		}else{
			return(-4 + 4*x);//de nouveau pente montante
		}
	}
    
    /** Methode qui recalcule les points des signaux en fonction de la taille de la fenetre...
     */
    public void calculPoint(){ //calcul des points
    	super.calculPoint();
    }
    
    
    /** @return la forme du Signal, ici : TRI. */
	public String getForme() {return SIGNAL_TYPES[1];}

}
