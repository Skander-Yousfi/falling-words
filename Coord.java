package acual_project;

public class Coord {
	private int x;
	private int y;
	
	public Coord (int a, int b) {
		this.x = a;
		this.y = b;
	}
	public int getX () {
		return(x);
	}
	public int getY () {
		return(y);
	}
	public void down () {
		y -= 1;
	}
}
