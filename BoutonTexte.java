import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe qui permet d'afficher un bouton et son texte en dessous
 * @author Pierre-Yves
 *
 */
public class BoutonTexte extends JPanel{
	public JButton bouton;	//bouton qui est accessible de l'exterieur
	private JLabel texte;	//Label qui contient le texte fourni en parametre
	
	/**
	 * Constructeur qui permet de generer le bouton avec son texte
	 * @param String texte : le texte a afficher dans le bouton
	 */
	public BoutonTexte(String texte) {
		super();
		setBackground(Color.RED);
		setLayout(new GridBagLayout());	//on utilise un layout avance, mais c'est le seul moyen que j'ai trouve
		GridBagConstraints contraintes = new GridBagConstraints();	//outil qui permet de parametrer le layout
		
		//On cree le bouton
		bouton = new JButton("");
		contraintes.gridx = 0;	//colonne 0
		contraintes.gridy = 0;	//ligne 0
		contraintes.fill = GridBagConstraints.VERTICAL;	//on autorise le composant a s'etendre juste verticalement
		add(bouton, contraintes);
		
		//et le texte
		this.texte = new JLabel(texte);
		this.texte.setHorizontalAlignment(JLabel.CENTER);
		this.texte.setOpaque(true);
		this.texte.setBackground(Color.YELLOW);
		contraintes.gridx = 0;
		contraintes.gridy = 1;	//ligne 1
		contraintes.fill = GridBagConstraints.VERTICAL;
		add(this.texte, contraintes);
	}
}
