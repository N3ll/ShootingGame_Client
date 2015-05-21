package client;

import game.Player;
import game.ScoreList;
import game.gameplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {
	public static PrintWriter outToServer;

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		final int PORT = 7913;
		final String HOST = "localhost";
		Socket clientSocket = new Socket(HOST, PORT);
		Player me = new Player();

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));

		outToServer = new PrintWriter(clientSocket.getOutputStream(), true);

		System.out.println("Write your username ");
		String in = inFromUser.readLine();
		me.setName(in);

		Thread thr = new Thread(new ListenToServer(inFromServer, me));
		thr.start();

		outToServer.println("NAME" + in);

	}

	public static PrintWriter getPrintWriter() {
		return outToServer;
	}

	private static class ListenToServer implements Runnable {
		BufferedReader inFromServer;
		ArrayList<Player> players;
		ScoreList scoreList;
		Player me;
		gameplayer g;

		public ListenToServer(BufferedReader inFromServer, Player me) {
			this.inFromServer = inFromServer;
			this.players = new ArrayList<Player>();
			this.me = me;
		}

		@Override
		public void run() {
			while (true) {
				String fromServer = null;
				try {
					fromServer = inFromServer.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (fromServer != null) {

					System.out.println("From server:" + fromServer);

					String[] temp = fromServer.substring(4).split("/");
					Player tempPl;
					String[] playerInfo;
					ArrayList<Player> playersFromServer = new ArrayList<Player>();

					for (int i = 0; i < temp.length; i++) {
						System.out.println("Before split " + temp[i]);
						playerInfo = null;
						playerInfo = temp[i].split(" ");
						tempPl = new Player();

						tempPl.setName(playerInfo[0]);
						tempPl.setXpos(Integer.parseInt(playerInfo[1]));
						tempPl.setYpos(Integer.parseInt(playerInfo[2]));
						tempPl.setDirection(playerInfo[3]);
						tempPl.setPoint(Integer.parseInt(playerInfo[4]));
						tempPl.setxShoot(Integer.parseInt(playerInfo[5]));
						tempPl.setyShoot(Integer.parseInt(playerInfo[6]));

						System.out.println("temp" + tempPl);
						playersFromServer.add(tempPl);
					}

					if (fromServer.startsWith("MAKE")) {

						Player toBeRemoved = null;
						for (Player playerFromServer : playersFromServer) {
							if (playerFromServer.getName().equals(me.getName())) {

								if (g == null) {
									me.setXpos(playerFromServer.getXpos());
									me.setYpos(playerFromServer.getYpos());
									me.setDirection(playerFromServer
											.getDirection());
									me.setPoint(playerFromServer.getPoint());

									players.add(this.me);
									scoreList = new ScoreList(players);
									g = new gameplayer(me, scoreList);
									scoreList.setVisible(true);
									System.out.println("me no "
											+ players.size());
									toBeRemoved = playerFromServer;
									break;
								}
							}
						}

						if (toBeRemoved != null) {
							playersFromServer.remove(toBeRemoved);
						}

						for (Player playerFromServer : playersFromServer) {
							int count = 0;
							for (Player pl : players) {
								System.out.println("check for name with " + pl);
								if (!playerFromServer.getName().equals(
										pl.getName())) {
									count++;
								}
							}

							System.out.println("count " + count);
							if (count == players.size()) {
								System.out.println("contains no "
										+ players.size());
								players.add(playerFromServer);
								for (Player player : players) {
									System.out.println("Before lable" + player);
								}
								scoreList.addLabel(playerFromServer);
							}
						}

						for (Player player : players) {
							System.out.println("Before updating " + player);
						}

						for (Player pl : players) {
							System.out.println("update no " + players.size());
							System.out.println("to update " + pl);

							scoreList.updateScoreOnScreen(pl);
							g.playerAppears(pl.getXpos(), pl.getYpos(),
									pl.getDirection());
						}
					}

					else if (fromServer.startsWith("MOVE")) {
						System.out.println("In the move");

						for (Player pl : playersFromServer) {
							System.out.println("Player from server " + pl);
							for (Player pl2 : players) {
								System.out
										.println("Player from players " + pl2);
								if (pl.getName().equals(pl2.getName())) {

									g.PlayerMoved(pl2.getXpos(), pl2.getYpos(),
											pl.getXpos(), pl.getYpos(),
											pl.getDirection());
									pl2.setXpos(pl.getXpos());
									pl2.setYpos(pl.getYpos());
									pl2.setDirection(pl.getDirection());
									pl2.setPoint(pl.getPoint());
									scoreList.updateScoreOnScreen(pl2);

								}
							}
						}

					} else if (fromServer.startsWith("FIRE")) {
						System.out.println("In the fire");

						for (Player pl : playersFromServer) {
							System.out.println("Player from server " + pl);

							for (Player pl2 : players) {
								System.out
										.println("Player from players " + pl2);
								if (pl.getName().equals(pl2.getName())) {

									if (pl.getxShoot() != pl2.getxShoot()
											|| pl.getyShoot() != pl2
													.getyShoot()) {

										g.shoot(pl2.getXpos(), pl2.getYpos(),
												pl.getxShoot(), pl.getyShoot());

										pl2.setxShoot(pl.getxShoot());
										pl2.setyShoot(pl.getyShoot());

										System.out
												.println("from server x shot "
														+ pl.getxShoot()
														+ " y "
														+ pl.getyShoot());
									}

									g.PlayerMoved(pl2.getXpos(), pl2.getYpos(),
											pl.getXpos(), pl.getYpos(),
											pl.getDirection());
									pl2.setXpos(pl.getXpos());
									pl2.setYpos(pl.getYpos());
									pl2.setDirection(pl.getDirection());
									pl2.setPoint(pl.getPoint());

									scoreList.updateScoreOnScreen(pl2);
								}
							}

						}
					}

				}

			}
		}
	}
}
