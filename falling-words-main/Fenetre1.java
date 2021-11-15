package projet;

import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * La classe Fenetre1 qui est la fenetre initiale du jeu sur laquelle on choisit notre pseudo.
 */
public class Fenetre1 implements ActionListener{
	
	/** La fenetre f. */
	private JFrame f;
	
	/** L'image représentant le nom de notre jeu. */
	private ImageIcon img = new ImageIcon("img.jpeg");
	
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
	
	/**
	 * Instancie une nouvelle Fenetre1 et l'affiche à l'écran.
	 */
	public Fenetre1() {
		f = new JFrame("Ma fenetre");
		f.setSize(400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		button.addActionListener(this);
		conteneur.add(icone);
		conteneur.add(label);
		conteneur.add(t);
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
		  new Partie(t.getText());
		  f.dispose();
	}
	
	/**
	 * Getter permettant d'accéder à la fenetre JFrame.
	 *
	 * @return f la fenetre
	 */
	public JFrame getFrame () {
		return(f);
	}
	
}
