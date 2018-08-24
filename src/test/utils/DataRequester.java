package test.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataRequester {
	
	private static DataRequester _requester = null;
	
	private DataRequester() {

	}

	public static DataRequester getInstance() {
		
		if( _requester == null) {
			_requester = new DataRequester();
		}
		return _requester;
		
	}

	// HTTP GET request
	public void sendGetRequest(Request request) throws Exception {

		File f = new File("..\\NeuralNetAPI\\rsc\\rawdata\\" + request.toTitle() +".txt");
		System.out.println(f.toString());
		
		if( !f.exists() ) {
		f.createNewFile();
		}else {
			System.out.println("File already exists - Overwriting");
			f.delete();
			f.createNewFile();
		}
			
		URL obj = request.toURL();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", request.getKey());

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + obj.toString());
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		String response = "";

		// Printing text to file
		int i = 0;
		PrintWriter writer = new PrintWriter(f);
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			writer.print(inputLine);
			writer.flush();
			// Flushing to avoid buffer memory issues

		}

		
		in.close();

	
	}

}
