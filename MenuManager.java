import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class MenuManager extends JPanel implements ActionListener{
	
	/** Les signaux. */
	protected Signal[] signaux;
	
	/** JPanel de titre. */
	private JLabel titre = new JLabel ();
	
	/** Les 5 menus. */
	protected ArrayList<JButton> menus = new ArrayList<JButton>();
	
	
	public MenuManager(Signal[] s) {
		signaux = s;
		
	}
	//private JButton bouton1, bouton2, bouton3, bouton4, bouton5; 
	
	/** Ecrit le titre et affiche les 5 boutons. 
	 * @param g L'element graphique fort appreciable. */
	public void paint (Graphics g) {
		super.repaint();
		g.setColor(java.awt.Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		int titreHeight = 50;
		titre.setBounds(0, 0, this.getWidth(), titreHeight);
		titre.repaint();
		
		int tailleRestante = this.getHeight() - titreHeight;
		for (int i = 0; i < menus.size(); i++) {
			menus.get(i).setBounds(0, titreHeight + tailleRestante * i, this.getWidth(), tailleRestante / 5);
			menus.get(i).repaint();
		}
		
	}
	
	public void actionPerformed (ActionEvent e){
	}
}
//Mes quetions : pk faire un ArrayList de menus en attributs (on ne fait jamais appel à cette liste ni dans
//ni dans oscilloscope (si c'est pour choisir le type de menu--> classe héritière préférable
//je ne comprends pas comment la bande noire s'affiche avec les bonnes dimensions sur le côté
// j'ai vu que les tailles étaient définies dans la méthode paint de l'écran mais à aucun moment cette méthode n'est appelée 
// dans oscilloscope
//5 JButton quand on cliquera dessus, infos qui changent comment faire pour changer le texte d'un JButton
