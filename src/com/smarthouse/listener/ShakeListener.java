package com.smarthouse.listener;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.sax.StartElementListener;
import android.util.Log;

public class ShakeListener implements SensorEventListener{

	//速度阈值，当摇晃速度达到这个值时产生摇晃
	private static final int SHAKE_SPEED = 3000;
	//两次检测的时间间隔
	private static final int UPDATE_INTERVAL_TIME = 70;
	//传感器管�?�器
	private SensorManager sensorManager;
	//传感器
	private Sensor sensor;
	//�?力感应检测器
	private OnShakeListener onshakeListener;
	private Context mContext;
	//一个�?置的�?力感应�??标
	private float lastx,lasty,lastz;
	
	private long lastUpdateTime;
	
	
	//构造器
	public ShakeListener(Context c) {
		mContext = c;
		Start();
	}

	public void Start() {
		// TODO Auto-generated method stub
		sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
		if(sensorManager != null){
			sensor = sensorManager.getDefaultSensor(sensor.TYPE_ACCELEROMETER);
		}
		if(sensor != null){
			sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);
		}
	}

	//�?�止
	public void stop(){
		sensorManager.unregisterListener(this);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
		
	}

	public void setOnShakeListener(OnShakeListener listener){
		onshakeListener = listener;
	}
	
	@Override
	// �?力感应器感应获得�?�化数�?�  
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		//获得当�?时间
		long currentUpdateTime = System.currentTimeMillis();
		//计算两次时间间隔
		long timeInterval = currentUpdateTime - lastUpdateTime;
		if(timeInterval < UPDATE_INTERVAL_TIME){
			return;
		}
		lastUpdateTime = currentUpdateTime;
		//获得当�?的�?力感应�??标
		float X = event.values[0];
		float Y = event.values[1];
		float Z  =event.values[2];
		Log.i("lyc","x:"+X+"y:"+Y+"z:"+Z);
		
		//获得x y z 的�?�化值
		float currentlyX = X - lastx;
		float currentlyY = Y - lasty;
		float currentlyZ = Z - lastz;
		
		//将现在的�??标�?��?last�??标
		lastx = X;
		lasty = Y;
		lastz = Z;
		
		
		double speed  = Math.sqrt(currentlyX * currentlyX + currentlyY * currentlyY + currentlyZ * currentlyZ)/timeInterval*10000;
		Log.i("lyc","speed:"+speed);
		if(speed >= SHAKE_SPEED){
			onshakeListener.onShake();
		}
		
	}
	
	public interface OnShakeListener{
		public void onShake();
	}

}
