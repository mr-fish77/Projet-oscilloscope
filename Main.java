public class Main{
	public static void main (String[] args){
		/* On crée les deux signaux. */
		Signal signal1 = new Signal(1);
		Signal signal2 = new Signal(2);
		
		new Generateur(signal1, signal2);
		new Oscilloscope(signal1, signal2);
	}
}
