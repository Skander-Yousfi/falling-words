package projet;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Partie implements ActionListener{
	private Player player;
	private WordList list;
	private int compteur; 
	private ArrayList<Words> screenWords; 
	final int LOWER = 14; // ordonnee la plus basse a ne pas depasser => utile???
	private int index = 0; //pour parcourir notre tableau de base de mots 
	private FenetreJeu fenetre;
	private Timer timer;
	private int score = 0;
	private Words wordToRemove;
	private int etape = 0;
	
	
	public Partie(String ps) {
		compteur=0;
		list= new WordList();
//		list.shuffle();
		player = new Player(ps);
		screenWords= new ArrayList<Words>();
		fenetre = new FenetreJeu(screenWords, player);
		timer = new Timer(1000, this);
		new ALTextField(this);
		fenetre.setVisible(true);
		timer.start();
	}
	
	public void updatePosition(){ 
		for (Words w : screenWords) {
	    	if (w.getCoord().getY() == LOWER) {
		    	player.looseHealth();
		    	wordToRemove = w;
		    	fenetre.updateHealth(player);
	    	}
	    	else {w.downWord();}
	    }
		removeWord(wordToRemove);
		if (compteur==1){
    		if( index==(list.getWords().size())-1) {
//    			list.shuffle();
    			index=0;
    		}
    		Words word = list.getWords().get(index);
    		word.initCoord();
    		screenWords.add(word);
    		compteur=0;
    		index++;
		}
		compteur++;
		if (score>50 && etape < 1){
			timer.setDelay(900);
			etape = 1;
		}
		if (score>100 && etape < 2){
			timer.setDelay(800);
			etape = 2;
		}
		if (score>150 && etape < 3){
			timer.setDelay(700);
			etape = 3;
		}
		if (score>200 && etape < 4){
			timer.setDelay(600);
			etape = 4;
		}
		if (score>250 && etape < 5){
			timer.setDelay(500);
			etape = 5;
		}
		if (score>300 && etape < 6){
			timer.setDelay(400);
			etape = 6;
		}
		if (score>350 && etape < 7){
			timer.setDelay(300);
			etape = 7;
		}
		if (score>400 && etape < 8){
			timer.setDelay(200);
			etape = 8;
		}
		if (player.verif_health()) {
			fenetre.updateScreen(screenWords);
		}
		else {
			fenetre.dispose();
			timer.stop();
			new FenetreFin(player, score);
		}
	}
	
	public Words verif_word(String word) {
		Words res = null;
		for (Words w:screenWords) {
			if(w.getWord().equalsIgnoreCase(word)) {
				res = w;
			}
		}
		return(res);
	}
	
	public void addToScore(int a) {
		score += a;
	}
	
	public void removeWord(Words w) {
		screenWords.remove(w);
	}
	
	/*
	 * public void end() { //FenetreFin //avec swing et mothode sauvegarde // a
	 * completer, la partie est terminée, on est sortie du while verifiant le nbr de
	 * vie restant, on affiche le temps de jeu, je score, on sauvegarde le score
	 * etc... //appel a la methode sauvegarde etc... new FenetreFin(player, score);
	 * }
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		updatePosition();
	}
	
	public FenetreJeu getFrame() {
		return(fenetre);
	}
	
	public Player getPlayer() {
		return(player);
	}
	
	public int getScore() {
		return(score);
	}
	
	public ArrayList<Words> getScreenWords(){
		return(screenWords);
	}
}
