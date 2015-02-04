package com.smarthouse.thread;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.smarthouse.activity.MainActivity;
import com.smarthouse.dealer.DealRequest;

public class LightsThread extends Thread {
	
	private static final String TAG = LightsThread.class.getSimpleName();
	
	private MainActivity mainActivity;
	private String result = null;
	
	public LightsThread(MainActivity mainActivity) {
		// TODO Auto-generated constructor stub
		this.mainActivity = mainActivity;
	}
	
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Log.d(TAG, "lightsThread starting run.....");
		DealRequest dealRequest = new DealRequest();
		result = dealRequest.GetContentFromUrl("http://www.reveriedream.com/test/appget2.asp");
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("lights", result);
		msg.setData(bundle);
		
		mainActivity.getLightsHandler().sendMessage(msg);
	}

}
