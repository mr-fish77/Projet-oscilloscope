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
		constructeurDeporte(texte);
	}
	
	
	/** Constructeur qui permet de generer le bouton avec son texte.
	 * @param String texte : le texte a afficher dans le bouton.
	 */
	public BoutonTexte(String texte, ActionListener al) {
		super();
		constructeurDeporte(texte);
		bouton.addActionListener(al);
		bouton.setEnabled(true);
	}
	
	/**
	 * Constructeur deporte (si jamais on doit retoucer tout)
	 * @param texte
	 */
	public void constructeurDeporte(String texte) {
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
	 * Le BoutonTexte devient actif et colore en jaune.
	 * @param obj Un objet qui implemente ActionListener. */
	public void addActionListener (ActionListener al){
		this.bouton.addActionListener(al);
		this.bouton.setEnabled(true);
	}
	
	/** Modifie le texte du Label.
	 *@param le texte a modifier. */
	public void setText(String s){
		this.texte.setText(s);
	}
	
	/** @return le texte du Label. */
	public String getText(){
		return this.texte.getText();
	}
	
	/**
	 * Retourne le bouton
	 *@return JButton le bouton
	 */
	public JButton getJButton() {
		return bouton;
	}
			 
}
