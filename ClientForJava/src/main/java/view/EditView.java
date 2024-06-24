package view;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import org.json.JSONObject;
import client.Client;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import controller.AddController;
import controller.EditController;

import java.text.DateFormat;
import java.text.ParseException;

import model.VatLieuXayDung;

public class EditView extends JFrame {
	//
	private JLabel welcome;
	private JLabel welcome2;
	private JLabel idVatLieu;
	private JTextField idVatLieuB;
	private JLabel tenVatLieu;
	private JTextField tenVatLieuB;
	private JLabel giaBan;
	private JTextField giaBanB;
	private JLabel donViTinh;
	private JTextField donViTinhB;
	private JLabel giaMua;
	private JTextField giaMuaB;
	private JLabel viTriTrongKho;
	private JTextField viTriTrongKhoB;
	private JLabel tonKho;
	private JTextField tonKhoB;
	private JButton update;
	public VatLieuXayDung vlxd = new VatLieuXayDung();
	private MainWindowView login3;
	private ActionListener al;
	private Client client;

	public EditView() {
	}

	public EditView(Client client4) {
		this.client = client4;
		init();
		this.setVisible(true);
	}

	public void init() {
		//generate
		this.setSize(900, 580);
		this.setLocationRelativeTo(null);
		this.setTitle("Edit");
		al = new EditController(this, login3);
		
		//generate
		update = new JButton("Update");
		welcome = new JLabel("Cập Nhật Dữ Liệu"); 
		idVatLieu = new JLabel("Vật Liệu ID");			idVatLieuB = new JTextField();
		tenVatLieu = new JLabel("Tên Vật Liệu");			tenVatLieuB = new JTextField();
		giaBan = new JLabel("Giá Bán");			giaBanB = new JTextField();
		donViTinh = new JLabel("Đơn vị tính");		donViTinhB = new JTextField();
		giaMua = new JLabel("Giá Mua");	giaMuaB = new JTextField();
		tonKho = new JLabel("Tồn Kho");	tonKhoB = new JTextField();
		viTriTrongKho = new JLabel("Vị Trí");		viTriTrongKhoB = new JTextField();
		
		//label                                 
		this.setLayout(null);
		update.setBounds(620, 450, 160, 50);	update.setFont(new Font ("Consolas", Font.BOLD, 18));;
		welcome.setBounds(320, 5, 300, 50); 	welcome.setFont(new Font ("Consolas", Font.BOLD, 27));
		tenVatLieu.setBounds(50, 70, 200, 50);		idVatLieu.setFont(new Font ("Consolas", Font.BOLD, 18));
												tenVatLieu.setFont(new Font ("Consolas", Font.BOLD, 18));
		idVatLieu.setBounds(50, 170, 200, 50);	
		giaBan.setBounds(50, 220, 200, 50);		donViTinh.setFont(new Font ("Consolas", Font.BOLD, 18));
		donViTinh.setBounds(450, 220, 200, 50);		giaBan.setFont(new Font ("Consolas", Font.BOLD, 18));	
		giaMua.setBounds(50, 370, 200, 50);		giaMua.setFont(new Font ("Consolas", Font.BOLD, 18));
		tonKho.setBounds(450, 370, 200, 50);	tonKho.setFont(new Font ("Consolas", Font.BOLD, 18));
		viTriTrongKho.setBounds(50, 420, 200, 50);	viTriTrongKho.setFont(new Font ("Consolas", Font.BOLD, 18));
		
		//textfield
		tenVatLieuB.setBounds(200, 73, 200, 40);		donViTinhB.setBounds(600, 223, 200, 40);
		giaMuaB.setBounds(200, 373, 200, 40);
		idVatLieuB.setBounds(200, 173, 200, 40);		tonKhoB.setBounds(600, 373, 200, 40);
		viTriTrongKhoB.setBounds(200, 423, 200, 40);
		giaBanB.setBounds(200, 223, 200, 40); 	
		
		//add
		this.add(welcome); 		this.add(update);	this.update.addActionListener(al);
		this.add(idVatLieu); 	this.add(tenVatLieu); 	 		this.add(giaBan);
		this.add(donViTinh);	this.add(tonKho);
		this.add(giaMua); 	this.add(viTriTrongKho);
		
		this.add(idVatLieuB); 	this.add(tenVatLieuB); 	
		this.add(giaBanB); 	this.add(donViTinhB); 	this.add(viTriTrongKhoB);
		this.add(tonKhoB); 	this.add(giaMuaB);
		
		
	}

	public VatLieuXayDung I4Update() throws ParseException {
	    try {
	        String idVatLieu = idVatLieuB.getText();
	        String tenVatLieu = tenVatLieuB.getText();
	        String giaBan = giaBanB.getText();
	        String donViTinh = donViTinhB.getText();
	        String giaMua = giaMuaB.getText();
	        String tonKho = tonKhoB.getText();
	        String viTriTrongKho = viTriTrongKhoB.getText();
	        // tao 1 json de hung tt
	        JSONObject vatLieuJSON = new JSONObject();
	        vatLieuJSON.put("action", "Edit");
	        vatLieuJSON.put("idVatLieu", idVatLieu);
	        vatLieuJSON.put("tenVatLieu", tenVatLieu);
	        vatLieuJSON.put("giaBan", giaBan);
	        vatLieuJSON.put("donViTinh", donViTinh);
	        vatLieuJSON.put("giaMua", giaMua);
	        vatLieuJSON.put("tonKho", tonKho);
	        vatLieuJSON.put("viTriTrongKho", viTriTrongKho);
	        // ep json nay thanh 1 kieu string
	        client.sendData(vatLieuJSON);	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
	    }
	    return null;
	}

	 public void setEditData(Object[] rowData) {
	        
	        idVatLieuB.setText((String) rowData[0]);
	        tenVatLieuB.setText((String) rowData[1]);
	        giaBanB.setText((String) rowData[2]);
	        donViTinhB.setText((String) rowData[3]);
	        giaMuaB.setText((String) rowData[4]);
	        tonKhoB.setText((String) rowData[5]);
	        viTriTrongKhoB.setText((String) rowData[6]);
	    }
}
