import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe qui permet d'afficher un bouton et son texte en dessous.
 * Si le bouton n'a pas de Listener, il est grise et le clic dessus ne marche pas.
 * Sinon, il est jaune et cliquable.
 * @author Pierre-Yves
 *
 */
public class BoutonTexte extends JPanel {
	/** Bouton. */
	private JButton bouton;	
	/** Label contenant le texte en paramètre du constructeur. */
	private JLabel texte;
	
	/** Constructeur qui permet de generer le bouton avec son texte.
	 * @param String texte : le texte a afficher dans le bouton.
	 */
	public BoutonTexte(String texte) {
		super();
		setOpaque(false);
		setLayout(new GridBagLayout());	//on utilise un layout avance, mais c'est le seul moyen que j'ai trouve
		GridBagConstraints contraintes = new GridBagConstraints();	//outil qui permet de parametrer le layout
		
		//On cree le bouton
		bouton = new JButton("");
		bouton.setEnabled(false);
		contraintes.gridx = 0;	//colonne 0
		contraintes.gridy = 0;	//ligne 0
		contraintes.fill = GridBagConstraints.VERTICAL;	//on autorise le composant a s'etendre juste verticalement
		add(bouton, contraintes);
		
		//et le texte
		this.texte = new JLabel(texte);
		this.texte.setHorizontalAlignment(JLabel.CENTER);
		this.texte.setOpaque(true);
		this.texte.setBackground(Color.LIGHT_GRAY);
		contraintes.gridx = 0;
		contraintes.gridy = 1;	//ligne 1
		contraintes.fill = GridBagConstraints.VERTICAL;
		add(this.texte, contraintes);
	}
	
	/** Ajoute un ActionListener sur le bouton. 
	 * @param obj Un objet qui implemente ActionListener. */
	public void addActionListener (ActionListener obj){
		this.bouton.addActionListener(obj);
		this.bouton.setActionCommand(this.texte.getText());
		this.bouton.setEnabled(true);
		this.texte.setBackground(Color.YELLOW);
	}
	
	/** @return le texte du Label. */
	public void setText(String s){
		this.texte.setText(s);
	}
	
	/** Modifie le text du Label.
	 *@param le texte a modifier. */
	public String getText(){
		return this.texte.getText();
	}
}
