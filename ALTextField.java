package projet;

import java.awt.event.*;

public class ALTextField implements KeyListener{
	
	private Partie p;
	private FenetreJeu f;
	
	public ALTextField(Partie p) {
		this.p = p;
		f = p.getFrame();
		f.getTextField().addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

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

	@Override
	public void keyReleased(KeyEvent e) {
	}

	

}
