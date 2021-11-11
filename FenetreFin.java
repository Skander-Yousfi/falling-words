package projet;

import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;

public class FenetreFin implements ActionListener{
	
	private JFrame f;
	private JPanel pane;
	private JPanel tab;
	private JButton b1;
	private JButton b2;
	private JLabel lab;
	private Player p;
	private int score;
	
	public FenetreFin(Player p, int score) {
		this.p = p;
		this.score = score;
		f = new JFrame();
		f.setSize(400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		pane = new JPanel(); 
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		tab = new JPanel();
		lab = new JLabel("Score de "+p.getPseudo()+" : "+score);
		tab.add(lab);
		b1 = new JButton("Rejouer");
		b1.addActionListener(this);
		b2 = new JButton("Quitter");
		b2.addActionListener(this);
		pane.add(tab, Component.CENTER_ALIGNMENT);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(b1);
		pane.add(b2);
		f.setContentPane(pane);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			new Partie(p.getPseudo());
		}
		if (e.getSource() == b2) {
			f.dispose();
		}
	}
}
