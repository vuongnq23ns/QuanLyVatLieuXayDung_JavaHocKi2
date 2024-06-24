package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

public class Client {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	public Client() {
	}

	public void connectServer() {
		try {
			this.socket = new Socket("localhost", 7331);
			this.out = new PrintWriter(socket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Connected to server.");
			out.println("connected");
		} catch (IOException e) {
			System.out.println("Connection to server failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void sendData(JSONObject vatLieuJSON) {
		out.println(vatLieuJSON.toString());
	}

	public JSONArray getData() {
		JSONArray response = new JSONArray();
		try {
			String data = in.readLine();
			System.out.println("Data received from server: " + data);
			response = new JSONArray(data);
		} catch (Exception e) {
			System.out.println("Failed to receive data from server: " + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	public void closeConnection() {
		try {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
			if (socket != null) {
				socket.close();
				System.out.println("Connection closed.");
			}
		} catch (IOException e) {
			System.out.println("Failed to close connection: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
