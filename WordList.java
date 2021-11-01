package acual_project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class WordList {
	private static ArrayList<Words> words;
	
	public WordList() throws IOException{ 
		words = new ArrayList<Words>();
		for (String line : Files.readAllLines(Paths.get("/Users/kulesza/Desktop/mots.txt"))){
			Words l = new Words(line);
			words.add(l);
		}
	}
	public ArrayList<Words> getWords(){
		return(words);
	}
	public void shuffle() {
		Collections.shuffle(words);
	}
}

/*QUESTIONS PROF :
- creer une classe WordList ou bien lire le fichier txt et completer l'ArrayList au debut du main ?
-Que fait "throws" ?  mieux vaut utiliser Try/catch ? Obligation de gerer les exceptions ?



*/
// Remarques : 

/* Le caractère \ étant un caractère réservé, il faut le banaliser en le doublant, on remplace donc les \ par \\
File fichier = new File("D:\\dossier\\fichier.txt");
File dossier = new File("D:\\dossier"); */



/* une méthode qui va effectuer une lecture à l'intérieur d'un fichier respectera généralement le schéma suivant :

import java.io.*; 

class Classe1{
  void lireFichier (String fichier){
    try{
      // Suite d'instructions accédant au fichier et
      // ne s'occupant pas de la gestion des erreurs
      // Tentative d'ouvrir un fichier
      // Lecture dans le fichier
    }
    catch (FileNotFoundException e){
      // Exception déclenchée si le fichier n'existe pas
    }
    catch (IOException e){
      // Exception déclenchée si un autre problème survient
      // pendant l'accès au fichier
    }
    finally{
      // Le bloc finally est toujours exécuté ce qui permet d'être sûr
      // que la fermeture du fichier sera effectuée
      try{
        // Fermeture du fichier si le fichier a été ouvert
      }
      catch (IOException e){
        // Exception déclenchée si un problème survient pendant la fermeture
      }
    }
  }
}*/
