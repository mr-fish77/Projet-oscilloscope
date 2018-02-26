import java.awt.Color;
import java.awt.event.*;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Generateur extends JFrame implements ActionListener {
	/** Les signaux. */
	private Signal sig1, sig2;
	
	/** Les JComponents utilisés. */
	private JPanel panSig1, panSig2;
	private JPanel affSup = new JPanel();
	private JPanel boutonsPan = new JPanel();
	private JButton [] boutonsTab = new JButton [3];
	private JLabel [] labels = new JLabel [10];
	
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
		affSup.setLayout(new GridLayout());
		for(JLabel l : labels){
			l = new JLabel();
			affSup.add(l);
		}
		
		panSig1 = new JPanel();
		panSig2 = new JPanel();
		
		boutonsPan.setBounds(0, 600, this.getWidth(), 200);
		boutonsPan.setLayout(null);
		
		
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
		
		for(JPanel p : boutonsPan){
			add(p);
		}
		
		pack();
		setVisible(true);
	}
	
	private void refreshLabels(){
		// CH1 et CH2
		labels[0].setText("CH1");
		labels[0].setBackground(Color.WHITE);
		labels[5].setText("CH2");
		labels[5].setBackground(Color.WHITE);
		
		// Actif ou non
		if(sig1.isActive()){
			labels[1].setText("ON");
			labels[1].setBackground("GREEN");
		} else {
			labels[1].setText("OFF");
			labels[1].setBackground(Color.BROWN);
		}
		if (sig2.isActive()){
			labels[6].setText("ON");
			labels[6].setBackground("GREEN");
		} else {
			labels[6].setText("OFF");
			labels[6].setBackground(Color.BROWN);
		}
		
		// Types de signaux
		labels[2].setText(sig1.getType());
		labels[2].setBackground(Color.GRAY);
		labels[7].setText(sig2.getType());
		labels[7].setBackground(Color.GRAY);
		
		// Fréquence
		labels[3].setText(sig1.getFreq() + " Hz");
		labels[3].setBackground(Color.GRAY);
		labels[8].setText(sig2.getFreq() + " Hz");
		labels[8].setBackground(Color.GRAY);
		
		// Amplitude
		labels[4].setText(Math.round(sig1.getAmplitude()) + " V");
		labels[4].setBackground(Color.GRAY);
		labels[9].setText(Math.round(sig2.getAmplitude()) + " V");
		labels[9].setBackground(Color.GRAY);
}
