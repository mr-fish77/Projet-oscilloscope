import javax.swing.*;
import java.awt.*;

public class Oscilloscope extends JFrame{
  private Channel ch1;      //channel du premier signal
  private Channel ch2;      //channel du deuxieme signal

  public Oscilloscope(Signal signal1, Signal signal2){
    super("Oscilloscope");      //constructeur par defaut de la classe JFrame
    ch1 = new Channel(signal1);
    ch2 = new Channel(signal2);

  }


}
