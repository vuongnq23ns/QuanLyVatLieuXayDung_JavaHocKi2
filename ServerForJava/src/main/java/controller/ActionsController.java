package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.Client;
import view.MainWindowView;
import view.AddView;
import view.EditView;

public class ActionsController implements ActionListener {
    private MainWindowView login3;
    private AddView login4;
    private Client client;

    public ActionsController(MainWindowView login3, Client client3) {
        this.login3 = login3;
        this.client = client3;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Add".equals(command)) {
            new AddView(client);
        } else if ("Edit".equals(command)) {
            openEditWindow();
        } else if ("Delete".equals(command)) {
            login3.delete();
        } else if ("Refresh".equals(command)) {
        	login3.refresh();
        } else if ("Exit".equals(command)) {
        	login3.dispose();
        }

    }

    public void openEditWindow() {
        int rowIndex = login3.getTable().getSelectedRow();
        if (rowIndex != -1) {
            Object[] rowData = new Object[login3.getModel().getColumnCount()];
            for (int i = 0; i < login3.getModel().getColumnCount(); i++) {
                rowData[i] = login3.getModel().getValueAt(rowIndex, i);
            }
            EditView editWindow = new EditView(client);
            editWindow.setEditData(rowData);
            editWindow.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(login3, "Choose A Row To Edit !");
        }
    }
}