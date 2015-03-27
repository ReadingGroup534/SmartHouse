package com.smarthouse.activity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.smarthouse.handler.DoorHanlder;
import com.smarthouse.handler.LightsHandler;
import com.smarthouse.handler.OthersHandler;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	private RadioButton doorButton,lightsButton,othersButton,settingButton;
	private RadioGroup radioGroup;
//	doorTextView,lightsTextView,othersTextView;
	private static MainActivity instanceActivity = null;
	private ViewPager mViewPager;
	private int currIndex = 0;
	private int ScreenWidth;
	private int one,two;
	//listview 实例对象
//	private ListView mListView;
	private TextView doorDetails,lightsDetails,othersDetails;
	private boolean isDoubleClick = false; // 点击两次返回键推出程序

	private DoorHanlder doorHanlder = null;
	private LightsHandler lightsHandler = null;
	private OthersHandler othersHandler = null;
	
	
	public DoorHanlder getDoorHanlder() {
		return doorHanlder;
	}

	public void setDoorHanlder(DoorHanlder doorHanlder) {
		this.doorHanlder = doorHanlder;
	}

	public LightsHandler getLightsHandler() {
		return lightsHandler;
	}

	public void setLightsHandler(LightsHandler lightsHandler) {
		this.lightsHandler = lightsHandler;
	}

	public OthersHandler getOthersHandler() {
		return othersHandler;
	}

	public void setOthersHandler(OthersHandler othersHandler) {
		this.othersHandler = othersHandler;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainactivity);
		
		//set ActionBar's back action
		ActionBar actionBar = this.getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		instanceActivity = this;
		findViewById();
		
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
				
		DisplayMetrics dm = getResources().getDisplayMetrics();
		ScreenWidth = dm.widthPixels;
		one = ScreenWidth/3;
		two = one*2;
		
		
		//page in V4
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View view1 = layoutInflater.inflate(R.layout.door_details, null);
		View view2 = layoutInflater.inflate(R.layout.lights_details, null);
		View view3 = layoutInflater.inflate(R.layout.door_details, null);
		View view4 = layoutInflater.inflate(R.layout.setting_layout, null);	
		
	    //获取每个页面对应按钮的实例
	    doorDetails = (TextView) view1.findViewById(R.id.doorDetails);
	    lightsDetails = (TextView) view2.findViewById(R.id.lightsDetails);
		
	    radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.door:
					setTabSelection(0);
					break;
				case R.id.lights:
					// 当点击了灯光tab时，选中第2个tab
					setTabSelection(1);
					break;
				case R.id.others:
					// 当点击了其他tab时，选中第3个tab
					setTabSelection(2);
					break;
				case R.id.setting:
					// 当点击了设置tab时，选中第4个tab
					setTabSelection(3);
					break;
				default:
					break;
				}
			}
		});
	    //默认选中第一个
	    setTabSelection(0);
	    
		/*shake.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "click shake button", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this,ShakeActivity.class);
				startActivity(intent);
				
			}
		});*/
		
	    final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);
		
		doorHanlder = new DoorHanlder(this);
		lightsHandler = new LightsHandler(this);
		
		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}


			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};
		mViewPager.setAdapter(mPagerAdapter);
		
	}


	/**
	 * 根据传入的index参数来设置选中的tab页。
	 * @param i
	 * 每个tab页对应的下标。0表示房门，1表示灯光，2表示其他，3表示设置。
	 */
	protected void setTabSelection(int index) {
		// TODO Auto-generated method stub
		// 每次选中之前先清除掉上次的选中状态
		clearSelection();
		switch (index) {
		case 0:
			setButton(doorButton);
			break;
		case 1:
			setButton(lightsButton);
			break;
		case 2:
			setButton(othersButton);
			break;
		case 3:
			setButton(settingButton);
		default:
			break;
		}
		mViewPager.setCurrentItem(index);
	}

	public void UpdateDoorDetails(String string) {
		doorDetails.setText(string);
	}
	
	public void UpdateLightsDetails(String string) {
		lightsDetails.setText(string);
	}
	
	public void UpdateOthersDetails(String string) {
		othersDetails.setText(string);
	}
	
	private void findViewById(){
		radioGroup = (RadioGroup) findViewById(R.id.foot_radio_group);
		doorButton = (RadioButton) findViewById(R.id.door);
		lightsButton = (RadioButton) findViewById(R.id.lights);
		othersButton = (RadioButton) findViewById(R.id.others);
		settingButton = (RadioButton) findViewById(R.id.setting);
		mViewPager = (ViewPager) findViewById(R.id.tabpager);
	}
	
	
	//page change method
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			clearSelection();
			switch (arg0) {
			case 0:
				setButton(doorButton);
				break;
			case 1:
				setButton(lightsButton);
				break;
			case 2:
				setButton(othersButton);
				break;
			case 3:
				setButton(settingButton);
			default:
				break;
			}
		}
	}
	
	private void setButton(RadioButton button){
		button.setChecked(true);
		button.setTextColor(Color.GREEN);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		//�?�索视窗，因为showAsAction="ifRoom"，所以图三中出现了�?�索按钮  
//		 MenuItem searchItem = menu.findItem(R.id.action_search);
//		 SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
		 SearchView searchView2 = (SearchView) menu.findItem(R.id.action_search).getActionView();
		return super.onCreateOptionsMenu(menu);
	}
	


	private void clearSelection() {
		doorButton.setChecked(false);
		doorButton.setTextColor(Color.WHITE);
		lightsButton.setChecked(false);
		lightsButton.setTextColor(Color.WHITE);
		othersButton.setChecked(false);
		othersButton.setTextColor(Color.WHITE);
		settingButton.setChecked(false);
		settingButton.setTextColor(Color.WHITE);
	}

	@Override
	/*public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_search:
			Log.i("lyc","click search button");
			Toast.makeText(MainActivity.this, "你点解了�?�索按钮", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_add:
			Log.i("lyc","click add button");
			Toast.makeText(MainActivity.this, "你点解了添加按钮", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}*/
	
	//onMenuOpened()方法用于让隐藏在overflow当中的Action按钮的图标显示出来
	  public boolean onMenuOpened(int featureId, Menu menu) {  
	        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {  
	            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {  
	                try {  
	                    Method m = menu.getClass().getDeclaredMethod(  
	                            "setOptionalIconsVisible", Boolean.TYPE);  
	                    m.setAccessible(true);  
	                    m.invoke(menu, true);  
	                } catch (Exception e) {  
	                }  
	            }  
	        }  
	        return super.onMenuOpened(featureId, menu);  
	    }  
	
	Timer mQuitTimer = new Timer();

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				if (!isDoubleClick) {
					isDoubleClick = true;
					Toast.makeText(this, getText(R.string.msg_quit),
							Toast.LENGTH_LONG).show();
					mQuitTimer.schedule(new TimerTask() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							isDoubleClick = false;
						}
					}, 1500);
				} else {
					System.exit(0);
				}
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
