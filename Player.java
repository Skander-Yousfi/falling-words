package projet;

/**
 * La classe Player represente le joueur
 */
public class Player implements java.io.Serializable{
	
	/** Constante nécessaire à la sérialisation */
	private static final long serialVersionUID = 1L;

	/** Pseudonyme du joueur */
	private String pseudo;
	
	/** Nombre de vies du joueur*/
	private int health = 3;

	/**
	 * Instantiation d'un nouveau joueur
	 *
	 * @param pseudo
	 */
	public Player(String pseudo) {
		this.pseudo=pseudo;
	}
	
	/**
	 * Verification du nombre de vie du joueur
	 *
	 * @return true si la condition est verifiee
	 */
	public boolean verif_health() {
		return(this.health > 0);
	}
	
	/**
	 * Getter du pseudo
	 *
	 * @return le pseudo
	 */
	public String getPseudo() {
		return(pseudo);
	}
	
	/**
	 * Getter du nombre de vie du joueur
	 *
	 * @return le nombre de vie du joueur health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Methode qui retire une vie au joueur
	 */
	public void looseHealth() {
		health -= 1;
	}
}

