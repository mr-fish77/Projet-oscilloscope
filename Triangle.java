/**
 * Classe qui permet de gérer un signal triangulaire marche pas
 * @author Mathieu
 *
 */
public class Triangle extends Signal {
    
    private final int NB_POINTS = 50; //np de point entre chaque graduation
    private final int CASE_X = 10; //nb de graduation selon X
    private final int CASE_Y = 10; //nb de graduation selon Y
    public double echelleX; //valeur d'une graduation selon X en s
    public double echelleY; //valeur d'une graduation selon Y en V


    public static void main (String[] args){
		Triangle teste = new Triangle(1);
        teste.calculPoint();
	  }
      
    
    public Triangle(int n){
        super(n);
        echelleX = 0.05;
        echelleY = 1;
    }
    
    
    public void calculPoint(){ //calcul des points
        
       this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
       
       double xChangement =  ((-((NB_POINTS * CASE_X)/2)) * echelleX)/ NB_POINTS; //pt de départ
       double coefDirecteur = 2 * this.getAmplitude();
       double ordonneO = this.getAmplitude();
       double iChangement = 0;
    
       for(int i = (int) (-((NB_POINTS * CASE_X)/2)) ; i < (nuagePoint.length /2) ; i++){ //soustraction pour remplir les nb négatifs
           
                
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] = (double) (((i * echelleX))/ NB_POINTS); //mise à l'echlle des x
                
               if( Math.abs( (nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] - xChangement)) >= (1/ getFreq())/2 ){ //chagement du signe du coef direteur toutes les demi-périodes
                      xChangement= nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0];
                      iChangement = (double) (((i * echelleX))/ NB_POINTS);
                       if(coefDirecteur > 0){
                            coefDirecteur = - (2 * this.getAmplitude()) / ((1/this.getFreq())/2);
                            ordonneO = this.getAmplitude();
                            }
                      else if(coefDirecteur < 0){
                           coefDirecteur = (2 * this.getAmplitude()) / ((1/this.getFreq())/2);
                           ordonneO = - this.getAmplitude();
                       }
                      
                     // System.out.println( "  coef = " + coefDirecteur );
                      
                }
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] = coefDirecteur * ( ((double) i * echelleX/ NB_POINTS) -  iChangement) +  ordonneO ; //valeur du signal

                System.out.print( i +  "  x = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] ); //return pour debug
                System.out.println ( "   y = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] );
            
        }
       
       
    }
    
    /** @return la forme du Signal, ici : TRI. */
	public String getForme() {return "TRI";}

}
