import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class MenuManager extends JPanel implements ActionListener{
	
	/** Les signaux. */
	protected Signal[] signaux;
	
	/** JPanel de titre. */
	private JLabel titre = new JLabel ();
	
	/** Les 5 menus. */
	protected JButton bouton1 = new JButton();	//organisation de haut en bas
	protected JButton bouton2 = new JButton();
	protected JButton bouton3 = new JButton();
	protected JButton bouton4 = new JButton();
	protected JButton bouton5 = new JButton();
	
	
	
	public MenuManager(Signal[] s) {
		super();
		signaux = s;
		
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setVerticalAlignment(JLabel.CENTER);
		
		setLayout(new GridLayout(6,1));	//fenetre separee en 6 cases de meme taille
		add(titre);
		add(bouton1);
		add(bouton2);
		add(bouton3);
		add(bouton4);
		add(bouton5);
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
		bouton1.addActionListener(al);
		bouton2.addActionListener(al);
		bouton3.addActionListener(al);
		bouton4.addActionListener(al);
		bouton5.addActionListener(al);
	}
	
	
	/** Methode qui change les textes des boutons tous a la fois
	 * @param String strTitre : Le texte du titre
	 * @param String str1 : Le texte du 1e bouton
	 * @param String str2 : Le texte du 2e bouton
	 * @param String str3 : Le texte du 3e bouton
	 * @param String str4 : Le texte du 4e bouton
	 * @param String str5 : Le texte du 5e bouton
	 */
	public void setTexts(String strTitre, String str1, String str2, String str3, String str4, String str5) {
		titre.setText(strTitre);
		bouton1.setText(str1);
		bouton2.setText(str2);
		bouton3.setText(str3);
		bouton4.setText(str4);
		bouton5.setText(str5);
	}
	
	/** MEthode qui prend en charge l'action sur les boutons
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
