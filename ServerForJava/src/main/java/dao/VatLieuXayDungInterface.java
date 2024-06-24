package dao;

import java.util.ArrayList;

import org.json.JSONArray;

import model.VatLieuXayDung;

public interface VatLieuXayDungInterface <T>{
	
//	public void Save (VatLieuXayDung t);
	
	public void Save (String jsonData);
	
//	public void Delete (VatLieuXayDung t);
	
	public void Delete (String jsonData);
	
//	public ArrayList<VatLieuXayDung> SelectAll ();
	
	public JSONArray SelectAll();

	public void Edit(String jsonData);
	
//	public void Edit (VatLieuXayDung t);
}
