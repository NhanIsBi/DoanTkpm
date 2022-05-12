package DoAn.View;

import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import DoAn.SinhVienDao;
import DoAn.Model.SinhVien;

public class tkgt extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		JFreeChart pieChart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(pieChart);
        JFrame frame = new JFrame();
        frame.getContentPane().add(chartPanel);
        frame.setTitle("Biểu đồ thống kê");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "THỐNG KÊ SINH VIÊN THEO GIỚI TÍNH", dataset, true, true, true);
        return chart;
    }

    @SuppressWarnings("removal")
	private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        double demnam=0,demnu=0;
        List<SinhVien> list = SinhVienDao.getAll();
		for(int i=0;i<list.size();i++) {
			SinhVien sv = list.get(i);
			if(sv.getGioitih().equals("Nam")) {
				demnam=demnam+1;
			}else if (sv.getGioitih().equals("Nữ")) {
				demnu=demnu+1;
			}
		}
		double tong=demnam+demnu;
		double ptnam =demnam/tong;
		double ptnnu =demnu/tong;
        dataset.setValue("Nam", new Double(ptnam));
        dataset.setValue("Nữ", new Double(ptnnu));
        return dataset;
    }
}
