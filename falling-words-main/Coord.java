package projet;

// TODO: Auto-generated Javadoc
/**
 * The Class Coord.
 */
public class Coord {
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/**
	 * Instantiates a new coord.
	 *
	 * @param a the a
	 * @param b the b
	 */
	public Coord (int a, int b) {
		x = a;
		y = b;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX () {
		return(x);
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY () {
		return(y);
	}
	
	/**
	 * Down.
	 */
	public void down () {
		y += 1;
	}
	
	/**
	 * Equals coord.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	//inutile 
	public boolean equalsCoord(Coord c) {
		return ((this.getX()==c.getX()) && (this.getY()==c.getY()));
	}
}
