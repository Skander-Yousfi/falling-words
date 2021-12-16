package projet;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.JTableHeader;

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
	
	/** Le panel tab qui contient le tableau des high scores. */
	private JPanel tab;
	
	/** Le bouton b1 "Rejouer avec le pseudo" qui relance une partie avec le même pseudo. */
	private JButton b1;
	
	/** Le bouton b2 "Changer de pseudo" qui permet de retourner à la fenetre de début pour changer de pseudo et éventuellement relancer une partie. */
	private JButton b2;
	
	/** Le bouton b3 "Mes Stats" qui affiche une fenetre pop up contenant les statistiques associées à la partie. */
	private JButton b3;
	
	/** Le bouton b4 "Quitter" qui fermer toutes les fenetres et d'arrêter le jeu. */
	private JButton b4;
	
	/** Le bouton qui apparait sur la fenetre pop up lorsqu'une sauvegarde est détectée. */
	private JButton b5 = new JButton("Reprendre la partie sauvegardée");
	
	/** Le bouton qui apparait sur la fenetre pop up lorsqu'une sauvegarde est détectée. */
	private JButton b6 = new JButton("Commencer une nouvelle partie");
	
	/** Le Label lab qui permet d'afficher "TOP DES 10 MEILLEURS SCORES" en haut de la fenetre. */
	private JLabel lab;
	
	/** La partie p jouée juste avant. */
	private Partie p;
	
	/** Le joueur player associé à la partie jouée juste avant. */
	private Player player;
	
	/** Le score obtenu par le joueur p lors de la partie. */
	private int score;
	
	/** La liste des pseudos des joueurs enregistrés dans les high-scores. */
	private ArrayList<String> pseudo;
	
	/** La liste des scores enregistrés dans les high-scores. */
	private ArrayList<String> sc;
	
	/** Le Joueur associé à la partie éventuellement sauvegardée. */
	private Player player2 ;
	
	/** Le Joueur associé à la partie éventuellement sauvegardée. */
    private ArrayList<Words> l;
    
    /** Le Joueur associé à la partie éventuellement sauvegardée. */
    private ArrayList<Words> screenWords; 
    
    /** Le Joueur associé à la partie éventuellement sauvegardée. */
    private Time time;
    
    /** Une valeur associée à la partie éventuellement sauvegardée. */
    private int score2;
    
    /** Une valeur associée à la partie éventuellement sauvegardée. */
    private int compteur;
    
    /** Une valeur associée à la partie éventuellement sauvegardée. */
    private int etape;
    
    /** Une valeur associée à la partie éventuellement sauvegardée. */
    private int add;
    
    /** Une valeur associée à la partie éventuellement sauvegardée. */
    private int index; 
    
    /** Une valeur associée à la partie éventuellement sauvegardée. */
    private int caract; 
    
    /** Une valeur associée à la partie éventuellement sauvegardée. */
    private int mots;
    
    /** Une valeur associée à la partie éventuellement sauvegardée. */
    private int corrMots; 
    
    /** Une chaine de caractères associée à la partie éventuellement sauvegardée. */
    private String text;
    
    /** Un Timer associée à la partie éventuellement sauvegardée. */
    private Timer timer; 
    
    /** Un Timer associée à la partie éventuellement sauvegardée. */
    private Timer chrono;
	
	/**
	 * Instancie une nouvelle FenetreFin.
	 *
	 * @param p
	 * @param score
	 */
	public FenetreFin(Partie p) {
		this.p = p ;
		player = p.getPlayer();
		score = p.getScore();
		f = new JFrame();
		f.setSize(500, 350);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		pane = new JPanel(); 
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		tab = new JPanel();
		lab = new JLabel("TOP DES 10 MEILLEURS SCORES");
		lab.setFont(new Font("Serif", Font.ITALIC, 20));
		tab.add(lab);
		tab.add(new JScrollPane(tableau()));
		pane.add(tab, Component.CENTER_ALIGNMENT);
		panel = new JPanel();
		b1 = new JButton("Rejouer avec le pseudo "+player.getPseudo());
		b1.addActionListener(this);
		b2 = new JButton("Changer de pseudo");
		b2.addActionListener(this);
		panel.add(b1);
		panel.add(b2);
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(panel);
		b3 = new JButton("Mes Stats");
		b3.addActionListener(this);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(b3);
		b4 = new JButton("Quitter");
		b4.addActionListener(this);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(b4);
		f.setContentPane(pane);
		f.setVisible(true);
	}

	/**
	 * La méthode Action performed donne les actions à effectuer lorsqu'un des boutons est cliqué.
	 *
	 * @param e un evenement associé aux boutons.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			try {
				FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+File.separator+p.getPlayer().getPseudo()+"-save.txt"));
				ObjectInputStream ois = new ObjectInputStream(fis);
			    player2 = (Player) ois.readObject();
			    l = (ArrayList<Words>) ois.readObject(); 
			    screenWords = (ArrayList<Words>) ois.readObject(); 
			    time = (Time) ois.readObject(); 
			    score2 = (Integer) ois.readObject();
			    compteur = (Integer) ois.readObject();
			    etape = (Integer) ois.readObject();
				add = (Integer) ois.readObject();
				index = (Integer) ois.readObject();
				caract = (Integer) ois.readObject();
				mots = (Integer) ois.readObject();
				corrMots = (Integer) ois.readObject();
				text = (String) ois.readObject(); 
				timer = (Timer) ois.readObject(); 
				chrono = (Timer) ois.readObject();
			    ois.close();
			    JDialog diag = new JDialog(f);
				JPanel tab = new JPanel();
				JLabel lab = new JLabel("Une sauvegarde a été trouvée pour ce pseudonyme. Voulez-vous reprendre la partie sauvegardée?");
				tab.add(lab);
				b5.addActionListener(this);
				b6.addActionListener(this);
				tab.add(b5);
				tab.add(b6);
				diag.add(tab);
				diag.setSize(800, 100);
				diag.setLocationRelativeTo(null);
				diag.setVisible(true);
			} 
			catch (ClassNotFoundException exp) {
				exp.printStackTrace();
			}
			catch (IOException exp) {
				new Partie(player.getPseudo());
				f.dispose();
			} 
		}
		else if (e.getSource() == b2) {
			new Fenetre1();
			f.dispose();
		}
		else if (e.getSource() == b3) {
			JDialog diag = new JDialog(f);
			JPanel pane = new JPanel(); 
			pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
			JLabel l = new JLabel("Durés de la partie : "+p.getTime().toString());
			l.setAlignmentX(Component.CENTER_ALIGNMENT);
			pane.add(l);
			JLabel l2 = new JLabel("Mots par secondes : "+String.format("%.2f", (float) p.getMots()/p.getTime().getTime()));
			l2.setAlignmentX(Component.CENTER_ALIGNMENT);
			pane.add(l2);
			JLabel l3 = new JLabel("Caractères par secondes : "+String.format("%.2f",(float) p.getCaract()/p.getTime().getTime()));
			l3.setAlignmentX(Component.CENTER_ALIGNMENT);
			pane.add(l3);
			if (p.getMots()!=0) {
				JLabel l4 = new JLabel("Pourcentage de mots correctement tapés : "+Integer.toString((p.getCorrMots()*100)/p.getMots())+"%");
				l4.setAlignmentX(Component.CENTER_ALIGNMENT);
				pane.add(l4);
				}
			else {
				JLabel l4 = new JLabel("Pourcentage de mots correctement tapés : 0");
				l4.setAlignmentX(Component.CENTER_ALIGNMENT);
				pane.add(l4);
				}
			diag.add(pane);
			diag.setTitle("Statistiques");
			diag.setSize(350, 100);
			diag.setLocationRelativeTo(null);
			diag.setVisible(true);
		}
		else if (e.getSource() == b5) {
			new Partie(player2, l, screenWords, time, score2, compteur, etape, add, index, caract, mots, corrMots, text, timer, chrono);
			f.dispose();
		}
		else if (e.getSource() == b6) {
			new Partie(player.getPseudo());
			f.dispose();
		}
		else {f.dispose();}
	}
	
	/**
	 * La méthode Tableau récupère les high scores enregistrés et rajoute le nouveau score et pseudo dans 
	 * les listes avant de les enregistrer à nouveau et de les stocker dans un JTable en vue d'etre affichés.
	 *
	 * @return le JTable contenant les high scores après leur mise à jour
	 */
	public JTable tableau () {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File (System.getProperty("user.dir")+File.separator+"abcd.txt")));
			String line = reader.readLine();
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
		pseudo.add(player.getPseudo());
		sc.add(Integer.toString(score));
		int n = sc.size();
		Integer[] score_int = new Integer[n] ;
        int j = 0;
		for (String i : sc) {
	          score_int[j]=Integer.valueOf(i); 
	          j++;
	        }
		Map<Integer,ArrayList<String>> map = new HashMap<Integer,ArrayList<String>>();
		for (int i=0; i<n; i++) {
			if (map.containsKey(score_int[i])) {
				ArrayList<String> l = map.get(score_int[i]);
				l.add(pseudo.get(i));
				map.put(score_int[i], l);
			}
			else {
				ArrayList<String> l = new ArrayList<String>();
				l.add(pseudo.get(i));
				map.put(score_int[i], l);
			}
		}
        Arrays.sort(score_int, Collections.reverseOrder());
        int index = 0;
		String[][] donnees = new String[n][2];
		int i=0;
		while (i<n) {
			for (String pseu : map.get(score_int[i])) {
				donnees[i][0] = pseu;
				donnees[i][1]=Integer.toString(score_int[i]);
				if (pseu.equals(player.getPseudo()) && score_int[i].equals(score)) {
					index = i;
				}
				i++;
			}
		}		
		String[] titre= {"Pseudonyme","Score"};
		JTable jtable = new JTable(donnees,titre) {
	        private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {return false;};
		};
		jtable.setShowGrid(true);
		jtable.setShowVerticalLines(true);
	    jtable.setGridColor(Color.gray);
	    JTableHeader header = jtable.getTableHeader();
	    header.setBackground(Color.black);
	    header.setForeground(Color.yellow);
	    jtable.setSelectionBackground(Color.red);
	    jtable.setSelectionForeground(Color.white);
	    jtable.setRowSelectionInterval(index, index);
		jtable.setEnabled(false);
		ArrayList<String> res1 = new ArrayList<String>();
		ArrayList<String> res2 = new ArrayList<String>();
		if (n>10) {
			for (int k = 0; k<10; k++) {
				res1.add(donnees[k][0]);
				res2.add(donnees[k][1]);
			}
		}
		else {
			for (int k = 0; k<n; k++) {
				res1.add(donnees[k][0]);
				res2.add(donnees[k][1]);
			}
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
		return(jtable);
	}
}