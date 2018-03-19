import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

public class MenuDuBas extends JPanel {
	// informations generales
	private JLabel texte;
	
	// JLabel associes a chaque information de volts/div ou sec/div
	private JLabel texteCh1;
	private JLabel texteCh2;
	private JLabel texteTemps;
	private JPanel conteneurInfo;	//contient les 3 precedents JLabels
	
	/**
	 * Constructeur du menu du bas
	 */
	public MenuDuBas() {
		setLayout(new BorderLayout());
		
		//informations generales
		texte = new JLabel();
		add(texte, BorderLayout.EAST);
		
		//textes des channels et du temps
		conteneurInfo = new JPanel();
		conteneurInfo.setLayout(new GridLayout(1, 3));
		
		texteCh1 = new JLabel();
		texteCh2 = new JLabel();
		texteTemps = new JLabel();
		
		conteneurInfo.add(texteCh1);
		conteneurInfo.add(texteCh2);
		conteneurInfo.add(texteTemps);
		
		add(conteneurInfo, BorderLayout.WEST);
		
		
	}
	
	/** Permet de changer le texte general
	 * @param String str : le texte a afficher
	 */
	public void setText(String str){
		texte.setText(str);
	}
	
	
	/** Permet de changer le texte Ch2
	 * @param String str : le texte a afficher
	 */
	public void setCh(String str, int n){
		if(n == 0){
			texteCh1.setText(str);
		}else if(n == 1){
			texteCh2.setText(str);
		}
	}
	
	/** Permet de changer le texte du temps
	 * @param String str : le texte a afficher
	 */
	public void setTemps(String str){
		texteTemps.setText(str);
	}
	
}
