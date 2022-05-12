package DoAn.View;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DoAn.SinhVienDao;
import DoAn.Model.SinhVien;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GiangVienSV extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JButton btnTim;
	private JLabel lblDanhSchSinh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiangVienSV frame = new GiangVienSV();
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
	public GiangVienSV() {
		setResizable(false);
		setTitle("Danh sách sinh viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 702, 444);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdSinhVin = new JLabel("ID Sinh viên");
		lblIdSinhVin.setBounds(10, 92, 106, 21);
		contentPane.add(lblIdSinhVin);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(126, 92, 212, 20);
		contentPane.add(textField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 667, 270);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		LoadTable(table);
		
		btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadTableID(table, textField.getText());
			}
		});
		btnTim.setBackground(new Color(0, 255, 127));
		btnTim.setBounds(348, 91, 89, 23);
		contentPane.add(btnTim);
		
		lblDanhSchSinh = new JLabel("DANH SÁCH SINH VIÊN");
		lblDanhSchSinh.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblDanhSchSinh.setBounds(270, 21, 227, 39);
		contentPane.add(lblDanhSchSinh);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnThot.setBackground(new Color(255, 0, 0));
		btnThot.setBounds(588, 91, 89, 23);
		contentPane.add(btnThot);
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
	public void LoadTableID(JTable table,String id) {
		DefaultTableModel model = new DefaultTableModel(new String[] {
				"ID", "Tên", "Giới tính", "Ngày sinh", "Lớp", "Email", "Số điện thoại"},0);
		List<SinhVien> list = SinhVienDao.getAll();
		for(int i=0;i<list.size();i++) {
			SinhVien sv = list.get(i);
			if(sv.getID().equals(id)==true) {
				Object[] object = {sv.getID(), sv.getTen(),sv.getGioitih(),sv.getNgaysinh(), sv.getLop(),sv.getEmail(),sv.getSdt()};
				model.addRow(object);
			}
		}
		table.setModel(model);		
	}
}
