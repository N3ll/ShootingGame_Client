package game;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScoreList extends JFrame {

	/**
	 * @param args
	 */
	private ArrayList<Player> players;
	// Player me;
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();

	public ScoreList(ArrayList<Player> players) {
		super("TKgame v. 1.0");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(600, 100);
		this.setSize(250, 500);
		this.setResizable(true);
		this.setLayout(new GridLayout(20, 1, 0, 0));
		this.setVisible(true);
		this.players = players;
		draw();
		this.setAlwaysOnTop(true);
	}

	public void draw() {
		for (int j = 0; j < players.size(); j++) {
			JLabel l = new JLabel(players.get(j).ToString());
			l.setSize(200, 50);
			this.add(l);
			labels.add(l);
		}
	}

	public void addLabel(Player p) {
		// players.add(p);
		System.out.println("players size " + players.size()+" "+p);
		JLabel l = new JLabel(p.ToString());
		l.setSize(50, 200);
		this.add(l);
		labels.add(l);
		this.update(getGraphics());
	}

	public void updateScoreOnScreen(Player p) {

		int playerno = players.indexOf(p);
		System.out.println("Update score of " + players.get(playerno));
		labels.get((playerno)).setText(players.get(playerno).ToString());
	}

}
