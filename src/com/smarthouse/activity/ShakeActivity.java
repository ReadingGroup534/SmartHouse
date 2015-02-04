package com.smarthouse.activity;

import com.smarthouse.listener.ShakeListener;
import com.smarthouse.listener.ShakeListener.OnShakeListener;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.Toast;

@SuppressLint("NewApi")
/**
 * 监�?�手机摇晃
 * @author liyangchao
 *
 */
public class ShakeActivity extends Activity{

	private RelativeLayout mLayoutUp,mLayoutDown;
	private SlidingDrawer mDrawer;
	private Button mButton;
	ShakeListener mListener = null;
	Vibrator mVibrator;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shake);
		findViewById();
//		mButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Toast.makeText(ShakeActivity.this, "摇一摇设置", Toast.LENGTH_SHORT).show();
//			}
//		});
		
		mDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				mButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.shake_report_dragger_down));
//				TranslateAnimation titleup = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-1.0f);
//				titleup.setDuration(200);
//				titleup.setFillAfter(true);
//				mTitle.startAnimation(titleup);
			}
		});
		
		mDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				mButton.setBackground(getResources().getDrawable(R.drawable.shake_report_dragger_down));
			}
		});
		
		mListener = new ShakeListener(this);
		mListener.setOnShakeListener(new OnShakeListener() {
			
			@Override
			public void onShake() {
				// TODO Auto-generated method stub
				//摇一摇动画
				startAnim();
				mListener.stop();
				//定义震动
				startVibrator();
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						 Toast mtoast;  
		                 mtoast = Toast.makeText(getApplicationContext(),  
		                             "抱歉，暂时没有找到\n在�?�一时刻摇一摇的人。\n�?试一次�?��?", 10);  
		                //mtoast.setGravity(Gravity.CENTER, 0, 0);  
		                 mtoast.show();  
		                 mVibrator.cancel();  
		                 mListener.Start();  
					}
				}, 2000);
			}

			

			
		});
	}
	

	//自定义震动
	private void startVibrator() {
		// TODO Auto-generated method stub
		mVibrator.vibrate(new long[]{500,200,500,200}, -1); //第一个｛�?里�?�是节�?数组， 第二个�?�数是�?�?次数，-1为�?�?�?，�?�-1俄日从pattern的指定下标开始�?�?  
	}
	
	//定义摇一摇动画
	private void startAnim() {
		// TODO Auto-generated method stub
		AnimationSet animup = new AnimationSet(true);  
	    TranslateAnimation mytranslateanimup0 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-0.5f);  
	    mytranslateanimup0.setDuration(1000);  
	    TranslateAnimation mytranslateanimup1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,+0.5f);  
	    mytranslateanimup1.setDuration(1000);  
	    mytranslateanimup1.setStartOffset(1000);  
	    animup.addAnimation(mytranslateanimup0);  
	    animup.addAnimation(mytranslateanimup1);  
	    mLayoutUp.startAnimation(animup);  
	      
	    AnimationSet animdn = new AnimationSet(true);  
	    TranslateAnimation mytranslateanimdn0 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,+0.5f);  
	    mytranslateanimdn0.setDuration(1000);  
	    TranslateAnimation mytranslateanimdn1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-0.5f);  
	    mytranslateanimdn1.setDuration(1000);  
	    mytranslateanimdn1.setStartOffset(1000);  
	    animdn.addAnimation(mytranslateanimdn0);  
	    animdn.addAnimation(mytranslateanimdn1);  
	    mLayoutDown.startAnimation(animdn);    
	}
	
	private void findViewById(){
		mVibrator = (Vibrator) getApplication().getSystemService(VIBRATOR_SERVICE);
		mLayoutUp = (RelativeLayout) findViewById(R.id.shakeImgUp);
		mLayoutDown = (RelativeLayout) findViewById(R.id.shakeImgDown);
		mDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		mButton = (Button) findViewById(R.id.handle);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		super.onStart();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.shake_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.shake_setting:
			Toast.makeText(ShakeActivity.this, "摇一摇设置", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mListener != null){
			mListener.stop();
		}
	}


	
}
