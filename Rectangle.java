/**
 * Classe qui permet de gérer un signal rectangle  
 * @author Mathieu
 *
 */


public class Rectangle extends Signal {
    
    private final int NB_POINTS = 50; //np de point entre chaque graduation
    private final int CASE_X = 10; //nb de graduation selon X
    private final int CASE_Y = 10; //nb de graduation selon Y
    public double echelleX; //valeur d'une graduation selon X en s
    public double echelleY; //valeur d'une graduation selon Y en V


    public static void main (String[] args){
		Rectangle essai = new Rectangle(1);
        essai.calculPoint();
	  }
      
    
    public Rectangle(int n){
        super(n);
        echelleX = 0.05;
        echelleY = 5;
    }
    
    
    public void calculPoint(){ //calcul des points
        
       this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
       double signe = 1;
       
       double  xChangement =  ((-((NB_POINTS * CASE_X)/2)) * echelleX)/ NB_POINTS; //pt de départ
    
       for(int i = (int) (-((NB_POINTS * CASE_X)/2)) ; i < (nuagePoint.length /2) ; i++){ //soustraction pour remplir les nb négatifs
           
                
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] = (double) (((i * echelleX))/ NB_POINTS); //mise à l'echlle des x
                
               if( Math.abs( (nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] - xChangement)) >= (1/ this.getFreq())/2 ){ //chagement de signe toutes les demi-périodes
                      xChangement= nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0];
                      if(signe == -1) signe = 1;
                      else if(signe == 1) signe = - 1;
                }
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] =   this.getAmplitude() * signe / echelleY; //valeur du signal
                
                System.out.print( i +  "  x = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] ); //return pour debug
                System.out.println ( "   y = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] );
            
        }
       
       
    }
    
    /** Renvoie la forme du Signal, ici : RECT. */
	public String getForme() {return "RECT";}

}
