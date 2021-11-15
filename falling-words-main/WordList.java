package projet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

// TODO: Auto-generated Javadoc
/**
 * The Class WordList.
 */
public class WordList {
	
	/** The words. */
	private static ArrayList<Words> words;
	
	/**
	 * Instantiates a new word list.
	 */
	public WordList() {
		words = new ArrayList<Words>();
		try{
			for (String line : Files.readAllLines(Paths.get(System.getProperty("user.dir")+"\\src\\projet\\mots.txt"))){
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
	    	System.out.print("ProblÃ¨me pendant l'accÃ¨s au fichier.");
	    }
	}
	
	/**
	 * Gets the words.
	 *
	 * @return the words
	 */
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

/* Le caractÃ¨re \ Ã©tant un caractÃ¨re rÃ©servÃ©, il faut le banaliser en le doublant, on remplace donc les \ par \\
File fichier = new File("D:\\dossier\\fichier.txt");
File dossier = new File("D:\\dossier"); */


