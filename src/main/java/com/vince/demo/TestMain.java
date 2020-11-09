package com.vince.demo;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestMain {
	
	public static void main (String args[]) {
		
		String url = "http://riqualificazione.formez.it/content/concorso-ripam-agenzia-italiana-cooperazione-sviluppo-e-ministero-dellambiente-e-tutela";
					//http://riqualificazione.formez.it/content/concorso-ripam-agenzia-italiana-cooperazione-sviluppo-e-ministero-dellambiente-e-tutela
		System.out.println(url.indexOf("/"));
		
		String inputLine;
		try {
			URL data = new URL(url);
			HttpURLConnection con = (HttpURLConnection) data.openConnection();
			
			con.setRequestProperty("User-Agent", "Mozilla/5.0 Firefox/26.0");
			con.connect();
			
			System.out.println(con.getResponseCode());
			/* Read webpage coontent */
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			FileWriter myWriter = new FileWriter(".store_page/ciao.html");
			// myWriter.write("Files in Java might be tricky, but it is fun enough!");

			// BufferedWriter writer = Files.newBufferedWriter(dst, StandardCharsets.UTF_8);
			/* Read line by line */
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				myWriter.write(inputLine);
				myWriter.write("\n");
			}

			myWriter.close();
			/* close BufferedReader */
			in.close();
			/* close HttpURLConnection */
			con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
 