/**
 * Classe qui permet de gérer un signal rectangle  
 * @author Mathieu
 *
 */


public class Rectangle extends Signal {
        
   /*private final int NB_POINTS = 50; //np de point entre chaque graduation
    private final int CASE_X = 10; //nb de graduation selon X
    private final int CASE_Y = 10; //nb de graduation selon Y
    public double echelleX; //valeur d'une graduation selon X en s
    public double echelleY; //valeur d'une graduation selon Y en V
    
    public  int nbPixelX = 75; // nb de pixel par graduation X
    public  int nbPixelY = 75; // nb de pixel par graduation Y
    public  int ox = 300; // origine
    public  int oy =300; // origine
*/

    public static void main (String[] args){
		Rectangle essai = new Rectangle(1);
        essai.calculPoint();
	  }
      
    
    public Rectangle(int n){
        super(n);
        echelleX = 0.5;
        echelleY = 5;
        this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
    }
    
    
    public void calculPoint(){ //calcul des points
        
       this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
       double signe = 1;
       double periode = 1/ this.getFreq();
       System.out.println( periode );
       
       
       double  xChangement =   ((((NB_POINTS * CASE_X)/2)) * echelleX)/ NB_POINTS; //pt de départ
    
       for(int i = (int) (-((NB_POINTS * CASE_X)/2)) ; i < (nuagePoint.length /2) ; i++){ //soustraction pour remplir les nb négatifs
           
                
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] = (double) ( (  (((i * echelleX))/ NB_POINTS)) * nbPixelX / this.echelleX )   + ox; //mise à l'echlle des x
                
               if( (Math.abs( (double) (((i * echelleX)/ NB_POINTS) ) -  xChangement)) >= periode/2 ){ //chagement de signe toutes les demi-périodes
                      xChangement= (double) ((i * echelleX)/ NB_POINTS);
                      if(signe == -1) signe = 1;
                      else if(signe == 1) signe = - 1;
                        System.out.println( signe + " xch: " + xChangement + " i :" + ((i * echelleX)/ NB_POINTS) );

                }
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] = (double)   ((this.getAmplitude() * signe  * nbPixelY ) / this.echelleY )   + oy; //valeur du signal
                
                System.out.print( i +  "  x = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] ); //return pour debug
                System.out.println ( "   y = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] );
            
        }
       
       
    }
    
    /** Renvoie la forme du Signal, ici : RECT. */
	public String getForme() {return "RECT";}

}
