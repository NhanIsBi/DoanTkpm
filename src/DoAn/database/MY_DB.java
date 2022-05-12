package DoAn.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MY_DB {
	static String dbURL = "jdbc:mysql://localhost:3306/sys";
    static String username = "root";
    static String password = "12345";
    public static Connection getConnection() throws ClassNotFoundException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(dbURL, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*public static Connection myConnection() throws SQLException {
		//load driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String url ="jdbc:mysql://localhost:3306/sys";
		String user ="nhan";
		String pw ="12345";
		
		Connection conn = DriverManager.getConnection(url, user, pw);
		return conn;
	}*/
}
