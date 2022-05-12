package DoAn.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DoAn.GiangVienDao;
import DoAn.Model.GiangVien;
import net.sourceforge.jdatepicker.impl.JDatePicker_Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Font;

public class GiangVienGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_ten;
	private JTextField textField_id;
	private JTextField textField_email;
	private JTextField textField_sdt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiangVienGui frame = new GiangVienGui();
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
	public GiangVienGui() {
		setTitle("Quản lý giảng viên");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 695);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 127, 80));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDatePicker_Date datePicker_Date = new JDatePicker_Date();
		datePicker_Date.setBounds(126, 193, 212, 23);
		contentPane.add(datePicker_Date);
		
		JLabel lblSinThoi = new JLabel("Ngày sinh");
		lblSinThoi.setBounds(10, 195, 106, 21);
		contentPane.add(lblSinThoi);
		
		JLabel lblLp = new JLabel("Giới tính");
		lblLp.setBounds(10, 163, 106, 21);
		contentPane.add(lblLp);
		
		JRadioButton rdbtnNam = new JRadioButton("Nam", true);
		rdbtnNam.setBounds(126, 163, 53, 21);
		contentPane.add(rdbtnNam);
		
		JRadioButton RadioButton_Nu = new JRadioButton("Nữ");
		RadioButton_Nu.setBounds(219, 162, 70, 23);
		contentPane.add(RadioButton_Nu);
		
		ButtonGroup bg = new ButtonGroup();
        bg.add(rdbtnNam);
        bg.add(RadioButton_Nu);
		
		textField_ten = new JTextField();
		textField_ten.setColumns(10);
		textField_ten.setBounds(126, 131, 212, 20);
		contentPane.add(textField_ten);
		
		JLabel lblTn = new JLabel("Tên");
		lblTn.setBounds(10, 131, 106, 21);
		contentPane.add(lblTn);
		
		JLabel lblIdSinhVin = new JLabel("ID Giảng viên");
		lblIdSinhVin.setBounds(10, 99, 106, 21);
		contentPane.add(lblIdSinhVin);
		
		textField_id = new JTextField();
		textField_id.setColumns(10);
		textField_id.setBounds(126, 99, 212, 20);
		contentPane.add(textField_id);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 227, 106, 21);
		contentPane.add(lblEmail);
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(126, 227, 212, 20);
		contentPane.add(textField_email);
		
		JLabel lblSinThoi_1 = new JLabel("Số điện thoại");
		lblSinThoi_1.setBounds(10, 259, 106, 21);
		contentPane.add(lblSinThoi_1);
		
		textField_sdt = new JTextField();
		textField_sdt.setColumns(10);
		textField_sdt.setBounds(126, 259, 212, 20);
		contentPane.add(textField_sdt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 291, 621, 301);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		LoadTable(table);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_id.getText();
				GiangVien sv = null;
				try {
					sv = GiangVienDao.find(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(sv != null) {
					textField_ten.setText(sv.getTen());
					System.out.print(sv.getGioitih());
					if(sv.getGioitih().equals("Nam")==true) {
						rdbtnNam.setSelected(true);
					}
					else if (sv.getGioitih().equals("Nữ")==true) {
						RadioButton_Nu.setSelected(true);
					}
					datePicker_Date.getJFormattedTextField().setText(sv.getNgaysinh());
					textField_email.setText(sv.getEmail());
					textField_sdt.setText(sv.getSdt());
				}
				else {
					JOptionPane.showMessageDialog(contentPane,"Không tìm thấy giảng viên này", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnTim.setForeground(new Color(0, 0, 0));
		btnTim.setBackground(new Color(0, 250, 154));
		btnTim.setBounds(146, 622, 89, 23);
		contentPane.add(btnTim);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textField_id.getText();
					GiangVien svt = null;
					svt=GiangVienDao.find(id);
					if(svt==null) {
						String ten = textField_ten.getText();
						String sdt = textField_sdt.getText();
						String gioitinh;
						if(rdbtnNam.isSelected()) {
							gioitinh="Nam";
							String email = textField_email.getText();
							String ngaysinh = datePicker_Date.getJFormattedTextField().getText();
							GiangVien sv = new GiangVien(id,ten,gioitinh,ngaysinh,email,sdt);
							GiangVienDao.insert(sv);
							LoadTable(table);
						}
						else if (RadioButton_Nu.isSelected()) {
							gioitinh="Nữ";
							String email = textField_email.getText();
							String ngaysinh = datePicker_Date.getJFormattedTextField().getText();
							GiangVien sv = new GiangVien(id,ten,gioitinh,ngaysinh,email,sdt);
							GiangVienDao.insert(sv);
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
		btnThem.setForeground(new Color(0, 0, 0));
		btnThem.setBackground(new Color(0, 0, 205));
		btnThem.setBounds(245, 622, 89, 23);
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textField_id.getText();
					GiangVien sv = GiangVienDao.find(id);
					if(sv != null) {
						GiangVienDao.delete(id);
						JOptionPane.showMessageDialog(contentPane,"Xóa Thành công", "delete", JOptionPane.INFORMATION_MESSAGE);
						LoadTable(table);
					}else {
						JOptionPane.showMessageDialog(contentPane,"Không tìm thấy giảng viên", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error: \n"+ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setForeground(new Color(0, 0, 0));
		btnXoa.setBounds(344, 622, 89, 23);
		contentPane.add(btnXoa);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textField_id.getText();
					String ten = textField_ten.getText();
					String sdt = textField_sdt.getText();
					String email = textField_email.getText();
					String ngaysinh = datePicker_Date.getJFormattedTextField().getText();
					String gioitinh;
					if(rdbtnNam.isSelected()) {
						gioitinh="Nam";
						GiangVien sv = new GiangVien(id,ten,gioitinh,ngaysinh,email,sdt);
						GiangVienDao.update(sv);
						LoadTable(table);
					}
					else if (RadioButton_Nu.isSelected()) {
						gioitinh="Nữ";
						GiangVien sv = new GiangVien(id,ten,gioitinh,ngaysinh,email,sdt);
						GiangVienDao.update(sv);
						LoadTable(table);
					}
					else {
						JOptionPane.showMessageDialog(contentPane,"Bạn chưa chọn giới tính", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error !!!\n"+ex.getMessage(), "Error", 
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSua.setBackground(new Color(255, 255, 0));
		btnSua.setBounds(443, 622, 89, 23);
		contentPane.add(btnSua);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnThoat.setBackground(new Color(165, 42, 42));
		btnThoat.setBounds(542, 622, 89, 23);
		contentPane.add(btnThoat);
		
		JLabel lblQunLGing = new JLabel("QUẢN LÝ GIẢNG VIÊN");
		lblQunLGing.setForeground(Color.BLACK);
		lblQunLGing.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblQunLGing.setBounds(206, 25, 227, 39);
		contentPane.add(lblQunLGing);
	}
	public void LoadTable(JTable table) {
		DefaultTableModel model = new DefaultTableModel(new String[] {
				"ID", "Tên", "Giới tính", "Ngày sinh","Email", "Số điện thoại"},0);
		List<GiangVien> list = GiangVienDao.getAll();
		for(int i=0;i<list.size();i++) {
			GiangVien sv = list.get(i);
			Object[] object = {sv.getID(), sv.getTen(),sv.getGioitih(),sv.getNgaysinh(),sv.getEmail(),sv.getSdt()};
			model.addRow(object);
		}
		table.setModel(model);		
	}
}
