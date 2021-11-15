package projet;

// TODO: Auto-generated Javadoc
/**
 * The Class Player.
 */
public class Player {
	
	/** The pseudo. */
	private String pseudo;
	
	/** The health. */
	private int health = 3;

	/**
	 * Instantiates a new player.
	 *
	 * @param pseudo the pseudo
	 */
	public Player(String pseudo) {
		this.pseudo=pseudo;
	}
	
	/**
	 * Verif health.
	 *
	 * @return true, if successful
	 */
	public boolean verif_health() {
		return(this.health > 0);
	}
	
	/**
	 * Gets the pseudo.
	 *
	 * @return the pseudo
	 */
	public String getPseudo() {
		return(pseudo);
	}
	
	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Loose health.
	 */
	public void looseHealth() {
		health -= 1;
	}
}
