import java.awt.Color;

import javax.swing.JFrame;

public abstract class Fenetre extends JFrame {

	/**
	 * Constructeur principal G�n�re une fen�tre d'apr�s des mod�les pr�d�finis.
	 * 
	 * @param c1
	 *            Un caract�re qui d�termine le mod�le de fen�tre � appliquer : 'o'
	 *            pour Oscilloscope, 'g' pour G�n�rateur de courant.
	 * @throws IllegalArgumentException
	 *             Si le param�tre c1 ne correspond pas aux mod�les pr�d�finis.
	 */
	public Fenetre(char c1) {
		super();
		switch (c1) {
		case 'o':
			super.setTitle("Oscilloscope");
			super.setSize(1200, 600);
			break;
		case 'g':
			super.setTitle("G�n�rateur de courant");
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
