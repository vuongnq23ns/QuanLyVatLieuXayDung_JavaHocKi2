package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.VatLieuXayDungDAO;

public class ClientHandler extends Thread {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private VatLieuXayDungDAO vlxdDAO;

	public ClientHandler(Socket socket, VatLieuXayDungDAO vlxdDAO) {
		this.clientSocket = socket;
		this.vlxdDAO = vlxdDAO;
		try {
			InputStream i = clientSocket.getInputStream();
			OutputStream o = clientSocket.getOutputStream();
			InputStreamReader inn = new InputStreamReader(i);
			this.in = new BufferedReader(inn);
			this.out = new PrintWriter(o, true);
		} catch (IOException e) {
			System.out.println("Can't open IO stream in server: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			String data;
			System.out.println(2);
			while ((data = in.readLine()) != null) {
				System.out.println(data);
				if (isValidJSON(data)) {
					JSONObject jsonObject = new JSONObject(data);
					String action = jsonObject.getString("action");
					switch (action) {
						case "Edit":
							vlxdDAO.Edit(data);
							break;
						case "Save":
							vlxdDAO.Save(data);
							System.out.println("data : " +data);
							break;
						case "Delete":
							vlxdDAO.Delete(data);
							break;
						case "Show":
							JSONArray jsArray = vlxdDAO.SelectAll();
							out.println(jsArray.toString());
							break;
						case "Check":
							JSONArray jsAccount = vlxdDAO.selectAllAccounts();
							out.println(jsAccount.toString());
							break;
						default:
							break;
					}
				} else {
					System.out.println("Waiting for valid JSON data...");
				}
			}
		} catch (Exception e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private boolean isValidJSON(String data) {
		try {
			new JSONObject(data);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
