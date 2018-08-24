package test.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class Request {
	
	private String _key;
	private String _function;
	private String _symbol;
	private String _outputSize;
	private final String DEFAULT_KEY = "H1TEXOMHFVBPSCII";
	private final String DEFAULT_OUTPUT_SIZE = "full";
	private final String DEFAULT_FUNCTION = "TIME_SERIES_DAILY";
	
	public Request(String key, String symbol, String function, String outputSize) {
		_key = key;
		_function = function;
		_symbol = symbol;
		_outputSize = outputSize;
	}
	
	public Request(String symbol) {
		_key = DEFAULT_KEY;
		_function = DEFAULT_FUNCTION;
		_symbol = symbol;
		_outputSize = DEFAULT_OUTPUT_SIZE;
	}
	
	public URL toURL() throws MalformedURLException {
		
		String urlString = "https://www.alphavantage.co/query?function=" + _function + "&symbol=" + _symbol + "&interval=15min&outputsize=" + _outputSize + "&apikey=" + _key;;
		return new URL(urlString);
	}
	
	public String toTitle() {
		
		String title = "StockDataProject_" +_symbol + "_" + _function + "_" + _outputSize;
		return title;
	}
	
	public String getKey() {
		return _key;
	}
	


}
