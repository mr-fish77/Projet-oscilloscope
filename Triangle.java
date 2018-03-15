import java.awt.Color;

/**
 * Classe qui permet de generer un signal triangle
 * @author Mathieu
 *
 */
public class Triangle extends Signal {
    

    /*public static void main (String[] args){
		Triangle teste = new Triangle(1);
        teste.calculPoint();
	  }*/
      
    
    /**
	 * Constructeur par signal trinagle.
	 */
    public Triangle(int n){
        super(n);
        echelleX = 1;
        echelleY = 5;
        this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
    }
    
    public Triangle(Signal s) {
    	super(s);
    	this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
    }
    
    /** calcul les points du signal pour l'affichage **/
    public void calculPoint(){ //calcul des points
        
       this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
       
       double xChangement =  ((((NB_POINTS * CASE_X)/2)) * echelleX)/ NB_POINTS; 
       double coefDirecteur = 2 * this.getAmplitude();
       double ordonneO = this.getAmplitude();
       double iChangement = 0;
       double periode = 1/ this.getFreq(); //periode
    
       for(int i = (int) (-((NB_POINTS * CASE_X)/2)) ; i < (nuagePoint.length /2) ; i++){ //soustraction pour remplir les nb negatifs
           
                
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] = (double) ( (  (((i * echelleX))/ NB_POINTS)) * nbPixelX / this.echelleX )   + ox; //mise a l'echlle des x
                
                      
                if( (Math.abs( (double) (((i * echelleX)/ NB_POINTS) ) -  xChangement)) >= periode/2 ){ //chagement de coef directeur toutes les demi-périodes
                      xChangement= (double) ((i * echelleX)/ NB_POINTS);  
                      iChangement = (double) (((i * echelleX))/ NB_POINTS);
                      
                       if(coefDirecteur > 0){
                            coefDirecteur = - (2 * this.getAmplitude()) / ((1/this.getFreq())/2);
                            ordonneO = this.getAmplitude();
                            //System.out.println( " changement de coef  " );

                            }
                      else if(coefDirecteur < 0){
                           coefDirecteur = (2 * this.getAmplitude()) / ((1/this.getFreq())/2);
                           ordonneO = - this.getAmplitude();
                            //System.out.println( " changement de coef  " );

                       }
                      
                      
                }
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] = ( coefDirecteur * ( ((double) i * echelleX/ NB_POINTS) -  iChangement) +  ordonneO  )      * nbPixelY  / this.echelleY    + oy; //valeur du signal avec mise à l'echelle

                //System.out.print( i +  "  x = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] ); //return pour debug
                //System.out.println ( "   y = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] );
            
        }
       
       
    }
    
    /** @return la forme du Signal, ici : TRI. */
	public String getForme() {return SIGNAL_TYPES[1];}

}
