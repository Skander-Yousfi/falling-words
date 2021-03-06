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
	
	/** Le label contenant le minuteur associé à la partie en cours. */
	private JLabel temps;
	
	/** Le label contenant le score associé à la partie en cours. */
	private JLabel score;
	
	/** Le label contenant le nombre de vie associé au joueur de la partie en cours. */
	private JLabel health;
	
	/** Le panel contenant tous les objets de la fenetre. */
	private JPanel panel;
	
	/** Le panel contenant la zone avec les mots qui tombent. */
	private JPanel pane;
	
	/** Le gridbagconstraint associé à pane. */
	private GridBagConstraints c;
	
	/** L'objet de la classe Time qui contient le nombre de secondes de jeu. */
	private Time time ;
	
	/**
	 * Instancie une nouvelle fenetre de jeu.
	 *
	 * @param l la liste de mots complète
	 * @param p le joueur
	 */
	public FenetreJeu(ArrayList<Words> l, Player p, int score, Color col, int time) {
		super("Falling Words");
		setSize(450, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		panel = new JPanel(); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		this.time = new Time(time);
		temps = new JLabel("Temps de jeu : "+this.time.toString());
		temps.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(temps);
		this.score = new JLabel("Nombre de points : "+score);
		this.score.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(this.score);
		health = new JLabel("Nombre de vies : "+p.getHealth());
		health.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(health);
		pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		Words[][] list = createTab(l);
		for (int i = 0; i<5; i++) {
			for (int j = 0; j<15; j++) {
				JLabel t = new JLabel(list[i][j].getWord());
				c.gridx = list[i][j].getCoord().getX();
				c.gridy = list[i][j].getCoord().getY();
				pane.add(t, c); 
			}
		}
		pane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.red),pane.getBorder()));
		pane.setBackground(col);
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
	 * Getter qui permet d'accéder au temps de jeu.
	 *
	 * @return le temps de jeu
	 */
	public Time getTime() {
		return(time);
	}
	
	/**
	 * Setter qui permet de modifier le temps de jeu.
	 *
	 * @return le temps de jeu
	 */
	public void setTime(Time t) {
		time = t;
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
			for (int j = 0; j<15; j++) {
				JLabel t = new JLabel(list[i][j].getWord());
				c.gridx = list[i][j].getCoord().getX();
				c.gridy = list[i][j].getCoord().getY();
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
		panel.validate();
		panel.repaint();
	}
	
	/**
	 * Actualise la zone dans laquelle le minuteur est affiché.
	 *
	 */
	public void updateTime() {
		time.incr();
		temps.setText("Temps de jeu : "+time.toString());
		panel.validate();
		panel.repaint();
	}
	
	
	/**
	 * Actualise la zone dans laquelle le score de la partie est affiché.
	 *
	 * @param score le score
	 */
	public void updateScore(int score) {
		this.score.setText("Score : "+ score);
		panel.validate();
		panel.repaint();
	}
	
	/**
	 * Actualise la couleur de la zone dans laquelle les mots tombent.
	 *
	 * @param score le score
	 */
	public void updateColor(Color c) {
		pane.setBackground(c);
		pane.validate();
		pane.repaint();
	}
}
