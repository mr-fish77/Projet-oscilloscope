import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

public class Potentiometre extends JLabel implements MouseMotionListener, MouseListener{
  private int taille;                   //la taille du Potentiometre
  private boolean switchAffichage;      //switch pour affichage de l'octogone normal ou rotationne
  private Color couleurPolygone = Color.GRAY;
  
  private int[] octoNormalX = {90, 90, 66, 33, 10, 10, 33, 66};	//coordonnees X et Y octogone normal
  private int[] octoNormalY = {33, 66, 90, 90, 67, 33, 10, 10};
  private int[] octoRotationX = {95, 81, 50, 19, 5, 19, 50, 81};//coordonnees X et Y octogone penche
  private int[] octoRotationY = {50, 81, 95, 81, 50, 19, 5, 19};
  private int[] X = new int[8], Y = new int[8];	//coordonnees modifiees
  private double decaX, decaY;			//decalage de l'octogone
  private double xMem, yMem;            //coordonnees de la souris au precedent cran du potentiometre
  private int cran;                     //cran actuel


  /**
   * Constructeur qui genere un JPanel qui contient un potentiometre
   */
  public Potentiometre(){
	super("0");
	setHorizontalAlignment(JLabel.CENTER);
	setVerticalAlignment(JLabel.CENTER);
	setForeground(Color.BLACK);
	
    cran = 0;
    switchAffichage= true;

    addMouseListener(this);       //support des clics de la souris
    addMouseMotionListener(this); //et de ses mouvements
    setOpaque(true);              //pour attribuer une couleur au JLabel (par defaut transparent)
    setSize(taille, taille);      //on lui donne sa taille
  }


  /**
   * Methode qui gere l'affichage du potentiometre a l'ecran
   * @param Graphics g : l'objet graphique
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
   * @param Graphics g : pour peindre le JPanel
   */
  public void affichageNormal(Graphics g){    //affichage normal de l'octogone
	  g.setColor(couleurPolygone);
	  
	  
	  for(int i=0; i<8; i++) {
		  X[i] = (int)(octoNormalX[i]*taille/100.0 + decaX);
		  Y[i] = (int)(octoNormalY[i]*taille/100.0 + decaY);
	  }
	  g.drawPolygon(X, Y, 8);
	  
  	}
  

  /**
   * Methode qui affiche un octogone penche
   * @param Graphics g : pour peindre le JPanel
   */
  	public void affichageRotation(Graphics g){  //affichage rotationne de l'octogone
  		g.setColor(couleurPolygone);
  		
  		for(int i=0; i<8; i++) {
  		  X[i] = (int)(octoRotationX[i]*taille/100.0 + decaX);
  		  Y[i] = (int)(octoRotationY[i]*taille/100.0 + decaY);
  		}
  		
  		g.drawPolygon(X, Y, 8);
  	}


  /**
   * Detection du clic de souris, pour mettre le potentiometre en vert
   */
  public void mousePressed(MouseEvent m){ //detection du clic dans le potentiometre pour le mettre en vert
    xMem = m.getX() - taille/2;       //position initiale de la souris (relative par rapport au centre du potentiometre)
    yMem = m.getY() - taille/2;
    couleurPolygone = Color.GREEN;    //on le met en vert pour montrer qu'il est actif
    repaint();
  }
  

  /**
   * Detection du relachement du clic de souris, pour mettre le potentiometre en gris
   */
  public void mouseReleased(MouseEvent m){  //on desactive le bouton
	  couleurPolygone = Color.GRAY;
	  repaint();
  }


  /**
   * Detectection lorsqu'il y a mouvement de la souris, bouton appuye
   */
  public void mouseDragged(MouseEvent m){   //detection de la position quand le clic de souris est enclenche
    double x = m.getX() - decaX;   //on recupere la position relative par rapport au centre
    double y = m.getY() - decaY;

    double a = Math.atan2( xMem*y - yMem*x, xMem*x + yMem*y );  //on en deduit l'angle par rapport au precedent cran (formule d'internet)

    if(Math.abs(a) > 0.3926990817){ //si l'angle est supperieur à 22,5 degre on change de cran (c'est la moitie d'un angle d'octogone)

      cran += (int)(a/Math.abs(a));   //pour savoir dans quel sens on a tourne la molette
      this.setText(String.valueOf(cran));

      xMem = x;   //on change la sauvegarde de la position de la souris au niveau du cran
      yMem = y;
      switchAffichage= !switchAffichage;  //on inverse l'affichage
      repaint();
    }
  }

  public void mouseEntered(MouseEvent m){}
  public void mouseExited(MouseEvent m){}
  public void mouseClicked(MouseEvent m){}
  public void mouseMoved(MouseEvent m){}

}
