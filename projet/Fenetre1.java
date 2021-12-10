package projet;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.JTableHeader;

/**
 * La classe Fenetre1 qui est la fenetre initiale du jeu sur laquelle on choisit notre pseudo.
 */
public class Fenetre1 implements ActionListener{
	
	/** La fenetre f. */
	private JFrame f;
	
	/** L'image représentant le nom de notre jeu. */
	private ImageIcon img = new ImageIcon("img.jpeg");
	
	/** Le bouton "Règles du jeu" qui permet d'ouvrir un fenetre pop-up qui contient les règles du jeu. */
	private JButton r = new JButton("Règles du jeu");
	
	/** Le bouton "High Scores" qui permet d'ouvrir un fenetre pop-up qui contient les high scores. */
	private JButton hs = new JButton("High Scores");
	
	/** Le bouton "Jouer" qui permet de lancer une partie. */
	private JButton button = new JButton("Jouer");
	
	/** Le bouton qui apparait sur la fenetre pop up lorsqu'une sauvegarde est détectée. */
	private JButton b1 = new JButton("Reprendre la partie sauvegardée");
	
	/** Le bouton qui apparait sur la fenetre pop up lorsqu'une sauvegarde est détectée. */
	private JButton b2 = new JButton("Commencer une nouvelle partie");
	
	/** La zone de saisie t dans laquelle on peut entrer notre pseudonyme. */
	private JTextField t = new JTextField("Pseudonyme");
	
	/** Le label "Entrez votre pseudonyme pour jouer : " à côté de la zone de saisie. */
	private JLabel label = new JLabel("Entrez votre pseudonyme pour jouer : ");
	
	/** Le Jlabel qui contient l'image. */
	private JLabel icone = new JLabel(img, JLabel.CENTER);
	
	/** Le panel qui contient tous les objets. */
	private JPanel conteneur = new JPanel();
	
	/** La liste qui contiendra tous les pseudos pour les high scores. */
	private ArrayList<String> pseudo;
	
	/** La liste qui contiendra tous les high scores. */
	private ArrayList<String> sc;
	
	/** Le Joueur associé à la partie éventuellemnt sauvegardée. */
	private Player p ;
	
	/** Le Joueur associé à la partie éventuellemnt sauvegardée. */
    private ArrayList<Words> l;
    
    /** Le Joueur associé à la partie éventuellemnt sauvegardée. */
    private ArrayList<Words> screenWords; 
    
    /** Le Joueur associé à la partie éventuellemnt sauvegardée. */
    private Time time;
    
    /** Une valeur associée à la partie éventuellemnt sauvegardée. */
    private int score;
    
    /** Une valeur associée à la partie éventuellemnt sauvegardée. */
    private int compteur;
    
    /** Une valeur associée à la partie éventuellemnt sauvegardée. */
    private int etape;
    
    /** Une valeur associée à la partie éventuellemnt sauvegardée. */
    private int add;
    
    /** Une valeur associée à la partie éventuellemnt sauvegardée. */
    private int index; 
    
    /** Une valeur associée à la partie éventuellemnt sauvegardée. */
    private int caract; 
    
    /** Une valeur associée à la partie éventuellemnt sauvegardée. */
    private int mots;
    
    /** Une valeur associée à la partie éventuellemnt sauvegardée. */
    private int corrMots; 
    
    /** Une chaine de caractères associée à la partie éventuellemnt sauvegardée. */
    private String text;
    
    /** Un Timer associée à la partie éventuellemnt sauvegardée. */
    private Timer timer; 
    
    /** Un Timer associée à la partie éventuellemnt sauvegardée. */
    private Timer chrono;
	
	/**
	 * Instancie une nouvelle Fenetre1 et l'affiche à l'écran.
	 */
	public Fenetre1() {
		f = new JFrame("Ma fenetre");
		f.setSize(400, 275);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		r.addActionListener(this);
		hs.addActionListener(this);
		button.addActionListener(this);
		conteneur.add(icone);
		conteneur.add(label);
		conteneur.add(t);
		conteneur.add(r);
		conteneur.add(hs);
		conteneur.add(button);
		f.setContentPane(conteneur);
		f.setVisible(true);
		}
	
