import java.awt.Color;

import javax.swing.JFrame;

public abstract class Fenetre extends JFrame {

	/**
	 * Constructeur principal Génère une fenêtre d'après des modèles prédéfinis.
	 * 
	 * @param c1
	 *            Un caractère qui détermine le modèle de fenêtre à appliquer : 'o'
	 *            pour Oscilloscope, 'g' pour Générateur de courant.
	 * @throws IllegalArgumentException
	 *             Si le paramètre c1 ne correspond pas aux modèles prédéfinis.
	 */
	public Fenetre(char c1) {
		super();
		switch (c1) {
		case 'o':
			super.setTitle("Oscilloscope");
			super.setSize(1200, 600);
			break;
		case 'g':
			super.setTitle("Générateur de courant");
			super.setSize(800, 500);
			break;
		default:
			throw new IllegalArgumentException();
		}
		super.setResizable(false);
		super.setBackground(Color.GRAY);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(null);
		super.setVisible(true);

	}
}
