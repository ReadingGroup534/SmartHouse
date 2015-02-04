package com.smarthouse.handler;

import com.smarthouse.activity.MainActivity;
import com.smarthouse.activity.R;
import com.smarthouse.thread.DoorThread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * 处理Door 事件请求并更新对应的主UI线程
 * @author liyangchao
 *
 */
public class DoorHanlder extends Handler{
	
	private static final String TAG = DoorHanlder.class.getSimpleName();
	
	private MainActivity mainActivity;
	private DoorThread doorThread;
	
	public DoorHanlder() {
		// TODO Auto-generated constructor stub
	}
	
	public DoorHanlder(Looper L) {
		super(L);
	}

	public DoorHanlder(MainActivity mainActivity) {
		super();
		this.mainActivity = mainActivity;
		doorThread = new DoorThread(mainActivity);
		doorThread.start();
	}
	
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		Log.d(TAG, "DoorHandler starting handler message.....");
		
		super.handleMessage(msg);
		//更新UI
		Bundle bundle = msg.getData();
		String door_detail = bundle.getString("door") ;
		
		mainActivity.UpdateDoorDetails(door_detail);
	}
	
}
