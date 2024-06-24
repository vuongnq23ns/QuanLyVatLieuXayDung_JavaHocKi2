package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import client.Client;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import controller.LoginController;
import controller.ActionsController;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MainWindowView extends JFrame {
	private JLabel welcome;
	public static DefaultTableModel model;
	private JButton add, edit, delete, refresh, exit;
	private static JTable table;
	private ActionListener al;
	private static int editableRowIndex = -1;
	private static Client client;
	private static JSONArray jsonArray;

	public MainWindowView() {
	}

	public MainWindowView(Client client3) {
		try {
			this.client = client3;
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("loi~" + e);
		}
		System.out.println(1);
		init();
		System.out.println(2);
		this.setVisible(true);
		System.out.println(3);
		Show();
		System.out.println(4);
	}

	public void init() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1400, 800);
		this.setLocationRelativeTo(null);
		this.setTitle("Project Management");
		al = new ActionsController(this, client);

		//??
		model = new TableEdit();
		table = new JTable(model);
		// Tiêu đề của các cột
		String[] columnNames = {"ID Vật Liệu ", "Tên Vật Liệu","Đơn vị tính", "Giá bán", "Giá mua", "Tồn kho", "Vị trí"};
		model.setColumnIdentifiers(columnNames);
		//cangiua
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		//                   id, ten,sdt,giaBan,tenpj,lan,tien trinh
		int[] columnWidths = {80, 120, 100, 140, 250, 70, 80}; // Độ rộng mong muốn của từng cột
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}
		table.setPreferredScrollableViewportSize(new Dimension(
				table.getPreferredSize().width,
				table.getRowCount() * table.getRowHeight()
		));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 100, 1390, 580);
		this.add(scrollPane);

		//generate
		welcome = new JLabel("DANH SÁCH VẬT LIỆU");
		exit = new JButton("Exit");
		add = new JButton("Add");
		edit = new JButton("Edit");
		delete = new JButton("Delete");
		refresh = new JButton("Refresh");

		//layout
		this.setLayout(null);
		welcome.setBounds(570, 10, 400, 100);
		welcome.setFont(new Font("Consolas", Font.BOLD, 28));
		add.setBounds(1050, 695, 100, 50);
		edit.setBounds(1160, 695, 100, 50);
		delete.setBounds(1270, 695, 100, 50);
		refresh.setBounds(940, 695, 100, 50);
		
		//add
		this.add(add);
		add.addActionListener(al);
		this.add(edit);
		edit.addActionListener(al);
		this.add(refresh);
		refresh.addActionListener(al);
		this.add(welcome);
		exit.addActionListener(al);
		delete.addActionListener(al);

		if (LoginController.getCount() == 1) {
			this.add(delete);
			exit.setBounds(610, 695, 100, 50);
			this.add(exit);
		} else if (LoginController.getCount() == 0) {
			exit.setBounds(1270, 695, 100, 50);
			this.add(exit);
		}
	}

	public static int getEditableRowIndex() {
		return editableRowIndex;
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public static void Show() throws JSONException {
		try {
			model.setRowCount(0);
			JSONObject get = new JSONObject();
			get.put("action", "Show");
			client.sendData(get);
			jsonArray = new JSONArray(client.getData());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject vlxdJson = jsonArray.getJSONObject(i);
				Object[] rowData = new Object[]{
						vlxdJson.getString("idVatLieu"),
						vlxdJson.getString("tenVatLieu"),
						vlxdJson.getString("donViTinh"),
						vlxdJson.getString("giaBan"),
						vlxdJson.getString("giaMua"),
						vlxdJson.getString("tonKho"),
						vlxdJson.getString("viTriTrongKho")
				};
				model.addRow(rowData);
				System.out.println(vlxdJson.toString());
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.print("Loi~" + e);
		}
	}



	public static void refresh() {
		model.setRowCount(0);
		Timer timer = new Timer(150, e -> {
			JSONObject get = new JSONObject();
			get.put("action", "Show");
			client.sendData(get);
			JSONArray jsonArray = client.getData();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject vlxdJson = jsonArray.getJSONObject(i);
				model.addRow(new Object[]{
						vlxdJson.getString("idVatLieu"),
						vlxdJson.getString("tenVatLieu"),
						vlxdJson.getString("donViTinh"),
						vlxdJson.getString("giaBan"),
						vlxdJson.getString("giaMua"),
						vlxdJson.getString("tonKho"),
						vlxdJson.getString("viTriTrongKho")
				});
			}
			table.repaint();
			table.revalidate();
		});
		timer.setRepeats(false);
		timer.start();
	}

	public void delete() {
		int n = table.getSelectedRow();
		if (n >= 0) {
			String studentID = (String) model.getValueAt(n, 0);

			JSONObject vatLieuJSON = new JSONObject();
			vatLieuJSON.put("action", "Delete");
			vatLieuJSON.put("idVatLieu", studentID);
			model.removeRow(n);
			client.sendData(vatLieuJSON);
			JOptionPane.showMessageDialog(this, "Xóa Thành Công !");
		} else {
			JOptionPane.showMessageDialog(this, "Chọn một dòng để xóa !");
		}
	}

	public void addDataFromUserInput(Object[] userInputData) {
		model.addRow(userInputData);
		editableRowIndex = model.getRowCount() - 1;
	}

	public void viTriTrongKhoUserInput(Object[] userInputData) {
		addDataFromUserInput(userInputData);
		((TableEdit) table.getModel()).setEditableRow(editableRowIndex);
	}

}