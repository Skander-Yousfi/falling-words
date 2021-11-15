package projet;

import java.util.Random;

public class Words {
	private String word;
	private int points;
	private Coord coord;
	
	public Words (String mot) {
		this.word = mot;
		this.points = mot.length();
		Random random = new Random();
		this.coord = new Coord(random.nextInt(5), 0);
	}
	
	public Words (String mot, int x, int y) {
		this.word = mot;
		this.points = mot.length();
		this.coord = new Coord(x,y);
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
	
	public void initCoord() {
		Random random = new Random();
		coord = new Coord(random.nextInt(5), 0);
	}
}
