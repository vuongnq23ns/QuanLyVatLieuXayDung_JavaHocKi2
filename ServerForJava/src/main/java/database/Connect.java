package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	public static Connection getConnection() throws SQLException {
		Connection c = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/quanlydoan";
			String user = "root";
			String matkhau = "123123";
			Connection connection = DriverManager.getConnection(url, user, matkhau);
			return connection;
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
		return c;
	}
	public static void closeConnection(Connection c) {
		try {
			if(c!= null) {
				c.close();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}