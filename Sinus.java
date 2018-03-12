/**
 * Classe qui permet de gérer un signal sinusoidale   ===> pb de mise à l'échelle
 * @author Mathieu
 *
 */
public class Sinus extends Signal {
    private final int NB_POINTS = 50; //np de point entre chaque graduation
    private final int CASE_X = 10; //nb de graduation selon X
    private final int CASE_Y = 10; //nb de graduation selon Y
    public double echelleX; //valeur d'une graduation selon X en s
    public double echelleY; //valeur d'une graduation selon Y en V
    
    public  int nbPixelX = 75; // nb de pixel par graduation X
    public  int nbPixelY = 75; // nb de pixel par graduation Y
    public  int ox = 300; // origine
    public  int oy =300; // origine
   

    public static void main (String[] args){
		Sinus essai = new Sinus(1);
        essai.calculPoint();
	  }
      
    
    public Sinus(int n){
        super(n);
        echelleX = 5;
        echelleY = 5;
        this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
    }
    
    /** Renvoie la forme du Signal, ici : SIN. */
    public String getForme() {return "SIN";}
    
    public void calculPoint(){ //calcul des points
        
       
       
       for(int i = (int) (-((NB_POINTS * CASE_X)/2)) ; i < (nuagePoint.length /2) ; i++){ //soustraction pour remplir les nb négtifs
           
                
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] = (double) ( (  (((i * echelleX))/ NB_POINTS)) * nbPixelX / this.echelleX )   + ox; //mise à l'echlle des x
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] = (double ) (this.getAmplitude() * Math.sin( getFreq() * 2.* Math.PI * ((i * echelleX)/ NB_POINTS))   * nbPixelY / this.echelleY ) + oy;  //calcul de y en prenant compte de l'échelle 
                
                System.out.print( i +  "  x = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] ); //return pour debug
                System.out.println ( "   y = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] );
            
        }
       
       
    }
    
    


}
