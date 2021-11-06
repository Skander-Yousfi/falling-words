package projet;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.* ;

public class FenetreJeu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField text;
	private JPanel panel;
	private JPanel pane;
	private GridBagConstraints c;
	
	public FenetreJeu(ArrayList<Words> l) {
		super("Falling Words");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		panel = new JPanel(); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		Words[][] list = createTab(l);
		for (int i = 0; i<5; i++) {
			for (Words w : list[i]) {
				JLabel t = new JLabel(w.getWord());
				c.gridx = w.getCoord().getX();
				c.gridy = w.getCoord().getY();
//				t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),pane.getBorder()));
				pane.add(t, c); 
			}
		}
		panel.add(pane, Component.CENTER_ALIGNMENT);
//		pane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),pane.getBorder()));
		text = new JTextField("Ecrivez ici !");
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
				list[i][j] = new Words("                  ", i, j);
			}
		}
		for (Words w : l) {
			list[w.getCoord().getY()][w.getCoord().getX()]=w;
		}
		return(list);
	}
	public void update(ArrayList<Words> l) {
		pane.removeAll();
		Words[][] list = createTab(l);
		for (int i = 0; i<5; i++) {
			for (Words w : list[i]) {
				JLabel t = new JLabel(w.getWord());
				c.gridx = w.getCoord().getX();
				c.gridy = w.getCoord().getY();
//				t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red),pane.getBorder()));
				pane.add(t, c); 
			}
		}
		pane.validate();
		pane.repaint();
	}
}
