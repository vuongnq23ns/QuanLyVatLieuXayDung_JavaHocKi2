package dao;

import model.*;
import util.*;
import view.MainWindowView;
import org.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.Date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.hibernate.Session;
import java.util.List;

import dao.VatLieuXayDungInterface;
import database.Connect;

public class VatLieuXayDungDAO implements VatLieuXayDungInterface<VatLieuXayDung> {
	@Override
	public void Delete(String jsonData) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			JSONObject vatLieuJSON = new JSONObject(jsonData);
			String IdVatLieu = vatLieuJSON.getString("idVatLieu");
			transaction = session.beginTransaction();
			VatLieuXayDung vlxd = session.get(VatLieuXayDung.class, IdVatLieu);
				session.delete(vlxd);
				transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void Save(String jsonData) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			JSONObject vatLieuJSON = new JSONObject(jsonData);
			// tạo 1 vlxd để hứng dữ liệu
			VatLieuXayDung vlxd = new VatLieuXayDung();
			vlxd.setIdVatLieu(vatLieuJSON.getString("idVatLieu"));
			vlxd.setTenVatLieu(vatLieuJSON.getString("tenVatLieu"));
			vlxd.setDonViTinh(vatLieuJSON.getString("donViTinh"));
			vlxd.setGiaBan(vatLieuJSON.getString("giaBan"));
			vlxd.setGiaMua(vatLieuJSON.getString("giaMua"));
			vlxd.setTonKho(vatLieuJSON.getString("tonKho"));
			vlxd.setViTriTrongKho(vatLieuJSON.getString("viTriTrongKho"));
			transaction = session.beginTransaction();
			session.save(vlxd);
			transaction.commit();
			System.out.println("VatLieuXayDung added successfully!");
		} catch (Exception e) {
			System.out.println("Error saving VatLieuXayDung: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void Edit(String jsonData) {
		Transaction transaction = null;
		Session session = null;
		try {
			// Parse JSON từ chuỗi nhận được
			JSONObject vatLieuJSON = new JSONObject(jsonData);
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			// Lấy ID từ JSON và tìm kiếm VatLieuXayDung tương ứng
			String IdVatLieu = vatLieuJSON.getString("idVatLieu");
			VatLieuXayDung vlxd = session.get(VatLieuXayDung.class, IdVatLieu);
			// Cập nhật thông tin từ JSON
			vlxd.setTenVatLieu(vatLieuJSON.getString("tenVatLieu"));
			vlxd.setDonViTinh(vatLieuJSON.getString("donViTinh"));
			vlxd.setGiaBan(vatLieuJSON.getString("giaBan"));
			vlxd.setGiaMua(vatLieuJSON.getString("giaMua"));
			vlxd.setTonKho(vatLieuJSON.getString("tonKho"));
			vlxd.setViTriTrongKho(vatLieuJSON.getString("viTriTrongKho"));
			session.update(vlxd);
			transaction.commit();
			System.out.println("VatLieuXayDung updated successfully!");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Error updating VatLieuXayDung: " + e.getMessage());
			e.printStackTrace();
		}
	}


	public JSONArray SelectAll() {
		JSONArray jsonArray = new JSONArray();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// lay tt tu db
			List<VatLieuXayDung> vlxdList = session.createQuery("from VatLieuXayDung", VatLieuXayDung.class).list();
			for (VatLieuXayDung vlxd : vlxdList) {
				JSONObject vatLieuJSON = new JSONObject();
				vatLieuJSON.put("idVatLieu", vlxd.getIdVatLieu());
				vatLieuJSON.put("tenVatLieu", vlxd.getTenVatLieu());
				vatLieuJSON.put("donViTinh", vlxd.getDonViTinh());
				vatLieuJSON.put("giaBan", vlxd.getGiaBan());
				vatLieuJSON.put("giaMua", vlxd.getGiaMua());
				vatLieuJSON.put("tonKho", vlxd.getTonKho());
				vatLieuJSON.put("viTriTrongKho", vlxd.getViTriTrongKho());
				jsonArray.put(vatLieuJSON);
			}
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

		public JSONArray selectAllAccounts() {
			Transaction transaction = null;
			List<Account> accounts = null;
			JSONArray jsArray = new JSONArray();
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				accounts = session.createQuery("from Account", Account.class).list();
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			if (accounts != null && !accounts.isEmpty()) {
				for (Account account : accounts) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("username", account.getUsername());
					jsonObj.put("password", account.getPassword());
					jsArray.put(jsonObj);
				}
			} else {
				System.out.println("No accounts found in the database.");
			}
			return jsArray;
		}
}
