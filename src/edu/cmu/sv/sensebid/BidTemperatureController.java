//package edu.cmu.sv.sensebid;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Iterator;
//import java.util.List;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//
//public class BidTemperatureController extends
//		AsyncTask<String, String, Long> {
//
//	private static final String URL = "ip.address.Ira";
//
//
//	protected String doInBackground(String... arguments) {
//
//		String urlStr = arguments[0];
//		String jsonString = arguments[1];
//		
//		URL url = new URL(urlStr);
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty("Content-Type", "application/json");
//		conn.setRequestProperty("Accept", "application/json");
//		conn.setDoOutput(true);
//
//		OutputStream out = null;
//		Writer writer = null;
//		try {
//			out = conn.getOutputStream();
//			writer = new OutputStreamWriter(out, "UTF-8");
//
//			writer.write((new Gson()).toJson(jsonString));
//		} finally {
//			writer.close();
//			out.close();
//		}
//
//		if (conn.getResponseCode() != 200) {
//			throw new IOException(conn.getResponseMessage());
//		}
//		StringBuilder sb;
//		BufferedReader rd = null;
//		try {
//			rd = new BufferedReader(
//					new InputStreamReader(conn.getInputStream()));
//			sb = new StringBuilder();
//			String line;
//			while ((line = rd.readLine()) != null) {
//				sb.append(line);
//			}
//		} finally {
//			rd.close();
//		}
//
//		conn.disconnect();
//		return sb.toString();
//	}
//
//
//
//}