package com.smarthouse.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.smarthouse.fragment.DoorFragment;
import com.smarthouse.fragment.LightsFragment;
import com.smarthouse.fragment.OthersFragment;
import com.smarthouse.view.SlidingMenuView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Main extends FragmentActivity {

	private RadioButton doorButton, lightsButton, othersButton, settingButton;
	private RadioGroup radioGroup;
	
	private SlidingMenuView slidingMenu;
	
	private boolean isDoubleClick = false; // 点击两次返回键推出程序
	
	private DoorFragment doorFragment;
	private LightsFragment lightsFragment;
	private OthersFragment othersFragment;
	
	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		initView();

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.door:
					setTabSelection(0);
					if (doorFragment == null) {
						doorFragment = new DoorFragment();
					}
					changeFragment(doorFragment);
					break;
				case R.id.lights:
					// 当点击了灯光tab时，选中第2个tab
					setTabSelection(1);
					if (lightsFragment == null) {
						lightsFragment = new LightsFragment();
					}
					changeFragment(lightsFragment);
					break;
				case R.id.others:
					// 当点击了其他tab时，选中第3个tab
					setTabSelection(2);
					if (othersFragment == null) {
						othersFragment = new OthersFragment();
					}
					changeFragment(new OthersFragment());
					break;
				case R.id.setting:
					// 当点击了设置tab时，选中第4个tab
					setTabSelection(3);
					if (doorFragment == null) {
						doorFragment = new DoorFragment();
					}
					changeFragment(doorFragment);
					break;
				}
			}
		});
		
		// 默认选中第一个
		setTabSelection(0);

	}

	private void initView() {
		// TODO Auto-generated method stub
		radioGroup = (RadioGroup) findViewById(R.id.foot_radio_group);
		doorButton = (RadioButton) findViewById(R.id.door);
		lightsButton = (RadioButton) findViewById(R.id.lights);
		othersButton = (RadioButton) findViewById(R.id.others);
		settingButton = (RadioButton) findViewById(R.id.setting);
		slidingMenu = (SlidingMenuView) findViewById(R.id.id_menu);
	}

	private void clearSelection() {
		doorButton.setChecked(false);
		doorButton.setTextColor(Color.GRAY);
		lightsButton.setChecked(false);
		lightsButton.setTextColor(Color.GRAY);
		othersButton.setChecked(false);
		othersButton.setTextColor(Color.GRAY);
		settingButton.setChecked(false);
		settingButton.setTextColor(Color.GRAY);
	}

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 * @param i
	 *            每个tab页对应的下标。0表示房门，1表示灯光，2表示其他，3表示设置。
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
	}


	private void setButton(RadioButton button) {
		button.setChecked(true);
		button.setTextColor(Color.GREEN);
	}

	private void changeFragment(Fragment targetFragment) {
		// TODO Auto-generated method stub
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_layout, targetFragment, "fragment")
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}
	
	//点击按钮显示左边侧滑栏
	public void onClickLiftMenu(View v)
	{
		slidingMenu.toggle();
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
