package projet;

public class Player {
	private String pseudo;
	private int health = 3;

	public Player(String pseudo) {
		this.pseudo=pseudo;
	}
	
	public boolean verif_health() {
		if (this.health > 0) {
			return true;
		}
		return false ;
	}
	
	
	public static void main(String[] args) {
		Player p= new Player("jean");
		p.health=0;
		System.out.println(p.verif_health());
	}
}
