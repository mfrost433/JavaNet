package data;

import org.jfree.data.xy.XYSeries;

/**
 * This class makes calls to  APIs to retrieve time series data on stock prices.
 * Uses singleton method.
 * @author Matthew
 *
 */
public class APIHandler {
	private static APIHandler a = null;
	private APIHandler() {
		
	}
	
	public static APIHandler getInstance() {
		if(a == null) {
			a = new APIHandler();
		}
		return a;
	}
	
	public void getData(){}
}
