package game;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Screen extends JFrame {
	private JLabel[][] labels = new JLabel[20][20];

	private String[][] level;

	public Screen(String[][] level, int posX, int posY) {
		super("TKgame v. 1.0");
		this.level = level;

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(100, 100);
		this.setSize(500, 500);
		this.setResizable(true);
		this.setVisible(true);
		this.setLayout(new GridLayout(20, 20, 0, 0));
		draw(posX, posY);
		this.setAlwaysOnTop(true);
	}

	public void movePlayerOnScreen(int oldX, int oldY, int x, int y,
			String playerDirection) {

		labels[oldX][oldY].setIcon(new ImageIcon("./Image/gulv2.png"));

		if (playerDirection.equals("right")) {
			labels[x][y].setIcon(new ImageIcon("./Image/heltHoejre.png"));
		}
		if (playerDirection.equals("left")) {
			labels[x][y].setIcon(new ImageIcon("./Image/heltVenstre.png"));
		}
		if (playerDirection.equals("up")) {
			labels[x][y].setIcon(new ImageIcon("./Image/heltOp.png"));
		}
		if (playerDirection.equals("down")) {
			labels[x][y].setIcon(new ImageIcon("./Image/heltNed.png"));
		}

	}

	public void appearPlayerOnScreen(int x, int y, String playerDirection) {
		if (playerDirection.equals("right")) {
			labels[x][y].setIcon(new ImageIcon("./Image/heltHoejre.png"));
		}
		if (playerDirection.equals("left")) {
			labels[x][y].setIcon(new ImageIcon("./Image/heltVenstre.png"));
		}
		if (playerDirection.equals("up")) {
			labels[x][y].setIcon(new ImageIcon("./Image/heltOp.png"));
		}
		if (playerDirection.equals("down")) {
			labels[x][y].setIcon(new ImageIcon("./Image/heltNed.png"));
		}
	}

	public void shoot(int x, int y, int xShoot, int yShoot) {
		System.out.println("screen shot");
		if (x == xShoot && y < yShoot) {
			for (int i = y; i < yShoot; i++) {
				if (i == yShoot - 1) {
					labels[x][i].setIcon(new ImageIcon("./Image/ildOp.png"));
					System.out.println("here");
				} else {
					labels[x][i]
							.setIcon(new ImageIcon("./Image/ildLodret.png"));
				}
			}
		} else if (x == xShoot && y > yShoot) {
			for (int i = y; i > yShoot; i--) {
				if (i == yShoot + 1) {
					labels[x][i].setIcon(new ImageIcon("./Image/ildNed.png"));
					System.out.println("here2");
				} else {
					labels[x][i]
							.setIcon(new ImageIcon("./Image/ildLodret.png"));
				}
			}
		} else if (y == yShoot && x < xShoot) {
			for (int i = x; i < xShoot; i++) {
				if (i == xShoot - 1) {
					System.out.println("here3");
					labels[i][y]
							.setIcon(new ImageIcon("./Image/ildVenstre.png"));
				} else {
					labels[i][y]
							.setIcon(new ImageIcon("./Image/ildVandret.png"));
				}
			}
		} else if (y == yShoot && x > xShoot) {
			for (int i = x; i > xShoot; i--) {
				if (i == xShoot + 1) {
					System.out.println("here4");
					labels[i][y]
							.setIcon(new ImageIcon("./Image/ildHoejre.png"));
				} else {
					labels[i][y]
							.setIcon(new ImageIcon("./Image/ildVandret.png"));
				}
			}
		}

	}

	public void draw(int posX, int posY) {
		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < 20; i++) {
				if (level[i][j].equalsIgnoreCase("w")) {
					JLabel l = new JLabel(new ImageIcon("./Image/mur1.png"));
					l.setSize(50, 50);
					this.add(l);
					labels[i][j] = l;
				} else if (level[i][j].equalsIgnoreCase("e")) {
					JLabel l = new JLabel(new ImageIcon("./Image/gulv2.png"));
					l.setSize(50, 50);
					this.add(l);
					labels[i][j] = l;
				}

			}

		}
		labels[posX][posY].setIcon(new ImageIcon("./Image/heltOp.png"));
	}

	public void drawScreen() {
		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < 20; i++) {
				if (level[i][j].equalsIgnoreCase("w")) {
					JLabel l = new JLabel(new ImageIcon("./Image/mur1.png"));
					l.setSize(50, 50);
					this.add(l);
					labels[i][j] = l;
				} else if (level[i][j].equalsIgnoreCase("e")) {
					JLabel l = new JLabel(new ImageIcon("./Image/gulv2.png"));
					l.setSize(50, 50);
					this.add(l);
					labels[i][j] = l;
				}
			}
		}
	}
}
