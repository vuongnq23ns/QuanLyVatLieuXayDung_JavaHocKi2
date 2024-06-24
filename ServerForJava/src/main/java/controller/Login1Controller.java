package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Login1View;
import view.Login2View;

public class Login1Controller implements ActionListener {
	private Login1View login1;
	private Login2View login2;
	public static int count;

	public Login1Controller(Login1View login1) {
		this.login1 = login1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("Admin".equals(command)) {
			count = 1;
			login1.dispose();
			new Login2View();
		} else if ("Nhân Viên".equals(command)) {
			count = 0;
			login1.dispose();
			login2 = new Login2View();
		}
	}

	public static int getCount() {
		return count;
	}
}
