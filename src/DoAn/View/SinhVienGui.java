package DoAn.View;


import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DoAn.SinhVienDao;
import DoAn.Model.SinhVien;
import builder.SinhVienBuilder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import net.sourceforge.jdatepicker.impl.JDatePicker_Date;
import javax.swing.JRadioButton;
import java.awt.Font;

public class SinhVienGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_ten;
	private JTable table;
	private JTextField textField_sdt;
	private JTextField textField_email;
	private JTextField textField_lop;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SinhVienGui frame = new SinhVienGui();
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
	public SinhVienGui() {
		setResizable(false);
		setTitle("Quản lý sinh viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 667);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdSinhVin = new JLabel("ID Sinh vi\u00EAn");
		lblIdSinhVin.setBounds(10, 90, 106, 21);
		contentPane.add(lblIdSinhVin);
		
		textField_id = new JTextField();
		textField_id.setColumns(10);
		textField_id.setBounds(126, 90, 212, 20);
		contentPane.add(textField_id);
		
		JLabel lblTn = new JLabel("T\u00EAn");
		lblTn.setBounds(10, 122, 106, 21);
		contentPane.add(lblTn);
		
		textField_ten = new JTextField();
		textField_ten.setColumns(10);
		textField_ten.setBounds(126, 122, 212, 20);
		contentPane.add(textField_ten);
		
		JLabel lblLp = new JLabel("Giới tính");
		lblLp.setBounds(10, 154, 106, 21);
		contentPane.add(lblLp);
		
		JLabel lblSinThoi = new JLabel("Ngày sinh");
		lblSinThoi.setBounds(10, 186, 106, 21);
		contentPane.add(lblSinThoi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 313, 667, 270);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		LoadTable(table);
		
		JRadioButton rdbtnNam = new JRadioButton("Nam",true);
		rdbtnNam.setBounds(126, 154, 53, 21);
		contentPane.add(rdbtnNam);
		
		JRadioButton RadioButton_Nu = new JRadioButton("Nữ");
		RadioButton_Nu.setBounds(219, 153, 70, 23);
		contentPane.add(RadioButton_Nu);
		
		ButtonGroup bg = new ButtonGroup();
        bg.add(rdbtnNam);
        bg.add(RadioButton_Nu);
		
		JDatePicker_Date datePicker_Date = new JDatePicker_Date();
		datePicker_Date.setBounds(126, 184, 212, 23);
		contentPane.add(datePicker_Date);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(188, 143, 143));
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_id.getText();
				SinhVien sv = null;
				try {
					sv = SinhVienDao.find(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(sv != null) {
					textField_ten.setText(sv.getTen());
					if(sv.getGioitih().equals("Nam")==true) {
						rdbtnNam.setSelected(true);
					}
					else if (sv.getGioitih().equals("Nữ")==true) {
						RadioButton_Nu.setSelected(true);
					}
					datePicker_Date.getJFormattedTextField().setText(sv.getNgaysinh());
					textField_lop.setText(sv.getLop());
					textField_email.setText(sv.getEmail());
					textField_sdt.setText(sv.getSdt());
				}
				else {
					JOptionPane.showMessageDialog(contentPane,"Không tìm thấy Sinh viên này", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnTim.setBounds(120, 594, 89, 23);
		contentPane.add(btnTim);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(50, 205, 50));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textField_id.getText();
					SinhVien svt = null;
					svt=SinhVienDao.find(id);
					if(svt==null) {
						String ten = textField_ten.getText();
						String lop = textField_lop.getText();
						String sdt = textField_sdt.getText();
						String gioitinh;
						if(rdbtnNam.isSelected()) {
							gioitinh="Nam";
							String email = textField_email.getText();
							String ngaysinh = datePicker_Date.getJFormattedTextField().getText();
							SinhVien sv = new SinhVienBuilder().setId(id).setTen(ten).setGioiTinh(gioitinh).setNgaysinh(ngaysinh).setLop(lop).setEmail(email).setSDT(sdt).build();
							SinhVienDao.insert(sv);
							LoadTable(table);
						}
						else if (RadioButton_Nu.isSelected()) {
							gioitinh="Nữ";
							String email = textField_email.getText();
							String ngaysinh = datePicker_Date.getJFormattedTextField().getText();
							SinhVien sv = new SinhVienBuilder().setId(id).setTen(ten).setGioiTinh(gioitinh).setNgaysinh(ngaysinh).setLop(lop).setEmail(email).setSDT(sdt).build();
							SinhVienDao.insert(sv);
							LoadTable(table);
						}
						else {
							JOptionPane.showMessageDialog(contentPane,"Bạn chưa chọn giới tính", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(contentPane,"ID này đã có thông tin", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error !!!\n"+ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnThem.setBounds(219, 594, 89, 23);
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textField_id.getText();
					SinhVien sv = SinhVienDao.find(id);
					if(sv != null) {
						SinhVienDao.delete(id);
						JOptionPane.showMessageDialog(contentPane,"Xóa Thành công", "delete", JOptionPane.INFORMATION_MESSAGE);
						LoadTable(table);
					}else {
						JOptionPane.showMessageDialog(contentPane,"Không tìm thấy sinh viên", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error: \n"+ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoa.setBounds(318, 594, 89, 23);
		contentPane.add(btnXoa);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(255, 105, 180));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textField_id.getText();
					SinhVien svt = null;
					svt=SinhVienDao.find(id);
					if(svt!=null) {
						String ten = textField_ten.getText();
						String lop = textField_lop.getText();
						String sdt = textField_sdt.getText();
						String email = textField_email.getText();
						String ngaysinh = datePicker_Date.getJFormattedTextField().getText();
						String gioitinh;
						if(rdbtnNam.isSelected()) {
							gioitinh="Nam";
							SinhVien sv = new SinhVienBuilder().setId(id).setTen(ten).setGioiTinh(gioitinh).setNgaysinh(ngaysinh).setLop(lop).setEmail(email).setSDT(sdt).build();
							SinhVienDao.update(sv);
							LoadTable(table);
						}
						else if (RadioButton_Nu.isSelected()) {
							gioitinh="Nữ";
							SinhVien sv = new SinhVienBuilder().setId(id).setTen(ten).setGioiTinh(gioitinh).setNgaysinh(ngaysinh).setLop(lop).setEmail(email).setSDT(sdt).build();
							SinhVienDao.update(sv);
							LoadTable(table);
						}
						else {
							JOptionPane.showMessageDialog(contentPane,"Bạn chưa chọn giới tính", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(contentPane,"ID này chưa có thông tin", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error !!!\n"+ex.getMessage(), "Error", 
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSua.setBounds(417, 594, 89, 23);
		contentPane.add(btnSua);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setBackground(new Color(255, 69, 0));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnThoat.setBounds(516, 594, 89, 23);
		contentPane.add(btnThoat);
		
		textField_sdt = new JTextField();
		textField_sdt.setColumns(10);
		textField_sdt.setBounds(126, 282, 212, 20);
		contentPane.add(textField_sdt);
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(126, 250, 212, 20);
		contentPane.add(textField_email);
		
		textField_lop = new JTextField();
		textField_lop.setColumns(10);
		textField_lop.setBounds(126, 218, 212, 20);
		contentPane.add(textField_lop);
		
		JLabel lblLp_2 = new JLabel("Lớp");
		lblLp_2.setBounds(10, 218, 106, 21);
		contentPane.add(lblLp_2);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 250, 106, 21);
		contentPane.add(lblEmail);
		
		JLabel lblSinThoi_1 = new JLabel("Số điện thoại");
		lblSinThoi_1.setBounds(10, 282, 106, 21);
		contentPane.add(lblSinThoi_1);
		
		JLabel lblDanhSchSinh = new JLabel("QUẢN LÝ SINH VIÊN");
		lblDanhSchSinh.setForeground(new Color(0, 0, 0));
		lblDanhSchSinh.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblDanhSchSinh.setBounds(219, 23, 227, 39);
		contentPane.add(lblDanhSchSinh);
		
		
		
		
	}
	public void LoadTable(JTable table) {
		DefaultTableModel model = new DefaultTableModel(new String[] {
				"ID", "Tên", "Giới tính", "Ngày sinh", "Lớp", "Email", "Số điện thoại"},0);
		List<SinhVien> list = SinhVienDao.getAll();
		for(int i=0;i<list.size();i++) {
			SinhVien sv = list.get(i);
			Object[] object = {sv.getID(), sv.getTen(),sv.getGioitih(),sv.getNgaysinh(), sv.getLop(),sv.getEmail(),sv.getSdt()};
			model.addRow(object);
		}
		table.setModel(model);		
	}
}
