package projet;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Timer;
import java.util.Random;


/**
 * La Class Partie
 */
public class Partie implements ActionListener, java.io.Serializable{
	
	/** Constante nécessaire à la sérialisation */
	private static final long serialVersionUID = 1L;

	/** Joueur associé à la partie */
	private Player player;
	
	/** Liste de mots associée à la partie */
	private ArrayList<Words> list;
	
	/** Compteur déterminant la fréquence d'ajout des mots à l'ecran */
	private int compteur; 
	
	/** Liste de mots apparaissant à l'ecran */
	private ArrayList<Words> screenWords; 
	
	/** Ordonnee la plus basse que peut avoir un mot à l'écran, avant que celui-ci ne disparaisse */
	final int LOWER = 14; 
	
	/** Permet de parcourir notre tableau de base de mots */
	private int index; //
	
	/** Fenêtre de jeu affichant l'écran de jeu */
	private FenetreJeu fenetre;
	
	/** Le timer qui à chaque "tic" actualise les données de la fêntre de jeu */
	private Timer timer;
	
	/** Le timer qui à chaque "tic" actualise le temps de jeu */
	final private Timer chrono;
	
	/** Score du joueur */
	private int score;
	
	/** Mot à retirer de la liste de mots affichés à l'écran lors du parcours de celle ci */
	private Words wordToRemove;
	
	/** Variable permettant d'établir la vitesse de descente des mots à l'écran */
	private int etape;
	
	/** Valeur aléatoire comprise entre 1 et 4 telle que lorsque (compteur == add), 
	un nouveau mot est ajouté à l'ecran et add prend une nouvelle valeur générée aléatoirement.*/
	private int add;
	
	/** Objet de la classe Random permettant de générer la valeur aléatoire de "add" */
	private final Random r = new Random();

	/** Nombre de caractère que l'on incrémente à chaque nouveau caractère tapé. */
	private int caract;

	/** Nombre de mots tapés que l'on incrémente à chaque appuis sur le touche espace, que le mot soit reconnu ou non. */
	private int mots;
	
	/** Nombre de mots tapés que l'on incrémente à chaque appuis sur le touche espace, uniquement si le mot est reconnu. */
	private int corrMots;
	
	/**
	 * Instanciation d'une nouvelle partie
	 *
	 * @param ps (pseudo)
	 */
	public Partie(String ps) {
		index = 0;
		score = 0;
		compteur=0;
		etape = 0;
		add = 1;
		list=  new ArrayList<Words>();
		try{
			for (String line : Files.readAllLines(Paths.get(System.getProperty("user.dir")+File.separator+"mots.txt"))){
				Words l = new Words(line);
				list.add(l);
			}
		}
	    catch (FileNotFoundException e){
	    	System.out.print("Le fichier que vous essayez d'ouvrir n'existe pas.");
	    }
	    catch (IOException e){
	    	System.out.print("Probleme pendant l'acces au fichier.");
	    }
		Collections.shuffle(list);
		player = new Player(ps);
		screenWords= new ArrayList<Words>();
		fenetre = new FenetreJeu(screenWords, player, score);
		timer = new Timer(1000, this);
		chrono = new Timer(1000, this);
		new ALTextField(this);
		fenetre.setVisible(true);
		timer.start();
		chrono.start();
	}
	
