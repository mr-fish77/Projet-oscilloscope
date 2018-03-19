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
				nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] = (double) (-fctTriangle(freq * ((i * echelleX / NB_POINTS) - decalageX * echelleX)) * (amplitude / echelleY) * nbPixelY + oy);
                //System.out.println( i +  "  x = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] ); //return pour debug
                //System.out.println ( "   y = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] );
            
        }
       
       
    }
    
    public double fctTriangle(double x){
		if(x<0){
			x = (-x)%1;
			
			if(x<=0.25){
				return(-4*x);
			}else if(x<=0.75){
				return(-2 + 4*x);
			}else{
				return(4 - 4*x);
			}
		
		}else{
			x = x%1;
			
			if(x<=0.25){
				return(4*x);
			}else if(x<=0.75){
				return(2 - 4*x);
			}else{
				return(-4 + 4*x);
			}
		}
		
	}
    
    /** @return la forme du Signal, ici : TRI. */
	public String getForme() {return SIGNAL_TYPES[1];}

}
