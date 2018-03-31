/**
 * Classe qui defini les fonctions du menu maths
 * @author Anas
 *
 */
import java.awt.Color;
import java.awt.Graphics;

public class FonctionMath{
	
	public double[][] resultat;
    
    public Signal[] s;
	
	
	public FonctionMath (Signal[] sigaux){
        this.s = sigaux;
	}
	
	public void  Addition( ){
        
    
        this.resultat = new double[s[0].NB_POINTS * s[0].CASE_X ][2];
        
    	for(int i = (int) (-((s[0].NB_POINTS * s[0].CASE_X)/2)) ; i < (resultat.length /2) ; i++){ //soustraction pour remplir les nb negatifs
            resultat[i + ((s[0].NB_POINTS * s[0].CASE_X)/2)][0] = (i * s[0].echelleX / s[0].NB_POINTS) * s[0].nbPixelX / s[0].echelleX   + s[0].ox; //mise à l'echlle des x
			resultat[i + ((s[0].NB_POINTS * s[0].CASE_X)/2)][1] =  - (( s[0].fonction(s[0].getFreq() * ((i * s[0].echelleX / s[0].NB_POINTS) - s[0].decalageX * s[0].echelleX) - s[0].getDephasage()/(Math.PI*2))) * (s[0].getAmplitude() / s[0].echelleY)  * s[0].nbPixelY   
            + s[1].fonction( s[1].getFreq() * ((i * s[1].echelleX / s[1].NB_POINTS) - s[1].decalageX * s[1].echelleX) - s[1].getDephasage()/(Math.PI*2)) * (s[1].getAmplitude() / s[1].echelleY)  * s[1].nbPixelY  ) + s[0].oy;                
        }
	}
	
	public void Soustraction1( ){ 
        this.resultat = new double[s[0].NB_POINTS * s[0].CASE_X ][2];
        
    	for(int i = (int) (-((s[0].NB_POINTS * s[0].CASE_X)/2)) ; i < (resultat.length /2) ; i++){ //soustraction pour remplir les nb negatifs
            resultat[i + ((s[0].NB_POINTS * s[0].CASE_X)/2)][0] = (i * s[0].echelleX / s[0].NB_POINTS) * s[0].nbPixelX / s[0].echelleX   + s[0].ox; //mise à l'echlle des x
			resultat[i + ((s[0].NB_POINTS * s[0].CASE_X)/2)][1] =  - (( s[0].fonction(s[0].getFreq() * ((i * s[0].echelleX / s[0].NB_POINTS) - s[0].decalageX * s[0].echelleX) - s[0].getDephasage()/(Math.PI*2))) * (s[0].getAmplitude() / s[0].echelleY)  * s[0].nbPixelY   
            - s[1].fonction( s[1].getFreq() * ((i * s[1].echelleX / s[1].NB_POINTS) - s[1].decalageX * s[1].echelleX) - s[1].getDephasage()/(Math.PI*2)) * (s[1].getAmplitude() / s[1].echelleY)  * s[1].nbPixelY  ) + s[0].oy;                
        }
       
	}
	
	public void Soustraction2( ){ 
        this.resultat = new double[s[0].NB_POINTS * s[0].CASE_X ][2];
        
    	for(int i = (int) (-((s[0].NB_POINTS * s[0].CASE_X)/2)) ; i < (resultat.length /2) ; i++){ //soustraction pour remplir les nb negatifs
            resultat[i + ((s[0].NB_POINTS * s[0].CASE_X)/2)][0] = (i * s[0].echelleX / s[0].NB_POINTS) * s[0].nbPixelX / s[0].echelleX   + s[0].ox; //mise à l'echlle des x
			resultat[i + ((s[0].NB_POINTS * s[0].CASE_X)/2)][1] =  - (-( s[0].fonction(s[0].getFreq() * ((i * s[0].echelleX / s[0].NB_POINTS) - s[0].decalageX * s[0].echelleX) - s[0].getDephasage()/(Math.PI*2))) * (s[0].getAmplitude() / s[0].echelleY)  * s[0].nbPixelY   
            + s[1].fonction( s[1].getFreq() * ((i * s[1].echelleX / s[1].NB_POINTS) - s[1].decalageX * s[1].echelleX) - s[1].getDephasage()/(Math.PI*2)) * (s[1].getAmplitude() / s[1].echelleY)  * s[1].nbPixelY  ) + s[0].oy;                
        }
     
	}
	
	public void inverse1( ){ 
        this.resultat = new double[s[0].NB_POINTS * s[0].CASE_X ][2];
        
    	for(int i = (int) (-((s[0].NB_POINTS * s[0].CASE_X)/2)) ; i < (resultat.length /2) ; i++){ //soustraction pour remplir les nb negatifs
            resultat[i + ((s[0].NB_POINTS * s[0].CASE_X)/2)][0] = (i * s[0].echelleX / s[0].NB_POINTS) * s[0].nbPixelX / s[0].echelleX   + s[0].ox; //mise à l'echlle des x
			resultat[i + ((s[0].NB_POINTS * s[0].CASE_X)/2)][1] =  - ( - ( s[0].fonction(s[0].getFreq() * ((i * s[0].echelleX / s[0].NB_POINTS) - s[0].decalageX * s[0].echelleX) - s[0].getDephasage()/(Math.PI*2))) * (s[0].getAmplitude() / s[0].echelleY)  * s[0].nbPixelY ) + s[0].oy;                
        }
	}
	
	public void inverse2( ){ 
        this.resultat = new double[s[0].NB_POINTS * s[0].CASE_X ][2];
        
    	for(int i = (int) (-((s[0].NB_POINTS * s[0].CASE_X)/2)) ; i < (resultat.length /2) ; i++){ //soustraction pour remplir les nb negatifs
            resultat[i + ((s[0].NB_POINTS * s[0].CASE_X)/2)][0] = (i * s[0].echelleX / s[0].NB_POINTS) * s[0].nbPixelX / s[0].echelleX   + s[0].ox; //mise à l'echlle des x
			resultat[i + ((s[0].NB_POINTS * s[0].CASE_X)/2)][1] =  - ( - s[1].fonction( s[1].getFreq() * ((i * s[1].echelleX / s[1].NB_POINTS) - s[1].decalageX * s[1].echelleX) - s[1].getDephasage()/(Math.PI*2)) * (s[1].getAmplitude() / s[1].echelleY)  * s[1].nbPixelY  ) + s[0].oy;                
        }
	}
	
	public void dessineCourbe(Graphics g) {
    	if(resultat != null) {		//on regarde si le signal doit etre affiche
	        g.setColor(Color.RED);
	        
	        int a = 0;
	        while(a < (resultat.length-1)){
	            g.drawLine((int) (resultat[a][0]), (int) (resultat[a][1]),(int) (resultat[a+1][0]),(int) (resultat[a+1][1]));
	            a++;
	        }
    	}
	    	
    }
	
	
}	

