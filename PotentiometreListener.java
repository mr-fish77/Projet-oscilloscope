/** Interface d'ecoute liee a un Potentiometre. */
public interface PotentiometreListener {
	
	/** Action declenchee lorsque la valeur du Potentiometre est modifiee.
	 * @param potentiometre La source de l'evenement.
	 * @param evolutionEcran Nouvelle valeur du potentiometre. */
	public void potentiometrePerformed(Potentiometre potentiometre, int evolutionCran);
}
