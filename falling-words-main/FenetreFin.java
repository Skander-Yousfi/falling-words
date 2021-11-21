package projet;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * La Classe FenetreFin.
 */
public class FenetreFin implements ActionListener{
	
	/** La fenetre de fin. */
	private JFrame f;
	
	/** Le panel pane qui contient tous les objets de la fenetre. */
	private JPanel pane;
	
	/** Le panel panel qui contient les boutons b1 et b2. */
	private JPanel panel;
	
	/** Le panel tab qui contient le tableau des high-scores. */
	private JPanel tab;
	
	/** Le bouton b1 "Rejouer avec le pseudo" qui relance une partie avec le même pseudo. */
	private JButton b1;
	
	/** Le bouton b2 "Changer de pseudo" qui permet de retourner à la fenetre de début pour changer de pseudo et éventuellement relancer une partie. */
	private JButton b2;
	
	/** Le bouton b3 "Quitter" qui fermer toutes les fenetres et d'arrêter le jeu. */
	private JButton b3;
	
	/** Le Label lab qui permet d'afficher "TOP DES 10 MEILLEURS SCORES" en haut de la fenetre. */
	private JLabel lab;
	
	/** Le joueur p associé à la partie jouée juste avant. */
	private Player p;
	
	/** Le score obtenu par le joueur p lors de la partie. */
	private int score;
	
	/** La liste des pseudos des joueurs enregistrés dans les high-scores. */
	private ArrayList<String> pseudo;
	
	/** La liste des scores enregistrés dans les high-scores. */
	private ArrayList<String> sc;
	
	/**
	 * Instancie une nouvelle FenetreFin.
	 *
	 * @param p
	 * @param score
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
	 * La méthode Action performed donne les actions à effectuer lorsqu'un des boutons est cliqué.
	 *
	 * @param e un evenement associé aux boutons.
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
	 * La méthode Tableau récupère les high scores enregistrés et rajoute le nouveau score et pseudo dans les listes avant de les enregistrer à nouveau et de les stocker dans un JTable en vue d'etre affichés.
	 *
	 * @return le JTable contenant les high scores après leur mise à jour
	 */
	public JTable tableau () {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File (System.getProperty("user.dir")+File.separator+"abcd.txt")));
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
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(System.getProperty("user.dir")+File.separator+"abcd.txt")));
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
