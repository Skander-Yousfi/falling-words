package projet;

public class Coord {
	private int x;
	private int y;
	
	public Coord (int a, int b) {
		x = a;
		y = b;
	}
	public int getX () {
		return(x);
	}
	public int getY () {
		return(y);
	}
	public void down () {
		y += 1;
	}
	
	//inutile 
	public boolean equalsCoord(Coord c) {
		return ((this.getX()==c.getX()) && (this.getY()==c.getY()));
	}
}
