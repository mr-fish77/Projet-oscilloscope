import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.*;

import javax.swing.*;

public class Generateur2 extends JFrame /*implements ActionListener */{
	
	public Signal signal1;
	public Signal signal2;
	
	public JPanel infoSignal = new JPanel();
	public JPanel menu1 = new JPanel();
	public JPanel menu2 = new JPanel();
	
	public JLabel canal1 = new JLabel("CH1"), volt1, freq1, active1 = new JLabel("OFF");
	public JLabel canal2 = new JLabel("CH2"), volt2, freq2, active2 = new JLabel("OFF");
	
	public JButton onOff1 = new JButton("OFF"), onOff2 = new JButton("OFF");
	
	public static void main (String[] args){
		new Generateur2();
	  }
	
	public Generateur2(){
		super("Generateur2");
		signal1 = new Signal();
		signal2 = new Signal();
		
		setSize(600, 600);
		setMinimumSize(new Dimension(600, 150));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		//setResizable(false);
		
		infoSignal.setBackground(Color.GREEN);
		infoSignal.setPreferredSize(new Dimension(getWidth() - getInsets().left*2, 50));
		this.add(infoSignal, BorderLayout.NORTH);
		
		infoSignal.setLayout(new GridLayout(0, 8));
		freq1 = new JLabel(signal1.getFreqAsString()[0]);
		freq2 = new JLabel(signal2.getFreqAsString()[0]);
		volt1 = new JLabel(signal1.getAmplAsString()[0]);
		volt2 = new JLabel(signal2.getAmplAsString()[0]);
		
		canal1.setHorizontalAlignment(JLabel.CENTER);
		canal2.setHorizontalAlignment(JLabel.CENTER);
		active1.setHorizontalAlignment(JLabel.CENTER);
		active2.setHorizontalAlignment(JLabel.CENTER);
		freq1.setHorizontalAlignment(JLabel.CENTER);
		freq2.setHorizontalAlignment(JLabel.CENTER);
		volt1.setHorizontalAlignment(JLabel.CENTER);
		volt2.setHorizontalAlignment(JLabel.CENTER);
		
		infoSignal.add(canal1);
		infoSignal.add(active1);
		infoSignal.add(volt1);
		infoSignal.add(freq1);
		infoSignal.add(canal2);
		infoSignal.add(active2);
		infoSignal.add(volt2);
		infoSignal.add(freq2);
		
		menu1.setBackground(Color.RED);
		menu1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		menu1.setLayout(new GridLayout(3, 0));
		menu1.add(onOff1);
		
		menu2.setBackground(Color.YELLOW);
		menu2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		menu2.setLayout(new GridLayout(3, 0));
		menu2.add(onOff2);
		
		this.add(menu1, BorderLayout.WEST);
		this.add(menu2, BorderLayout.EAST);
		
		setVisible(true);
		
		
		
	}
}
	
	
	
	
