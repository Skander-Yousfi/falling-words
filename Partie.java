package projet;

import java.util.ArrayList;

public class Partie {
	private Player player;
	private WordList list;
	private int compteur; // ??
	private ArrayList<Words> screenWords;
	final int LOWER=10; // ordonnee la plus basse a ne pas depasser 
	
	
	public Partie() {
		// a completer, on initialise tous les parametres de la partie avec les données entrés par l'utilisateur 
		
	}
	
	public void end() {
        // a completer, la partie est terminée, on est sortie du while verifiant le nbr de vie restant, on affiche le temps de jeu, je score, on sauvegarde le score etc...
		//appel a la methode sauvegarde etc...
	}
	
	
	public void verif_row(){ //ou alors renvoit un int (1 si partie continue et 0 si partie terminée)  a verifier dans boucle while et plus besoin du verif_health ?
	    for (Words w : this.screenWords) {
	    	Coord coo = w.getCoord();
	    	if (coo.getY() < LOWER) { //si un des mots de la liste a l'ecran est plus bas que LOWER
	    		this.player.looseHealth(); //on enlève une vie 
	    	}
	    	//else      //c'est ici qu'on fait descendre les mots ? 
	    	//	w.(coord.down);
	    }
	}
	
	
	
	public static void main(String[] args){
		
	}
}
