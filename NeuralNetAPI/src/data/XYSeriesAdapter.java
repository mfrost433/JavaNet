package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Day;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * Takes API input or text input (JSON) and returns XYSeries data.
 * @author Matthew
 *
 */
public class XYSeriesAdapter {


	public static void readFromFile(String stock) {

		JSONParser parser = new JSONParser();

		try {     
			Object obj = parser.parse(new FileReader("D:\\Neural Net\\Neural-net-deep-learning-API\\NeuralNetAPI\\stockdata\\"+stock+".json"));

			JSONObject jsonObject =  (JSONObject) obj;

			JSONObject timeSeries = (JSONObject)jsonObject.get("Time Series (Daily)");
			
			JSONObject daily =  (JSONObject)timeSeries.get("2017-11-06");
			
			double dailyOpen =  Double.parseDouble((String)daily.get("4. close"));
			
			System.out.println(dailyOpen);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	 private XYDataset createDataset(String stock, JSONObject timeSeries) {
		 
		 
	      final TimeSeries series = new TimeSeries( stock + " Data" ); 
	      
	      Day current = new Day( );         
	      double value = 0;         
	      
	      for (int i = 0; i < 4000; i++) {
	         
	         try {             
	            series.add(current, new Double( value ) );                 
	            current = ( Day ) current.next( ); 
	         } catch ( SeriesException e ) {
	            System.err.println("Error adding to series");
	         }
	      }
	      

	      return new TimeSeriesCollection(series);
	   }     

}
