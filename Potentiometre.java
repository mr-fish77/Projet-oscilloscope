import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JLabel;

/** Objet permettant l'affichage d'un potentiometre.
 * L'objet herite de JLabel donc on peut y appliquer les memes methodes (hormis setText et getText).
 * Le constructeur par defaut cree un potentiometre normal donc la valeur est nulle.
 */

public class Potentiometre extends JLabel implements MouseMotionListener, MouseListener{
  /** La taille du potentiometre. */
  private int taille;
  /** switch pour affichage de l'octogone normal ou rotationne.
   * Vaut true si l'octogone est normal, false si l'octogone est penche. */
  private boolean switchAffichage;
  /** Couleur d'arriere-plan. */
  private Color couleurPolygone = Color.GRAY;
  
  /** Coordonnees de l'octogone normal. */
  private int[] octoNormalX = {90, 90, 66, 33, 10, 10, 33, 66}, octoNormalY = {33, 66, 90, 90, 67, 33, 10, 10};
  /** Coordonnees de l'octogone penche.*/
  private int[] octoRotationX = {95, 81, 50, 19, 5, 19, 50, 81},  octoRotationY = {50, 81, 95, 81, 50, 19, 5, 19};
  /** Coordonnees modifiees. */
  private int[] X = new int[8], Y = new int[8];
  /** Decalage de l'octogone. */
  private double decaX, decaY;
  /** Coordonnees de la souris au precedent cran du potentiometre. */
  private double xMem, yMem;
  /** Cran actuel. */
  private int cran;
  
  /** Gestion du cran du potentiometre */
  private PotentiometreListener listener;


  /**
   * Constructeur qui genere un JPanel qui contient un potentiometre
   */
  public Potentiometre(){
	super("");
	setHorizontalAlignment(JLabel.CENTER);
	setVerticalAlignment(JLabel.CENTER);
	
    cran = 0;
    switchAffichage = true; 	  // On commence par un octogone normal.

    addMouseListener(this);       //support des clics de la souris
    addMouseMotionListener(this); //et de ses mouvements
    setSize(taille, taille);      //on lui donne sa taille
  }


  /**
   * Methode qui gere l'affichage du potentiometre a l'ecran
   * @param g : objet Graphics l'objet graphique
   */
  public void paint(Graphics g){
	  super.paint(g);       //pour peindre l'arriere plan automatiquement
      taille = Math.min(getHeight(), getWidth());
      decaX = (getWidth()-taille)/2;
      decaY = (getHeight()-taille)/2;
      
      if(switchAffichage){  //suivant le cran, on affiche l'octogone normal ou rotationne
        affichageNormal(g);
      }else{
        affichageRotation(g);
      }
  }

  
  /**
   * Methode qui affiche un octogone normal
   * @param g : objet Graphics pour peindre le JPanel
   */
  public void affichageNormal(Graphics g){
	  g.setColor(couleurPolygone);
	  
	  
	  for(int i=0; i<8; i++) {
		  X[i] = (int)(octoNormalX[i]*taille/100.0 + decaX);
		  Y[i] = (int)(octoNormalY[i]*taille/100.0 + decaY);
	  }
	  g.fillPolygon(X, Y, 8);
	  
  	}
  

  /**
   * Methode qui affiche un octogone penche
   * @param g : objet Graphics pour peindre le JPanel
   */
  	public void affichageRotation(Graphics g){
  		g.setColor(couleurPolygone);
  		
  		for(int i=0; i<8; i++) {
  		  X[i] = (int)(octoRotationX[i]*taille/100.0 + decaX);
  		  Y[i] = (int)(octoRotationY[i]*taille/100.0 + decaY);
  		}
  		
  		g.fillPolygon(X, Y, 8);
  	}
  	
  	
  public void addPotentiometreListener(PotentiometreListener listener) {
	  this.listener = listener;
  }


  /**
   * Detection du clic de souris, pour mettre le potentiometre en vert
   * @param m Informations sur l'emplacement de la souris. */
  public void mousePressed(MouseEvent m){ 
    xMem = m.getX() - decaX - taille/2;       //position initiale de la souris (relative par rapport au centre du potentiometre)
    yMem = m.getY() - decaY - taille/2;
    couleurPolygone = Color.GREEN;    //on le met en vert pour montrer qu'il est actif
    repaint();
  }
  

  /**
   * Detection du relachement du clic de souris, pour mettre le potentiometre en gris.
   * @param m Informations sur l'evenement. 
   */
  public void mouseReleased(MouseEvent m){  //on desactive le bouton
	  couleurPolygone = Color.GRAY;
	  repaint();
  }


  /**
   * Detection lorsqu'il y a mouvement de la souris, bouton appuye.
   * @param m Informations sur l'emplacement de la souris.
   */
  public void mouseDragged(MouseEvent m){
    double x = m.getX() - decaX - taille/2;   //on recupere la position relative par rapport au centre
    double y = m.getY() - decaY - taille/2;

    double a = Math.atan2( xMem*y - yMem*x, xMem*x + yMem*y );  //on en deduit l'angle par rapport au precedent cran (formule d'internet)

    if(Math.abs(a) > 1){ //si l'angle est supperieur à 22,5 degre on change de cran (c'est la moitie d'un angle d'octogone)
      if(listener != null) {
    	listener.potentiometrePerformed(this, (int)(a/(Math.abs(a))));
      }

      xMem = x;   //on change la sauvegarde de la position de la souris au niveau du cran
      yMem = y;
      switchAffichage= !switchAffichage;  //on inverse l'affichage
      repaint();
    }
  }
	
	public void setText(String s){}
	public String getText(){return "";}

  public void mouseEntered(MouseEvent m){}
  public void mouseExited(MouseEvent m){}
  public void mouseClicked(MouseEvent m){}
  public void mouseMoved(MouseEvent m){}

}
