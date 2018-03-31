/**
 * Classe qui permet controler le menu math
 * @author Ã€nas
 *
 */


import java.awt.Graphics;

public class MenuMath extends AbstractMenu {
	private FonctionMath smaths;
    public int operation = 0;
    
	
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
		
        switch (operation)
        {
          case 0:
            break;
          case 1:
            smaths.Addition();
            break;
          case 2:
            smaths.Soustraction1();
            break;
          case 3:
            smaths.Soustraction2();
            break;
          case 4:
            smaths.inverse1();
            break;
          case 5:
            smaths.inverse2();
            break;
          default:
            
        }
    
		smaths.dessineCourbe(g);		
	}
	
    public void miseEnRoute() {}
    
    public void actionBouton1() {
        
        smaths.Addition();
        this.operation =1;
        oscillo.ecran.grille.repaint();
        
    }
    
    public void actionBouton2() {
        
        smaths.Soustraction1();
        this.operation =2;
        oscillo.ecran.grille.repaint();

        
        }
        
    public void actionBouton3() {
        
        smaths.Soustraction2();
        this.operation =3;
        oscillo.ecran.grille.repaint();

    }
    
    public void actionBouton4() {
        
        smaths.inverse1();
        this.operation =4;
        oscillo.ecran.grille.repaint();

        
    }
    
    public void actionBouton5() {
        
        smaths.inverse2();
        this.operation =5;
        oscillo.ecran.grille.repaint();

        
    }


	
	
	
}
