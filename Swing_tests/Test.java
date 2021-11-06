package projet;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.Timer;

public class Test implements ActionListener{
	
	public static FenetreJeu f2;
	private ArrayList<Words> l;
	private ArrayList<Words> l2;
	private Words w ;
	private Words w1;
	private Words w2;
	public Timer timer;

	public Test() {
		timer = new Timer(2000, this);
		l = new ArrayList<Words>();
		l2 = new ArrayList<Words>();
		Words w = new Words("somptueux");
		w.downWord();
		l.add(w);
		l2.add(w);
		Words w1 = new Words("berceuse");
		l.add(w1);
		l2.add(w1);
		Words w2 = new Words("test", 0, 2);
		l.add(w2);
		f2 = new FenetreJeu(l);
		f2.setVisible(true);
	}
	
	/*
	 * public static void main (String [] args) {
	 * 
	 * // Fenetre1 f = new Fenetre1(); // f.getFrame().setVisible(true);
	 * ArrayList<Words> l = new ArrayList<Words>(); Words w = new
	 * Words("somptueux"); w.downWord(); l.add(w); Words w1 = new Words("berceuse");
	 * l.add(w1); Words w2 = new Words("test", 0, 2); l.add(w2); f2 = new
	 * FenetreJeu(l); f2.setVisible(true); timer.start();
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		afficheList(l);
		afficheList(l2);
		f2.update(l2);
		timer.stop();
	}
	public void afficheList(ArrayList<Words> list) {
		if (list.isEmpty()) {
			System.out.println("Liste vide");
		}
		for (Words w:list) {
			System.out.print(w.getWord()+"\t");
		}
	}
}