	/**
	 * Instanciation d'une nouvelle partie
	 *
	 * @param p (player), l (liste de tous les mots), screenWords (mots à l'écran), time (temps de jeu), score, compteur, etape, 
			add, index, caract, mots, corrMots, text, timer, chrono
	 */
	public Partie(Player p, ArrayList<Words> l, ArrayList<Words> screenWords, Time time, int score, int compteur, int etape, 
			int add, int index, int caract, int mots, int corrMots, String text, Timer timer, Timer chrono) {
		this.index = index;
		this.score = score;
		this.compteur = compteur;
		this.etape = etape;
		this.add = add;
		this.caract = caract;
		this.mots = mots;
		this.corrMots = corrMots;
		list=  l;
		player = p;
		this.screenWords= screenWords;
		fenetre = new FenetreJeu(screenWords, player, score);
		fenetre.setTime(time);
		fenetre.getTextField().setText(text);
		this.timer = timer;
		this.timer.addActionListener(this);
		this.chrono = chrono;
		this.chrono.addActionListener(this);
		new ALTextField(this);
		fenetre.setVisible(true);
		this.timer.start();
		this.chrono.start();
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
    			if( index==(list.size())-1) {
    				index=0;
    			}
			Words word = list.get(index);
			word.initCoord();
			screenWords.add(word);
			compteur=0;
			index++;
			add = r.nextInt(3)+1;
		}
		compteur++;
		if (score>25 && etape < 1){
			timer.setDelay(950);
			etape = 1;
			fenetre.updateColor(218, 247, 166);
		}
		if (score>50 && etape < 2){
			timer.setDelay(900);
			etape = 2;
			fenetre.updateColor(50, 193, 190);
		}
		if (score>75 && etape < 3){
			timer.setDelay(850);
			etape = 3;
			fenetre.updateColor(38, 186, 237);
		}
		if (score>100 && etape < 4){
			timer.setDelay(800);
			etape = 4;
			fenetre.updateColor(102, 255, 102);
		}
		if (score>125 && etape < 5){
			timer.setDelay(750);
			etape = 5;
			fenetre.updateColor(134, 92, 206);
		}
		if (score>150 && etape < 6){
			timer.setDelay(700);
			etape = 6;
			fenetre.updateColor(203, 92, 206);
		}
		if (score>175 && etape < 7){
			timer.setDelay(675);
			etape = 7;
			fenetre.updateColor(191, 57, 105);
		}
		if (score>200 && etape < 8){
			timer.setDelay(650);
			etape = 8;
			fenetre.updateColor(235, 140, 50);
		}
		if (score>225 && etape < 9){
			timer.setDelay(625);
			etape = 9;
			fenetre.updateColor(238, 234, 30);
		}
		if (score>250 && etape < 10){
			timer.setDelay(600);
			etape = 10;
			fenetre.updateColor(160, 229, 32);
		}
		if (score>275 && etape < 11){
			timer.setDelay(575);
			etape = 11;
			fenetre.updateColor(94, 207, 33);
		}
		if (score>300 && etape < 12){
			timer.setDelay(550);
			etape = 12;
			fenetre.updateColor(27, 164, 54);
		}
		if (score>325 && etape < 13){
			timer.setDelay(525);
			etape = 13;
			fenetre.updateColor(27, 164, 112);
		}
		if (score>350 && etape < 14){
			timer.setDelay(500);
			etape = 14;
			fenetre.updateColor(152, 218, 217);
		}
		if (player.verif_health()) {
			fenetre.updateScreen(screenWords);
		}
		else {
			fenetre.dispose();
			timer.stop();
			chrono.stop();
			new FenetreFin(this);
			
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
		if (e.getSource() == timer) {
			updatePosition();
		}
		if (e.getSource() == chrono) {
			fenetre.updateTime();
		}
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
	 * Getter du temps de jeu 
	 *
	 * @return le temps
	 */
	public Time getTime() {
		return(fenetre.getTime());
	}
	
	/**
	 * Getter du nombre de caractères tapés
	 *
	 * @return le nombre de caractères
	 */
	public int getCaract() {
		return(caract);
	}
	
	/**
	 * Getter du nombre de mots tapés 
	 *
	 * @return le nombre de mots
	 */
	public int getMots() {
		return(mots);
	}
	
	/**
	 * Getter du nombre de mots tapés correctement
	 *
	 * @return le nombre de mots
	 */
	public int getCorrMots() {
		return(corrMots);
	}
	
	/**
	 * Getter du timer
	 *
	 * @return le timer
	 */
	public Timer getTimer() {
		return(timer);
	}
	
	/**
	 * Getter du chrono
	 *
	 * @return le chrono
	 */
	public Timer getChrono() {
		return(chrono);
	}
	
	/**
	 * Getter des mots aparaissant à l'écran
	 *
	 * @return screenwords
	 */
	public ArrayList<Words> getScreenWords(){
		return(screenWords);
	}
	
	/**
	 * Getter des mots qui peuvent apparaitre à l'écran
	 *
	 * @return list
	 */
	public ArrayList<Words> getWords(){
		return(list);
	}
	
	/**
	 * Getter du nombre de mots tapés correctement
	 *
	 * @return le comp
	 */
	public int getCompteur() {
		return(compteur);
	}
	/**
	 * Getter de l'étape
	 *
	 * @return l'étape
	 */
	public int getEtape() {
		return(etape);
	}
	/**
	 * Getter de la variable add
	 *
	 * @return la variable add
	 */
	public int getAdd() {
		return(add);
	}
	/**
	 * Getter de l'index
	 *
	 * @return l'index
	 */
	public int getIndex() {
		return(index);
	}

	/**
	 * Méthode qui incrémente le nombre de caractères.
	 */
	public void incrCaract() {
		caract++;
	}
	
	/**
	 * Méthode qui incrémente le nombre de mots.
	 */
	public void incrMot() {
		mots++;
	}
	
	/**
	 * Méthode qui incrémente le nombre de mots correctement tapés.
	 */
	public void incrCorrMots() {
		corrMots++;
	}
}
