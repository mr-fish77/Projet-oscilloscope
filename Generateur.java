import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** @author Martin FRANCESCHI entre 10h et 11h30, avant manger. */

public class Generateur extends JFrame implements ActionListener {
	/** Les signaux. */
	private Signal sig1, sig2;
	private boolean activeSig;
	
	/** Les JComponents utilisés. */
	private JPanel panSig;
	private JPanel affSup = new JPanel();
	private JPanel boutonsPan = new JPanel();
	private JButton [] boutons = new JButton [3];
	private JLabel [] labels = new JLabel [10];
	
	/** Constructeur principal.
	 * @param sig1 Le signal du channel 1.
	 * @param sig2 Le signal du channel 2.
	 */
	public Generateur(Signal s1, Signal s2) {
		super("Générateur");
		setSize(800, 500);
		setResizable(false);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		this.sig1 = s1;
		this.sig2 = s2;
		activeSig = true;
		
		affSup.setBounds(0,0,800,80);
		affSup.setLayout(new GridLayout());
		for(JLabel l : labels){
			l = new JLabel();
			affSup.add(l);
		}
		
		panSig = new JPanel();
		panSig.setBounds(0,80, 800, 280);
		panSig.setLayout(null);
		
		boutonsPan.setBounds(0, 600, this.getWidth(), 140);
		boutonsPan.setLayout(null);
		
		boutons[0] = new JButton("Appliquer");
		boutons[0].setBounds(20, 20, 360, 100);
		boutons[0].setActionListener(this);
		boutonsPan.add(boutons[0]);
		
		boutons[1] = new JButton("Éteindre");
		boutons[1].setBounds(420, 20, 360, 100);
		boutons[1].setActionListener(this);
		boutonsPan.add(boutons[1]);
		
		
		/* Si on a un signal aléatoire, débloquer ce bouton ^^
		boutons[2] = new JButton("Générer un signal aléatoire");
		boutons[2].setBounds(400, 20, 380, 100);
		boutons[2].setActionListener(this);
		boutonsPan.add(boutons[2]);
		*/
		
		for(JPanel p : boutons){
			boutonsPan.add(p);
		}
		
		pack();
		setVisible(true);
	}
	
	public void paint(Graphics g){
		refreshLabels();
	}
	
	/** Méthode appelée à chaque paint() */
	private void refreshLabels(){
		for(JLabel l : labels){
			l.setBackground(Color.WHITE);
			l.setFont(new Font("Courier", Font.PLAIN, 11));
		}
		
		// CH1 et CH2
		labels[0].setText("CH1");
		labels[0].setFont(new Font("Courier", Font.BOLD, 11));
		labels[5].setText("CH2");
		labels[5].setFont(new Font("Courier", Font.BOLD, 11));
		
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
		labels[7].setText(sig2.getType());
		
		// Fréquence
		labels[3].setText(sig1.getFreq() + " Hz");
		labels[8].setText(sig2.getFreq() + " Hz");
		
		// Amplitude
		labels[4].setText(Math.round(sig1.getAmplitude()) + " V");
		labels[9].setText(Math.round(sig2.getAmplitude()) + " V");
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(boutons[0])){ // Bouton APPLIQUER
			"Appliquer";
		} else if (e.getSource().equals(boutons[1])){ // Bouton ETEINDRE
			activeSig ? sig1.desactiver() : sig2.desactiver();
		}
	}
}
