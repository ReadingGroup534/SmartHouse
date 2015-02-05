package com.smarthouse.activity;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Welcome extends Activity{
	private ViewPager mViewPager;
	private ImageView mImageView0,mImageView1,mImageView2,mImageView3;//,mImageView4;

	private int currIndex = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		//get the corresponding ID
		findViewById();
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				switch (arg0) {
				case 0:
					mImageView0.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
					mImageView1.setImageDrawable(getResources().getDrawable(R.drawable.page));
					break;
				case 1:
					mImageView1.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
					mImageView0.setImageDrawable(getResources().getDrawable(R.drawable.page));
					mImageView2.setImageDrawable(getResources().getDrawable(R.drawable.page));
					break;
				case 2:
					mImageView2.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
					mImageView1.setImageDrawable(getResources().getDrawable(R.drawable.page));
					mImageView3.setImageDrawable(getResources().getDrawable(R.drawable.page));
					break;
				case 3:
					mImageView3.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
					mImageView2.setImageDrawable(getResources().getDrawable(R.drawable.page));
					break;
				default:
					break;
				}
				currIndex = arg0;
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
		
		/*startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Welcome.this, MainActivity.class);
				startActivity(intent);
				Welcome.this.finish();
			}
		});*/
		
		LayoutInflater mLayoutInflater = LayoutInflater.from(this);
		View view1 = mLayoutInflater.inflate(R.layout.wel1, null);
		View view2 = mLayoutInflater.inflate(R.layout.wel2, null);
		View view3 = mLayoutInflater.inflate(R.layout.wel3, null);
//		View view4 = mLayoutInflater.inflate(R.layout.wel4, null);
		View view5 = mLayoutInflater.inflate(R.layout.wel5, null);
		view1.bringToFront();
		
		final ArrayList<View>  views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
//		views.add(view4);
		views.add(view5);
		
		PagerAdapter mPagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				// TODO Auto-generated method stub
				((ViewPager)container).removeView(views.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				// TODO Auto-generated method stub
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}
		};
		mViewPager.setAdapter(mPagerAdapter);
	}

	private void findViewById() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) findViewById(R.id.welcome);
		mImageView0 = (ImageView) findViewById(R.id.imview0);
		mImageView1 = (ImageView) findViewById(R.id.imview1);
		mImageView2 = (ImageView) findViewById(R.id.imview2);
		mImageView3 = (ImageView) findViewById(R.id.imview3);
//		mImageView4 = (ImageView) findViewById(R.id.imview4);
//		startButton = (Button) findViewById(R.id.start);
	}
	
	public void startbutton(View v) {  
      	Intent intent = new Intent();
      	intent.setClass(Welcome.this, MainActivity.class);
		startActivity(intent);
		this.finish();
      }  
	
}
