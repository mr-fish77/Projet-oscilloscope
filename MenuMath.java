/**
 * Classe qui permet controler le menu math
 * @author Ànas
 *
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MenuMath extends JFrame implements ActionListener{
	
	private JButton Ajouter = new JButton("Ajouter");
	private JButton Soustraire1= new JButton("Soustraction de ch1 a ch2");
	private JButton Soustraire2= new JButton("Soustraction de ch2 a ch1");
	private JButton Inverser1= new JButton("inverser CH1 ");
	private JButton Inverser2= new JButton("inverser CH2 ");
	private JButton Math= new JButton("Math");
	private JPanel Menu= new JPanel();
	
	/*Math.addActionListener(this);
	Ajouter.addActionListener(this);
	Soustraire1.addActionListener(this);
	Soustraire2.addActionListener(this);
	Inverser.addActionListener(this);
	
	Menu.add( Ajouter, Soustraire, Multiplication);
	Ajouter.setBounds();
	Multiplier.setBounds();
	Soustraction.setBounds();*/ //jai un petit problème là
	
	public void actionPerformed( ActionEvent e){
		Menu.setBounds(0,0,0,0);/*a definir avec vous aussi */
		if(e.getSource()==Ajouter){
			Addition( nuagePoint1, nuagePoint2);/*je sais pas comment recuperer les signaux depuis l'affichage*/
		}
		else if(e.getSource()==Soustraire1){
			Soustraction1( nuagePoint1, nuagePoint2);/*je sais pas comment recuperer les signaux depuis l'affichage*/
		}
		else if(e.getSource()==Soustraire2){
			Soustraction2( nuagePoint1, nuagePoint2);/*je sais pas comment recuperer les signaux depuis l'affichage*/
		}
		else if(e.getSource()==Inverser1 ){
			inverse1( nuagePoint1, nuagePoint2);/*je sais pas comment recuperer les signaux depuis l'affichage*/
		}
	}
	
}
