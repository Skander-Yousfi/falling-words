package projet;

/**
 * La classe Time qui nous permet de stocker le temps de jeu et de le transformer en chaine de caractère lorsque c'est nécessaire.
 */

public class Time {
	
	/** La durée de la partie */
	private int time;
	
	/**
	 * Instantie un nouveau temps.
	 *
	 * @param s le nombre de secondes
	 */
	public Time(int s) {
		time = s;
	}
	
	/**
	 * Instantie un nouveau temps par défaut à zéro.
	 */
	public Time () {
		this(0);
	}
	
	/**
	 * Méthode qui transforme notre nombre de secondes en chaine de caractère de la forme MM:SS.
	 */
	public String toString() {
		String res = "";
		if (time>60) {
			if (time % 60 >9) {
				res = Integer.toString(time / 60)+":"+Integer.toString(time % 60);
			}
			else {
				res = Integer.toString(time / 60)+":0"+Integer.toString(time % 60);
			}
		}
		else if (time>9) {
			res = "00:"+Integer.toString(time);
		}
		else {
			res = "00:0"+Integer.toString(time);
		}
		return res;
	}
	
	/**
	 * Getter qui permet d'accéder au nombre de secondes.
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Méthode qui incrémente le nombre de secondes.
	 */
	public void incr() {
		time++;
	}
}
