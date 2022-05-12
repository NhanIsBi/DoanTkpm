package DoAn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import DoAn.Model.MonHoc;
import DoAn.database.MY_DB;

public class MonHocDao {
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
	public static int insert(MonHoc a) throws SQLException{		
		
		String sqlInsert ="INSERT INTO `doan`.`monhoc` (`id`, `ten`, `idgv`) VALUES (?, ?, ?)";
		//tao connect
		try {
			Connection conn = MY_DB.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setString(1,a.getID());
			stmt.setString(2, a.getTen());
			stmt.setString(3, a.getIdgv());
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
		String strSQL1 = "Delete from `doan`.`monhoc` where id = '" + maso + "'";
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
	public static int update(MonHoc a) throws SQLException{		
		MonHoc checkSv = find(a.getID());
		if(checkSv == null)
			return 0;
		else {
			try {
				Connection connection = MY_DB.getConnection();
				PreparedStatement ps = null;
				String query = "Update `doan`.`monhoc` "
						+ "set ten = ?, idgv= ?"
						+ "Where id = ?";
				ps = connection.prepareStatement(query);
				ps.setString(3, a.getID());
				ps.setString(1, a.getTen());
				ps.setString(2, a.getIdgv());
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
	public static MonHoc find(String maso) throws SQLException{		
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`monhoc` Where id = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, maso);
			rs = ps.executeQuery();
			if(rs.next()) {
				MonHoc sv = new MonHoc();
				sv.setID(maso);
				sv.setTen(rs.getString("ten"));
				sv.setIdgv(rs.getString("idgv"));
				return sv;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<MonHoc> getAll() {
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`monhoc`";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			List<MonHoc> listsv = new ArrayList<MonHoc>();
			while(rs.next()) {
				MonHoc sv = new MonHoc();
				sv.setID(rs.getString("id"));
				sv.setTen(rs.getString("ten"));
				sv.setIdgv(rs.getString("idgv"));
				listsv.add(sv);
			}
			connection.close();
			return listsv;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static JComboBox<String> getAlltinchi() {
		JComboBox<String> comboBox = null;
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`monhoc`";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			comboBox = new JComboBox<String>();
			while(rs.next()) {
				comboBox.addItem(rs.getString("id"));
			}
			connection.close();
			return comboBox;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
