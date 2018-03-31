/**
 * Classe qui defini les fonctions du menu maths
 * @author Anas
 *
 */
import java.awt.Color;
import java.awt.Graphics;

public class FonctionMath{
	
	/** Tableau de points permettant l'affichage du resultat. */
	public double[][] resultat;
    /** Signal etudie. */
    private Signal[] s;
	
	/** Constructeur par defaut. 
	 * @param signaux Les Signaux. 
	 */
	public FonctionMath (Signal[] signaux){
        this.s = signaux;
	}
	
	/** Operation correspondant a l'addition. 
	 * Le resultat est dans la variable resultat. */
	public void  Addition( ){
		this.resultat = new double[s[0].NB_POINTS * Signal.CASE_X ][2];
        
    	for(int i = (int) (-((s[0].NB_POINTS * Signal.CASE_X)/2)) ; i < (resultat.length /2) ; i++){ //soustraction pour remplir les nb negatifs
            resultat[i + ((s[0].NB_POINTS * Signal.CASE_X)/2)][0] = (i * s[0].echelleX / s[0].NB_POINTS) * s[0].nbPixelX / s[0].echelleX   + s[0].ox; //mise à l'echlle des x
			resultat[i + ((s[0].NB_POINTS * Signal.CASE_X)/2)][1] =  - (( s[0].fonction(s[0].getFreq() * ((i * s[0].echelleX / s[0].NB_POINTS) - s[0].decalageX * s[0].echelleX) - s[0].getDephasage()/(Math.PI*2))) * (s[0].getAmplitude() / s[0].echelleY)  * s[0].nbPixelY   
            + s[1].fonction( s[1].getFreq() * ((i * s[1].echelleX / s[1].NB_POINTS) - s[1].decalageX * s[1].echelleX) - s[1].getDephasage()/(Math.PI*2)) * (s[1].getAmplitude() / s[1].echelleY)  * s[1].nbPixelY  ) + s[0].oy;                
        }
	}
	
	/** Operation correspondant a la soustraction : signal1 - signal2. 
	 * Le resultat est dans la variable resultat. */
	public void Soustraction1( ){ 
        this.resultat = new double[s[0].NB_POINTS * Signal.CASE_X ][2];
        
    	for(int i = (int) (-((s[0].NB_POINTS * Signal.CASE_X)/2)) ; i < (resultat.length /2) ; i++){ //soustraction pour remplir les nb negatifs
            resultat[i + ((s[0].NB_POINTS * Signal.CASE_X)/2)][0] = (i * s[0].echelleX / s[0].NB_POINTS) * s[0].nbPixelX / s[0].echelleX   + s[0].ox; //mise à l'echlle des x
			resultat[i + ((s[0].NB_POINTS * Signal.CASE_X)/2)][1] =  - (( s[0].fonction(s[0].getFreq() * ((i * s[0].echelleX / s[0].NB_POINTS) - s[0].decalageX * s[0].echelleX) - s[0].getDephasage()/(Math.PI*2))) * (s[0].getAmplitude() / s[0].echelleY)  * s[0].nbPixelY   
            - s[1].fonction( s[1].getFreq() * ((i * s[1].echelleX / s[1].NB_POINTS) - s[1].decalageX * s[1].echelleX) - s[1].getDephasage()/(Math.PI*2)) * (s[1].getAmplitude() / s[1].echelleY)  * s[1].nbPixelY  ) + s[0].oy;                
        }
       
	}
	
	/** Operation correspondant a la soustraction : signal2 - signal1. 
	 * Le resultat est dans la variable resultat. */
	public void Soustraction2( ){ 
        this.resultat = new double[s[0].NB_POINTS * Signal.CASE_X ][2];
        
    	for(int i = (int) (-((s[0].NB_POINTS * Signal.CASE_X)/2)) ; i < (resultat.length /2) ; i++){ //soustraction pour remplir les nb negatifs
            resultat[i + ((s[0].NB_POINTS * Signal.CASE_X)/2)][0] = (i * s[0].echelleX / s[0].NB_POINTS) * s[0].nbPixelX / s[0].echelleX   + s[0].ox; //mise à l'echlle des x
			resultat[i + ((s[0].NB_POINTS * Signal.CASE_X)/2)][1] =  - (-( s[0].fonction(s[0].getFreq() * ((i * s[0].echelleX / s[0].NB_POINTS) - s[0].decalageX * s[0].echelleX) - s[0].getDephasage()/(Math.PI*2))) * (s[0].getAmplitude() / s[0].echelleY)  * s[0].nbPixelY   
            + s[1].fonction( s[1].getFreq() * ((i * s[1].echelleX / s[1].NB_POINTS) - s[1].decalageX * s[1].echelleX) - s[1].getDephasage()/(Math.PI*2)) * (s[1].getAmplitude() / s[1].echelleY)  * s[1].nbPixelY  ) + s[0].oy;                
        }
     
	}
	
	/** Operation correspondant a l'inversion du signal1 par rapport a l'axe des abscisses. 
	 * Le resultat est dans la variable resultat. */
	public void inverse1( ){ 
        this.resultat = new double[s[0].NB_POINTS * Signal.CASE_X ][2];
        
    	for(int i = (int) (-((s[0].NB_POINTS * Signal.CASE_X)/2)) ; i < (resultat.length /2) ; i++){ //soustraction pour remplir les nb negatifs
            resultat[i + ((s[0].NB_POINTS * Signal.CASE_X)/2)][0] = (i * s[0].echelleX / s[0].NB_POINTS) * s[0].nbPixelX / s[0].echelleX   + s[0].ox; //mise à l'echlle des x
			resultat[i + ((s[0].NB_POINTS * Signal.CASE_X)/2)][1] =  - ( - ( s[0].fonction(s[0].getFreq() * ((i * s[0].echelleX / s[0].NB_POINTS) - s[0].decalageX * s[0].echelleX) - s[0].getDephasage()/(Math.PI*2))) * (s[0].getAmplitude() / s[0].echelleY)  * s[0].nbPixelY ) + s[0].oy;                
        }
	}
	
	/** Operation correspondant a l'inversion du signal2 par rapport a l'axe des abscisses. 
	 * Le resultat est dans la variable resultat. */
	public void inverse2( ){ 
        this.resultat = new double[s[0].NB_POINTS * Signal.CASE_X ][2];
        
    	for(int i = (int) (-((s[0].NB_POINTS * Signal.CASE_X)/2)) ; i < (resultat.length /2) ; i++){ //soustraction pour remplir les nb negatifs
            resultat[i + ((s[0].NB_POINTS * Signal.CASE_X)/2)][0] = (i * s[0].echelleX / s[0].NB_POINTS) * s[0].nbPixelX / s[0].echelleX   + s[0].ox; //mise à l'echlle des x
			resultat[i + ((s[0].NB_POINTS * Signal.CASE_X)/2)][1] =  - ( - s[1].fonction( s[1].getFreq() * ((i * s[1].echelleX / s[1].NB_POINTS) - s[1].decalageX * s[1].echelleX) - s[1].getDephasage()/(Math.PI*2)) * (s[1].getAmplitude() / s[1].echelleY)  * s[1].nbPixelY  ) + s[0].oy;                
        }
	}
	
	/** Dessine la courbe a l'ecran via l'objet Graphics.
	 * @param g Objet graphique qui correspond a l'affichage du signal.
	 */
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
