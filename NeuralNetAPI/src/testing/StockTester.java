package testing;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import data.JSONAdapter;
import data.StockTrainingData;
import net.Network;

public class StockTester {	
	SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	JFreeChart chart;
	XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();

	Calendar cal = Calendar.getInstance();

	JSONAdapter a = new JSONAdapter();
	List<StockTrainingData> l;
	int[] nodes = {16, 20,20,1};
	Network n = new Network(nodes);

	public StockTester() {
		cal.set(2017, 1, 1);
		chart = ChartFactory.
				createTimeSeriesChart(null, null, null, null);

		XYPlot plot = chart.getXYPlot();
		plot.setDataset(0,a.getXYDataset("MSFT", cal.getTime(), 400));		

		r1.setSeriesPaint(0, new Color(0xff, 0xff, 0x00)); 
		r1.setSeriesPaint(1, new Color(0x00, 0xff, 0xff)); 
		r1.setSeriesShapesVisible(0,  false);
		r1.setSeriesShapesVisible(1,  false);

		plot.setRenderer(1,r1);		
		//plot.setDataset(1,createDataset());
		JFrame f = new JFrame();

		JPanel p = new JPanel();
		ChartPanel chartPanel = new ChartPanel(chart);
		p.add(chartPanel);
		f.add(p);
		f.pack();
		f.setVisible(true);
		
		cal.set(2014, 1, 1);
		Date start = cal.getTime() ;
		cal.set(2017, 1, 1);
		Date end = cal.getTime() ;
		l = a.getTrainingData("MSFT",start, end);

		for(StockTrainingData t : l) {
			t.normalize(100);

		}
		for(int i = 0 ; i < 1000; i ++) {
			for(StockTrainingData t : l) {
				n.train(t.getInput(), t.getTarget());
			}
		}
		plot.setDataset(1,guessData());
	}

	public static void main(String[] args) {
		new StockTester();
	}


	private XYDataset guessData() {
		final TimeSeries series = new TimeSeries(" Data" );
		cal.set(2017, 1, 1);
		Date start = cal.getTime() ;
		cal.set(2017, 11, 1);
		Date end = cal.getTime() ;
		l = a.getTrainingData("MSFT",start, end);
		for (StockTrainingData t : l) {
			t.normalize(100);
		}
		for (StockTrainingData t : l) {
			double out = n.guess(t.getInput());
			try {             
				series.add(new Day(t.getDate()), new Double(out));					
			} catch ( SeriesException e ) {
				System.err.println("Error adding to series");
			}

		}



		return new TimeSeriesCollection(series);
	}
	private Date getDate(String date) {	
		Date d;
		try {
			d = newDateFormat.parse(date);
			return d;
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
