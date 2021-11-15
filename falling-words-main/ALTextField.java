package projet;

import java.awt.event.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ALTextField.
 */
public class ALTextField implements KeyListener{
	
	/** The p. */
	private Partie p;
	
	/** The f. */
	private FenetreJeu f;
	
	/**
	 * Instantiates a new AL text field.
	 *
	 * @param p the p
	 */
	public ALTextField(Partie p) {
		this.p = p;
		f = p.getFrame();
		f.getTextField().addKeyListener(this);
	}

	/**
	 * Key typed.
	 *
	 * @param e the e
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Key pressed.
	 *
	 * @param e the e
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
	 * Key released.
	 *
	 * @param e the e
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}

	

}
