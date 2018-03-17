/**
 * Classe qui defini les fonctions du menu maths
 * @author Anas
 *
 */



public class FonctionMath{
	
	public double[][] nuagePoint1;
	public double[][] nuagePoint2;
	
	public FonctionMath (Signal signal1,Signal signal2){
		nuagePoint1= signal1.nuagePoint;/* il faut definir deux signaux */
		nuagePoint2= signal2.nuagePoint;
	}
	
	public double[][] Addition( double[][] nuagePoint1,double[][] nuagePoint2){
		double[][] resultat1 = new double[nuagePoint1.length][nuagePoint1[0].length];
		for (int i = 0; i < nuagePoint1.length; i++)
		{
			for (int j = 0; i < nuagePoint1[0].length ; i++)
			{
				resultat1[i][j]=nuagePoint1[i][j]+nuagePoint2[i][j];
			}
			
		}
		return(resultat1);
	}
	
	public double[][] Soustraction1( double[][] nuagePoint1,double[][] nuagePoint2){ /*soustrait ch1 a ch2 */
		double[][] resultat2 = new double[nuagePoint1.length][nuagePoint1[0].length];
		for (int i = 0; i < nuagePoint1.length; i++)
		{
			for (int j = 0; i < nuagePoint1[0].length ; i++)
			{
				resultat2[i][j]=nuagePoint1[i][j]-nuagePoint2[i][j];
			}
			
		}
		return(resultat2);
	}
	
	public double[][] Soustraction2( double[][] nuagePoint1,double[][] nuagePoint2){ /*soustrait ch2 a ch1 */
		double[][] resultat3 = new double[nuagePoint1.length][nuagePoint1[0].length];
		for (int i = 0; i < nuagePoint1.length; i++)
		{
			for (int j = 0; i < nuagePoint1[0].length ; i++)
			{
				resultat3[i][j]=nuagePoint1[i][j]-nuagePoint2[i][j];
			}
			
		}
		return(resultat3);
	}
	
	public double[][] inverse1( double[][] nuagePoint1 ){ /*inverse ch1 */
		double[][] resultat4 = new double[nuagePoint1.length][nuagePoint1[0].length];
		for (int i = 0; i < nuagePoint1.length; i++)
		{
			for (int j = 0; i < nuagePoint1[0].length ; i++)
			{
				resultat4[i][j]=1/(nuagePoint1[i][j]);
			}
			
		}
		return(resultat4);
	}
	
	public double[][] inverse2( double[][] nuagePoint2 ){ /*inverse ch2 */
		double[][] resultat5 = new double[nuagePoint2.length][nuagePoint2[0].length];
		for (int i = 0; i < nuagePoint2.length; i++)
		{
			for (int j = 0; i < nuagePoint2[0].length ; i++)
			{
				resultat5[i][j]=1/(nuagePoint2[i][j]);
			}
			
		}
		return(resultat5);
	}
	
	
}	

