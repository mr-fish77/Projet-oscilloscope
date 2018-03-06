
public class Sinus extends Signal {
    
    private final int NB_POINTS = 20; //np de point entre chaque graduation
    private final int CASE_X = 10; //np de graduation selon X
    private final int CASE_Y = 10; //np de graduation selon Y
    public double echelleX; //valeur d'une graduation selon X
    public double echelleY; //valeur d'une graduation selon Y


    public static void main (String[] args){
		Sinus essai = new Sinus(1);
        essai.calculPoint();
	  }
      
    
    public Sinus(int n){
        super(n);
        echelleX = 1;
        echelleY = 1;
    }
    
    
    public void calculPoint(){ //calcul des points
        
       this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
       
       for(int i = (int) (-((NB_POINTS * CASE_X)/2)) ; i < (nuagePoint.length /2) ; i++){ //soustraction pour remplir les nb négtifs
           
                
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] = (double) (((i * echelleX))/ NB_POINTS); //mise à l'echlle des x
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] = (double ) (echelleY *(this.getAmplitude() * Math.sin( getFreq() * 2.* Math.PI * nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0])) / NB_POINTS );  //calcul de y en prenant compte de l'échelle 
                
                System.out.print( i +  "  x = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] ); //return pour debug
                System.out.println ( "   y = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] );
            
        }
       
       
    }
    
    


}
