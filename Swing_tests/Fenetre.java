package Swing_tests;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	private ImageIcon img = new ImageIcon("img.jpeg");
	private JButton button = new JButton("Jouer");
	private JTextField t = new JTextField("Pseudonyme");
	private JLabel label = new JLabel("Entrez votre pseudonyme pour jouer : ");
	private JLabel icone = new JLabel(img, JLabel.CENTER);
	private JPanel conteneur = new JPanel();
	
	public Fenetre() {
		JFrame f = new JFrame("Ma fenetre");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		conteneur.add(icone);
		conteneur.add(label);
		conteneur.add(t);
		conteneur.add(button);
		setContentPane(conteneur);
	}
}
