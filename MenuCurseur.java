import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCurseur extends MenuManager{
	
	
	public MenuCurseur(Signal[] s) {
		super(s);
		
		setTexts("CURSEURS", "Type : aucun", "Source", "Delta", "Curseur 1", "Curseur 2");
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bouton1) {
			
		}else if(e.getSource()==bouton2) {
			
		}else if(e.getSource()==bouton3) {
			
		}else if(e.getSource()==bouton4) {
			
		}else if(e.getSource()==bouton5) {
			
		}
		
	}
}
