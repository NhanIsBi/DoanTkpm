package DoAn.View;

import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import DoAn.DiemDao;
import DoAn.SinhVienDao;
import DoAn.Model.Diem;
import DoAn.Model.SinhVien;

public class ThongKeXepLoaiSV extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Thống kê xếp loại sinh viên");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public static JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "THỐNG KÊ SINH VIÊN THEO XẾP LOẠI",
                "Năm", "Số người",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private static CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int yeu=0,trungbinh=0,kha=0,gioi=0;
		double dtb=0;
		double tong=0;
		double dem=0;
		List<Diem> list = DiemDao.getAll();
		List<SinhVien> listsv = SinhVienDao.getAll();
		for(int i=0;i<listsv.size();i++) {
			SinhVien sv = listsv.get(i);
			for(int j=0;j<list.size();j++) {
				Diem di = list.get(j);
				if (sv.getID().equals(di.getIDSV())==true) {
					tong =tong+  Double.parseDouble(di.getDiem());
					dem=dem+1;
				}
			}
			dtb=tong/dem;
			System.out.println(dtb);
			if(dtb<5 & dtb>0) {
				yeu=yeu+1;
			}else if (dtb<6.5 & dtb>=5) {
				trungbinh=trungbinh+1;
			}else if (dtb<8 & dtb>=6.5) {
				kha=kha+1;
			}else if(dtb<10 & dtb>=8){
				gioi=gioi+1;
			}
			dem=0;
			tong=0;
		}
		
        dataset.addValue(yeu, "Số lượng", "yếu");
        dataset.addValue(trungbinh, "Số lượng", "trung bình");
        dataset.addValue(kha, "Số lượng", "khá");
        dataset.addValue(gioi, "Số lượng", "giỏi");
        return dataset;
    }

}
