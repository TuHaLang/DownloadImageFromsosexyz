package com.hung.download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetLinkImage {
	public static String getUrlImage(String urlStr) throws IOException {
		
		URL url = new URL(urlStr);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestProperty("Accept-Charset", "UTF-8");
		httpConn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
		if(httpConn.getResponseCode()==400) return "-1";
		BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
		String line;
		while((line=br.readLine())!=null) {
			if(line.indexOf(".jpg")!=-1) {
				String[] arrStr = line.split("\"");
				for(int i=0; i<arrStr.length; i++) {
					if(arrStr[i].endsWith(".jpg") && arrStr[i].startsWith("https")) {
						return arrStr[i];
					}
				}
			}
			
		}
		return null;
	}

}
