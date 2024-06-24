package view;

import java.awt.Font;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.json.JSONObject;

import client.Client;
import controller.AddController;

import java.text.ParseException;

import model.VatLieuXayDung;

public class AddView extends JFrame {
	//
	private JLabel welcome;	
	private JLabel idVatLieu; 	private JTextField idVatLieuB;
	private JLabel tenVatLieu;	private JTextField tenVatLieuB;
	private JLabel giaBan;	private JTextField giaBanB;
	private JLabel donViTinh;	private JTextField donViTinhB;
	private JLabel giaMua;	private JTextField giaMuaB;
	private JLabel viTriTrongKho;	private JTextField viTriTrongKhoB;
	private JLabel tonKho;	private JTextField tonKhoB;
	private JButton save;	
	public VatLieuXayDung vlxd = new VatLieuXayDung();
	private ActionListener al;
	private Client client;

	public AddView() {}
	public AddView(Client client4) {
		this.client = client4;
		init();
		this.setVisible(true);
	}
	public void init() {
		//generate
		this.setSize(900, 580);
		this.setLocationRelativeTo(null);
		this.setTitle("Add");
		al = new AddController(this);
		
		//generate
		save = new JButton("Save");
		welcome = new JLabel("Thêm Vật Liệu"); 
		idVatLieu = new JLabel("ID Vật Liệu");			idVatLieuB = new JTextField();
		tenVatLieu = new JLabel("Tên Vật Liệu");			tenVatLieuB = new JTextField();
		giaBan = new JLabel("Giá Bán");			giaBanB = new JTextField();
		donViTinh = new JLabel("Đơn Vị Tính");		donViTinhB = new JTextField();
		giaMua = new JLabel("Giá Mua");	giaMuaB = new JTextField();
		tonKho = new JLabel("Tồn Kho");	tonKhoB = new JTextField();
		viTriTrongKho = new JLabel("Vị Trí");		viTriTrongKhoB = new JTextField();
		
		
		//label                                 
		this.setLayout(null);
		save.setBounds(620, 450, 160, 50);		save.setFont(new Font ("Consolas", Font.BOLD, 18));;
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
		this.add(welcome); 		this.add(save);		save.addActionListener(al);
		this.add(idVatLieu); 	this.add(tenVatLieu); 	this.add(giaBan);
		this.add(donViTinh);	this.add(tonKho);
		this.add(giaMua); 	this.add(viTriTrongKho);
		
		this.add(idVatLieuB); 	this.add(tenVatLieuB); 
		this.add(giaBanB); 	this.add(donViTinhB); 	this.add(viTriTrongKhoB);
		this.add(tonKhoB); 	this.add(giaMuaB);
		
	}
		public VatLieuXayDung I4() throws ParseException {
			 try {
			        String idVatLieu = idVatLieuB.getText();
			        String tenVatLieu = tenVatLieuB.getText();
			        String donViTinh = donViTinhB.getText();
			        String giaBan = giaBanB.getText();
			        String giaMua = giaMuaB.getText();
			        String tonKho = tonKhoB.getText();
			        String viTriTrongKho = viTriTrongKhoB.getText();
			        // tao 1 json de hung tt
			        JSONObject vatLieuJSON = new JSONObject();
			        vatLieuJSON.put("action", "Save");
			        vatLieuJSON.put("idVatLieu", idVatLieu);
			        vatLieuJSON.put("tenVatLieu", tenVatLieu);
			        vatLieuJSON.put("giaBan", giaBan);
			        vatLieuJSON.put("donViTinh", donViTinh);
			        vatLieuJSON.put("giaMua", giaMua);
			        vatLieuJSON.put("tonKho", tonKho);
			        vatLieuJSON.put("viTriTrongKho", viTriTrongKho);
			        client.sendData(vatLieuJSON);
			    } catch (Exception e) {
			        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage());
			    }
			    return null;
		}
}
