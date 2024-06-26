package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
import controller.Login1Controller;
import controller.ActionsController;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MainWindowView extends JFrame {
	private JLabel welcome;
	public static DefaultTableModel model;
	private JButton add, edit,  refresh, exit,delete
//	, sort, find
	;
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
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(1400, 800);
	    setLocationRelativeTo(null);
	    setTitle("Quản Lý Vật Liệu Xây Dựng");
	    setLayout(null);  // Set layout to null for absolute positioning

	    al = new ActionsController(this, client);

	    model = new TableEdit();
	    table = new JTable(model);
	    String[] columnNames = {" ID Vật Liệu", "Tên Vật Liệu", "Đơn vị tính", "Giá bán", "Giá mua", "Tồn kho", "Vị trí"};
	    model.setColumnIdentifiers(columnNames);
	    table.setRowHeight(110);
	    // Customize table header
	    

	    TableEdit.CustomRenderer customRenderer = ((TableEdit) model).new CustomRenderer();
	    for (int i = 0; i < table.getColumnCount(); i++) {
	        table.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
	    }
	    
	    
	    JTableHeader header = table.getTableHeader();
	    header.setDefaultRenderer(new DefaultTableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                setHorizontalAlignment(SwingConstants.CENTER);  
	            setFont(new Font("Arial", Font.BOLD, 30));  
	            setBackground(new Color(45, 45, 45));  
	            setForeground(new Color(254,247,96,255));  
	            return this;
	        }
	    });
	    // header 
	    Dimension dimension = header.getPreferredSize();
	    dimension.height = 60;  
	    header.setPreferredSize(dimension);

	    
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(0, 100, 1390, 580);
	    add(scrollPane);

	    welcome = new JLabel("DANH SÁCH VẬT LIỆU");
	    welcome.setOpaque(true);
	    welcome.setBackground(new Color(31,40,57,255));
	    welcome.setForeground(new Color(160,75,96,255));
	    welcome.setFont(new Font("Helvetica", Font.BOLD, 28));
	    welcome.setBounds(0, 0, 1400, 100);
	    welcome.setHorizontalAlignment(SwingConstants.CENTER);
	    add(welcome);

	    JPanel actionPanel = new JPanel(null);
	    actionPanel.setBounds(0, 680, 1400, 120);
	    actionPanel.setBackground(new Color(45, 45, 45));

	    // buttons
	    add = new JButton("Add");
	    add.setBounds(310, 35, 150, 40);
	    add.addActionListener(al);
	    add.setBackground(Color.DARK_GRAY);  
        add.setForeground(Color.WHITE);  
	    actionPanel.add(add);

	    edit = new JButton("Edit");
	    edit.setBounds(470, 35, 150, 40);
	    edit.addActionListener(al);
	    edit.setBackground(Color.DARK_GRAY); 
        edit.setForeground(Color.WHITE);  
	    actionPanel.add(edit);

	    delete = new JButton("Delete");
	    delete.setBounds(630, 35, 150, 40);
	    delete.addActionListener(al);
	    delete.setBackground(Color.DARK_GRAY);  
        delete.setForeground(Color.WHITE);  
	    actionPanel.add(delete);

	    refresh = new JButton("Refresh");
	    refresh.setBounds(790, 35, 150, 40);
	    refresh.addActionListener(al);
	    refresh.setBackground(Color.DARK_GRAY);  
        refresh.setForeground(Color.WHITE);  
	    actionPanel.add(refresh);

	    exit = new JButton("Exit");
	    exit.setBounds(950, 35, 150, 40);
	    exit.addActionListener(al);
	    exit.setBackground(Color.DARK_GRAY);  
        exit.setForeground(Color.WHITE); 
	    actionPanel.add(exit);
	    
	    add(actionPanel); 

	    setVisible(true); 
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
			System.out.print("error" + e);
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