package projet;

/**
 * La classe Coord.
 */
public class Coord implements java.io.Serializable{
	
	/** Constante nécessaire à la sérialisation */
	private static final long serialVersionUID = 1L;

	/** Initialisation de l'abcisse */
	private int x;
	
	/** Initialisation de l'ordonnee */
	private int y;
	
	/**
	 * Instantiation d'une nouvelle coordonnee
	 *
	 * @param a, l'abcisse
	 * @param b, l'ordonnee
	 */
	public Coord (int a, int b) {
		x = a;
		y = b;
	}
	
	/**
	 * Getter de l'abcisse
	 *
	 * @return l'abcisse x
	 */
	public int getX () {
		return(x);
	}
	
	/**
	 * Getter de l'ordonnee
	 *
	 * @return l'ordonnee y
	 */
	public int getY () {
		return(y);
	}
	
	/**
	 * methode qui permet d'incrementer l'ordonnee 
	 */
	public void down () {
		y += 1;
	}
}