package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JOptionPane;
import dao.VatLieuXayDungDAO;
import model.VatLieuXayDung;
import view.AddView;
import view.MainWindowView;

public class AddController implements ActionListener {
    private AddView login4;
    private VatLieuXayDungDAO buttonDao;
    private MainWindowView login3;

    public AddController(AddView login4) {
        this.login4 = login4;
        this.buttonDao = new VatLieuXayDungDAO();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Save".equals(e.getActionCommand())) {
			try {
				VatLieuXayDung vlxd = login4.I4();
				login3.refresh();
				login4.dispose();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
        }
    }
}