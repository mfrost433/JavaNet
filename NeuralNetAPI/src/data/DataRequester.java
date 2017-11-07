package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataRequester {
	
	
	private final String USER_KEY = "H1TEXOMHFVBPSCII";
	private final String FUNCTION = "TIME_SERIES_DAILY";
	private final String SYMBOL = "GOOG";
	private final String OUTPUT_SIZE = "full";
	
	
	

	// HTTP GET request
	public void sendGetRequest(Request request) throws Exception {

		URL obj = request.toURL();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_KEY);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + obj.toString());
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		File f = new File(request.toTitle());
		PrintWriter writer = new PrintWriter(f);
		writer.print(response.toString());

	}

}
