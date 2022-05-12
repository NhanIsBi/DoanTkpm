package DoAn.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DoAn.AccountDao;
import DoAn.Model.Account;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class DangKy extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_us;
	private JTextField textField_pass;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKy frame = new DangKy();
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
	public DangKy() {
		setTitle("Quản lý tài khoản");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 554);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 100, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQunLTi = new JLabel("QU\u1EA2N L\u00DD T\u00C0I KHO\u1EA2N");
		lblQunLTi.setForeground(Color.BLACK);
		lblQunLTi.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblQunLTi.setBounds(179, 11, 198, 39);
		contentPane.add(lblQunLTi);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(10, 78, 81, 21);
		contentPane.add(lblUsername);
		
		textField_us = new JTextField();
		textField_us.setColumns(10);
		textField_us.setBounds(126, 78, 153, 20);
		contentPane.add(textField_us);
		
		JLabel lblPassword = new JLabel("PassWord");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(10, 110, 62, 21);
		contentPane.add(lblPassword);
		
		textField_pass = new JTextField();
		textField_pass.setColumns(10);
		textField_pass.setBounds(126, 110, 153, 20);
		contentPane.add(textField_pass);
		
		JLabel lblLp_2 = new JLabel("V\u1ECB Tr\u00ED");
		lblLp_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLp_2.setBounds(10, 142, 47, 21);
		contentPane.add(lblLp_2);
		
		JRadioButton rdbtnNewRadioButton_AD = new JRadioButton("Admin", true);
		rdbtnNewRadioButton_AD.setBackground(Color.BLUE);
		rdbtnNewRadioButton_AD.setBounds(126, 142, 109, 23);
		contentPane.add(rdbtnNewRadioButton_AD);
		
		JRadioButton rdbtnGingVin = new JRadioButton("Giảng viên");
		rdbtnGingVin.setBackground(Color.GREEN);
		rdbtnGingVin.setBounds(255, 142, 109, 23);
		contentPane.add(rdbtnGingVin);
		
		JRadioButton rdbtnSinhVin = new JRadioButton("Sinh Viên");
		rdbtnSinhVin.setBackground(Color.RED);
		rdbtnSinhVin.setBounds(385, 142, 109, 23);
		contentPane.add(rdbtnSinhVin);
		
		ButtonGroup bg = new ButtonGroup();
        bg.add(rdbtnNewRadioButton_AD);
        bg.add(rdbtnGingVin);
        bg.add(rdbtnSinhVin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 174, 495, 270);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		LoadTable(table);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setBackground(new Color(255, 69, 0));
		btnThoat.setBounds(516, 582, -45, 23);
		contentPane.add(btnThoat);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textField_us.getText();
				Account sv = null;
				try {
					sv = AccountDao.find(user);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(sv != null) {
					textField_pass.setText(sv.getPass());
					if(sv.getVitri().equals("admin")==true) {
						rdbtnNewRadioButton_AD.setSelected(true);
					}
					else if (sv.getVitri().equals("giangvien")==true) {
						rdbtnGingVin.setSelected(true);
					}
					else if (sv.getVitri().equals("sinhvien")==true) {
						rdbtnSinhVin.setSelected(true);
					}
				}
				else {
					JOptionPane.showMessageDialog(contentPane,"Không tìm thấy account", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTim.setBackground(new Color(188, 143, 143));
		btnTim.setBounds(20, 455, 89, 23);
		contentPane.add(btnTim);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String user = textField_us.getText();
					Account svt = null;
					svt=AccountDao.find(user);
					if(svt==null) {
						String pass = textField_pass.getText();
						String vitri;
						if(rdbtnNewRadioButton_AD.isSelected()) {
							vitri="admin";
							Account sv = new Account(user,pass,vitri);
							AccountDao.insert(sv);
							LoadTable(table);
						}
						else if (rdbtnGingVin.isSelected()) {
							vitri="giangvien";
							Account sv = new Account(user,pass,vitri);
							AccountDao.insert(sv);
							LoadTable(table);
						}else if (rdbtnSinhVin.isSelected()) {
							vitri="sinhvien";
							Account sv = new Account(user,pass,vitri);
							AccountDao.insert(sv);
							LoadTable(table);
						}
						else {
							JOptionPane.showMessageDialog(contentPane,"Bạn chưa chọn vị trí", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(contentPane,"User này đã có thông tin", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error !!!\n"+ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThem.setBackground(new Color(50, 205, 50));
		btnThem.setBounds(119, 455, 89, 23);
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String user = textField_us.getText();
					Account sv = AccountDao.find(user);
					if(sv != null) {
						AccountDao.delete(user);
						JOptionPane.showMessageDialog(contentPane,"Xóa Thành công", "delete", JOptionPane.INFORMATION_MESSAGE);
						LoadTable(table);
					}else {
						JOptionPane.showMessageDialog(contentPane,"Không tìm thấy user", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error: \n"+ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoa.setBackground(Color.RED);
		btnXoa.setBounds(218, 455, 89, 23);
		contentPane.add(btnXoa);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String user = textField_us.getText();
					Account svt = null;
					svt=AccountDao.find(user);
					if(svt!=null) {
						String pass = textField_pass.getText();
						String vitri;
						if(rdbtnNewRadioButton_AD.isSelected()) {
							vitri="admin";
							Account sv = new Account(user,pass,vitri);
							AccountDao.update(sv);
							LoadTable(table);
						}
						else if (rdbtnGingVin.isSelected()) {
							vitri="giangvien";
							Account sv = new Account(user,pass,vitri);
							AccountDao.update(sv);
							LoadTable(table);
						}else if (rdbtnSinhVin.isSelected()) {
							vitri="sinhvien";
							Account sv = new Account(user,pass,vitri);
							AccountDao.update(sv);
							LoadTable(table);
						}
					}else {
						JOptionPane.showMessageDialog(contentPane,"User này chưa có thông tin", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error !!!\n"+ex.getMessage(), "Error", 
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSua.setBackground(new Color(255, 105, 180));
		btnSua.setBounds(317, 455, 89, 23);
		contentPane.add(btnSua);
		
		JButton btnThoat_1 = new JButton("Thoát");
		btnThoat_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnThoat_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThoat_1.setBackground(new Color(255, 69, 0));
		btnThoat_1.setBounds(416, 455, 89, 23);
		contentPane.add(btnThoat_1);
	}
	public void LoadTable(JTable table) {
		DefaultTableModel model = new DefaultTableModel(new String[] {
				"UserName", "PassWord", "Vị Trí"},0);
		List<Account> list = AccountDao.getAll();
		for(int i=0;i<list.size();i++) {
			Account sv = list.get(i);
			Object[] object = {sv.getUsername(), sv.getPass(),sv.getVitri()};
			model.addRow(object);
		}
		table.setModel(model);		
	}
}
