package com.smarthouse.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class NewWelcome extends Activity{
	
	private ViewPager pager;
	
	ArrayList<View> mArrayList = new ArrayList<View>();
	LayoutInflater mInflater;
	LinearLayout mLinearLayout;
	
	Button mSeletedButton;
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
	    pager = (ViewPager) findViewById(R.id.welcome);
		
		mInflater = getLayoutInflater();
		
		View view1 = mInflater.inflate(R.layout.wel1, null);
		View view2 = mInflater.inflate(R.layout.wel2, null);
		View view3 = mInflater.inflate(R.layout.wel3, null);
		View view4 = mInflater.inflate(R.layout.wel5, null);
		
		mArrayList.add(view1);
		mArrayList.add(view2);
		mArrayList.add(view3);
		mArrayList.add(view4);
		
		
		
//		mLinearLayout = (LinearLayout) findViewById(R.id.ll_num);
		
		PagerAdapter mPagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mArrayList.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				// TODO Auto-generated method stub
				((ViewPager)container).removeView(mArrayList.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				// TODO Auto-generated method stub
				((ViewPager)container).addView(mArrayList.get(position));
				return mArrayList.get(position);
			}
		};
		
		pager.setAdapter(mPagerAdapter);
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.page);  
	       for (int i =0; i < mArrayList.size(); i++) {  
	         Button bt = new Button(this);  
	         bt.setLayoutParams(new ViewGroup.LayoutParams(bitmap.getWidth(),bitmap.getHeight()));  
	         bt.setBackgroundResource(R.drawable.page);  
	         bt.setRight(10);
	         mLinearLayout.addView(bt);  
	       }  
	       
	    pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				if (mSeletedButton != null) {
					mSeletedButton.setBackgroundResource(R.drawable.page);
				}
				
				Button currentButton = (Button) mLinearLayout.getChildAt(position);
				currentButton.setBackgroundResource(R.drawable.page_now);
				mSeletedButton = currentButton;
				
				Log.i("lyc", "current item:"+position);
			}
			
			
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	 
	 
	 public void startbutton(View v) {  
	      	Intent intent = new Intent();
	      	intent.setClass(NewWelcome.this, MainActivity.class);
			startActivity(intent);
			this.finish();
	      }  
}
