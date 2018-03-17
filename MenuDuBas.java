import javax.swing.JPanel;

public class MenuDuBas extends JPanel {
	
	/** Les signaux. */
	private Signal[] signaux;
	
	public MenuDuBas(Signal[] s) {
		signaux = s;
		setBackground(java.awt.Color.GREEN);
	}
	
}
