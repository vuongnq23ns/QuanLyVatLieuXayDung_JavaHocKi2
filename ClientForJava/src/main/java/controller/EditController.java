package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import model.VatLieuXayDung;
import view.EditView;
import view.MainWindowView;

public class EditController implements ActionListener {
    private EditView login4;
    private MainWindowView login3;

    public EditController(EditView login4_EditWindow, MainWindowView login3argu) {
        this.login4 = login4_EditWindow;
        this.login3 = login3argu;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Update".equals(e.getActionCommand())) {
			try {
				VatLieuXayDung vlxd = login4.I4Update();
//				login3.Show();
				login3.refresh();
				login4.dispose();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }
}