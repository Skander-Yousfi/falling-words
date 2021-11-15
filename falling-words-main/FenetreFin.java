package projet;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

// TODO: Auto-generated Javadoc
/**
 * The Class FenetreFin.
 */
public class FenetreFin implements ActionListener{
	
	/** The f. */
	private JFrame f;
	
	/** The pane. */
	private JPanel pane;
	
	/** The panel. */
	private JPanel panel;
	
	/** The tab. */
	private JPanel tab;
	
	/** The b 1. */
	private JButton b1;
	
	/** The b 2. */
	private JButton b2;
	
	/** The b 3. */
	private JButton b3;
	
	/** The lab. */
	private JLabel lab;
	
	/** The p. */
	private Player p;
	
	/** The score. */
	private int score;
	
	/** The pseudo. */
	private ArrayList<String> pseudo;
	
	/** The sc. */
	private ArrayList<String> sc;
	
	/**
	 * Instantiates a new fenetre fin.
	 *
	 * @param p the p
	 * @param score the score
	 */
	public FenetreFin(Player p, int score) {
		this.p = p;
		this.score = score;
		f = new JFrame();
		f.setSize(500, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		pane = new JPanel(); 
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		tab = new JPanel();
		lab = new JLabel("TOP DES 10 MEILLEURS SCORES");
		lab.setFont(new Font("Serif", Font.ITALIC, 20));
		tab.add(lab);
		tab.add(new JScrollPane(tableau()));
		panel = new JPanel();
		b1 = new JButton("Rejouer avec le pseudo "+p.getPseudo());
		b1.addActionListener(this);
		b2 = new JButton("Changer de pseudo");
		b2.addActionListener(this);
		panel.add(b1);
		panel.add(b2);
		b3 = new JButton("Quitter");
		b3.addActionListener(this);
		pane.add(tab, Component.CENTER_ALIGNMENT);
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(panel);
		pane.add(b3);
		f.setContentPane(pane);
		f.setVisible(true);
	}

	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			new Partie(p.getPseudo());
		}
		if (e.getSource() == b2) {
			new Fenetre1();;
		}
		f.dispose();
	}
	
	/**
	 * Tableau.
	 *
	 * @return the j table
	 */
	public JTable tableau () {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File (System.getProperty("user.dir")+"\\src\\projet\\abcd.txt")));
			String line = reader.readLine();
			System.out.println(line==null);
			if (line == null) {
				pseudo = new ArrayList<String>();
				sc  = new ArrayList<String>();
			}
			else{
				pseudo = new ArrayList<String>(Arrays.asList(line.split(",")));
				line=reader.readLine();
				sc  = new ArrayList<String>(Arrays.asList(line.split(",")));	
			}
			reader.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		pseudo.add(p.getPseudo());
		sc.add(Integer.toString(score));
		Integer[] score_int = new Integer[sc.size()] ;
        int i = 0;
		for (String myInt : sc) {
          score_int[i]=Integer.valueOf(myInt); 
          i++;
        }
        Arrays.sort(score_int, Collections.reverseOrder());
        ArrayList<String> r1 = new ArrayList<String>();
        ArrayList<String> r2 = new ArrayList<String>();
		for (int s : score_int) {
			r1.add(pseudo.get(sc.indexOf(Integer.toString(s))));
			r2.add(Integer.toString(s));
		}
		ArrayList<String> res1 = new ArrayList<String>();
		ArrayList<String> res2 = new ArrayList<String>();
		if (r1.size()>10) {
			for (int k =0; k<10; k++) {
				res1.add(r1.get(k));
				res2.add(r2.get(k));
			}
		}
		else {
			res1=r1;
			res2=r2;
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(System.getProperty("user.dir")+"\\src\\projet\\abcd.txt")));
			writer.write(String.join(",", res1));
			writer.newLine();
			writer.write(String.join(",", res2));
			writer.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		String[][] donnees = new String[res1.size()][2];
		i=0;
		while (i<res1.size()) {
			donnees[i][0]=res1.get(i);
			donnees[i][1]=res2.get(i);
			i++;
		}			
		String[] titre= {"Pseudonyme","Score"};
		JTable jtable = new JTable(donnees,titre) {
	        	private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
                		return false;               
        		};
		};
		return(jtable);
	}
}
