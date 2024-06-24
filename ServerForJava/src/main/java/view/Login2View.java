package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

import client.Client;
import controller.Login2Controller;

public class Login2View extends JFrame {
	private JLabel user;
	private JLabel password;
	private JTextField enterUser;
	private JPasswordField enterPassword;
	private JButton login;
	private ActionListener al;
	private static Client client = new Client();
	private MainWindowView login3;

	public Login2View() {
		init();
		this.setVisible(true);
		client.connectServer();
	}

	public void init() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
		this.setTitle("Login");

		al = new Login2Controller(this, client, login3);
		user = new JLabel("User");
		password = new JLabel("Password");
		enterPassword = new JPasswordField();
		enterUser = new JTextField();
		login = new JButton("Login");
		login.addActionListener(al);

		this.setLayout(null);
		this.add(enterPassword);
		this.add(enterUser);
		this.add(password);
		this.add(user);
		this.add(password);
		this.add(login);
		user.setBounds(65, 10, 100, 50);
		user.setFont(new Font("Consolas", Font.PLAIN, 16));
		password.setBounds(54, 90, 80, 50);
		password.setFont(new Font("Consolas", Font.PLAIN, 16));
		enterUser.setBounds(180, 10, 200, 50);
		enterPassword.setBounds(180, 90, 200, 50);
		login.setBounds(150, 170, 100, 50);
	}

	public boolean checkLogin() {
		JSONObject user = new JSONObject();
		user.put("action", "Check");
		client.sendData(user);
		JSONArray users = client.getData();
		System.out.println(users);
		String usernameInput = enterUser.getText();
		String passwordInput = new String(enterPassword.getPassword());

		for (int i = 0; i < users.length(); i++) {
			JSONObject userCheck = users.getJSONObject(i);
			String username = userCheck.getString("username");
			String password = userCheck.getString("password");
			if (usernameInput.equals(username) && passwordInput.equals(password)) {
				return true;
			}
		}
		return false;
	}
}
