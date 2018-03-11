public class Main{
	public static void main (String[] args){
		/* On crée les deux signaux. */
		Signal[] signaux = new Signal[2];
		signaux[0] = new Sinus(1);
		signaux[1]= new Sinus(2);
		
		new Generateur (signaux);
		new Oscilloscope (signaux);
	}
}
