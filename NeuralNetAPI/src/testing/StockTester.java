package testing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.SeriesException;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import net.Network;

public class StockTester {
	JFreeChart chart;
	int[] nodes = {26, 50,50,1};
	
	Network n = new Network(nodes);
	
	public StockTester() {
		double[] i = {1,2,3,4};
		chart = ChartFactory.
				createTimeSeriesChart(null, null, null, null);

		XYPlot plot = chart.getXYPlot();
		plot.setDataset(0,createDataset());
		plot.setDataset(1,createDataset());
		
		XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();
		r1.setSeriesPaint(0, new Color(0xff, 0xff, 0x00)); 
		r1.setSeriesPaint(1, new Color(0x00, 0xff, 0xff)); 
		r1.setSeriesShapesVisible(0,  false);
		r1.setSeriesShapesVisible(1,  false);
		
		plot.setRenderer(1,r1);		
		
		JFrame f = new JFrame();
		f.setSize(800,800);
		JPanel p = new JPanel();
		ChartPanel chartPanel = new ChartPanel(chart);
		p.add(chartPanel);
		f.add(p);

		f.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new StockTester();
	}
	
	 private XYDataset createDataset( ) {
	      final TimeSeries series = new TimeSeries( "Random Data" );       
	      
	      Second current = new Second( );         
	      double value = 100.0;         
	      
	      for (int i = 0; i < 4000; i++) {
	         
	         try {
	            value = value + Math.random( ) - 0.5;                 
	            series.add(current, new Double( value ) );                 
	            current = ( Second ) current.next( ); 
	         } catch ( SeriesException e ) {
	            System.err.println("Error adding to series");
	         }
	      }
	      

	      return new TimeSeriesCollection(series);
	   }     
	
	
}
