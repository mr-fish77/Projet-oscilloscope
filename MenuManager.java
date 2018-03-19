import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuManager extends JPanel {
	
	/** Les signaux. */
	private Signal[] signaux;
	
	/** JPanel de titre. */
	private JLabel titre = new JLabel ();
	
	/** Les 5 menus. */
	protected ArrayList<JPanel> menus = new ArrayList<JPanel>();
	
	public MenuManager(Signal[] s) {
		super();
		setBackground(Color.BLACK);
		signaux = s;
	}
	
	/** Ecrit le titre et affiche les 5 boutons. 
	 * @param g L'element graphique fort appreciable. */
	public void paint (Graphics g) {
		super.paint(g);
		
		int titreHeight = 50;
		titre.setBounds(0, 0, this.getWidth(), titreHeight);
		titre.repaint();
		
		int tailleRestante = this.getHeight() - titreHeight;
		for (int i = 0; i < menus.size(); i++) {
			menus.get(i).setBounds(0, titreHeight + tailleRestante * i, this.getWidth(), tailleRestante / 5);
			menus.get(i).repaint();
		}
		
	}
}
