package DoAn.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DoAn.GiangVienDao;
import DoAn.MonHocDao;
import DoAn.Model.GiangVien;
import DoAn.Model.MonHoc;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class SinhVienMonHoc extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SinhVienMonHoc frame = new SinhVienMonHoc();
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
	public SinhVienMonHoc() {
		setTitle("Xem danh sách môn học");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 459);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 20, 60));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLp_2_1 = new JLabel("Tên môn học");
		lblLp_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLp_2_1.setBounds(10, 11, 92, 21);
		contentPane.add(lblLp_2_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(112, 12, 212, 20);
		contentPane.add(textField);
		
		JLabel lblMnHc = new JLabel("Môn Học: ");
		lblMnHc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMnHc.setBounds(20, 43, 367, 42);
		contentPane.add(lblMnHc);
		
		JButton btnXemim = new JButton("Xem danh sách");
		btnXemim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnXemim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadTable(table);
				lblMnHc.setText("Môn Học: ");
			}
		});
		btnXemim.setBackground(Color.GREEN);
		btnXemim.setBounds(436, 45, 137, 42);
		contentPane.add(btnXemim);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 96, 667, 311);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		LoadTable(table);
		
		JButton btnTm = new JButton("Tìm");
		btnTm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadTableTen(table, textField.getText());
				lblMnHc.setText("Môn Học: "+textField.getText());
			}
		});
		btnTm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTm.setBackground(Color.GREEN);
		btnTm.setBounds(334, 5, 79, 32);
		contentPane.add(btnTm);
		
		JButton btnXemim_1 = new JButton("Thoát");
		btnXemim_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//System.exit(0); ket thuc chuong trinh va tat chuong trinh
			}
		});
		btnXemim_1.setBackground(Color.GREEN);
		btnXemim_1.setBounds(585, 56, 92, 33);
		contentPane.add(btnXemim_1);
	}
	public void LoadTable(JTable table) {
		DefaultTableModel model = new DefaultTableModel(new String[] {
				"ID môn học", "Tên", "Giảng viên phụ trách"},0);
		List<MonHoc> list = MonHocDao.getAll();
		List<GiangVien> listgv = GiangVienDao.getAll();
		for(int i=0;i<list.size();i++) {
			MonHoc sv = list.get(i);
			String aString=null;
			for(int j=0;j<listgv.size();j++) {
				GiangVien gv = listgv.get(j);
				if(sv.getIdgv().equals(gv.getID())==true) {
					aString=gv.getTen();
				}
			}
			if(aString!=null) {
				Object[] object = {sv.getID(), sv.getTen(),aString};
				model.addRow(object);
			}
		}
		table.setModel(model);		
	}
	public void LoadTableTen(JTable table,String ten) {
		DefaultTableModel model = new DefaultTableModel(new String[] {
				"ID môn học", "Giảng viên phụ trách"},0);
		List<MonHoc> list = MonHocDao.getAll();
		List<GiangVien> listgv = GiangVienDao.getAll();
		for(int i=0;i<list.size();i++) {
			MonHoc sv = list.get(i);
			String aString=null;
			for(int j=0;j<listgv.size();j++) {
				GiangVien gv = listgv.get(j);
				if(sv.getIdgv().equals(gv.getID())==true & sv.getTen().equals(ten)) {
					aString=gv.getTen();
				}
			}
			if(aString!=null) {
				Object[] object = {sv.getID(),aString};
				model.addRow(object);
			}
		}
		table.setModel(model);	
	}
}
