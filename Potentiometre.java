import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class Potentiometre extends JLabel implements MouseMotionListener, MouseListener{
  private int taille;                   //la taille du Potentiometre
  private boolean switchAffichage;      //switch pour affichage de l'octogone normal ou rotationne
  private int[][] octoNormal = {{90, 33}, {90, 66}, {66, 90}, {33, 90}, {10, 67}, {10, 33}, {33, 10}, {66, 10}};          //coordonnees pour l'octogone normal
  private int[][] octoRotation = {{95, 50},  {81, 81},  {50, 95},  {19, 81},  {5, 50},  {19, 19},  {50, 5},  {81, 19}};   //coordonnees pour l'octogone rotationne
  private double xMem, yMem;            //coordonnees de la souris au precedent cran du potentiometre
  private int cran;                     //cran actuel


  public Potentiometre(int taille){
    super();
    this.taille = taille;
    cran = 0;
    switchAffichage= true;

    //on ajuste la taille du potentiometre en fonction de la taille du JLabel
    for(int i=0; i<octoNormal.length; i++){
      octoNormal[i][0] = (int) (octoNormal[i][0] * taille/100.0);
      octoNormal[i][1] = (int) (octoNormal[i][1] * taille/100.0);
      octoRotation[i][0] = (int) (octoRotation[i][0] * taille/100.0);
      octoRotation[i][1] = (int) (octoRotation[i][1] * taille/100.0);
    }

    addMouseListener(this);       //support des clics de la souris
    addMouseMotionListener(this); //et de ses mouvements
    setOpaque(true);              //pour attribuer une couleur au JLabel (par defaut transparent)
    setBackground(Color.white);
    setSize(taille, taille);      //on lui donne sa taille
  }


  public void paint(Graphics g){
      super.paint(g);       //pour peindre l'arriere plan automatiquement

      if(switchAffichage){  //suivant le cran, on affiche l'octogone normal ou rotationne
        affichageNormal(g);
      }else{
        affichageRotation(g);
      }

  }


  public void affichageNormal(Graphics g){    //affichage normal de l'octogone
    g.setColor(Color.black);

    g.drawLine(octoNormal[octoNormal.length-1][0], octoNormal[octoNormal.length-1][1], octoNormal[0][0], octoNormal[0][1]);
    for(int i=0; i<octoNormal.length-1; i++){
      g.drawLine(octoNormal[i][0], octoNormal[i][1], octoNormal[i + 1][0], octoNormal[i + 1][1]);
    }
  }

  public void affichageRotation(Graphics g){  //affichage rotationne de l'octogone
    g.setColor(Color.black);

    g.drawLine(octoRotation[octoRotation.length-1][0], octoRotation[octoRotation.length-1][1], octoRotation[0][0], octoRotation[0][1]);
    for(int i=0; i<octoRotation.length-1; i++){
      g.drawLine(octoRotation[i][0], octoRotation[i][1], octoRotation[i + 1][0], octoRotation[i + 1][1]);
    }
  }


  public void mousePressed(MouseEvent m){ //detection du clic dans le potentiometre pour le mettre en vert
    xMem = m.getX() - taille/2;       //position initiale de la souris (relative par rapport au centre du potentiometre)
    yMem = m.getY() - taille/2;
    setBackground(Color.green);       //on le met en vert pour montrer qu'il est actif
  }

  public void mouseReleased(MouseEvent m){  //on desactive le bouton
    setBackground(Color.white);
  }


  public void mouseDragged(MouseEvent m){   //detection de la position quand le clic de souris est enclenche
    double x = m.getX() - taille/2;   //on recupere la position relative par rapport au centre
    double y = m.getY() - taille/2;

    double a = Math.atan2( xMem*y - yMem*x, xMem*x + yMem*y );  //on en deduit l'angle par rapport au precedent cran (formule d'internet)

    if(Math.abs(a) > 0.3926990817){ //si l'angle est supperieur à 22,5 degre on change de cran (c'est la moitie d'un angle d'octogone)

      cran += (int)(a/Math.abs(a));   //pour savoir dans quel sens on a tourne la molette

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
