package projet;

import java.util.Random;

public class Words {
	private String word;
	private int points;
	private Coord coord;
	
	public Words (String mot) {
		this.word = mot;
		if (mot.length()>6) {
			this.points = 2;
		}
		else {
			this.points = 1;
		}
		Random random = new Random();
		this.coord = new Coord(random.nextInt(5), 0);
	}
	// Méthode pour créer un mot ou l'on veut sur l'écran : utile pour vérifications mais pas pour le projet en soi.
	public Words (String mot, int x, int y) {
		this.word = mot;
		if (mot.length()>6) {
			this.points = 2;
		}
		else {
			this.points = 1;
		}
		this.coord = new Coord(x,y);
	}
	public Words () {
		this("");
	}
	public String getWord() {
		return(word);
	}
	public int getPoints() {
		return(points);
	}
	public Coord getCoord() {
		return(coord);
	}
	public void downWord () {
		coord.down();
	}
	
	//inutile
	public boolean equalsWords(Words w) {
		return ((this.word.equals(w.word)) && (this.points==w.points) && (this.coord.equalsCoord(w.coord)));
	}
	
}
