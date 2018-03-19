import java.awt.Component;

/** Evenement produit lors d'un clic sur un Bouton_OnOff. */
public class Bouton_OnOff_Event {
	/** Source du clic. C'est toujours un Bouton_OnOff 
	 * mais laisser sous forme de Component laisse plus 
	 * de libertes avec les methodes eventuelles. */
	private Component source;
	
	/** Chaine de caracteres qui caracterise le clic,
	 * normalement renseignee par l'utilisateur. */
	private String actionCommand;
	
	/** Valeur du Bouton_OnOff lors d'un clic. */
	private boolean actualValue;
	
	/** Date a laquelle s'est produit l'evenement. */
	private long time;
	
	/** Cree un Bouton_OnOff_Event avec les parametres specifies. 
	 * @param src Source du clic.
	 * @param actionComm L'actionCommand. */
	public Bouton_OnOff_Event(Component src, String actionComm) {
		source = src;
		actionCommand = actionComm;
		
		Bouton_OnOff btn = (Bouton_OnOff)src;
		actualValue = btn.valeur();
		
		time = System.currentTimeMillis();
	}
	
	/** @return Le Bouton_OnOff source sous forme de Component. */
	public Component getSource() {
		return source;
	}
	
	/** @return Le Bouton_OnOff source sous forme de Bouton_OnOff! */
	public Bouton_OnOff getSourceAsBtn() {
		return (Bouton_OnOff)source;
	}
	
	/** @return L'ActionCommand. */
	public String getActionCommand() {
		return actionCommand;
	}
	
	/** @return Valeur du Bouton_OnOff a l'issue du clic. */
	public boolean getActualValue() {
		return actualValue;
	}
	
	/** @return L'heure de l'evenement. */
	public long getTime() {
		return time;
	}
}
