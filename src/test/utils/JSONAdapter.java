package test.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Day;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * Takes API input or text input (JSON) and returns XYSeries data.
 * @author Matthew
 *
 */
public class JSONAdapter {

	private static final String TIMESERIES_CATEGORY = "Time Series (Daily)";
	private static final String OPEN_CATEGORY = "1. open";
	private static final String HIGH_CATEGORY = "2. high";
	private static final String LOW_CATEGORY = "3. low";
	private static final String CLOSE_CATEGORY = "4. close";

	static Calendar cal = Calendar.getInstance();
	static SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public JSONAdapter() {}

	public XYDataset getXYDataset(String stock, Date start, int range) {
		return createDataset(stock, readFromFile(stock), start, range);
	}

	public JSONObject readFromFile(String stock) {

		JSONParser parser = new JSONParser();

		try {     
			Object obj = parser.parse(new FileReader("D:\\Neural Net\\Neural-net-deep-learning-API\\NeuralNetAPI\\stockdata\\"+stock+".json"));

			JSONObject jsonObject =  (JSONObject) obj;

			JSONObject timeSeries = (JSONObject)jsonObject.get(TIMESERIES_CATEGORY);
			return timeSeries;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private XYDataset createDataset(String stock, JSONObject timeSeries, Date d, int range) {
		Date startDate = d;
		final TimeSeries series = new TimeSeries( stock + " Data" );

		Date cDate = startDate;
		for (int i = 0; i < range; i++) {

			JSONObject daily =  (JSONObject)timeSeries.get(newDateFormat.format(cDate));
			if(daily != null) {
				double dailyClose =  Double.parseDouble((String)daily.get(CLOSE_CATEGORY));
				try {             
					series.add(new Day(cDate), new Double( dailyClose));					
				} catch ( SeriesException e ) {
					System.err.println("Error adding to series");
				}
			}

			cal.setTime(cDate);
			cal.add(Calendar.DATE, 1);
			cDate = cal.getTime();

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

	public List<StockTrainingData> getTrainingData(String stock, Date start, Date end){

		double[][] inpt = new double[1][16];
		int inpIndex = 0;

		int day = 1;
		List<StockTrainingData> out = new ArrayList<StockTrainingData>();
		JSONObject o = readFromFile(stock);
		Date cDate = start;
		while(cDate.compareTo(end) != 0) {
			JSONObject daily =  (JSONObject)o.get(newDateFormat.format(cDate));

			if(daily != null) {
				
				if(day == 5) {
					double[][] targ = {{ Double.parseDouble((String)daily.get(CLOSE_CATEGORY))}};
					out.add(new StockTrainingData(inpt, targ, cDate));
					day = 0;
					inpIndex = 0;
					inpt = new double[1][16];
					cal.setTime(cDate);
					cal.add(Calendar.DATE, -4);
					cDate = cal.getTime();
				}else{
					inpt[0][inpIndex] = Double.parseDouble((String)daily.get(OPEN_CATEGORY));
					inpIndex++;
					inpt[0][inpIndex] = Double.parseDouble((String)daily.get(HIGH_CATEGORY));
					inpIndex++;
					inpt[0][inpIndex] = Double.parseDouble((String)daily.get(LOW_CATEGORY));
					inpIndex++;
					inpt[0][inpIndex] = Double.parseDouble((String)daily.get(CLOSE_CATEGORY));
					inpIndex++;
				}
				day++;

			}
			cal.setTime(cDate);
			cal.add(Calendar.DATE, 1);
			cDate = cal.getTime();
		}
		System.out.print(cDate);
		return out;
	}
}
