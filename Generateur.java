import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Generateur extends JFrame implements ActionListener, MouseListener {
	/** Les signaux. */
	private Signal signal[] = new Signal[2];
	
	/** Les JPanel utilises. */
	private JPanel affInfos = new JPanel(), mainPanel = new JPanel();
	/** Les SigPan utilises (voir doc plus bas). */
	private SigPan[] pan = new SigPan[2];
	/** Les JButton a surveiller. 
	 * Le premier indice indique le numero de signal, le deuxieme le numero du bouton. */
	private JButton[][] btns = new JButton[2][3];
	/** Les JPanel a surveiller (comme des JButton). */
	private OnOff[] onOff = new OnOff[2];
	
	/** Police par defaut du generateur : Calibri 15. */
	private final Font DEFAULT_FONT = new Font("Calibri", Font.PLAIN, 18);
	/** Police Courier. */
	private final Font COURIER = new Font("Courier", Font.BOLD, 20);
	/** Police de titre de section : Arial gras 30. */
	private final Font TITLE = new Font ("Arial", Font.BOLD, 30);
	/** La fenetre est un carre de taille SIZE. */
	private final int SIZE = 600;
	
	
	public Generateur(Signal s1, Signal s2) {
		super("Generateur");
		setSize(SIZE, SIZE); // Taille carree.
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.signal[0] = s1;
		this.signal[1]= s2;
		
		/* Texte de titre de fenetre. */
		JLabel txt1 = new JLabel("Generateur de courant", JLabel.CENTER);
		txt1.setFont(new Font("Calibri", Font.BOLD + Font.ITALIC, 40));
		txt1.setBounds(0, 10, this.getWidth(), 40);
		txt1.setForeground(Color.DARK_GRAY);
		
		JLabel txt2 = new JLabel("Signaux actuels :");
		txt2.setFont(TITLE);
		txt2.setBounds(0, 75, this.getWidth(), 30);
		
		/* Affichage des infos des signaux. */
		affInfos.setBounds(0, 115, this.getWidth(), 50);
		affInfos.setLayout(new GridLayout(2,5));
		for(int i = 0; i < 10; i++){
			JLabel l = new JLabel();
			l.setOpaque(true); // Afin de modifier la couleur
			l.setForeground(Color.BLACK);
			
			if (i==0) {
				l.setText("CH1");
				l.setBackground(Color.LIGHT_GRAY);
			} else if (i==5) {
				l.setText("CH2");
				l.setBackground(Color.LIGHT_GRAY);
			} else {
				l.setBackground(Color.WHITE);
			}
			l.setFont(COURIER);
			l.setHorizontalAlignment(JLabel.CENTER);
			l.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Affichage du quadrillage du GridLayout, plus joli.
			
			affInfos.add(l);
		}
		
		/* Parametres du Signal 1. */
		pan[0] = new SigPan (1, 200);
		pan[1] = new SigPan (2, 400);
		for (int i = 0; i < 2; i++) {
			for(JButton b : pan[i].boutons) {
				b.addActionListener(this);
			}
			onOff[i] = pan[i].on;
			onOff[i].addMouseListener(this);
		}
		
		/* Conteneur principal. */
		mainPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(affInfos);
		mainPanel.add(pan[0]);
		mainPanel.add(pan[1]);
		mainPanel.add(txt1);
		mainPanel.add(txt2);
		
		/* Affichage. */
		refreshItems();
		setContentPane(mainPanel);
		setVisible(true);
	}
	
	/** Re-calcule le texte affiché dans tous les composants le nécessitant. */
	private void refreshItems(){
		// Pour stocker les get-Xx-As-String de Signal.
		String [] s; 
		
		// Creation d'un ArrayList de JLabel a partir de la liste des composants de affInfos.
		Component[] labelTemp = affInfos.getComponents();
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		for (Component l : labelTemp)
			labels.add((JLabel)l);
		
		// Pour chaque signal...
		for(int i = 0; i < signal.length ; i++) {
			// Récupération des composants du Sig-Pan
			Component[] tempTab = pan[i].getComponents();
			
			// Actif ou non.
			if (signal[i].getActive()) {
				labels.get(5*i+1).setText("ON");
				labels.get(5*i+1).setForeground(Color.GREEN);
			} else {
				labels.get(5*i+1).setText("OFF");
				labels.get(5*i+1).setForeground(Color.DARK_GRAY);
			}
			OnOff tempOnOff = (OnOff) tempTab[2];
			tempOnOff.soWhat = signal[i].getActive();
			tempOnOff.repaint();
			
			// Types de signaux.
			labels.get(5*i+2).setText(signal[i].getForme());
			JComboBox<String> tempComboBox = (JComboBox) tempTab[1];
			tempComboBox.setSelectedItem(signal[i].getForme());
			
			// Frequence.
			s = signal[i].getFreqAsString();
			labels.get(5*i+3).setText(s[0] + " " + s[1]);
			JTextField tempTxtField = (JTextField) tempTab[7];
			tempTxtField.setText(s[0]);
			tempComboBox = (JComboBox) tempTab[8];
			tempComboBox.setSelectedItem(s[1]);
			
			// Amplitude.
			s = signal[i].getAmplAsString();
			labels.get(5*i+4).setText(s[0] + " " + s[1]);
			tempTxtField = (JTextField) tempTab[4];
			tempTxtField.setText(s[0]);
			tempComboBox = (JComboBox) tempTab[5];
			tempComboBox.setSelectedItem(s[1]);
		}
	}
	
	/** JPanel permettant la modification des informations du signal. */
	public class SigPan extends JPanel {

		private static final long serialVersionUID = 1L;
		
		JTextField[] txtField = new JTextField[2]; // Initialises dans le constructeur.
		JButton[] boutons = new JButton [3]; // Initialises dans le constructeur.
		OnOff on = new OnOff();
		JComboBox<String> signalType = new JComboBox<String>(Signal.SIGNAL_TYPES);
		JComboBox<String> ampUnit = new JComboBox<String>(Signal.AMPL_UNITES);
		JComboBox<String> freqUnit = new JComboBox<String>(Signal.FREQ_UNITES);
		
		/** Constructeur.
		 * @param sig Signal concerne
		 * @param posY Position selon y
		 */
		public SigPan(int n, int posY) {
			super();
			setBounds(0, posY, SIZE, 150);
			setLayout(new GridLayout(4,3));
			
			/* Initialisation des JTextField et JButton en attributs. */
			txtField[0] = new JTextField(); // Valeur de l'amplitude.
			txtField[1] = new JTextField(); // Valeur de la frequence.
			boutons[0] = new JButton ("Appliquer");
			boutons[1] = new JButton ("Annuler");
			boutons[2] = new JButton ("Par défaut");
			
			/* Ligne 1 : numero du Signal, Type et Actif ou non. */
			JLabel title = new JLabel("Signal " + n);
			title.setFont(TITLE);
			add(title);
			add(signalType);
			add(on);
			
			/* Ligne 2 : Amplitude. */
			JLabel ampLab = new JLabel("Amplitude");
			ampLab.setFont(DEFAULT_FONT);
			add(ampLab);
			add(txtField[0]);
			add(ampUnit);
			
			/* Ligne 3 : Frequence. */
			JLabel freqLab = new JLabel("Frequence");
			freqLab.setFont(DEFAULT_FONT);
			add(freqLab);
			add(txtField[1]);
			add(freqUnit);
			
			/* Ligne 4 : les boutons. */
			for (JButton b : boutons) {
				b.setFont(DEFAULT_FONT);
				add(b);
			}
		}
	}
	
	/** Bouton pour allumer ou eteindre un Signal. 
	 * Sa couleur indique l'etat (actif ou non) du Signal. */
	public class OnOff extends JPanel {
		/** true si signal actif, false si signal inactif. */
		public boolean soWhat = false;
		
		/** Constructeur par défaut. */
		private OnOff() {
			super();
			setSize(200, 30);
			setBackground(null);
			setOpaque(true);
		}
		
		/** Colorie le bouton d'apres la couleur correspondant a l'etat du Signal. */
		public void paint (Graphics g) {
			g.drawOval(85, 0, 30, 30);
			if (soWhat == true) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.RED);
			}
			g.fillOval(86, 1, 28, 28);
			g.setColor(Color.WHITE);
			g.drawLine(100, 7, 100, 15);
			g.drawArc(93, 8, 15, 15, 180, 180);
		}
	}

	/** Se déclenche en cas de clic sur un bouton.
	 * Pour les OnOff, c'est dans les méthodes de MouseListener.
	 * @param e L'évènement qui contient sa source.
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		String source = null;
		
		// Détermination de la source sous forme d'un String.
		for (int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				if(src.equals(btns[i][j]));{
					source = "BTN_" + i + "_" + j;
					break;
				}
			}
		}
		
		// Application de la commande.
		switch (source) {
		
		case "BTN_0_0": // Bouton APPLIQUER du signal 1
			break;
		case "BTN_1_0": // Bouton APPLIQUER du signal 2
			break;
		case "BTN_0_1": // Bouton ANNULER du signal 1
			break;
		case "BTN_1_1": // Bouton ANNULER du signal 2
			break;
		case "BTN_0_2": // Bouton PAR DEFAUT du signal 1
			break;
		case "BTN_1_2": // Bouton PAR DEFAUT du signal 2
			break;
		}
		
		refreshItems();
	}

	/** Se déclenche en cas de clic sur un OnOff.
	 * Ce sont des JPanel donc le ActionListener ne fonctionne pas sur eux.
	 * @param e L'evenement.
	 */
	public void mouseClicked(MouseEvent e) {
		Component src = e.getComponent();
		if(src.equals(onOff[0])) {
			signal[0].setActive(!signal[0].getActive());
		} else {
			signal[1].setActive(!signal[1].getActive());
		}
		refreshItems();
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
