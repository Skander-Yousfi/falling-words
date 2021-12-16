package projet;

import java.awt.Component;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.*;

/**
 * La classe ActionListener de la zone de saisie sur la fenetre de jeu: ALTextField.
 */
public class ALTextField implements KeyListener, ActionListener{
	
	/** La partie en cours*/
	private Partie p;
	
	/** La fenêtre de jeu associée à la partie */
	private FenetreJeu f;
	
	/** Le bouton "Sauvegarder la partie et quitter" */
	private JButton b1;
	
	/** Le bouton "Reprendre" qui entraine la reprise de la partie. */
	private JButton b2;

	/** Le bouton "Oui" qui entraine le remplacement d'une partie déjà sauvegardée. */
	private JButton b3= new JButton("Sauvegarder et quitter");
	
	/** Le bouton "Non". On quitte la partie sans sauvegarder. */
	private JButton b4 = new JButton("Ne pas sauvegarder et quitter");
	
	/** La fenêtre pop up qui apparait lorsque le jeu est en pause */
	private JDialog diag;
	
	/** La fenêtre pop up qui apparait lorsqu'on souhaite sauvegarder une partie déjà sauvegardée. */
	private JDialog diag2;
	
	/**
	 * Instantie un nouveau Action Listener tout en le liant à la zone de saisie de la fenêtre.
	 *
	 * @param p la partie
	 */
	public ALTextField(Partie p) {
		this.p = p;
		f = p.getFrame();
		f.getTextField().addKeyListener(this);
	}

	/**
	 * Méthode appelée lorsqu'une touche est enfoncée puis relachée par le joueur.
	 *
	 * @param e l'événement
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Méthode appelée lorsqu'une touche est enfoncée par le joueur.
	 *
	 * @param e l'événement
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()== 32 ) {
			Words w = p.verif_word(f.getTextField().getText().replace(" ", ""));
			if(w != null) {
				p.addToScore(w.getPoints());
				p.removeWord(w);
				p.getFrame().updateScreen(p.getScreenWords());
				p.getFrame().updateScore(p.getScore());
				p.incrCorrMots();
			}
			f.getTextField().setText(null);
			p.incrMot();
		}
		else if (e.getKeyCode()== 10 ) {
			p.getTimer().stop();
			p.getChrono().stop();
			diag = new JDialog(f);
			JPanel tab = new JPanel();
			tab.setLayout(new BoxLayout(tab, BoxLayout.PAGE_AXIS));
			JLabel lab = new JLabel("JEU MIS EN PAUSE");
			lab.setAlignmentX(Component.CENTER_ALIGNMENT);
			tab.add(lab);
			b1 = new JButton("Sauvegarder la partie et quitter");
			b1.addActionListener(this);
			b1.setAlignmentX(Component.CENTER_ALIGNMENT);
			tab.add(b1);
			b2 = new JButton("Reprendre");
			b2.addActionListener(this);
			b2.setAlignmentX(Component.CENTER_ALIGNMENT);
			tab.add(b2);
			diag.add(tab);
			diag.setSize(500, 250);
			diag.setLocationRelativeTo(null);
			diag.setVisible(true);
		}
		else {p.incrCaract();}
	}

	/**
	 * Méthode appelée lorsqu'une touche est relachée par le joueur.
	 *
	 * @param e l'événement
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	/**
	 * Méthode appelée lorsqu'un des boutons est pressé.
	 *
	 * @param e l'événement
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			File fi = new File(System.getProperty("user.dir")+File.separator+p.getPlayer().getPseudo()+"-save.txt"); 
			if (fi.exists()) {
				diag2 = new JDialog(f);
				JPanel tab = new JPanel();
				JLabel lab = new JLabel("Une sauvegarde existe déjà pour ce Pseudonyme, voulez-vous la remplacer ?");
				tab.add(lab);
				b3.addActionListener(this);
				b4.addActionListener(this);
				tab.add(b3);
				tab.add(b4);
				diag2.add(tab);
				diag2.setSize(800, 100);
				diag2.setLocationRelativeTo(null);
				diag2.setVisible(true);
			}
			else {
				save();
				f.dispose();
			}
		}
		if (e.getSource() == b2) {
			p.getTimer().start();
			p.getChrono().start();
			diag.dispose();
		}
		if (e.getSource()==b3) {
			save();
			f.dispose();
		}
		if(e.getSource()==b4) { 
			f.dispose();
		}
	}

	/**
	 * Méthode appelée pour sauvegarder la partie.
	 */
	public void save () {
		try {
			File file = new File(System.getProperty("user.dir")+File.separator+p.getPlayer().getPseudo()+"-save.txt");
	        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
	        oos.writeObject(p.getPlayer());
	        oos.writeObject(p.getWords());
	        oos.writeObject(p.getScreenWords());
	        oos.writeObject(p.getTime());
	        oos.writeObject(p.getScore());
	        oos.writeObject(p.getCompteur());
	        oos.writeObject(p.getEtape());
	        oos.writeObject(p.getAdd());
	        oos.writeObject(p.getIndex());
	        oos.writeObject(p.getCaract());
	        oos.writeObject(p.getMots());
	        oos.writeObject(p.getCorrMots());
	        oos.writeObject(p.getFrame().getTextField().getText());
	        oos.writeObject(p.getTimer());
	        oos.writeObject(p.getChrono());
	        oos.close();
		}
		catch (IOException exep) {
			exep.printStackTrace();
		}
	}
}
