package DoAn.View;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DoAn.GiangVienDao;
import DoAn.MonHocDao;
import DoAn.Model.GiangVien;
import DoAn.Model.MonHoc;
import builder.MonhocBuilder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class MonHocGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_monhoc;
	private JTextField textField_ten;
	private JTable table;
	private JTextField textField_tenGV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonHocGui frame = new MonHocGui();
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
	public MonHocGui() {
		setResizable(false);
		setTitle("Quản lý môn học");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 596);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 192, 203));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdMnHc = new JLabel("ID M\u00F4n H\u1ECDc");
		lblIdMnHc.setBounds(10, 123, 106, 21);
		contentPane.add(lblIdMnHc);
		
		textField_monhoc = new JTextField();
		textField_monhoc.setColumns(10);
		textField_monhoc.setBounds(126, 123, 212, 20);
		contentPane.add(textField_monhoc);
		
		JLabel lblTn = new JLabel("Tên môn học");
		lblTn.setBounds(10, 155, 106, 21);
		contentPane.add(lblTn);
		
		textField_ten = new JTextField();
		textField_ten.setColumns(10);
		textField_ten.setBounds(126, 155, 212, 20);
		contentPane.add(textField_ten);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String ID = (String) comboBox.getSelectedItem();
				GiangVien gv;
				try {
					gv = GiangVienDao.find(ID);
					textField_tenGV.setText(gv.getTen());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		LoadDialog(comboBox);
		comboBox.setBounds(126, 186, 212, 22);
		contentPane.add(comboBox);
		
		JLabel lblLp_2 = new JLabel("ID Giảng viên");
		lblLp_2.setBounds(10, 187, 106, 21);
		contentPane.add(lblLp_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 251, 667, 261);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		LoadTable(table);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textField_monhoc.getText();
					MonHoc sv = MonHocDao.find(id);
					if(sv != null) {
						textField_ten.setText(sv.getTen());
						comboBox.setSelectedItem(sv.getIdgv());
					}
					else {
						JOptionPane.showMessageDialog(contentPane,"Không tìm thấy thông tin môn học.", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(contentPane,"Some thing when wrong !!!\n"+e1.getMessage(), "Error", 
                            JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnTim.setBackground(new Color(0, 255, 0));
		btnTim.setBounds(192, 523, 89, 23);
		contentPane.add(btnTim);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textField_monhoc.getText();
					MonHoc svt = null;
					svt=MonHocDao.find(id);
					if(svt==null) {
						String ten = textField_ten.getText();
						String tinchi = (String) comboBox.getSelectedItem();
						MonHoc sv = new MonhocBuilder().setid(id).setten(ten).setidgv(tinchi).build();
						MonHocDao.insert(sv);
						LoadTable(table);
					}else {
						JOptionPane.showMessageDialog(contentPane,"ID này đã có thông tin", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error !!!\n"+ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnThem.setBackground(new Color(0, 0, 255));
		btnThem.setBounds(291, 523, 89, 23);
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(128, 128, 128));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textField_monhoc.getText();
					MonHoc sv = MonHocDao.find(id);
					if(sv != null) {
						MonHocDao.delete(id);
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
		btnXoa.setBounds(390, 523, 89, 23);
		contentPane.add(btnXoa);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(210, 105, 30));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = textField_monhoc.getText();
					MonHoc svt = null;
					svt=MonHocDao.find(id);
					if(svt!=null) {
						String ten = textField_ten.getText();
						String idgv = (String) comboBox.getSelectedItem();
						MonHoc sv = new MonhocBuilder().setid(id).setten(ten).setidgv(idgv).build();
						MonHocDao.update(sv);
						LoadTable(table);
					}else {
						JOptionPane.showMessageDialog(contentPane,"ID này chưa có thông tin", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane,"Error !!!\n"+ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSua.setBounds(489, 523, 89, 23);
		contentPane.add(btnSua);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setBackground(new Color(255, 0, 0));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnThoat.setBounds(588, 523, 89, 23);
		contentPane.add(btnThoat);
		
		JLabel lblTnGingVin = new JLabel("Tên giảng viên");
		lblTnGingVin.setBounds(10, 219, 106, 21);
		contentPane.add(lblTnGingVin);
		
		textField_tenGV = new JTextField();
		textField_tenGV.setColumns(10);
		textField_tenGV.setBounds(126, 219, 212, 20);
		contentPane.add(textField_tenGV);
		
		JLabel lblQunLMn = new JLabel("QUẢN LÝ MÔN HỌC");
		lblQunLMn.setForeground(Color.BLACK);
		lblQunLMn.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblQunLMn.setBounds(250, 34, 227, 39);
		contentPane.add(lblQunLMn);
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
	public void LoadDialog(JComboBox<String> comboBox) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		List<GiangVien> gv = GiangVienDao.getAll();
		for(int i =0;i<gv.size();i++) {
			model.addElement(gv.get(i).getID());
		}
		comboBox.setModel(model);
		comboBox.setSelectedIndex(0);
	}
}
