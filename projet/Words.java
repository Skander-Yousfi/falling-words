package projet;

import java.util.Random;

/**
 * La classe Words
 */
public class Words implements java.io.Serializable{
	
	/** Constante nécessaire à la sérialisation */
	private static final long serialVersionUID = 1L;

	/** Chaine de caracteres associee au mot */
	private String word;
	
	/** Nombre de points associes a chaque mot (1 caractere vaut 1 point) */
	private int points;
	
	/** Coordonnees du mot à l'ecran */
	private Coord coord;
	
	/**
	 * Instanciation d'un nouveau mot en le placant à un endroit au hasard
	 *
	 * @param mot, la chaine de caracteres associee au mot
	 */
	public Words (String mot) {
		this.word = mot;
		this.points = mot.length();
		Random random = new Random();
		this.coord = new Coord(random.nextInt(5), 0);
	}
	
	/**
	 * Instanciation d'un nouveau mot à une coordonnée précise
	 *
	 * @param mot, la chaine de caracteres associee au mot
	 * @param x, abscisse du mot 
	 * @param y, ordonnee du mot
	 */
	public Words (String mot, int x, int y) {
		this.word = mot;
		this.points = mot.length();
		this.coord = new Coord(x,y);
	}
	
	/**
	 * Getter du mot
	 *
	 * @return le mot word
	 */
	public String getWord() {
		return(word);
	}
	
	/**
	 * Getter pour le nombre de points du mot
	 *
	 * @return les points
	 */
	public int getPoints() {
		return(points);
	}
	
	/**
	 * Getter des coordonnees du mot
	 *
	 * @return les coordonnées coord
	 */
	public Coord getCoord() {
		return(coord);
	}
	
	/**
	 * methode downWord, permettant de modifier les coordonnees du mot afin de le faire descendre a l'ecran 
	 */
	public void downWord () {
		coord.down();
	}
	
	/**
	 * Initialisation aleatoire des coordonnees d'un mot afin de l'ajouter à l'ecran
	 */
	public void initCoord() {
		Random random = new Random();
		coord = new Coord(random.nextInt(5), 0);
	}
}