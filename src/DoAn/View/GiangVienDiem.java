package DoAn.View;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DoAn.DiemDao;
import DoAn.GiangVienDao;
import DoAn.MonHocDao;
import DoAn.SinhVienDao;
import DoAn.Model.Diem;
import DoAn.Model.GiangVien;
import DoAn.Model.MonHoc;
import DoAn.Model.SinhVien;
import builder.DiemBuilder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GiangVienDiem extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_sv;
	private JTextField textField_gv;
	private JTextField textField_mh;
	private JTextField textField_diem;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiangVienDiem frame = new GiangVienDiem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GiangVienDiem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLp_2_1 = new JLabel("ID sinh viên");
		lblLp_2_1.setBounds(10, 12, 106, 21);
		contentPane.add(lblLp_2_1);
		
		JComboBox<String> comboBox_sv = new JComboBox<String>();
		comboBox_sv.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String ID = (String) comboBox_sv.getSelectedItem();
				SinhVien sv;
				try {
					sv = SinhVienDao.find(ID);
					textField_sv.setText(sv.getTen());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		LoadDialogSV(comboBox_sv);
		comboBox_sv.setSelectedIndex(0);
		comboBox_sv.setBounds(126, 11, 212, 22);
		contentPane.add(comboBox_sv);
		
		JLabel lblTnGingVin_1 = new JLabel("Tên sinh viên");
		lblTnGingVin_1.setBounds(348, 12, 106, 21);
		contentPane.add(lblTnGingVin_1);
		
		textField_sv = new JTextField();
		textField_sv.setColumns(10);
		textField_sv.setBounds(464, 12, 212, 20);
		contentPane.add(textField_sv);
		
		JLabel lblLp_2_2 = new JLabel("ID Giảng viên");
		lblLp_2_2.setBounds(10, 45, 106, 21);
		contentPane.add(lblLp_2_2);
		
		textField_gv = new JTextField();
		textField_gv.setColumns(10);
		textField_gv.setBounds(126, 44, 212, 20);
		contentPane.add(textField_gv);
		
		JLabel lblLp_2 = new JLabel("ID Môn học");
		lblLp_2.setBounds(10, 78, 106, 21);
		contentPane.add(lblLp_2);
		
		JComboBox<String> comboBox_mh = new JComboBox<String>();
		comboBox_mh.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String ID = (String) comboBox_mh.getSelectedItem();
				MonHoc mh;
				try {
					mh = MonHocDao.find(ID);
					textField_mh.setText(mh.getTen());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		LoadDialogMH(comboBox_mh);
		comboBox_mh.setSelectedIndex(0);
		comboBox_mh.setBounds(126, 77, 212, 22);
		contentPane.add(comboBox_mh);
		
		JLabel lblTnGingVin = new JLabel("Tên môn học");
		lblTnGingVin.setBounds(348, 78, 106, 21);
		contentPane.add(lblTnGingVin);
		
		textField_mh = new JTextField();
		textField_mh.setColumns(10);
		textField_mh.setBounds(464, 78, 212, 20);
		contentPane.add(textField_mh);
		
		JLabel lblim = new JLabel("Điểm");
		lblim.setBounds(10, 110, 106, 21);
		contentPane.add(lblim);
		
		textField_diem = new JTextField();
		textField_diem.setColumns(10);
		textField_diem.setBounds(126, 110, 212, 20);
		contentPane.add(textField_diem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 142, 667, 339);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		LoadTable(table);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Diem sv = DiemDao.find(comboBox_sv.getSelectedItem().toString(),textField_gv.getText(),comboBox_mh.getSelectedItem().toString());			
					if(sv != null) {
						textField_diem.setText(sv.getDiem());
					}
					else {
						JOptionPane.showMessageDialog(contentPane,"Không tìm thấy thông tin", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(contentPane,"Some thing when wrong !!!\n"+e1.getMessage(), "Error", 
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnTim.setBackground(new Color(169, 169, 169));
		btnTim.setBounds(192, 492, 89, 23);
		contentPane.add(btnTim);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String idsv = (String) comboBox_sv.getSelectedItem();
					String idgv = textField_gv.getText();
					String idmh = (String) comboBox_mh.getSelectedItem();
					Diem dt = null;
					dt=DiemDao.find(idsv,idgv,idmh);
					if(dt==null) {
						String diem = textField_diem.getText();
						Diem di = new DiemBuilder().setidsv(idsv).setidgv(idgv).setidmh(idmh).setdiem(diem).build();
						DiemDao.insert(di);
						LoadTable(table);
					}else {
						JOptionPane.showMessageDialog(contentPane,"Điểm này đã được nhập", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error !!!\n"+ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnThem.setBackground(Color.GRAY);
		btnThem.setBounds(291, 492, 89, 23);
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String idsv = (String) comboBox_sv.getSelectedItem();
					String idgv = textField_gv.getText();
					String idmh = (String) comboBox_mh.getSelectedItem();
					Diem dt =DiemDao.find(idsv,idgv,idmh);
					if(dt != null) {
						DiemDao.delete(idsv,idgv,idmh);
						JOptionPane.showMessageDialog(contentPane,"Xóa Thành công", "delete", JOptionPane.INFORMATION_MESSAGE);
						LoadTable(table);
					}else {
						JOptionPane.showMessageDialog(contentPane,"Không tìm thấy thông tin", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error: \n"+ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoa.setBounds(390, 492, 89, 23);
		contentPane.add(btnXoa);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String idsv = (String) comboBox_sv.getSelectedItem();
					String idgv = textField_gv.getText();
					String idmh = (String) comboBox_mh.getSelectedItem();
					Diem dt =DiemDao.find(idsv,idgv,idmh);
					if(dt!=null) {
						String di = textField_diem.getText();
						Diem sv = new DiemBuilder().setidsv(idsv).setidgv(idgv).setidmh(idmh).setdiem(di).build();
						DiemDao.update(sv);
						LoadTable(table);
					}else {
						JOptionPane.showMessageDialog(contentPane,"ID này chưa có thông tin", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error !!!\n"+ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSua.setBounds(489, 492, 89, 23);
		contentPane.add(btnSua);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MenuGiangVien dn=new MenuGiangVien();
				dn.setVisible(true);
			}
		});
		btnThoat.setBounds(588, 492, 89, 23);
		contentPane.add(btnThoat);
	}
	public void LoadTable(JTable table) {
		DefaultTableModel model = new DefaultTableModel(new String[] {
				"Sinh viên","Giảng viên phụ trách","Môn học","Điểm"},0);
		List<Diem> list = DiemDao.getAll();
		List<GiangVien> listgv = GiangVienDao.getAll();
		List<SinhVien> listsv = SinhVienDao.getAll();
		List<MonHoc> listmh = MonHocDao.getAll();
		for(int i=0;i<list.size();i++) {
			Diem di = list.get(i);
			String tensv=null;
			String tengv=null;
			String tenmh=null;
			for(int j=0;j<listsv.size();j++) {
				SinhVien sv = listsv.get(j);
				if(di.getIDSV().equals(sv.getID())==true) {
					tensv=sv.getTen();
				}
			}
			for(int j=0;j<listgv.size();j++) {
				GiangVien gv = listgv.get(j);
				if(di.getIdgv().equals(gv.getID())==true) {
					tengv=gv.getTen();
				}
			}
			for(int j=0;j<listmh.size();j++) {
				MonHoc gv = listmh.get(j);
				if(di.getIDMH().equals(gv.getID())==true) {
					tenmh=gv.getTen();
				}
			}
			if(tensv!=null & tengv!=null & tenmh!=null) {
				Object[] object = {tensv, tengv,tenmh,di.getDiem()};
				model.addRow(object);
			}
		}
		table.setModel(model);		
	}
	public void LoadDialogSV(JComboBox<String> comboBox) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		List<SinhVien> sv = SinhVienDao.getAll();
		for(int i =0;i<sv.size();i++) {
			model.addElement(sv.get(i).getID());
		}
		comboBox.setModel(model);
		comboBox.setSelectedIndex(0);
	}
	public void LoadDialogMH(JComboBox<String> comboBox) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		List<MonHoc> mh = MonHocDao.getAll();
		for(int i =0;i<mh.size();i++) {
			model.addElement(mh.get(i).getID());
		}
		comboBox.setModel(model);
		comboBox.setSelectedIndex(0);
	}
}
