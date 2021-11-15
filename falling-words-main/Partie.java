package projet;

import java.awt.event.*;  
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Partie.
 */
public class Partie implements ActionListener{
	
	/** The player. */
	private Player player;
	
	/** The list. */
	private WordList list;
	
	/** The compteur. */
	private int compteur; 
	
	/** The screen words. */
	private ArrayList<Words> screenWords; 
	
	/** The lower. */
	final int LOWER = 14; // ordonnee la plus basse a ne pas depasser => utile???
	
	/** The index. */
	private int index = 0; //pour parcourir notre tableau de base de mots 
	
	/** The fenetre. */
	private FenetreJeu fenetre;
	
	/** The timer. */
	private Timer timer;
	
	/** The score. */
	private int score = 0;
	
	/** The word to remove. */
	private Words wordToRemove;
	
	/** The etape. */
	private int etape = 0;
	
	/** The add. */
	private int add = 1;
	
	/** The r. */
	private Random r = new Random();
	
	/**
	 * Instantiates a new partie.
	 *
	 * @param ps the ps
	 */
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

	/**
	 * Update position.
	 */
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
		if (compteur==add){
    			if( index==(list.getWords().size())-1) {
    				index=0;
    			}
			Words word = list.getWords().get(index);
			word.initCoord();
			screenWords.add(word);
			compteur=0;
			index++;
			add = r.nextInt(3)+1;
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
			//this.saveScore();
			new FenetreFin(player, score);
			
		}
	}
	
	/**
	 * Verif word.
	 *
	 * @param word the word
	 * @return the words
	 */
	public Words verif_word(String word) {
		Words res = null;
		for (Words w:screenWords) {
			if(w.getWord().equalsIgnoreCase(word)) {
				res = w;
			}
		}
		return(res);
	}
	
	/**
	 * Adds the to score.
	 *
	 * @param a the a
	 */
	public void addToScore(int a) {
		score += a;
	}
	
	/**
	 * Removes the word.
	 *
	 * @param w the w
	 */
	public void removeWord(Words w) {
		screenWords.remove(w);
	}
	
	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		updatePosition();
	}
	
	/**
	 * Gets the frame.
	 *
	 * @return the frame
	 */
	public FenetreJeu getFrame() {
		return(fenetre);
	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return(player);
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return(score);
	}
	
	/**
	 * Gets the screen words.
	 *
	 * @return the screen words
	 */
	public ArrayList<Words> getScreenWords(){
		return(screenWords);
	}
}
