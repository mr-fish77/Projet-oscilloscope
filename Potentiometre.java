import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Potentiometre extends JLabel implements MouseMotionListener, MouseListener{
  private int t; //la taille du Potentiometre
  private boolean swi;
  private double xMem, yMem;
  private int cran;
  private int[][] octoNormal = {{90, 33}, {90, 66}, {66, 90}, {33, 90}, {10, 67}, {10, 33}, {33, 10}, {66, 10}};
  private int[][] octoRotation = { {95, 50},  {81, 81},  {50, 95},  {19, 81},  {5, 50},  {19, 19},  {50, 5},  {81, 19}};

  public static void main(String[] args){
    JFrame fenetre = new JFrame();
    Potentiometre pot = new Potentiometre(50);

    fenetre.add(pot);
    fenetre.setSize(200, 200);
    fenetre.setLayout(null);
    fenetre.setVisible(true);
  }


  public Potentiometre(int t){
    super();
    this.t = t;
    cran = 0;
    swi = true;

    for(int i=0; i<octoNormal.length; i++){
      octoNormal[i][0] = (int) (octoNormal[i][0] * t/100.0);
      octoNormal[i][1] = (int) (octoNormal[i][1] * t/100.0);
      octoRotation[i][0] = (int) (octoRotation[i][0] * t/100.0);
      octoRotation[i][1] = (int) (octoRotation[i][1] * t/100.0);
    }

    addMouseListener(this);
    addMouseMotionListener(this);
    setOpaque(true);
    setSize(t, t);
    setBackground(Color.white);
  }

  public void paint(Graphics g){
      super.paint(g);

      if(swi){
        affichageNormal(g);
      }else{
        affichageRotation(g);
      }

  }

  public void affichageNormal(Graphics g){
    g.setColor(Color.black);

    g.drawLine(octoNormal[octoNormal.length-1][0], octoNormal[octoNormal.length-1][1], octoNormal[0][0], octoNormal[0][1]);
    for(int i=0; i<octoNormal.length-1; i++){
      g.drawLine(octoNormal[i][0], octoNormal[i][1], octoNormal[i + 1][0], octoNormal[i + 1][1]);
    }
  }

  public void affichageRotation(Graphics g){
    g.setColor(Color.black);

    g.drawLine(octoRotation[octoRotation.length-1][0], octoRotation[octoRotation.length-1][1], octoRotation[0][0], octoRotation[0][1]);
    for(int i=0; i<octoRotation.length-1; i++){
      g.drawLine(octoRotation[i][0], octoRotation[i][1], octoRotation[i + 1][0], octoRotation[i + 1][1]);
    }
  }

  public void mouseEntered(MouseEvent m){}
  public void mouseExited(MouseEvent m){}
  public void mouseClicked(MouseEvent m){}
  public void mouseReleased(MouseEvent m){
    setBackground(Color.white);
  }
  public void mousePressed(MouseEvent m){
    xMem = m.getX() - t/2;
    yMem = m.getY() - t/2;
    setBackground(Color.green);
  }

  public void mouseMoved(MouseEvent m){}
  public void mouseDragged(MouseEvent m){
    double x = m.getX() - t/2;
    double y = m.getY() - t/2;

    double a = Math.atan2( xMem*y - yMem*x, xMem*x + yMem*y );

    if(Math.abs(a) > 0.3926990817){
      cran += (int)(a/Math.abs(a));
      System.out.println(cran);
      xMem = x;
      yMem = y;

      swi = !swi;
      repaint();
    }
  }

}
