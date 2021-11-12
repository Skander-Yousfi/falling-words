package projet;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.* ;

public class FenetreJeu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField text;
	private JLabel score;
	private JPanel s;
	private JLabel health;
	private JPanel h;
	private JPanel panel;
	private JPanel pane;
	private GridBagConstraints c;
	
	public FenetreJeu(ArrayList<Words> l, Player p) {
		super("Falling Words");
		setSize(450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		panel = new JPanel(); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		s = new JPanel();
		score = new JLabel("Nombre de vies : 0");
		score.setMaximumSize(new Dimension(400, 50));
		s.add(score);
		panel.add(s, Component.CENTER_ALIGNMENT);
		h = new JPanel();
		health = new JLabel("Nombre de vies : "+p.getHealth());
		health.setMaximumSize(new Dimension(400, 50));
		h.add(health);
		panel.add(h, Component.CENTER_ALIGNMENT);
		pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		Words[][] list = createTab(l);
		for (int i = 0; i<5; i++) {
			for (Words w : list[i]) {
				JLabel t = new JLabel(w.getWord());
				c.gridx = w.getCoord().getX();
				c.gridy = w.getCoord().getY();
				if (w.getWord()!="                     ") {
					t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),pane.getBorder()));

				}
				else {
					t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray),pane.getBorder()));

				}
				pane.add(t, c); 
			}
		}
		pane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray),pane.getBorder()));
		pane.setBackground(Color.lightGray);
		panel.add(pane, Component.CENTER_ALIGNMENT);
		text = new JTextField("");
		text.setMaximumSize(new Dimension(400, 50));
		panel.add(text, Component.CENTER_ALIGNMENT);
		setContentPane(panel);
	}
	public JTextField getTextField() {
		return(text);
	}
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
	public void updateScreen(ArrayList<Words> l) {
		pane.removeAll();
		Words[][] list = createTab(l);
		for (int i = 0; i<5; i++) {
			for (Words w : list[i]) {
				JLabel t = new JLabel(w.getWord());
				c.gridx = w.getCoord().getX();
				c.gridy = w.getCoord().getY();
				if (w.getWord()!="                     ") {
//					t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),pane.getBorder()));
					t.setBackground(Color.gray);
				}
				else {
					t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray),pane.getBorder()));

				}
				pane.add(t, c); 
			}
		}
		pane.validate();
		pane.repaint();
	}
	public void updateHealth(Player p) {
		health.setText("Nombre de vies : "+p.getHealth());
		h.validate();
		h.repaint();
	}
	public void updateScore(int score) {
		this.score.setText("Score : "+ score);
		s.validate();
		s.repaint();
	}
}
