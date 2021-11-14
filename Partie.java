package projet;

import java.awt.event.*;  
import java.util.ArrayList;
import javax.swing.Timer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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
	
	public void saveScore() {
		String line="";
		int scoreToSave=this.getScore();
		Player player=this.getPlayer();
		String pseudonyme=player.getPseudo();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File ("C:\\Users\\cstav\\eclipse-workspace\\projet\\src\\projet\\abcd.txt")));
			line = reader.readLine();
			if(line!=null) {
				ArrayList<String> pseudo = new ArrayList<String>(Arrays.asList(line.split(",")));
				line=reader.readLine();
				ArrayList<String> score  = new ArrayList<String>(Arrays.asList(line.split(",")));
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:\\Users\\cstav\\eclipse-workspace\\projet\\src\\projet\\abcd.txt")));
				
				int j=0;//sauvegarde indice pour ecrire les scores sur deuxieme ligne
				
				if(pseudo.size()== 1){ //SI UN SEUL RESULTAT SAUVEGARDE
					if (Integer.parseInt(score.get(j))<=scoreToSave) {
						writer.write(pseudonyme+","+pseudo.get(j));
						writer.newLine();
						writer.write(Integer.toString(scoreToSave)+","+score.get(j));
					}
					else {
						writer.write(Integer.toString(scoreToSave)+","+score.get(j));
						writer.newLine();
						writer.write(pseudonyme+","+pseudo.get(j));
					}
				}
				else { //SI ENTRE 2 ET 10 ELEMENTS DANS LE FICHIER
					while ((j<score.size()) && Integer.parseInt(score.get(j))>scoreToSave) { //on trouve l'indice où inserer nouveau score ( on incremente j)
							j++;
					}
					if(j<10) {
						pseudo.add(j,pseudonyme);
						score.add(j,Integer.toString(scoreToSave));
						
						if(pseudo.size()==11) { // ON SUPPRIME L'ELEMENT EN TROP
							pseudo.remove(10);
							score.remove(10);
						}
					}	
					//on reecrit toutes les valeurs dans le fichier maintenant que score et pseudo ont les valeurs qu'ils faut 
					int k=0;
					while(k<(pseudo.size()-1)){ //on ecrit tous les elements sauf le dernier 
						writer.write(pseudo.get(k)+",");
						k++;
					}
					writer.write(pseudo.get(pseudo.size()-1)); //on ajoute le dernier element
					writer.newLine();
					k=0;
					while(k<(score.size()-1)){
						writer.write(score.get(k)+",");
						k++;
					}
					writer.write(score.get(score.size()-1));
				}
				writer.close();
			}
			else { //SI AUCUN SCORE ENCORE SAUVEGARDE
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:\\Users\\cstav\\eclipse-workspace\\projet\\src\\projet\\abcd.txt")));
				writer.write(pseudonyme);
				writer.newLine();
				writer.write(scoreToSave);
				writer.close();
			}
				
			reader.close();	
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			this.saveScore();
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
	 * completer, la partie est terminÃ©e, on est sortie du while verifiant le nbr de
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
