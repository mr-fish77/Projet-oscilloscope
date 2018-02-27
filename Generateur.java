import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** @author Martin FRANCESCHI entre 10h et 11h30, avant manger. */

public class Generateur extends JFrame implements ActionListener {
	/** Les signaux. */
	private Signal sig1, sig2;
	private boolean aLEcran; // Signal affich� � l'�cran, true pour 1, false pour 2.
	
	/** Les JComponents utilisés. */
	private JPanel panSig;
	private JPanel affSup = new JPanel();
	private JPanel boutonsPan = new JPanel();
	private JPanel mainPanel = new JPanel();
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
		aLEcran = true;
		
		affSup.setBounds(0,0,800,80);
		affSup.setLayout(new GridLayout());
		for(int i = 0; i < labels.length; i++){
			labels[i] = new JLabel();
			refreshLabels();
			affSup.add(labels[i]);
		}
		
		panSig = new JPanel();
		panSig.setBounds(0,80, 800, 280);
		panSig.setLayout(null);
		
		boutonsPan.setBounds(0, 600, this.getWidth(), 140);
		boutonsPan.setLayout(null);
		
		boutons[0] = new JButton("Appliquer");
		boutons[0].setBounds(20, 20, 360, 100);
		boutons[0].addActionListener(this);
		boutonsPan.add(boutons[0]);
		
		boutons[1] = new JButton("Éteindre");
		boutons[1].setBounds(420, 20, 360, 100);
		boutons[1].addActionListener(this);
		boutonsPan.add(boutons[1]);
		
		
		/* Si on a un signal aléatoire, débloquer ce bouton ^^
		boutons[2] = new JButton("Générer un signal aléatoire");
		boutons[2].setBounds(400, 20, 380, 100);
		boutons[2].setActionListener(this);
		boutonsPan.add(boutons[2]);
		*/
		
		for(JButton p : boutons){
			if(p != null) boutonsPan.add(p);
		}
		
		this.setContentPane(mainPanel);
		mainPanel.add(affSup);
		mainPanel.add(boutonsPan);
		pack();
		setVisible(true);
	}
	
	private void refreshLabels(){
		for(int i = 0; i < labels.length; i++){
			labels[i].setBackground(Color.WHITE);
			labels[i].setFont(new Font("Courier", Font.PLAIN, 11));
		}
		
		// CH1 et CH2
		labels[0].setText("CH1");
		labels[0].setFont(new Font("Courier", Font.BOLD, 11));
		labels[5].setText("CH2");
		labels[5].setFont(new Font("Courier", Font.BOLD, 11));
		
		// Actif ou non
		if(sig1.getActive()){
			labels[1].setText("ON");
			labels[1].setBackground(Color.GREEN);
		} else {
			labels[1].setText("OFF");
			labels[1].setBackground(Color.DARK_GRAY);
		}
		if (sig2.getActive()){
			labels[6].setText("ON");
			labels[6].setBackground(Color.GREEN);
		} else {
			labels[6].setText("OFF");
			labels[6].setBackground(Color.DARK_GRAY);
		}
		
		// Types de signaux
		labels[2].setText(sig1.getForme());
		labels[7].setText(sig2.getForme());
		
		// Fréquence
		labels[3].setText(sig1.getFreq() + " Hz");
		labels[8].setText(sig2.getFreq() + " Hz");
		
		// Amplitude
		labels[4].setText(Math.round(sig1.getAmplitude()) + " V");
		labels[9].setText(Math.round(sig2.getAmplitude()) + " V");
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(boutons[0])){ // Bouton APPLIQUER
			String O = "Appliquer";
		} else if (e.getSource().equals(boutons[1])){ // Bouton ETEINDRE
			if(aLEcran) 
				sig1.setActive(false);
			else 
				sig2.setActive(false);
		}
	}
}
