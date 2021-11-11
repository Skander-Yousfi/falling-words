package projet;

import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre1 implements ActionListener{
	private JFrame f;
	private ImageIcon img = new ImageIcon("img.jpeg");
	private JButton button = new JButton("Jouer");
	private JTextField t = new JTextField("Pseudonyme");
	private JLabel label = new JLabel("Entrez votre pseudonyme pour jouer : ");
	private JLabel icone = new JLabel(img, JLabel.CENTER);
	private JPanel conteneur = new JPanel();
	
	public JFrame getFrame () {
		return(f);
	}
	
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
	public void actionPerformed(ActionEvent evt) {
		  new Partie(t.getText());
		  f.dispose();
	}
}
