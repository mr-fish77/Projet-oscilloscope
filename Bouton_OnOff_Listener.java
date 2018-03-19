/** Interface d'ecoute liee a un Bouton_OnOff. */

public interface Bouton_OnOff_Listener {
	/** Action declenchee lors d'un clic sur le Bouton_OnOff. 
	 * @param evt Objet d'evenement lie au clic. */
	public void btnClicked(Bouton_OnOff_Event evt);
}
