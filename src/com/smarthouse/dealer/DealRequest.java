package com.smarthouse.dealer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import com.smarthouse.impl.HttpDao;

public class DealRequest implements HttpDao,Serializable{
	
	private static final String TAG = DealRequest.class.getSimpleName();
	
	public DealRequest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String GetContentFromUrl(String url) {
		// TODO Auto-generated method stub
		InputStream inputStream = null;
		String result = "";
		try {
			HttpGet httpGet = new HttpGet(url);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			inputStream = entity.getContent();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			StringBuilder sBuilder = new StringBuilder();
			String line = null;
			while((line = bufferedReader.readLine()) != null){
				sBuilder.append(line+"\n");
			}
			
			result = sBuilder.toString();
			System.out.println("result:"+result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return  result;
	}
}
