package projet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == button) {
			new Partie(t.getText());
			f.dispose();
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
			t.setText("Ce jeu est un jeu de rapidité.\nDes mots vont défiler evant vos yeux et le but du jeu est de les taper correctement et de les valider en appuyant sur la touche espace avant que ceux-ci touchent le bas de l'écran.\nLorsque vous tapez correctement un mot, celui-ci vous rapporte autant de points qu'il a de caractères.\nLorsque les mots touchent le bas de l'écran ils disparaissent à tout jamais et vous perdez une de vos trois vies.\nLorsque vous n'avez plus de vie la partie est terminée!\nVotre but est donc de survivre le plus longtemps possible en amassant le plus de points.\nA vous de jouer ;)");
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
	    JTableHeader header = jtable.getTableHeader();
	    header.setBackground(Color.black);
	    header.setForeground(Color.yellow);
		return(jtable);
	}
}