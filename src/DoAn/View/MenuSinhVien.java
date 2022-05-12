package DoAn.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class MenuSinhVien extends JFrame {

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
					MenuSinhVien frame = new MenuSinhVien();
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
	public MenuSinhVien() {
		setTitle("Menu Sinh vi\u00EAn");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 287);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(211, 211, 211));
		menuBar.setToolTipText("");
		menuBar.setBounds(0, 0, 434, 22);
		contentPane.add(menuBar);
		
		JMenu mnSinhVin = new JMenu("Sinh Vi\u00EAn");
		menuBar.add(mnSinhVin);
		
		JMenuItem MenuItem_ttSV = new JMenuItem("Th\u00F4ng tin sinh vi\u00EAn");
		MenuItem_ttSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setVisible(false);
				//System.exit(0); ket thuc chuong trinh va tat chuong trinh
				SinhVienGui dn=new SinhVienGui();
				dn.setVisible(true);
			}
		});
		mnSinhVin.add(MenuItem_ttSV);
		
		JMenuItem mntmXemim = new JMenuItem("Xem \u0111i\u1EC3m");
		mntmXemim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0); ket thuc chuong trinh va tat chuong trinh
				SinhVienDiem dn=new SinhVienDiem();
				dn.setVisible(true);
			}
		});
		mnSinhVin.add(mntmXemim);
		
		JMenuItem mntmDanhSahcsMn = new JMenuItem("Danh s\u00E1ch m\u00F4n h\u1ECDc");
		mntmDanhSahcsMn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0); ket thuc chuong trinh va tat chuong trinh
				SinhVienMonHoc dn=new SinhVienMonHoc();
				dn.setVisible(true);
			}
		});
		mnSinhVin.add(mntmDanhSahcsMn);
		
		JButton btnNewButton = new JButton("Tho\u00E1t");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//System.exit(0); ket thuc chuong trinh va tat chuong trinh
				DangNhap dn=new DangNhap();
				dn.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btnNewButton.setBounds(274, 199, 112, 38);
		contentPane.add(btnNewButton);
		
		JLabel lblMenuSinhVin = new JLabel("MENU SINH VI\u00CAN");
		lblMenuSinhVin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMenuSinhVin.setBounds(102, 95, 216, 42);
		contentPane.add(lblMenuSinhVin);
	}
}
