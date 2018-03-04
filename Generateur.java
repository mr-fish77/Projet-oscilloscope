import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Generateur extends JFrame implements ActionListener {
	
	/** Les signaux. */
	private Signal sig1, sig2;
	/* Signal affiché à l'écran.
	* A changer pour passer de byte à Signal. */
	private byte aLEcran; 
	
	/** Les JComponents utilisés. */
	private PanneauRemplir panSig;
	private JPanel affSup = new JPanel();
	private JPanel boutonsPan = new JPanel();
	private JPanel mainPanel = new JPanel();
	private ArrayList<JButton> boutons = new ArrayList<JButton>();
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	
	/** Constructeur principal.
	 * @param sig1 Le signal du channel 1.
	 * @param sig2 Le signal du channel 2.
	 */
	public Generateur(Signal s1, Signal s2) {
		super("Générateur");
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.sig1 = s1;
		this.sig2 = s2;
		aLEcran = 1;
		
		/** Affichage des infos des signaux dans la partie supérieure. */
		affSup.setBounds(0,0,this.getWidth(), 20);
		affSup.setLayout(new GridLayout(1,0));
		for(int i = 0; i < 10; i++){
			JLabel l = new JLabel();
			l.setOpaque(true);
			l.setBackground(Color.WHITE);
			l.setForeground(Color.BLACK);
			l.setFont(new Font("Courier", Font.BOLD, 15));
			l.setHorizontalAlignment(JLabel.CENTER);
			labels.add(l);
			affSup.add(l);
		}
		// Les cases d'indices 0 et 5 sont invariables.
		labels.get(0).setText("CH1");
		labels.get(0).setFont(new Font("Courier", Font.BOLD, 20));
		labels.get(0).setBackground(Color.LIGHT_GRAY);
		labels.get(5).setText("CH2");
		labels.get(5).setFont(new Font("Courier", Font.BOLD, 20));
		labels.get(5).setBackground(Color.LIGHT_GRAY);
		affSup.setBackground(null);
		
		/** Panneau ou on remplit les informations. 
		 * Voir en détail la sous-classe private PanneauRemplir. */
		panSig = new PanneauRemplir(0, 20, this.getWidth(), this.getHeight()-20-97);
		
		/** Boutons en bas. */
		boutonsPan.setBounds(0, this.getHeight()-97, this.getWidth(), 100);
		boutonsPan.setLayout(new FlowLayout()); // C'est par defaut mais comme ca c'est propre.
		boutonsPan.setBackground(Color.CYAN);
		
		boutons.add(new JButton("Appliquer"));
		boutons.add(new JButton()); // Son texte varie.
		boutons.add(new JButton()); // Son texte varie.
		boutons.add(new JButton("Réinitialiser"));
		for(JButton p : boutons){
			p.setFont(new Font("Arial", Font.PLAIN, 25));
			p.addActionListener(this);
			boutonsPan.add(p);
		}
		
		/** Conteneur principal. */
		mainPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(affSup);
		mainPanel.add(panSig);
		mainPanel.add(boutonsPan);
		
		/** Affichage. */
		refreshItems();
		this.setContentPane(mainPanel);
		this.revalidate(); // Je sais plus ce que c'est xD.
		setVisible(true);
	}
	
	/** Re-calcule le texte affiché dans les composants. 
	 * JComponents affectés : les JLabel en haut et les JButton en bas. */
	private void refreshItems(){
		String [] s;
		
		// Actif ou non
		if(sig1.getActive()){
			labels.get(1).setText("ON");
			labels.get(1).setForeground(Color.GREEN);
		} else {
			labels.get(1).setText("OFF");
			labels.get(1).setForeground(Color.DARK_GRAY);
		}
		if (sig2.getActive()){
			labels.get(6).setText("ON");
			labels.get(6).setForeground(Color.GREEN);
		} else {
			labels.get(6).setText("OFF");
			labels.get(6).setForeground(Color.DARK_GRAY);
		}
		
		// Types de signaux
		labels.get(2).setText(sig1.getForme());
		labels.get(7).setText(sig2.getForme());
		
		// Frequence
		s = sig1.getFreqAsString();
		labels.get(3).setText(s[0] + " " + s[1]);
		s = sig2.getFreqAsString();
		labels.get(8).setText(s[0] + " " + s[1]);
		
		// Amplitude
		s = sig1.getAmplAsString();
		labels.get(4).setText(s[0] + " " + s[1]);
		s = sig2.getAmplAsString();
		labels.get(9).setText(s[0] + " " + s[1]);
		
		// Bouton Allumer / Eteindre
		if (aLEcran == 1) {
			if(sig1.getActive()) {
				boutons.get(1).setText("Eteindre");
			} else {
				boutons.get(1).setText("Allumer");
			}
		} else {
			if(sig2.getActive()) {
				boutons.get(1).setText("Eteindre");
			} else {
				boutons.get(1).setText("Allumer");
			}
		}
		
		// Bouton Changer de canal
		if(aLEcran == 1) {
			boutons.get(2).setText("Canal 1");
		} else {
			boutons.get(2).setText("Canal 2");
		}
	}
	
	/** En cas d'appui sur un bouton.
	 * @param Informations sur l'action de l'utilisateur. */
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource().equals(boutons.get(0))){ // Bouton APPLIQUER
			System.out.println("Bouton APPLIQUER");
			sig1.setAmplitude(sig1.getAmplitude()/5); // pour un test
			
		} else if (e.getSource().equals(boutons.get(1))){ // Bouton ETEINDRE
			if(aLEcran == 1) {
				sig1.setActive(!sig1.getActive());
			} else {
				sig2.setActive(!sig2.getActive());
			}
			
		} else if (e.getSource().equals(boutons.get(2))) { // Bouton SWITCH
			if(aLEcran == 1) {
				aLEcran = 2;
				panSig.activeSig = sig2;
			} else {
				aLEcran = 1;
				panSig.activeSig = sig1;
			}
			panSig.refreshItems();
			
		} else if (e.getSource().equals(boutons.get(3))) { // Bouton RESET
			System.out.println("Bouton RESET");
			panSig.activeSig.resetSignal();
		}
		this.refreshItems();
	}
	
	/** Objet qui contient tous les JComponents nécessaires
	 * pour modifier la valeur d'un signal. 
	 * Placé dans une sous-classe private afin que cela soit plus lisible. */
	private class PanneauRemplir extends JPanel {
		
		private final Font DEFAULT_FONT = new Font("Calibri", Font.PLAIN, 15);
		private JComboBox<String> choixTypes = new JComboBox<>(Signal.SIGNAL_TYPES);
		private JComboBox<String> choixUniteFreq = new JComboBox<>(Signal.FREQ_UNITES);
		private JComboBox<String> choixUniteAmpl = new JComboBox<>(Signal.AMPL_UNITES);
		private JTextField[] txtFields = new JTextField[3];
		private JTextArea errorField = new JTextArea();
		private Signal activeSig = sig1;

		public PanneauRemplir(int posX, int posY, int wid, int hei) {
			super();
			this.setLocation(posX, posY);
			this.setSize(wid, hei);
			this.setBackground(new Color(192, 192, 192));
			this.setLayout(null);
			
			/* Espace supérieur. */
			JPanel justBlack = new JPanel();
			justBlack.setBackground(Color.BLACK);
			justBlack.setBounds(0, 0, this.getWidth(), 10);
			
			JLabel txt1 = new JLabel("Générateur de courant", JLabel.CENTER);
			txt1.setFont(new Font("Calibri", Font.BOLD + Font.ITALIC, 40));
			txt1.setBounds(0, 25, this.getWidth(), 40);
			txt1.setForeground(Color.DARK_GRAY);
			
			/* Espace de remplissage des composants. */
			JPanel ligne1 = new JPanel();
			ligne1.setBounds(40, 80, this.getWidth()-80, 30);
			ligne1.setLayout(new GridLayout(1,2));
			
			JLabel l = new JLabel("Forme");
			l.setFont(DEFAULT_FONT);
			ligne1.add(l);
			choixTypes.setFont(DEFAULT_FONT);
			ligne1.add(choixTypes);
			
			JPanel ligne2 = new JPanel();
			ligne2.setBounds(40, 80+30, this.getWidth()-80, 30);
			ligne2.setLayout(new GridLayout(1,3));
			
			l = new JLabel("Fréquence");
			l.setFont(DEFAULT_FONT);
			ligne2.add(l);
			txtFields[0] = new JTextField(Integer.toString(sig1.getFreq()));
			txtFields[0].setFont(DEFAULT_FONT);
			ligne2.add(txtFields[0]);
			choixUniteFreq.setFont(DEFAULT_FONT);
			ligne2.add(choixUniteFreq);
			
			JPanel ligne3 = new JPanel();
			ligne3.setBounds(40, 80+30+30, this.getWidth()-80, 30);
			ligne3.setLayout(new GridLayout(1,3));
			
			l = new JLabel("Amplitude");
			l.setFont(DEFAULT_FONT);
			ligne3.add(l);
			txtFields[1] = new JTextField(Double.toString(sig1.getAmplitude()));
			txtFields[1].setFont(DEFAULT_FONT);
			ligne3.add(txtFields[1]);
			choixUniteAmpl.setFont(DEFAULT_FONT);
			ligne3.add(choixUniteAmpl);
			
			/* Text Area pour les erreurs éventuelles. */
			errorField.setLineWrap(true);
			errorField.setEditable(false);
			errorField.setFont(DEFAULT_FONT);
			errorField.setBounds(40, 340, 520, 120);
			
			/* Assemblage et affichage. */
			add(justBlack);
			add(txt1);
			add(ligne1);
			add(ligne2);
			add(ligne3);
			add(errorField);
		}
		
		/** Re-calcule l'affichage des différentes cases 
		 * en fonction des propriétés du signal actif. */
		private void refreshItems() {
			this.choixTypes.setSelectedItem(activeSig.getForme());
			this.txtFields[0].setText(Integer.toString(activeSig.getFreq()));
			this.choixUniteFreq.setSelectedItem(activeSig.getFreqAsString()[1]);
			this.txtFields[1].setText(Double.toString(activeSig.getAmplitude()));
			this.choixUniteAmpl.setSelectedItem(activeSig.getForme());
		}
	}
	
	
}
