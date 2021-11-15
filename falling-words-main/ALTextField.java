package projet;

import java.awt.event.*;

/**
 * La classe ActionListener de la zone de saisie sur la fenetre de jeu: ALTextField.
 */
public class ALTextField implements KeyListener{
	
	/**  partie en cours*/
	private Partie p;
	
	/** fenetre de jeu associee a la partie */
	private FenetreJeu f;
	
	/**
	 * Instantie une nouvelle zone de saisie
	 *
	 * @param p
	 */
	public ALTextField(Partie p) {
		this.p = p;
		f = p.getFrame();
		f.getTextField().addKeyListener(this);
	}

	/**
	 * methode appelee lorsqu'une touche est enfoncee et relachee par le joueur
	 *
	 * @param e
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * touche enfoncee
	 *
	 * @param e
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
			}
			f.getTextField().setText(null);
		}
	}

	/**
	 * touche relachee
	 *
	 * @param e
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}

	

}
