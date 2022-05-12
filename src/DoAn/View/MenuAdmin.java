package DoAn.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdmin frame = new MenuAdmin();
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
	public MenuAdmin() {
		setResizable(false);
		setTitle("Menu Qu\u1EA3n l\u00FD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 351);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 105, 30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Tho\u00E1t");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				DangNhap dn=new DangNhap();
				dn.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(289, 260, 112, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Qu\u1EA3n l\u00FD sinh vi\u00EAn");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SinhVienGui dn=new SinhVienGui();
				dn.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(10, 64, 186, 38);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Qu\u1EA3n l\u00FD m\u00F4n h\u1ECDc");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MonHocGui dn=new MonHocGui();
				dn.setVisible(true);
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(10, 211, 186, 38);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Qu\u1EA3n l\u00FD gi\u1EA3ng vi\u00EAn");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GiangVienGui dn=new GiangVienGui();
				dn.setVisible(true);
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_2.setBounds(215, 64, 186, 38);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("Qu\u1EA3n l\u00FD \u0111i\u1EC3m");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiemGui dn=new DiemGui();
				dn.setVisible(true);
			}
		});
		btnNewButton_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_3.setBounds(215, 211, 186, 38);
		contentPane.add(btnNewButton_1_3);
		
		JButton btnNewButton_1_4 = new JButton("Qu\u1EA3n l\u00FD t\u00E0i kho\u1EA3n");
		btnNewButton_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DangKy dn=new DangKy();
				dn.setVisible(true);
			}
		});
		btnNewButton_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_4.setBounds(10, 266, 186, 38);
		contentPane.add(btnNewButton_1_4);
		
		JLabel lblNewLabel = new JLabel("MENU ADMIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(144, 11, 216, 42);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1_5 = new JButton("Th\u1ED1ng k\u00EA gi\u1EDBi t\u00EDnh sinh vi\u00EAn");
		btnNewButton_1_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SinhVienThongKe.main(null);
			}
		});
		btnNewButton_1_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_5.setBounds(10, 113, 391, 38);
		contentPane.add(btnNewButton_1_5);
		
		JButton btnNewButton_1_5_1 = new JButton("Th\u1ED1ng k\u00EA x\u1EBFp lo\u1EA1i sinh vi\u00EAn");
		btnNewButton_1_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKeXepLoaiSV.main(null);
			}
		});
		btnNewButton_1_5_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_5_1.setBounds(10, 162, 391, 38);
		contentPane.add(btnNewButton_1_5_1);
	}
}
