package com.smarthouse.thread;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import com.smarthouse.activity.MainActivity;
import com.smarthouse.dealer.DealRequest;

public class DoorThread extends Thread{
	private static final String TAG = DoorThread.class.getSimpleName();
	
	private MainActivity mainActivity;
//	private CountDownLatch countDownLatch;
	private String result = null;
	
	public DoorThread(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
//		countDownLatch = new CountDownLatch(1);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Log.d(TAG, "doorThread starting.......");
		DealRequest dealRequest = new DealRequest();
	    result = dealRequest.GetContentFromUrl("http://www.reveriedream.com/test/appget3.asp");
	    Message msg = new Message();
	    Bundle bundle = new Bundle();//存放数据
	    bundle.putString("door", result);
	    msg.setData(bundle);
	    
	    mainActivity.getDoorHanlder().sendMessage(msg);
	}

	
}
