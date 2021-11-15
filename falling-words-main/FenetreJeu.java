package projet;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.* ;

/**
 * La classe FenetreJeu.
 */
public class FenetreJeu extends JFrame {
	
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La zone de saisie dans laquelle on va taper nos mots. */
	private JTextField text;
	
	/** Le label contenant le score associé à la partie en cours. */
	private JLabel score;
	
	/** La panel contenant le label contenant le score. */
	private JPanel s;
	
	/** Le label contenant le nombre de vie associé au joueur de la partie en cours. */
	private JLabel health;
	
	/** La panel contenant le label contenant le nombre de vies. */
	private JPanel h;
	
	/** Le panel contenant tous les objets de la fenetre. */
	private JPanel panel;
	
	/** Le panel contenant la zone avec les mots qui tombent. */
	private JPanel pane;
	
	/** Le gridbagconstraint associé à pane. */
	private GridBagConstraints c;
	
	/**
	 * Instancie une nouvelle fenetre de jeu.
	 *
	 * @param l la liste de mots complète
	 * @param p le joueur
	 */
	public FenetreJeu(ArrayList<Words> l, Player p) {
		super("Falling Words");
		setSize(450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		panel = new JPanel(); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		s = new JPanel();
		score = new JLabel("Nombre de points : 0");
		score.setMaximumSize(new Dimension(400, 50));
		s.add(score);
		panel.add(s, Component.CENTER_ALIGNMENT);
		h = new JPanel();
		health = new JLabel("Nombre de vies : "+p.getHealth());
		health.setMaximumSize(new Dimension(400, 50));
		h.add(health);
		panel.add(h, Component.CENTER_ALIGNMENT);
		pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		Words[][] list = createTab(l);
		for (int i = 0; i<5; i++) {
			for (Words w : list[i]) {
				JLabel t = new JLabel(w.getWord());
				c.gridx = w.getCoord().getX();
				c.gridy = w.getCoord().getY();
				if (w.getWord()!="                     ") {
					t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),pane.getBorder()));

				}
				else {
					t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray),pane.getBorder()));

				}
				pane.add(t, c); 
			}
		}
		pane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray),pane.getBorder()));
		pane.setBackground(Color.lightGray);
		panel.add(pane, Component.CENTER_ALIGNMENT);
		text = new JTextField("");
		text.setMaximumSize(new Dimension(400, 50));
		panel.add(text, Component.CENTER_ALIGNMENT);
		setContentPane(panel);
	}
	
	/**
	 * Getter qui permet d'accéder à la zone de saisie.
	 *
	 * @return la zone de saisie
	 */
	public JTextField getTextField() {
		return(text);
	}
	
	/**
	 * Créé la liste de mots qui l'on va afficher à l'écran en remplissant tous les espaces vides par "                     " afin de garder la taille du tableau constante.
	 *
	 * @param l la liste de mots que l'on veut afficher à l'écran
	 * @return words[][] la liste de mots à la bonne forme
	 */
	public Words[][] createTab(ArrayList<Words> l){
		Words[][] list = new Words[5][15];
		for (int i=0; i<5; i++) {
			for (int j = 0; j<15; j++) {
				list[i][j] = new Words("                     ", i, j);
			}
		}
		for (Words w : l) {
			list[w.getCoord().getX()][w.getCoord().getY()]=w;
		}
		return(list);
	}
	
	/**
	 * Cette méthode actualise la zone où les mots tombent dans l'écran.
	 *
	 * @param l la liste de mots à afficher
	 */
	public void updateScreen(ArrayList<Words> l) {
		pane.removeAll();
		Words[][] list = createTab(l);
		for (int i = 0; i<5; i++) {
			for (Words w : list[i]) {
				JLabel t = new JLabel(w.getWord());
				c.gridx = w.getCoord().getX();
				c.gridy = w.getCoord().getY();
				if (w.getWord()!="                     ") {
					t.setBackground(Color.gray);
				}
				else {
					t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray),pane.getBorder()));

				}
				pane.add(t, c); 
			}
		}
		pane.validate();
		pane.repaint();
	}
	
	/**
	 * Actualise la zone dans laquelle le nombre de vies est affiché.
	 *
	 * @param p le joueur
	 */
	public void updateHealth(Player p) {
		health.setText("Nombre de vies : "+p.getHealth());
		h.validate();
		h.repaint();
	}
	
	/**
	 * Actualise la zone dans laquelle le score de la partie est affiché.
	 *
	 * @param score le score
	 */
	public void updateScore(int score) {
		this.score.setText("Score : "+ score);
		s.validate();
		s.repaint();
	}
}
