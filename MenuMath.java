/**
 * Classe qui permet controler le menu math
 * @author Ànas
 *
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MenuMath extends AbstractMenu {
	public FonctionMath smaths;
    
	
	public MenuMath(Signal[] s){
		super(s);
        smaths = new FonctionMath(s);
		
		bouton1.setText("Ajouter: ");
		bouton2.setText("Soustraire: CH1 - CH2");
		bouton3.setText("Soustraire: CH2 - CH1");
		bouton4.setText("Inverser CH1");
		bouton5.setText("Inverser CH2");
	}
	
	public void paintGrille(Graphics g, int hauteur, int largeur) {
		smaths.dessineCourbe(g);		
	}
	
    public void miseEnRoute() {}
    
    public void actionBouton1(ActionEvent e) {
        
        smaths.Addition();
        
    }
    
    public void actionBouton2(ActionEvent e) {
        
        smaths.Soustraction1();
        
        }
        
    public void actionBouton3(ActionEvent e) {
        
        smaths.Soustraction2();
        
    }
    
    public void actionBouton4(ActionEvent e) {
        
        smaths.inverse1();
        
    }
    
    public void actionBouton5(ActionEvent e) {
        
        smaths.inverse2();
        
    }


	
	
	
}
