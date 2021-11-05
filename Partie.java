package projet;

import java.util.ArrayList;

public class Partie {
	private Player player;
	private WordList list;
	private int compteur; // ??
	private ArrayList<Words> screenWords;
	final int LOWER=10; // ordonnee la plus basse a ne pas depasser 
	private int index=0; //pour parcourir notre tableau de base de mots 
	private JFrame fenetre;
	private int score;
	
	
	public Partie(String ps) throws IOException {
		compteur=0;
		list= new WordList();
		player = new Player(ps);
		screenWords= new ArrayList<Words>();
		fenetre = new FenetreJeu(screenWords);
	}
	
	public void end() {
		//avec swing et mothode sauvegarde 
        // a completer, la partie est terminée, on est sortie du while verifiant le nbr de vie restant, on affiche le temps de jeu, je score, on sauvegarde le score etc...
		//appel a la methode sauvegarde etc...
	}
	
	
	public void updatePosition(){ //ou alors renvoit un int (1 si partie continue et 0 si partie terminée)  a verifier dans boucle while et plus besoin du verif_health ?
		if (compteur==4){
	    		if( index==(list.getWords().size())-1) {
	    			list.shuffle();
	    			index=0;
	    		}
	    		Words word = list.getWords().get(index);
	    		screenWords.add(word);
	    		compteur=0;
	    }
		for (Words w : this.screenWords) {
	    	w.downWord();
	    	Coord coo = w.getCoord();
	    	if (coo.getY() < LOWER) { //si un des mots de la liste a l'ecran est plus bas que LOWER
	    	this.player.looseHealth(); //on enlève une vie 
	    	screenWords.remove(w);
	    	compteur++;
	    	}
	    }
		
		fenetre.update(screenWords);
	    }
	
	
	
	public static void main(String[] args){
		
	}
}
