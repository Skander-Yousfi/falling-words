package acual_project;

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
		this.coord = new Coord(1+random.nextInt(10), 10);
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
}
