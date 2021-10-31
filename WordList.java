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
	public void print() {
		for (Words l : words) {
			System.out.println(l.getWord());
		}
	}
}
