package projet;

import java.awt.event.*;  
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.Random;


/**
 * La Class Partie
 */
public class Partie implements ActionListener{
	
	/** Joueur associé à la partie */
	private Player player;
	
	/** Liste de mots associée à la partie */
	private WordList list;
	
	/** Compteur déterminant la fréquence d'ajout des mots à l'ecran */
	private int compteur; 
	
	/** Liste de mots apparaissant à l'ecran */
	private ArrayList<Words> screenWords; 
	
	/** Ordonnee la plus basse que peut avoir un mot à l'écran, avant que celui-ci ne disparaisse */
	final int LOWER = 14; 
	
	/** Permet de parcourir notre tableau de base de mots */
	private int index = 0; //
	
	/** Fenêtre de jeu affichant l'écran de jeu */
	private FenetreJeu fenetre;
	
	/** Le timer qui à chaque "tic" actualise les données de la fêntre de jeu */
	private Timer timer;
	
	/** Score du joueur */
	private int score = 0;
	
	/** Mot à retirer de la liste de mots affichés à l'écran lors du parcours de celle-ci */
	private Words wordToRemove;
	
	/** Variable permettant d'établir la vitesse de descente des mots à l'écran */
	private int etape = 0;
	
	/** Valeur aléatoire comprise entre 1 et 4 telle que lorsque (compteur == add), 
	un nouveau mot est ajouté à l'ecran et add prend une nouvelle valeur générée aléatoirement.*/
	private int add = 1;
	
	/** Objet de la classe Random permettant de générer la valeur aléatoire de "add" */
	private Random r = new Random();
	
	/**
	 * Instanciation d'une nouvelle partie
	 *
	 * @param ps (pseudo)
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
	 * Actualisation de la position des mots à l'écran
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
	 * Verifie que le mot saisie par l'utilisateur est bien un mot de la liste de mots apparaissant à l'écran
	 *
	 * @param word (mot saisie par l'utilisateur)
	 * @return l'objet de type Word contenu dans screenWords
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
	 * Incrementation du score du joueur après la saisie correcte d'un mot
	 *
	 * @param a (nombre de points associés au mot correctement saisie) 
	 */
	public void addToScore(int a) {
		score += a;
	}
	
	/**
	 * Retire un mot de la liste de mots apparaissant à l'écran 
	 *
	 * @param w (mot qu'il faut retirer)
	 */
	public void removeWord(Words w) {
		screenWords.remove(w);
	}
	
	/**
	 * Donne l'action à réaliser à chaque "tic" du timer (ici, actualisation de la position des mots à l'écran)
	 *
	 * @param e 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		updatePosition();
	}
	
	/**
	 * Lance la fenêtre de jeu
	 *
	 * @return the frame
	 */
	public FenetreJeu getFrame() {
		return(fenetre);
	}
	
	/**
	 * Getter des données du joueur
	 *
	 * @return les données de player
	 */
	public Player getPlayer() {
		return(player);
	}
	
	/**
	 * Getter du score du joueur 
	 *
	 * @return le score
	 */
	public int getScore() {
		return(score);
	}
	
	/**
	 * Getter des mots aparaissant à l'écran
	 *
	 * @return screenwords
	 */
	public ArrayList<Words> getScreenWords(){
		return(screenWords);
	}
}
