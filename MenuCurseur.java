import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

<<<<<<< HEAD
public class MenuCurseur extends AbstractMenu implements PotentiometreListener{
	/** Oscillo pour acceder aux variable utiles */
	protected Oscilloscope oscillo;		//l'objet ecran pour l'affichage
	
=======
public class MenuCurseur extends AbstractMenu implements ActionListener, PotentiometreListener{
>>>>>>> cf5880c0a97e2302f0ae4bff8fbcd3244aa4c29a
	/** Les differents curseurs */
	protected Curseur curseurCourant = null;	//curseur affiche
	protected Curseur[] curseurs;				//tableau des curseurs
	
	/** String associes au curseurs */
	protected String[] sType = {"Type : aucun", "Type : Temps", "Type : Volts"};
	protected String[] sSource = {"Source : CH1", "Source : CH2"};

	/** Potentiometres de reglage des curseurs */
	protected Potentiometre pot1;
	protected Potentiometre pot2;
	
	/** Informations relatives a l'affichage des curseurs */
	protected int type = 0; 	//0 : rien, 1:temps, 2:volts
	protected int source = 0;	//0 ou 1 suivant le signal
	
	
	public MenuCurseur(Signal[] s) {
		super(s);
		
		curseurs = new Curseur[4];
		curseurs[1] = new CurseurHorizontal(s);
		curseurs[2] = new CurseurVertical(s, 0);
		curseurs[3]= new CurseurVertical(s, 1);
		
		
		//On definit les textes des boutons
		bouton1.setText("Type : aucun");
		bouton2.setText("Source : CH1");
		bouton3.setText("Delta");
		bouton4.setText("Curseur 1");
		bouton5.setText("Curseur 2");

	}
	
	/**
	 * Methode qui permet de peindre sur l'ecran
	 * @param Graphics g : l'objet graphique bien pratique
	 * @param int hauteur : la hauteur de la grille
	 * @param int largeur : la largeur de la grille
	 */
	public void paintGrille(Graphics g, int hauteur, int largeur) {
		if(curseurCourant != null) {
			curseurCourant.paint(g, hauteur, largeur);
			curseurCourant.mAJEchelle();
			
			setText("Delta : " + curseurCourant.getDifference(), 3);
			setText("Curseur 1 : " + curseurCourant.getVraiVal1(), 4);
			setText("Curseur 2 : " + curseurCourant.getVraiVal2(), 5);
		}
	}
	
	
	/**
	 * Methode appelee lorsque tous les objets necessaires sont instancies correctement
	 */
	public void miseEnRoute() {
		//on recupere les potentiometres
		pot1 = this.oscillo.ch1.potPos;
		pot2 = this.oscillo.ch2.potPos;
		pot1.setPotentiometreListener(this);
		pot2.setPotentiometreListener(this);
		
		//on leur rajoute la couleur
		pot1.setOpaque(true);
		pot2.setOpaque(true);
		pot1.repaint();
		pot2.repaint();
	}
	
	
	/**
	 * Methode qui desactive le menu des curseurs
	 */
	public void desactiverMenu() {
		//on enleve la couleur
		pot1.setOpaque(false);
		pot2.setOpaque(false);
		pot1.repaint();
		pot2.repaint();
		
		//methode pour reatribuer les bons listener
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
			setText("Delta : " + curseurCourant.getDifference(), 3);
			setText("Curseur 1 : " + curseurCourant.getVraiVal1(), 4);
			setText("Curseur 2 : " + curseurCourant.getVraiVal2(), 5);
		}
	}
	
	
	/** Methode qui est appelee lorsque le 1er bouton est clique
	 */
	public void actionBouton1() {
		type = (type + 1) % 3;	//0-1-2-0...
		curseurCourant = curseurs[type + ((type == 2) ? source : 0)];	//on recupere le bon curseur (on fait la difference de signaux ssi on doit afficher des curseurs verticaux)
		
		setText(sType[type], 1);		//on met le bon texte
		oscillo.ecran.grille.repaint();	//on repaint
	}
	
	
	/** Methode qui est appelee lorsque le 2e bouton est clique
	 */
	public void actionBouton2() {
		source = (source + 1) % 2;//0-1-0...
		curseurCourant = curseurs[type + ((type == 2) ? source : 0)];	//on recupere le bon curseur (on fait la difference de signaux ssi on doit afficher des curseurs verticaux)
		
		setText(sSource[source], 2);	//on met le bon texte
		oscillo.ecran.grille.repaint();	//on repaint
	}
}
