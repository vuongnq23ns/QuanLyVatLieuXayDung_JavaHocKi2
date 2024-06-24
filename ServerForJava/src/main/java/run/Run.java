package run;

import javax.swing.*;

import client.Client;
import database.Connect;
import server.Server;
import view.*;

public class Run {
	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			String src = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
			UIManager.setLookAndFeel(src);
			Thread serverThread = new Thread(() -> {
				try {
					Client client = new Client();
					new Server(client);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			serverThread.start();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SwingUtilities.invokeLater(() -> new Login1View());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}