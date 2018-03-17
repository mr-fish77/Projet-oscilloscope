import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Generateur extends JFrame implements ActionListener, MouseListener {
	/** Les signaux. */
	private Signal signal[] = new Signal[2];
	/** L'ecran de l'oscilloscope */
	private Ecran ecran;
	
	/** Les JPanel utilises. */
	private JPanel affInfos = new JPanel(), mainPanel = new JPanel();
	/** Les SigPan utilises (voir doc plus bas). */
	private SigPan[] pan = new SigPan[2];
	/** Vaut true lors du premier affichage. */
	private boolean init_done = false;
	
	/** Les OnOff a surveiller (comme des JButton). */
	private Bouton_OnOff[] onOff = new Bouton_OnOff[2];
	
	/** Police par defaut du generateur : Calibri 15. */
	private final Font DEFAULT_FONT = new Font("Calibri", Font.PLAIN, 18);
	/** Police Courier. */
	private final Font COURIER = new Font("Courier", Font.BOLD, 20);
	/** Police de titre de section : Arial gras 30. */
	private final Font TITLE = new Font ("Arial", Font.BOLD, 30);
	/** La fenetre est un carre de taille SIZE. */
	private final int SIZE = 600;
	
	/** Cree un generateur de taille carree 600*600. 
	 * @param signaux Les signaux.
	 */
	public Generateur(Signal[] signaux) {
		super("Generateur");
		setSize(SIZE, SIZE); // Taille carree.
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.signal = signaux;
		
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
			for(int j = 0; j < 3; j++) {
				JButton o = pan[i].boutons[j];
				o.addActionListener(this);
				o.setActionCommand(o.getActionCommand() + " " + i);
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
		init_done = true;
		setVisible(true);
	}
	
	/** Re-calcule les items necessaires dans l'oscillo (affichage ecran) */
	public void refreshOscillo() {
		if(ecran != null) {
			ecran.repaint();
		}
	}
	
	
	/** Re-calcule le texte affiche dans tous les composants le necessitant. */
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
			// Recuperation des composants du Sig-Pan
			Component[] tempTab = pan[i].getComponents();
			
			/* Actif ou non.
			 * Appele uniquement une fois, ensuite c'est dans MouseClicked. */
			if(!init_done) {
				labels.get(5*i+1).setText("OFF");
				labels.get(5*i+1).setForeground(Color.DARK_GRAY);
			}
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
	
	/** Permet de recevoir l'ecran de l'oscilloscope */
	public void setEcran(Ecran ecran) {
		this.ecran = ecran;
	}
	
	/** JPanel permettant la modification des informations du signal. 
	 * Mettre ca dans une sous-classe permet d'alleger le contenu tout en gardant l'acces aux variables. */
	public class SigPan extends JPanel {

		private static final long serialVersionUID = 1L;
		
		JTextField[] txtField = new JTextField[2]; // Initialises dans le constructeur.
		JButton[] boutons = new JButton [3]; // Initialises dans le constructeur.
		Bouton_OnOff on = new Bouton_OnOff(Color.GREEN, Color.RED);
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
			setBackground(Color.WHITE);
			
			/* Initialisation des JTextField et JButton en attributs. */
			txtField[0] = new JTextField(); // Valeur de l'amplitude.
			txtField[1] = new JTextField(); // Valeur de la frequence.
			boutons[0] = new JButton ("Appliquer");
			boutons[1] = new JButton ("Annuler");
			boutons[2] = new JButton ("Par defaut");
			
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
	
	/** Se declenche en cas de clic sur un bouton.
	 * Selon le bouton, modifie la valeur des JTextField et ou du signal.
	 * Pour les OnOff, c'est dans les methodes de MouseListener.
	 * @param e L'evenement qui contient sa source.
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand(); // Bouton source = commande sous forme de String.
		int n = Character.getNumericValue(command.charAt(command.length()-1)); // Numero du Signal.
		
		/* Application de la commande. 
		* Pour ANNULER, il suffit de faire un refreshItems (donc rien a faire ici). */
		switch (command.charAt(1)) {
		case 'p':	// Bouton APPLIQUER
			
			// On recupere les valeurs saisies par l'utilisateur.
			double[] valeursSaisies = new double[2];
			try{
				for(int i = 0; i < 2; i++) {
					String tmp = pan[n].txtField[i].getText();
					valeursSaisies[i] = Double.valueOf(tmp);
				}
			} catch (Exception w) {
				JOptionPane.showMessageDialog(this,"Une erreur de saisie est detectee.");
				break;
			}
			String [] unitesChoisies = {(String)pan[n].freqUnit.getSelectedItem(), (String)pan[n].ampUnit.getSelectedItem(), (String)pan[n].signalType.getSelectedItem()};
			
			// Forme (donc re-creation d'une instance).
			if(this.signal[n].getForme() != unitesChoisies[2]) {
				switch (unitesChoisies[2]) {
				case "REC":
					this.signal[n] = new Rectangle(signal[n]);
					
					break;
				case "TRI":
					this.signal[n] = new Triangle(signal[n]);
					break;
				case "SIN":
					this.signal[n] = new Sinus(signal[n]);
					break;
				}
			}
			
			// Amplitude
			switch (unitesChoisies[1].charAt(0)) {
			case 'm':
				signal[n].setAmplitude(valeursSaisies[0] * .001);
				break;
			default:
				signal[n].setAmplitude(valeursSaisies[0]);
			}
			
			// Frequence
			switch (unitesChoisies[0].charAt(0)) {
			case 'k':
				signal[n].setFreq(valeursSaisies[1] * Math.pow(10, 3));
				break;
			case 'M':
				signal[n].setFreq(valeursSaisies[1] * Math.pow(10, 6));
				break;
			case 'G':
				signal[n].setFreq(valeursSaisies[1] * Math.pow(10, 9));
				break;
			default:
				signal[n].setFreq(valeursSaisies[1]);	
			}
			
			break;
		case 'a': // Bouton PAR DEFAUT
			this.signal[n] = new Sinus(n + 1); // Reinitialise un Signal en recreant une instance.
			onOff[n].setValeur(false); // Dit au Bouton_OnOff de s'eteindre.
			
			// On actualise l'affichage du JLabel en haut associe.
			JLabel txt = (JLabel)(affInfos.getComponent(5*n+1));
			txt.setText("OFF");
			txt.setForeground(Color.DARK_GRAY);
			break;
		}
		
		refreshItems();
		refreshOscillo();
	}
	
	/** Se declenche en cas de clic sur un Button_OnOff.
	 * Ce sont des JPanel donc le ActionListener ne fonctionne pas sur eux.
	 * Cette fonction active ou desactive simplement un Signal,
	 * donc actualise juste le necessaire.
	 * @param e L'evenement.
	 */
	public void mouseClicked(MouseEvent e) {
		Component src = e.getComponent(); // Objet source.
		int n = (src.equals(onOff[0])) ? 0 : 1; // Index du signal.
		
		boolean b = !signal[n].getActive(); // Valeur a appliquer
		signal[n].setActive(b); // On (des)active le signal.
		
		onOff[n].changeValeur(); // On informe le Bouton_OnOff associe.
		
		JLabel txt = (JLabel)(affInfos.getComponent(5*n+1));
		if (b) {
			txt.setText("ON");
			txt.setForeground(Color.GREEN);
		} else {
			txt.setText("OFF");
			txt.setForeground(Color.DARK_GRAY);
		}
		refreshOscillo();
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
