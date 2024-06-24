package view;

import java.awt.event.ActionListener;
import javax.swing.*;
import controller.LoginController;

public class LoginView extends JFrame {
	private JButton GV;
	private JButton HS;
	private JLabel title;
	private ActionListener al;

	public LoginView () {
		init();
		this.setVisible(true);
	}
	public void init() {

		this.setLayout(null);
		this.setTitle("Login");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(350, 200);
		this.setLocationRelativeTo(null);

		al = new LoginController(this);
		GV = new JButton("Admin");
		GV.addActionListener(al);
		HS = new JButton("Nhân Viên");
		HS.addActionListener(al);
		title = new JLabel("TƯ CÁCH ĐĂNG NHẬP ");

		title.setBounds(120, 10, 200, 50);
		GV.setBounds(40, 70, 100, 40);
		HS.setBounds(200, 70, 100, 40);
		this.add(GV);
		this.add(HS);
		this.add(title);
	}
}
