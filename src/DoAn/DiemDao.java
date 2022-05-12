package DoAn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import DoAn.Model.Diem;
import DoAn.database.MY_DB;

public class DiemDao {
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
	public static int insert(Diem a) throws SQLException{		
		
		String sqlInsert ="INSERT INTO `doan`.`diem` (`idsv`, `idgv`, `idmh`, `diem`) VALUES (?, ?, ?, ?)";
		//tao connect
		try {
			Connection conn = MY_DB.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setString(1,a.getIDSV());
			stmt.setString(2, a.getIdgv());
			stmt.setString(3, a.getIDMH());
			stmt.setString(4, a.getDiem());
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
	
	public static int delete(String sv,String gv,String mh) throws SQLException{
		String strSQL1 = "Delete from `doan`.`diem` where idsv = '" + sv + "' and idgv = '" + gv + "' and idmh = '" + mh + "'";
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
	public static int update(Diem a) throws SQLException{		
		Diem checkdiem = find(a.getIDSV(),a.getIdgv(),a.getIDMH());
		if(checkdiem == null)
			return 0;
		else {
			try {
				Connection connection = MY_DB.getConnection();
				PreparedStatement ps = null;
				String query = "Update `doan`.`diem` "
						+ "set diem= ?"
						+ "Where idsv = ? and idgv= ? and idmh = ?";
				ps = connection.prepareStatement(query);
				ps.setString(1, a.getDiem());
				ps.setString(2, a.getIDSV());
				ps.setString(3, a.getIdgv());
				ps.setString(4, a.getIDMH());
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
	/*public static MonHoc find(String maso) throws SQLException{		
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
	}*/
	public static Diem find(String idsv,String idgv,String idmh) throws SQLException{		
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`diem` Where idsv = ? and idgv = ? and idmh = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, idsv);
			ps.setString(2, idgv);
			ps.setString(3, idmh);
			rs = ps.executeQuery();
			if(rs.next()) {
				Diem sv = new Diem();
				sv.setIDSV(idsv);
				sv.setIdgv(idgv);
				sv.setIDMH(idmh);
				sv.setDiem(rs.getString("diem"));
				return sv;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<Diem> getAll() {
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`diem`";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			List<Diem> listsv = new ArrayList<Diem>();
			while(rs.next()) {
				Diem sv = new Diem();
				sv.setIDSV(rs.getString("idsv"));
				sv.setIdgv(rs.getString("idgv"));
				sv.setIDMH(rs.getString("idmh"));
				sv.setDiem(rs.getString("diem"));
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
	public static JComboBox<String> getAlldiem() {
		JComboBox<String> comboBox = null;
		try {
			ResultSet rs = null;
			Connection connection = MY_DB.getConnection();
			PreparedStatement ps = null;
			String query = "Select * From `doan`.`diem`";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			comboBox = new JComboBox<String>();
			while(rs.next()) {
				comboBox.addItem(rs.getString("idsv"));
			}
			connection.close();
			return comboBox;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
