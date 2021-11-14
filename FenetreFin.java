package projet;

import java.awt.Component; 
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class FenetreFin implements ActionListener{
	private JFrame f;
	private JPanel pane;
	private JPanel tab;
	private JButton b1;
	private JButton b2;
	private JLabel lab;
	private JTable tableau;
	private Player p;
	private int score;

	public FenetreFin(Player p, int score) {
		this.p = p;
		this.score = score;
		f = new JFrame();
		f.setSize(400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		pane = new JPanel(); 
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		tab = new JPanel();
		lab = new JLabel("Score de "+p.getPseudo()+" : "+score);
		tab.add(lab);
		b1 = new JButton("Rejouer");
		b1.addActionListener(this);
		b2 = new JButton("Quitter");
		b2.addActionListener(this);
		pane.add(tab, Component.CENTER_ALIGNMENT);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(b1);
		pane.add(b2);
		f.setContentPane(pane);
		f.setVisible(true);
		
		
	
		//AFFICHAGE DES SCORES
		String line="";
		try {
			JLabel tit = new JLabel("TOP DES 10 MEILLEURS SCORES");
			tit.setFont(new Font("Serif", Font.ITALIC, 20));
			f.add(tit);
			
			BufferedReader reader = new BufferedReader(new FileReader(new File ("C:\\Users\\cstav\\eclipse-workspace\\projet\\src\\projet\\abcd.txt")));
			line = reader.readLine();
			ArrayList<String> pseudo = new ArrayList<String>(Arrays.asList(line.split(",")));
			line=reader.readLine();
			ArrayList<String> sc  = new ArrayList<String>(Arrays.asList(line.split(",")));	
			
			String[][] donnees = new String[pseudo.size()][2];
			int i=0;
			while (i<pseudo.size()) { //remplit 1ere colonne du tableau
					donnees[i][0]=pseudo.get(i);
					i++;
				}
			i=0;
			while (i<sc.size()) { //remplit 2ere colonne du tableau
				donnees[i][1]=sc.get(i);
				i++;
			}			
			String[] titre= {"Pseudonyme","Score"};
			tableau = new JTable(donnees,titre);
			f.add(new JScrollPane(tableau));
			
			
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			new Partie(p.getPseudo());
		}
		if (e.getSource() == b2) {
			f.dispose();
		}
	}
}
