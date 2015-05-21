package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;

import client.Client;

public class KeyClass implements KeyListener {
	private gameplayer g;
	private PrintWriter toServer;

	public KeyClass(gameplayer g) {
		this.g = g;
		toServer = Client.getPrintWriter();
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_UP) {
			toServer.println("MOVEup");
			System.out.println(toServer == null);
			System.out.println("Up sent to server");
		}
		if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
			toServer.println("MOVEdown");
			System.out.println("Down sent to server");
		}
		if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
			toServer.println("MOVEleft");
			System.out.println("Left sent to server");
		}
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
			toServer.println("MOVEright");
			System.out.println("Right sent to server");
		}
		if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			toServer.println("FIRE");
			System.out.println("Fire sent to server");
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}