/**
 * Classe correspondant a un signal rectangulaire. 
 */


public class Rectangle extends Signal {
      
    /** Constructeur par signal rectangle. 
     * @param n : le numero du signal
     */
    public Rectangle(int n){
        super(n);
    }
    
	/** Constructeur qui cree un nouveau signal par recopie.
	 * @param s Signal a copier. */
    public Rectangle(Signal s) {
    	super(s);
    }
    
    
    /** Methode generale pour les signaux de calcul d'une valeur en un point
     * @param x : le point de calcul de la fonction
     * @return double : la valeur en x
     */
     public double fonction(double x){
    	//on a un signal de periode 1, on applique un modulo pour enlever tous les 1 en trop
		x = (x % 1 + 1) % 1;	// java produit des modulos negatifs avec des nombre negatifs, on le ramene en positif
		
		if(x<=0.5){	//si on est sur la phase positive du rectangle
			return(1);
		}else{
			return(-1);
		}
		
		
	}
    
    /** @return la forme du Signal, ici : REC. */
	public String getForme() {
		return SIGNAL_TYPES[2];
	}

}
