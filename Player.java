package projet;

public class Player {
	private String pseudo;
	private int health = 3;

	public Player(String pseudo) {
		this.pseudo=pseudo;
	}
	
	public boolean verif_health() {
		return(this.health > 0);
	}
	
	public String getPseudo() {
		return(pseudo);
	}
	
	public int getHealth() {
		return health;
	}
	
	public void looseHealth() {
		health -= 1;
	}
	
	/*public static void main(String[] args) {
		Player p= new Player("jean");
		p.health=2;
		p.looseHealth();
		System.out.println(p.verif_health());
		System.out.println(p.getHealth());
	}*/
}
