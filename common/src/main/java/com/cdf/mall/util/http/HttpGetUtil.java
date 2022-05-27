package com.cdf.mall.util.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpGetUtil {

	public static Map<String,List<String>> hostip = new HashMap<String,List<String>>();

	public static String getHttpRequest(String uri) throws Exception {
		URL url = new URL(uri);
		HttpURLConnection conn;
		StringBuffer buffer = new StringBuffer();
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setConnectTimeout(10000); // ���ӳ�ʱΪ10��
		conn.setRequestMethod("GET");
		InputStream inputStream = conn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		inputStream = null;
		conn.disconnect();
		return buffer.toString();
	}
	
	
	public static String postHttpRequest(String uri,String data) throws Exception {
		URL url = new URL(uri);
		HttpURLConnection conn;
		StringBuffer buffer = new StringBuffer();
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setConnectTimeout(10000); // ���ӳ�ʱΪ10��
		conn.setRequestMethod("GET");
		if (null != data) {
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(data.getBytes("UTF-8"));
			outputStream.close();
		}

		
		InputStream inputStream = conn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		inputStream = null;
		conn.disconnect();
		return buffer.toString();
	}
	
}
