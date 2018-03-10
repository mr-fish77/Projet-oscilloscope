import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Generateur extends JFrame {
	/** Les signaux. */
	private Signal sig1, sig2;
	
	/** Les JPanel utilises. */
	private JPanel affInfos = new JPanel(), sigPan1 = new JPanel(), sigPan2 = new JPanel(), mainPanel = new JPanel();
	
	/** Police par defaut du generateur : Calibri 15. */
	private final Font DEFAULT_FONT = new Font("Calibri", Font.PLAIN, 15);
	/** Police Courier. */
	private final Font COURIER = new Font("Courier", Font.BOLD, 15);
	/** Police de sous-titre. */
	private final Font TITLE = new Font ("Arial", Font.BOLD, 30);
	/** La fenetre est un carre de taille SIZE. */
	private final int SIZE = 600;
	
	
	public Generateur(Signal s1, Signal s2) {
		super("Generateur");
		setSize(SIZE, SIZE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.sig1 = s1;
		this.sig2 = s2;
		
		JLabel txt1 = new JLabel("Generateur de courant", JLabel.CENTER);
		txt1.setFont(new Font("Calibri", Font.BOLD + Font.ITALIC, 40));
		txt1.setBounds(0, 10, this.getWidth(), 40);
		txt1.setForeground(Color.DARK_GRAY);
		
		JLabel txt2 = new JLabel("Signaux actuels :");
		txt2.setFont(TITLE);
		txt2.setBounds(0, 75, this.getWidth(), 30);
		
		/** Affichage des infos des signaux. */
		affInfos.setBounds(0, 115, this.getWidth(), 40);
		affInfos.setLayout(new GridLayout(2,5));
		for(int i = 0; i < 10; i++){
			JLabel l = new JLabel();
			l.setOpaque(true);
			l.setBackground(Color.WHITE);
			l.setForeground(Color.BLACK);
			l.setFont(COURIER);
			l.setHorizontalAlignment(JLabel.CENTER);
			if (i==0) {
				l.setText("CH1");
				l.setBackground(Color.LIGHT_GRAY);
			} else if (i==5) {
				l.setText("CH2");
				l.setBackground(Color.LIGHT_GRAY);
			}
			
			affInfos.add(l);
		}
		
		/** Parametres du Signal 1. */
		SigPan p1 = new SigPan(sig1, 180);
		SigPan p2 = new SigPan(sig2, 400);
		
		
		/** Conteneur principal. */
		mainPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(affInfos);
		mainPanel.add(p1);
		mainPanel.add(p2);
		mainPanel.add(txt1);
		mainPanel.add(txt2);
		
		/** Affichage. */
		this.refreshItems();
		this.setContentPane(mainPanel);
		setVisible(true);
	}
	
	/** Re-calcule le texte affiche dans les composants. 
	 * JComponents affectes : les JLabel d'information vers le haut et les JButton en bas. */
	private void refreshItems(){
		/* Pour stocker les getXxAsString de Signal. */
		String [] s; 
		
		/* Creation d'un ArrayList de JLabel a partir 
		 * de la liste des composants de affInfos.*/
		Component[] lab = affInfos.getComponents();
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		for (Component l : lab)
			labels.add((JLabel)l);
		
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
	}
	
	/** JPanel permettant la modification des informations du signal. */
	private class SigPan extends JPanel {

		private static final long serialVersionUID = 1L;
		/** Signal concerné. */
		private Signal s;
		
		/** Constructeur preferenciel.
		 * @param sig Signal concerne
		 * @param posY Position selon y
		 */
		private SigPan(Signal sig, int posY) {
			super();
			setBounds(0, posY, SIZE, 150);
			s = sig;
			setLayout(new GridLayout(4,3));
			
			JLabel title = new JLabel("Signal " + s.numero);
			title.setFont(TITLE);
			OnOff btn1 = new OnOff();
			btn1.setLocation(50, 0);
			add(title);
			add(new JPanel());
			add(btn1);
			add(new JPanel());
			add(new JPanel());
			add(new JPanel());
			add(new JPanel());
			add(new JPanel());
			add(new JPanel());
			add(new JPanel());
			add(new JPanel());
			add(new JPanel());
		}
	}
	
	/** Bouton pour allumer ou eteindre un Signal. 
	 * Sa couleur indique l'etat (actif ou non) du Signal. */
	private class OnOff extends JPanel {
		/** true si signal actif, false si signal inactif. */
		public boolean soWhat = false;
		
		/** Constructeur par défaut. */
		private OnOff() {
			super();
			setSize(30, 30);
			setBackground(null);
		}
		
		/** Colorie le bouton d'apres la couleur correspondant a l'etat du Signal. */
		public void paint (Graphics g) {
			g.drawOval(0, 0, 30, 30);
			if (soWhat == true) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.RED);
			}
			g.fillOval(1, 1, 28, 28);
			g.setColor(Color.WHITE);
			g.drawLine(15, 7, 15, 15);
			g.drawArc(8, 8, 15, 15, 180, 180);
		}
	}
}
