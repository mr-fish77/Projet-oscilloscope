import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class MenuManager extends JPanel implements ActionListener{
	
	/** Les signaux. */
	protected Signal[] signaux;
	
	/** String indiquant le menu actuellement a l'ecran.
	 * Il est affiche dans le Terminal. */
	protected static String menuActuel = "NoMenu";
	
	/** Les 5 menus. */
	protected LinkedList<JButton> boutons = new LinkedList<JButton>();
	
	protected MenuManager(Signal[] s, String menuActuel) {
		super();
		signaux = s;
		this.menuActuel = menuActuel;
		System.out.println("MENU " + menuActuel);
		
		setLayout(new GridLayout(5,1));	//fenetre separee en 5 cases de meme taille
		for (int i = 0; i < 5; i++) {
			JButton b = new JButton();
			b.setActionCommand(Integer.toString(i));
			boutons.add(b);
			this.add(b);
		}
	}
	
	
	/** Ecrit le titre et affiche les 5 boutons. 
	 * @param g L'element graphique fort appreciable. */
	public void paint (Graphics g) {
		super.paint(g);
	}
	
	
	/** Methode qui ajoute un actionListener a tous les boutons a la fois
	 * @param ActionListener al : l'actionlistener a rajouter a tous les objets
	 */
	public void addActionListener(ActionListener al) {
		for (JButton b : boutons) {
			b.addActionListener(al);
		}
	}
	
	
	/** Methode qui prend en charge l'action sur les boutons
	 * @param ActionEvent e : l'action event habituel
	 */
	public abstract void actionPerformed (ActionEvent e);
}
//Mes quetions : pk faire un ArrayList de menus en attributs (on ne fait jamais appel à cette liste ni dans
//ni dans oscilloscope (si c'est pour choisir le type de menu--> classe héritière préférable
//je ne comprends pas comment la bande noire s'affiche avec les bonnes dimensions sur le côté
// j'ai vu que les tailles étaient définies dans la méthode paint de l'écran mais à aucun moment cette méthode n'est appelée 
// dans oscilloscope
//5 JButton quand on cliquera dessus, infos qui changent comment faire pour changer le texte d'un JButton
