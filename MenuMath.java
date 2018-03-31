/**
 * Classe qui permet controler le menu math
 * @author Ã€nas
 *
 */


import java.awt.Graphics;

public class MenuMath extends AbstractMenu {
	private FonctionMath smaths;
    
	
	public MenuMath(){
		super();
        smaths = new FonctionMath(signaux);
		
		bouton1.setText("Ajouter: ");
		bouton2.setText("Soustraire: CH1 - CH2");
		bouton3.setText("Soustraire: CH2 - CH1");
		bouton4.setText("Inverser CH1");
		bouton5.setText("Inverser CH2");
	}
	
	public void paintGrille(Graphics g, int hauteur, int largeur) {
		/* Cette methode est appelee des qu'un repaint est necessaire: !!! EN PARTICULIER QUAND LES SIGNAUX CHANGENT !!!
		 * C'est ici que vous devez recalculer a chaque fois le nuage de point
		 * 
		 * C'est pour cela qu'il est peut être judicieux de creer une variable qui retient le calcul a effectuer (du style 0 : soustraction 1-2 / 1 soustraction 2-1 ...)
		 */
		
		smaths.dessineCourbe(g);		
	}
	
    public void miseEnRoute() {}
    
    public void actionBouton1() {
        
        smaths.Addition();
        
    }
    
    public void actionBouton2() {
        
        smaths.Soustraction1();
        
        }
        
    public void actionBouton3() {
        
        smaths.Soustraction2();
        
    }
    
    public void actionBouton4() {
        
        smaths.inverse1();
        
    }
    
    public void actionBouton5() {
        
        smaths.inverse2();
        
    }


	
	
	
}
