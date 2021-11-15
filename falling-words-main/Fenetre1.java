package projet;

import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class Fenetre1.
 */
public class Fenetre1 implements ActionListener{
	
	/** The f. */
	private JFrame f;
	
	/** The img. */
	private ImageIcon img = new ImageIcon("img.jpeg");
	
	/** The button. */
	private JButton button = new JButton("Jouer");
	
	/** The t. */
	private JTextField t = new JTextField("Pseudonyme");
	
	/** The label. */
	private JLabel label = new JLabel("Entrez votre pseudonyme pour jouer : ");
	
	/** The icone. */
	private JLabel icone = new JLabel(img, JLabel.CENTER);
	
	/** The conteneur. */
	private JPanel conteneur = new JPanel();
	
	/**
	 * Gets the frame.
	 *
	 * @return the frame
	 */
	public JFrame getFrame () {
		return(f);
	}
	
	/**
	 * Instantiates a new fenetre 1.
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
	 * Action performed.
	 *
	 * @param evt the evt
	 */
	public void actionPerformed(ActionEvent evt) {
		  new Partie(t.getText());
		  f.dispose();
	}
}
