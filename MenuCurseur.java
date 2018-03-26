import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCurseur extends AbstractMenu implements ActionListener, PotentiometreListener{
	protected Oscilloscope oscillo;		//l'objet ecran pour l'affichage
	
	protected Curseur curseurCourant = null;
	protected Curseur curseurHor;
	protected Curseur curseurCH1;
	protected Curseur curseurCH2;
	
	protected Potentiometre pot1;
	protected Potentiometre pot2;
	
	protected int type = 0; 
	protected int source = 0;
	
	
	public MenuCurseur(Signal[] s, Oscilloscope oscillo) {
		super(s, "CURSEURS");
		this.oscillo = oscillo;
		
		curseurCH1 = new CurseurVertical(s, 0);
		curseurCH2 = new CurseurVertical(s, 1);
		
		//On definit les textes des boutons
		bouton1.setText("Type : aucun");
		bouton2.setText("Source : CH1");
		bouton3.setText("Delta");
		bouton4.setText("Curseur 1");
		bouton5.setText("Curseur 2");
	}
	
	
	public void miseEnRoute() {
		this.oscillo.ecran.grille.affichageCurseur(this);
		
		pot1 = this.oscillo.ch1.potPos;
		pot2 = this.oscillo.ch2.potPos;
		pot1.addPotentiometreListener(this);
		pot2.addPotentiometreListener(this);
	}
	
	
	/**
	 * Methode qui desactive le menu des curseurs
	 */
	public void desactiverMenu() {
		oscillo.desactiveCurseur();
	}	
	
	/**
	 * Methode qui gere le potentiometre
	 * @param Potentiometre p : le potentiometre
	 * @param int evolutionCran : l'evolution du cran
	 */
	public void potentiometrePerformed(Potentiometre p, int evolutionCran) {
		if(curseurCourant != null) {
			if(p == pot1) {
				curseurCourant.mAJCran(evolutionCran, 0);
			}else if(p == pot2) {
				curseurCourant.mAJCran(0, evolutionCran);
			}
			oscillo.ecran.grille.repaint();
			boutons.get(2).setText("Delta : " + curseurCourant.difference);
			boutons.get(3).setText("Curseur 1 : " + curseurCourant.vraiVal1);
			boutons.get(4).setText("Curseur 2 : " + curseurCourant.vraiVal2);
		}
	}
	
	
	public void actionBouton1(ActionEvent e) {
		switch(type) {
			case 0:
				type = 1;
				boutons.get(0).setText("Type : Volts");
				
				if(source == 0) {
					curseurCourant = curseurCH1;
				}else {
					curseurCourant = curseurCH2;
				}
				
				break;
			case 1:
				type = 2;
				boutons.get(0).setText("Type : Temps");
				curseurCourant = curseurHor;
				break;
			default:
				type = 0;
				boutons.get(0).setText("Type : aucun");
		}
		oscillo.ecran.grille.repaint();
	}
	
	
	public void actionBouton2(ActionEvent e) {
		switch(source) {
			case 0:
				source = 1;
				boutons.get(1).setText("Source : CH2");
				if(type == 1) {
					curseurCourant = curseurCH2;
				}
				break;
			default:
				source = 0;
				boutons.get(1).setText("Source : CH1");
				if(type == 1) {
					curseurCourant = curseurCH1;
				}
		}
		oscillo.ecran.grille.repaint();
	}
}
