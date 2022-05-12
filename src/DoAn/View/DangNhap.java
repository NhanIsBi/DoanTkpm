package DoAn.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DoAn.AccountDao;
import DoAn.Model.Account;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DangNhap extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_user;
	private JTextField textField_pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
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
	public DangNhap() {
		setTitle("Đăng nhập");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(49, 79, 106, 21);
		contentPane.add(lblUsername);
		
		textField_user = new JTextField();
		textField_user.setColumns(10);
		textField_user.setBounds(165, 79, 212, 20);
		contentPane.add(textField_user);
		
		JLabel lblPassword = new JLabel("PassWord");
		lblPassword.setBounds(49, 111, 106, 21);
		contentPane.add(lblPassword);
		
		textField_pass = new JTextField();
		textField_pass.setColumns(10);
		textField_pass.setBounds(165, 111, 212, 20);
		contentPane.add(textField_pass);
		
		JRadioButton rdbtnNewRadioButton_AD = new JRadioButton("Admin",true);
		rdbtnNewRadioButton_AD.setBackground(Color.BLUE);
		rdbtnNewRadioButton_AD.setBounds(46, 165, 109, 23);
		contentPane.add(rdbtnNewRadioButton_AD);
		
		JRadioButton rdbtnGingVin = new JRadioButton("Gi\u1EA3ng vi\u00EAn");
		rdbtnGingVin.setBackground(Color.GREEN);
		rdbtnGingVin.setBounds(175, 165, 109, 23);
		contentPane.add(rdbtnGingVin);
		
		JRadioButton rdbtnSinhVin = new JRadioButton("Sinh Vi\u00EAn");
		rdbtnSinhVin.setBackground(Color.RED);
		rdbtnSinhVin.setBounds(305, 165, 109, 23);
		contentPane.add(rdbtnSinhVin);
		
		ButtonGroup bg = new ButtonGroup();
        bg.add(rdbtnNewRadioButton_AD);
        bg.add(rdbtnGingVin);
        bg.add(rdbtnSinhVin);
		
		JButton btnNewButton_DN = new JButton("\u0110\u0103ng nh\u1EADp");
		btnNewButton_DN.setBackground(new Color(65, 105, 225));
		btnNewButton_DN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account account = new Account();
				account.setUsername(textField_user.getText());
				account.setPass(textField_pass.getText());
				
				if(rdbtnNewRadioButton_AD.isSelected() ) {
					account.setVitri("admin");
					try {
						if(AccountDao.acExit(account.getUsername(), account.getPass(), account.getVitri())==true) {
							setVisible(false);
							MenuAdmin sVienGui=new MenuAdmin();
							sVienGui.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(contentPane,"Đăng nhập thất bại", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(rdbtnGingVin.isSelected()){
					account.setVitri("giangvien");
					try {
						if(AccountDao.acExit(account.getUsername(), account.getPass(), account.getVitri())==true) {
							setVisible(false);
							MenuGiangVien sVienGui=new MenuGiangVien();
							sVienGui.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(contentPane,"Đăng nhập thất bại", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(rdbtnSinhVin.isSelected()){
					account.setVitri("sinhvien");
					try {
						if(AccountDao.acExit(account.getUsername(), account.getPass(), account.getVitri())==true) {
							setVisible(false);
							MenuSinhVien sVienGui=new MenuSinhVien();
							sVienGui.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(contentPane,"Đăng nhập thất bại", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton_DN.setBounds(271, 213, 106, 37);
		contentPane.add(btnNewButton_DN);
		
		JLabel lblNewLabel = new JLabel("\u0110\u0103ng Nh\u1EADp");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(167, 11, 149, 45);
		contentPane.add(lblNewLabel);
	}
}
