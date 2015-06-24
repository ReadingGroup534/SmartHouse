package com.smarthouse.activity;

import com.smarthouse.fragment.DoorFragment;
import com.smarthouse.fragment.LightsFragment;
import com.smarthouse.fragment.OthersFragment;
import com.smarthouse.view.SlidingMenuView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Main extends FragmentActivity {

	private RadioButton doorButton, lightsButton, othersButton, settingButton;
	private RadioGroup radioGroup;
	
	private SlidingMenuView slidingMenu;

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
					changeFragment(new DoorFragment());
					break;
				case R.id.lights:
					// 当点击了灯光tab时，选中第2个tab
					setTabSelection(1);
					changeFragment(new LightsFragment());
					break;
				case R.id.others:
					// 当点击了其他tab时，选中第3个tab
					setTabSelection(2);
					changeFragment(new OthersFragment());
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
		doorButton.setTextColor(Color.WHITE);
		lightsButton.setChecked(false);
		lightsButton.setTextColor(Color.WHITE);
		othersButton.setChecked(false);
		othersButton.setTextColor(Color.WHITE);
		settingButton.setChecked(false);
		settingButton.setTextColor(Color.WHITE);
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

}
