package com.smarthouse.activity;

import com.smarthouse.networkhelper.NetworkHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class AppStart extends Activity {
	/**
	 * 启动界面
	 * 
	 * @author liyangchao
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		if(checkNetwork()){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(AppStart.this, LoginActivity.class);
				startActivity(intent);
				AppStart.this.finish();
				}
			}, 1000);
		}else {
			Toast.makeText(AppStart.this, R.string.no_network, Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * 检查网络状态
	 */
	private boolean checkNetwork(){
		if(NetworkHelper.isNetworkAvailable(getApplicationContext())){
			//非wifi网络提醒用户数据流量
			if(!NetworkHelper.isWifiNetwork(getApplicationContext())){
				Toast.makeText(AppStart.this, R.string.msg_not_wifi, Toast.LENGTH_SHORT).show();
			}
			return true;
		}else{
			//没有可用的网络则显示网络链接失败页面
			return false;
		}
	}
	
}
