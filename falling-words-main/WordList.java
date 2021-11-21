package projet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

/**
 * La classe WordList
 */
public class WordList {
	
	/** La liste de mots */
	private static ArrayList<Words> words;
	
	/**
	 * Instantiation d'une nouvelle liste de mots, recuperee du fichier mots.txt
	 */
	public WordList() {
		words = new ArrayList<Words>();
		try{
			for (String line : Files.readAllLines(Paths.get(System.getProperty("user.dir")+File.separator+"mots.txt"))){
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
	 * Getter de la liste des mots.
	 *
	 * @return les mots mots
	 */
	public ArrayList<Words> getWords(){
		return(words);
	}

}



