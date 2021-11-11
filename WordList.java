package projet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class WordList {
	private static ArrayList<Words> words;
	
	public WordList() {
		words = new ArrayList<Words>();
		try{
			for (String line : Files.readAllLines(Paths.get("/Users/kulesza/Desktop/mots.txt"))){
				Words l = new Words(line);
				words.add(l);
			}
			System.out.print(words.size());
			Collections.shuffle(words);
		}
	    catch (FileNotFoundException e){
	    	System.out.print("Le fichier que vous essayez d'ouvrir n'existe pas.");
	    }
	    catch (IOException e){
	    	System.out.print("Problème pendant l'accès au fichier.");
	    }
	}
	public ArrayList<Words> getWords(){
		return(words);
	}
//	public void shuffle() {
//		Collections.shuffle(words);
//	}
}

/*QUESTIONS PROF :
- creer une classe WordList ou bien lire le fichier txt et completer l'ArrayList au debut du main ?
-Que fait "throws" ?  mieux vaut utiliser Try/catch ? Obligation de gerer les exceptions ?



*/
// Remarques : 

/* Le caractère \ étant un caractère réservé, il faut le banaliser en le doublant, on remplace donc les \ par \\
File fichier = new File("D:\\dossier\\fichier.txt");
File dossier = new File("D:\\dossier"); */


