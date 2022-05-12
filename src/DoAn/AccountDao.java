package DoAn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import DoAn.Model.Account;
import DoAn.database.MY_DB;

public class AccountDao {
	public static ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			Connection conn = MY_DB.getConnection();
			Statement stat = conn.createStatement();
			rs =stat.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	public static int insert(Account a) throws SQLException{		
		
		String sqlInsert ="INSERT INTO `doan`.`account` (`username`, `pass`, `vitri`) VALUES (?, ?, ?)";
		//tao connect
		try {
			Connection conn = MY_DB.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setString(1,a.getUsername());
			stmt.setString(2, a.getPass());
			stmt.setString(3, a.getVitri());
			int kq = stmt.executeUpdate();
			conn.close();
			return kq;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int delete(String maso) throws SQLException{		
		String strSQL1 = "Delete from `doan`.`account` where username = '" + maso + "'";
		//tao connect
		try {
			Connection conn = MY_DB.getConnection();
			PreparedStatement stmt = conn.prepareStatement(strSQL1);
			int kq = stmt.executeUpdate();
			conn.close();
			return kq;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	public static int update(Account a) throws SQLException{		
		Account check = find(a.getUsername());
		if(check == null)
			return 0;
		else {
			try {
				Connection connection = MY_DB.getConnection();
				PreparedStatement ps = null;
				String query = "Update `doan`.`account` "
						+ "set pass = ?, vitri= ? "
						+ "Where username = ?";
				ps = connection.prepareStatement(query);
				ps.setString(3, a.getUsername());
				ps.setString(1, a.getPass());
				ps.setString(2, a.getVitri());
				int kq = ps.executeUpdate();
				connection.close();
				return kq;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return 0;
		
	}
	public static Account find(String maso) throws SQLException{		
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`account` Where username = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, maso);
			rs = ps.executeQuery();
			if(rs.next()) {
				Account sv = new Account();
				sv.setUsername(maso);
				sv.setPass(rs.getString("pass"));
				sv.setVitri(rs.getString("vitri"));
				return sv;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<Account> getAll() {
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`account`";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			List<Account> list = new ArrayList<Account>();
			while(rs.next()) {
				Account sv = new Account();
				sv.setUsername(rs.getString("username"));
				sv.setPass(rs.getString("pass"));
				sv.setVitri(rs.getString("vitri"));
				list.add(sv);
			}
			connection.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static JComboBox<String> getAllAccount() {
		JComboBox<String> comboBox = null;
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`account`";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			comboBox = new JComboBox<String>();
			while(rs.next()) {
				comboBox.addItem(rs.getString("username"));
			}
			connection.close();
			return comboBox;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static boolean acExit(String username,String pass,String vitri) throws SQLException{		
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`account` Where username = ? and pass = ? and vitri= ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, pass);
			ps.setString(3, vitri);
			rs = ps.executeQuery();
			if(rs.next()) {
				Account sv = new Account();
				sv.setUsername(username);
				sv.setPass(pass);
				sv.setVitri(vitri);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/*public static boolean tkExists(String email,String pass) {
		MY_DB pool = ConnectionPool.getInstance();
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT email FROM \"User\" " + "WHERE \"email\" = ? and \"firstname\" = ? ";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}*/
}
