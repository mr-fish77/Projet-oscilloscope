import java.awt.Color;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Generateur extends JFrame implements ActionListener {
	/** Les signaux. */
	private Signal sig1, sig2;
	
	/** Les JComponents utilisés. */
	private JPanel panSig1, panSig2;
	private JPanel affSup = new JPanel();
	private JPanel boutonsPan = new JPanel();
	private JButton [] boutonsTab = new JButton [3];
	
	/** Constructeur principal.
	 * @param sig1 Le signal du channel 1.
	 * @param sig2 Le signal du channel 2.
	 */
	public Generateur(Signal sig1, Signal s2) {
		super("Générateur");
		setSize(800, 500);
		setResizable(false);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		this.sig1 = sig1;
		this.sig2 = sig2;
		
		affSup.setBounds(0,0,800,100);
		affSup.setLayout(null);
		
		
		panSig1 = new JPanel();
		panSig2 = new JPanel();
		
		boutonsPan.setBounds(0, 600, this.getWidth(), 200);
		boutonsPan.setLayout(null);
		add(boutonsPan);
		
		boutonsTab[0] = new JButton("Appliquer");
		boutonsTab[0].setBounds(20, 20, 360, 100);
		boutonsTab[0].setActionListener(this);
		boutonsPan.add(boutonsTab[0]);
		
		boutonsTab[1] = new JButton("Éteindre");
		boutonsTab[1].setBounds(420, 20, 360, 100);
		boutonsTab[1].setActionListener(this);
		boutonsPan.add(boutonsTab[1]);
		
		
		/* Si on a un signal aléatoire, débloquer ce bouton ^^
		boutonsTab[2] = new JButton("Générer un signal aléatoire");
		boutonsTab[2].setBounds(400, 20, 380, 100);
		boutonsTab[2].setActionListener(this);
		boutonsPan.add(boutonsTab[2]);
		*/
		setVisible(true);
	}
}
