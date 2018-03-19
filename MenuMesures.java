import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;


public class MenuMesures extends MenuManager implements ActionListener {
	private JButton source = new JButton ("Source : CH1");
	private JButton periode = new JButton ("Periode");
	private JButton frequence = new JButton ("Frequence");
	private JButton amplitude = new JButton ("Amplitude");
	
	public MenuMesures (Signal[] s) {
		super(s);
		menus.add(source);
		menus.add(periode);
		menus.add(frequence);
		menus.add(amplitude);
		source.addActionListener(this);
		periode.addActionListener(this);
		frequence.addActionListener(this);
		amplitude.addActionListener(this);	
	}
	
	public void actionPerformed (ActionEvent e){
		int etatSource = 1;
		if (e.getSource() == source) {
			if (etatSource == 1){
				source.setText("Source : CH2");
				etatSource = 2;
			} else {
				source.setText("Source : CH1");
				etatSource =1;
			}
		}
	}
}
