package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.Client;
import view.Login2View;
import view.MainWindowView;

public class Login2Controller implements ActionListener {
    private Login2View login2;
    private Client client;
    private MainWindowView login3;

    public Login2Controller(Login2View login2, Client cLient, MainWindowView login3) {
        this.login2 = login2;
        this.client = cLient;
        this.login3 = login3;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Login".equals(e.getActionCommand())) {
            	boolean log = login2.checkLogin();
            	if (log == true) {
                    JOptionPane.showMessageDialog(null, "Login Successfullyy!");
                    System.out.println(5);
                     login3 = new MainWindowView(client);
                     System.out.println(6);
                     login3.setVisible(true);
//                     login2.dispose();
                     login2.setVisible(false);
            	} else {
            		login2.dispose();
            		JOptionPane.showMessageDialog(null, "Login Failed !", "Login", JOptionPane.PLAIN_MESSAGE);
            		new Login2View();
            	}
        	}
        }
    }
