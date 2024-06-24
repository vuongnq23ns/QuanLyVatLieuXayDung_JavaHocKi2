package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JOptionPane;
import model.VatLieuXayDung;
import view.AddView;
import view.MainWindowView;

public class AddController implements ActionListener {
    private AddView login4;
    private MainWindowView login3;

    public AddController(AddView login4) {
        this.login4 = login4;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Save".equals(e.getActionCommand())) {
			try {
				VatLieuXayDung vlxd = login4.I4();
				login3.refresh();
				login4.dispose();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }
}