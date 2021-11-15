package projet;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Words.
 */
public class Words {
	
	/** The word. */
	private String word;
	
	/** The points. */
	private int points;
	
	/** The coord. */
	private Coord coord;
	
	/**
	 * Instantiates a new words.
	 *
	 * @param mot the mot
	 */
	public Words (String mot) {
		this.word = mot;
		this.points = mot.length();
		Random random = new Random();
		this.coord = new Coord(random.nextInt(5), 0);
	}
	
	/**
	 * Instantiates a new words.
	 *
	 * @param mot the mot
	 * @param x the x
	 * @param y the y
	 */
	public Words (String mot, int x, int y) {
		this.word = mot;
		this.points = mot.length();
		this.coord = new Coord(x,y);
	}
	
	/**
	 * Gets the word.
	 *
	 * @return the word
	 */
	public String getWord() {
		return(word);
	}
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int getPoints() {
		return(points);
	}
	
	/**
	 * Gets the coord.
	 *
	 * @return the coord
	 */
	public Coord getCoord() {
		return(coord);
	}
	
	/**
	 * Down word.
	 */
	public void downWord () {
		coord.down();
	}
	
	/**
	 * Inits the coord.
	 */
	public void initCoord() {
		Random random = new Random();
		coord = new Coord(random.nextInt(5), 0);
	}
}
