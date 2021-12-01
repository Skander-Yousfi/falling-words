package projet;

import java.awt.event.*;

/**
 * La classe ActionListener de la zone de saisie sur la fenetre de jeu: ALTextField.
 */
public class ALTextField implements KeyListener{
	
	/** La partie en cours*/
	private Partie p;
	
	/** La fenetre de jeu associée à la partie */
	private FenetreJeu f;
	
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

	

}
