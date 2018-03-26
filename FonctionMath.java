/**
 * Classe qui defini les fonctions du menu maths
 * @author Anas
 *
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class FonctionMath{
	
	public double[][] resultat;
	public double[][] nuagePoint1;
	public double[][] nuagePoint2;
	public boolean active;
    
    public Signal[] signaux;
	
	
	public FonctionMath (Signal[] s){
        signaux = s;
		active=true;
	}
	
	public void  Addition( ){
        
       
        nuagePoint1= signaux[0].nuagePoint;
		nuagePoint2= signaux[1].nuagePoint;
        
        
        resultat = new double[nuagePoint1.length][nuagePoint1[0].length];
		for (int i = 0; i < resultat.length; i++)
		{
			for (int j = 0; j < resultat[0].length ; j++)
			{
				resultat[i][j]=nuagePoint1[i][j]+nuagePoint2[i][j];
                System.out.println(resultat[i][j]);
			}
			
		}
	}
	
	public void Soustraction1( ){ /*soustrait ch1 a ch2 */
        nuagePoint1= signaux[0].nuagePoint;
		nuagePoint2= signaux[1].nuagePoint;
        
        resultat = new double[nuagePoint1.length][nuagePoint1[0].length];
        
		for (int i = 0; i < nuagePoint1.length; i++)
		{
			for (int j = 0; j < nuagePoint1[0].length ; j++)
			{
				resultat[i][j]=nuagePoint1[i][j]-nuagePoint2[i][j];
			}
			
		}
	}
	
	public void Soustraction2( ){ /*soustrait ch2 a ch1 */
        nuagePoint1= signaux[0].nuagePoint;
		nuagePoint2= signaux[1].nuagePoint;
        
        resultat = new double[nuagePoint1.length][nuagePoint1[0].length];
		for (int i = 0; i < nuagePoint1.length; i++)
		{
			for (int j = 0; j < nuagePoint1[0].length ; j++)
			{
				resultat[i][j]=nuagePoint1[i][j]-nuagePoint2[i][j];
			}
			
		}
	}
	
	public void inverse1( ){ /*inverse ch1 */
        nuagePoint1= signaux[0].nuagePoint;
		nuagePoint2= signaux[1].nuagePoint;
        
        resultat = new double[nuagePoint1.length][nuagePoint1[0].length];
		for (int i = 0; i < nuagePoint1.length; i++)
		{
			for (int j = 0; j < nuagePoint1[0].length ; j++)
			{
				resultat[i][j]=1/(nuagePoint1[i][j]);
			}
			
		}
	}
	
	public void inverse2( ){ /*inverse ch2 */
        nuagePoint1= signaux[0].nuagePoint;
		nuagePoint2= signaux[1].nuagePoint;
        
        resultat = new double[nuagePoint1.length][nuagePoint1[0].length];
		for (int i = 0; i < nuagePoint2.length; i++)
		{
			for (int j = 0; j < nuagePoint2[0].length ; j++)
			{
				resultat[i][j]=1/(nuagePoint2[i][j]);
			}
			
		}
	}
	
	public void dessineCourbe(Graphics g) {
    	if(active) {		//on regarde si le signal doit etre affiche
	        g.setColor(Color.RED);
	        
	        int a = 0;
	        while(a < (resultat.length-1)){
	            g.drawLine((int) (resultat[a][0]), (int) (resultat[a][1]),(int) (resultat[a+1][0]),(int) (resultat[a+1][1]));
	            a++;
	        }
    	}
	    	
    }
	
	
}	

