import java.awt.Color;

/**
 * Classe qui permet de generer un signal rectangle  
 * @author Mathieu
 *
 */


public class Rectangle extends Signal {
        
   

    /*public static void main (String[] args){
		Rectangle essai = new Rectangle(1);
        essai.calculPoint();
	  }*/
      
      
    /**
	 * Constructeur par signal rectangle.
	 */
    public Rectangle(int n){
        super(n);
        echelleX = 0.5;
        echelleY = 5;
        this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
    }
    
     /** 
      * calcul les points du signal pour l'affichage
      *  **/
    public void calculPoint(){ 
        
       this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
       double signe = 1;
       double periode = 1/ this.getFreq(); //periode du signal
       //System.out.println( periode );
       
       
       double  xChangement =   ((((NB_POINTS * CASE_X)/2)) * echelleX)/ NB_POINTS; //pt de depart
    
       for(int i = (int) (-((NB_POINTS * CASE_X)/2)) ; i < (nuagePoint.length /2) ; i++){ //soustraction pour remplir les nb negatifs
           
                
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] = (double) ( (  (((i * echelleX))/ NB_POINTS)) * nbPixelX / this.echelleX )   + ox; //mise à l'echlle des x
                
               if( (Math.abs( (double) (((i * echelleX)/ NB_POINTS) ) -  xChangement)) >= periode/2 ){ //chagement de signe toutes les demi-periodes
                      xChangement= (double) ((i * echelleX)/ NB_POINTS);
                      if(signe == -1) signe = 1;
                      else if(signe == 1) signe = - 1;

                }
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] = (double)   ((this.getAmplitude() * signe  * nbPixelY ) / this.echelleY )   + oy; //valeur du signal
                
                //System.out.print( i +  "  x = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] ); //return pour debug
                //System.out.println ( "   y = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] );
            
        }
       
       
    }
    
    /** Renvoie la forme du Signal, ici : REC. */
	public String getForme() {return SIGNAL_TYPES[2];}

}
