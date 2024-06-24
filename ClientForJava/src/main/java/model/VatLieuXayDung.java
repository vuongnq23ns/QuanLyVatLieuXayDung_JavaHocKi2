package model;

import java.sql.Date;
import javax.swing.JLabel;

public class VatLieuXayDung {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idVatLieu;
	private String tenVatLieu;
	private String donViTinh;
	private String giaBan;
	private String giaMua;
	private String tonKho;
	private String viTriTrongKho;
	
	
	public VatLieuXayDung() {
	}
	
	public VatLieuXayDung(String idVatLieu, String tenVatLieu, String donViTinh, String giaBan,
			String giaMua, String tonKho, String viTriTrongKho) {
		super();
		this.idVatLieu = idVatLieu;
		this.tenVatLieu = tenVatLieu;
		this.donViTinh = donViTinh;
		this.giaBan = giaBan;
		this.giaMua = giaMua;
		this.tonKho = tonKho;
		this.viTriTrongKho = viTriTrongKho;
	}

	public String getIdVatLieu() {
		return idVatLieu;
	}

	public void setIdVatLieu(String idVatLieu) {
		this.idVatLieu = idVatLieu;
	}

	public String getTenVatLieu() {
		return tenVatLieu;
	}

	public void setTenVatLieu(String tenVatLieu) {
		this.tenVatLieu = tenVatLieu;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public String getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(String giaBan) {
		this.giaBan = giaBan;
	}

	public String getGiaMua() {
		return giaMua;
	}

	public void setGiaMua(String giaMua) {
		this.giaMua = giaMua;
	}

	public String getTonKho() {
		return tonKho;
	}

	public void setTonKho(String tonKho) {
		this.tonKho = tonKho;
	}

	public String getViTriTrongKho() {
		return viTriTrongKho;
	}

	public void setViTriTrongKho(String viTriTrongKho) {
		this.viTriTrongKho = viTriTrongKho;
	}
	
}