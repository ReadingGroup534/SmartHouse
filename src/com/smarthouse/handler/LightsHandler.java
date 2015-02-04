package com.smarthouse.handler;

import com.smarthouse.activity.MainActivity;
import com.smarthouse.thread.LightsThread;

import android.R.string;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 处理灯光的网络请求并更新对应的UI
 * @author liyangchao
 *
 */
public class LightsHandler extends Handler{
	
	private static final String TAG = LightsHandler.class.getSimpleName();
	
	private MainActivity mainActivity;
	private LightsThread lightsThread;
	
	public LightsHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public LightsHandler(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		lightsThread = new LightsThread(mainActivity);
		lightsThread.start();
	}
	
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		Log.d(TAG, "DoorHandler starting handler message.....");
		super.handleMessage(msg);
		Bundle bundle = msg.getData();
		String lights_details = bundle.getString("lights");
		
		mainActivity.UpdateLightsDetails(lights_details);
	}

}
