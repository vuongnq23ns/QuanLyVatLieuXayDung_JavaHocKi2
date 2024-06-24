package view;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import client.Client;
import controller.Login1Controller;
import server.Server;

public class Login1View extends JFrame {
	private JButton admin;
	private JButton NV;
	private JLabel title;
	private ActionListener al;

	public Login1View () {
		init();
		this.setVisible(true);
	}
	public void init() {

		this.setLayout(null);
		this.setTitle("Login");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(350, 200);
		this.setLocationRelativeTo(null);

		al = new Login1Controller(this);
		admin = new JButton("Admin");
		admin.addActionListener(al);
		NV = new JButton("Nhân Viên");
		NV.addActionListener(al);
		title = new JLabel("Tư cách đăng nhập");

		title.setBounds(120, 10, 200, 50);
		admin.setBounds(40, 70, 100, 40);
		NV.setBounds(200, 70, 100, 40);
		this.add(admin);
		this.add(NV);
		this.add(title);
	}
}