	/**
	 * La méthode Action performed donne l'action à réaliser lorsque le bouton associé à l'action listener est pressé.
	 *
	 * @param evt l'événement
	 */
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == button) {
			try {
				FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+File.separator+t.getText()+"-save.txt"));
				ObjectInputStream ois = new ObjectInputStream(fis);
			    p = (Player) ois.readObject() ;
			    l = (ArrayList<Words>) ois.readObject(); 
			    screenWords = (ArrayList<Words>) ois.readObject(); 
			    time = (Time) ois.readObject(); 
			    score = (Integer) ois.readObject();
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
				b1.addActionListener(this);
				b2.addActionListener(this);
				tab.add(b1);
				tab.add(b2);
				diag.add(tab);
				diag.setSize(800, 100);
				diag.setLocationRelativeTo(null);
				diag.setVisible(true);
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				new Partie(t.getText());
				f.dispose();
			} 
		}
		if (evt.getSource() == r) {
			JDialog diag = new JDialog(f);
			JPanel pane = new JPanel(); 
			pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
			JLabel l = new JLabel("Regles du jeu");
			l.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20));
			l.setAlignmentX(Component.CENTER_ALIGNMENT);
			pane.add(l);
			JTextPane t = new JTextPane();
			t.setOpaque(false);  
			t.setFocusable(false);
			t.setText("Ce jeu est un jeu de rapidité.\nDes mots vont défiler devant vos yeux et "
					+ "le but du jeu est de les taper correctement (vous pouvez taper n'importe quel mot affiché à l'écran) "
					+ "et de les valider en appuyant sur "
					+ "la touche espace avant que ceux-ci touchent le bas de l'écran.\nLorsque vous tapez correctement "
					+ "un mot, celui-ci vous rapporte autant de points qu'il a de caractères.\nLorsque "
					+ "les mots touchent le bas de l'écran ils disparaissent à tout jamais et vous perdez "
					+ "une de vos trois vies.\nLorsque vous n'avez plus de vie la partie est terminée!\nVotre but "
					+ "est donc de survivre le plus longtemps possible en amassant le plus de points.\nA vous de jouer ;)");
			t.setAlignmentX(Component.CENTER_ALIGNMENT);
			pane.add(t);
			pane.add(t, Component.CENTER_ALIGNMENT);
			diag.add(pane);
			diag.setSize(450, 250);
			diag.setLocationRelativeTo(null);
			diag.setTitle("Règles du jeu");
			diag.setVisible(true);
		}
		if (evt.getSource() == hs) {
			JDialog diag = new JDialog(f);
			JPanel tab = new JPanel();
			JLabel lab = new JLabel("TOP DES 10 MEILLEURS SCORES");
			lab.setFont(new Font("Serif", Font.ITALIC, 20));
			tab.add(lab);
			JTable tableau = tableau();
			JScrollPane scrollPane = new JScrollPane(tableau);
			scrollPane.setColumnHeaderView(tableau.getTableHeader());
			scrollPane.setPreferredSize(new Dimension(450, 180));
			tab.add(scrollPane);
			diag.add(tab);
			diag.setSize(500, 250);
			diag.setLocationRelativeTo(null);
			diag.setVisible(true);
		}
		if (evt.getSource() == b1) {
	//		File file = new File(System.getProperty("user.dir")+File.separator+t.getText()+"-save.txt");
	//		file.delete();
			new Partie(p, l, screenWords, time, score, compteur, etape, add, index, caract, mots, corrMots, text, timer, chrono);
			f.dispose();
		}
		if (evt.getSource() == b2) {
			new Partie(t.getText());
			f.dispose();
		}
	}
	
	/**
	 * Getter permettant d'accéder à la fenetre JFrame.
	 *
	 * @return f la fenetre
	 */
	public JFrame getFrame () {
		return(f);
	}
	
	/**
	 * Methode permettant de récupérer les high scores.
	 *
	 * @return jtable le tableau que l'on va afficher
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
		String[][] donnees = new String[sc.size()][2];
		if (sc.size()>0) {
			int i=0;
			while (i<sc.size()) {
				donnees[i][0]=pseudo.get(i);
				donnees[i][1]=sc.get(i);
				i++;
			}
		}
		String[] titre= {"Pseudonyme","Score"};
		JTable jtable = new JTable(donnees,titre) {
	        	private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
                		return false;               
        		};
		};
		jtable.setShowGrid(true);
		jtable.setShowVerticalLines(true);
	    jtable.setGridColor(Color.gray);
		jtable.setEnabled(false);
	    JTableHeader header = jtable.getTableHeader();
	    header.setBackground(Color.black);
	    header.setForeground(Color.yellow);
		return(jtable);
	}
}