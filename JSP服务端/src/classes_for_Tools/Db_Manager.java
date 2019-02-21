package classes_for_Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db_Manager {
	private static String url = "jdbc:mysql://localhost:3306/hs?useUnicode=true&characterEncoding=utf-8";
	private static String username = "root";
	private static String pwd = "1234";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, pwd);
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		return null;
	}

	public static void closeJDBC(ResultSet rst, Statement st, Connection con) {

		if (rst != null) {

			try {
				rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (st != null)
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				st = null;
			}

		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				con = null;
			}

	}

}
