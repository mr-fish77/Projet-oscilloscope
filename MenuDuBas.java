import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

/**
 * Classe qui gere la zone de notification
 */
public class MenuDuBas extends JPanel {
	/** Informations generales. */
	private JLabel texte;
	
	/** JLabel associes a chaque information de volts/div ou sec/div. */
	private JLabel texteCh1, texteCh2, texteTemps;
	
	/**
	 * Constructeur du menu du bas
	 */
	public MenuDuBas() {
		setOpaque(false);
		setLayout(new GridLayout(1,4));
		
		//textes des channels et du temps
		texteCh1 = new JLabel();
		texteCh2 = new JLabel();
		texteTemps = new JLabel();
		add(texteCh1);
		add(texteCh2);
		add(texteTemps);
		
		//informations generales
		texte = new JLabel();
		add(texte);
	}
	
	/** Permet de changer le texte general
	 * @param str : le texte a afficher
	 */
	public void setText(String str){
		texte.setText(str);
	}
	
	
	/** Permet de changer le texte Ch2
	 * @param str : le texte a afficher
	 * @param n : le numero du signal
	 */
	public void setCh(String str, int n){
		if(n == 0){
			texteCh1.setText(str);
		}else if(n == 1){
			texteCh2.setText(str);
		}
	}
	
	/** Permet de changer le texte du temps
	 * @param str : le texte a afficher
	 */
	public void setTemps(String str){
		texteTemps.setText(str);
	}
	
}
