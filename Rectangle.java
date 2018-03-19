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
        this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
    }
    
    public Rectangle(Signal s) {
    	super(s);
    	this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
    }
    
     /** 
      * calcul les points du signal pour l'affichage
      */
    public void calculPoint(){ 
        
       this.nuagePoint = new double[NB_POINTS * CASE_X ][2]; 
       double signe = 1;
       double periode = 1/ this.getFreq(); //periode du signal
       //System.out.println( periode );
       
       
       double  xChangement =   ((((NB_POINTS * CASE_X)/2)) * echelleX)/ NB_POINTS; //pt de depart
    
       for(int i = (int) (-((NB_POINTS * CASE_X)/2)) ; i < (nuagePoint.length /2) ; i++){ //soustraction pour remplir les nb negatifs
           
                
                nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] = (double) ( (  (((i * echelleX))/ NB_POINTS)) * nbPixelX / this.echelleX )   + ox; //mise à l'echlle des x
				nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] = (double) (-fctRectangle(freq * ((i * echelleX / NB_POINTS) - decalageX * echelleX)) * (amplitude / echelleY) * nbPixelY + oy);                
                //System.out.print( i +  "  x = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][0] ); //return pour debug
                //System.out.println ( "   y = " + nuagePoint[i + ((NB_POINTS * CASE_X)/2)][1] );
            
        }
       
       
    }
    
     public double fctRectangle(double x){
		if(x<0){
			x = (-x)%1;
			
			if(x<=0.5){
				return(-1);
			}else{
				return(1);
			}
		
		}else{
			x = x%1;
			
			if(x<=0.5){
				return(1);
			}else{
				return(-1);
			}
		}
		
	}
    
    
    /** Renvoie la forme du Signal, ici : REC. */
	public String getForme() {return SIGNAL_TYPES[2];}

}
