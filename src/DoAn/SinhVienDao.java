package DoAn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import DoAn.Model.SinhVien;
import DoAn.database.MY_DB;

public class SinhVienDao {
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
	public static int insert(SinhVien a) throws SQLException{		
		
		String sqlInsert ="INSERT INTO `doan`.`sinhvien` (`id`, `ten`, `gioitinh`, `ngaysinh`, `lop`, `email`, `sdt`) VALUES (?, ?, ?, ?, ?, ?, ?)";
		//tao connect
		try {
			Connection conn = MY_DB.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setString(1,a.getID());
			stmt.setString(2, a.getTen());
			stmt.setString(3, a.getGioitih());
			stmt.setString(4, a.getNgaysinh());
			stmt.setString(5, a.getLop());
			stmt.setString(6, a.getEmail());
			stmt.setString(7, a.getSdt());
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
		String strSQL1 = "Delete from `doan`.`sinhvien` where id = '" + maso + "'";
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
	public static int update(SinhVien a) throws SQLException{		
		SinhVien checkSv = find(a.getID());
		if(checkSv == null)
			return 0;
		else {
			try {
				Connection connection = MY_DB.getConnection();
				PreparedStatement ps = null;
				String query = "Update `doan`.`sinhvien` "
						+ "set ten = ?, gioitinh= ?, ngaysinh = ?,lop = ?, email= ?, sdt = ? "
						+ "Where id = ?";
				ps = connection.prepareStatement(query);
				ps.setString(7, a.getID());
				ps.setString(1, a.getTen());
				ps.setString(2, a.getGioitih());
				ps.setString(3, a.getNgaysinh());
				ps.setString(4, a.getLop());
				ps.setString(5, a.getEmail());
				ps.setString(6, a.getSdt());
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
	public static SinhVien find(String maso) throws SQLException{		
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`sinhvien` Where id = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, maso);
			rs = ps.executeQuery();
			if(rs.next()) {
				SinhVien sv = new SinhVien();
				sv.setID(maso);
				sv.setTen(rs.getString("ten"));
				sv.setGioitinh(rs.getString("gioitinh"));
				sv.setNgaysinh(rs.getString("ngaysinh"));
				sv.setLop(rs.getString("lop"));
				sv.setEmail(rs.getString("email"));
				sv.setSdt(rs.getString("sdt"));
				return sv;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<SinhVien> getAll() {
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`sinhvien`";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			List<SinhVien> listsv = new ArrayList<SinhVien>();
			while(rs.next()) {
				SinhVien sv = new SinhVien();
				sv.setID(rs.getString("id"));
				sv.setTen(rs.getString("ten"));
				sv.setGioitinh(rs.getString("gioitinh"));
				sv.setNgaysinh(rs.getString("ngaysinh"));
				sv.setLop(rs.getString("lop"));
				sv.setEmail(rs.getString("email"));
				sv.setSdt(rs.getString("sdt"));
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
	public static JComboBox<String> getAllsinhvien() {
		JComboBox<String> comboBox = null;
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`sinhvien`";
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
