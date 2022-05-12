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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SinhVienDiem extends JFrame {

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
					SinhVienDiem frame = new SinhVienDiem();
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
	public SinhVienDiem() {
		setResizable(false);
		setBackground(new Color(0, 128, 128));
		setTitle("Xem \u0111i\u1EC3m");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 454);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 69, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLp_2_1 = new JLabel("ID sinh viên");
		lblLp_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLp_2_1.setBounds(10, 11, 72, 21);
		contentPane.add(lblLp_2_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(102, 11, 212, 20);
		contentPane.add(textField);
		
		JLabel Label_tensv = new JLabel("Sinh viên: ");
		Label_tensv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Label_tensv.setBounds(20, 43, 367, 42);
		contentPane.add(Label_tensv);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 96, 667, 311);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnXemim = new JButton("Xem \u0111i\u1EC3m");
		btnXemim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				SinhVien sv = null;
				try {
					sv = SinhVienDao.find(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(sv==null) {
					JOptionPane.showMessageDialog(contentPane,"Không tìm thấy thông tin", "Error",JOptionPane.ERROR_MESSAGE);
				}else {
					Label_tensv.setText("Sinh Viên: "+sv.getTen());
					LoadTable(table, id);
				}
			}
		});
		btnXemim.setBackground(new Color(0, 255, 0));
		btnXemim.setBounds(425, 51, 92, 33);
		contentPane.add(btnXemim);
		
		JButton btnXemim_1 = new JButton("Thoát");
		btnXemim_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//System.exit(0); ket thuc chuong trinh va tat chuong trinh
			}
		});
		btnXemim_1.setBackground(Color.GREEN);
		btnXemim_1.setBounds(545, 52, 92, 33);
		contentPane.add(btnXemim_1);
		
		
	}
	public void LoadTable(JTable table,String id) {
		DefaultTableModel model = new DefaultTableModel(new String[] {
				"Giảng viên phụ trách","Môn học","Điểm"},0);
		double dtb=0;
		double tong=0;
		double dem=0;
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
				if(id.equals(sv.getID())==true) {
					tensv=sv.getTen();
					for(int k=0;k<listgv.size();k++) {
						GiangVien gv = listgv.get(k);
						if(di.getIdgv().equals(gv.getID())==true & di.getIDSV().equals(id)) {
							tengv=gv.getTen();
						}
					}
					for(int l=0;l<listmh.size();l++) {
						MonHoc mh = listmh.get(l);
						if(di.getIDMH().equals(mh.getID())==true & di.getIDSV().equals(id)) {
							tenmh=mh.getTen();
						}
					}
				}
			}
			
			if(tensv!=null & tengv!=null & tenmh!=null) {
				Object[] object = {tengv,tenmh,di.getDiem()};
				tong =tong+  Double.parseDouble(di.getDiem());
				dem=dem+1;
				model.addRow(object);
			}
		}
		dtb=tong/dem;
		Object[] object1 = {"Điểm trung bình","",dtb};
		model.addRow(object1);
		table.setModel(model);		
	}
}
