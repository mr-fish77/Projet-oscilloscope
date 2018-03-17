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

        this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
    }
    
    public Sinus(Signal s) {
    	super(s);
    	this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
    }

    
    

    /** calcul les points du signal pour l'affichage **/
    public void calculPoint(){ //calcul des points
       for(int i = (int) (-((NB_POINTS * CASE_X)/2)) ; i < (nuagePoint.length /2) ; i++){ //soustraction pour remplir les nb negatifs
    	   nuagePoint[i + (int)(NB_POINTS * CASE_X/2)][0] = ox + (double)((i * nbPixelX/NB_POINTS)); //mise a l'echelle des x
    	   nuagePoint[i + (int)(NB_POINTS * CASE_X/2)][1] = oy - (double) (Math.sin( 2.*Math.PI*freq * ( (i * echelleX / NB_POINTS) - decalageX * echelleX)) * (amplitude / echelleY) * nbPixelY);
    	   
           //System.out.println( i +  "  x = " + nuagePoint[i + (int)((NB_POINTS * CASE_X)/2)][0]); //return pour debug
           //System.out.println ( "   y = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] );
        }
    }
    
    /** Renvoie la forme du Signal, ici : SIN. */
    public String getForme() {return SIGNAL_TYPES[0];}
}
