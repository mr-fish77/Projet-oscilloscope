public class Main{
	public static void main (String[] args){
		/* On cree les deux signaux. */
		Signal[] signaux = new Signal[2];
		signaux[0] = new Triangle(1);
		signaux[1]= new Triangle(2);
		
		new Generateur (signaux);
		new Oscilloscope (signaux);
        
	}
}
