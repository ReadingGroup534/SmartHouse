package com.smarthouse.fragment;

import java.util.ArrayList;
import java.util.List;

import com.smarthouse.activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class OthersFragment extends Fragment implements OnCheckedChangeListener {

	private View parentView;
	private RadioGroup radioGroup;
	private RadioButton rbTongZhi, rbDongTai;
	private ViewPager viewPager;
	private ImageView imageView;

	List<Fragment> listFragments = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		parentView = inflater.inflate(R.layout.fragment_others, null);

		radioGroup = (RadioGroup) parentView.findViewById(R.id.radioGroup);
		rbTongZhi = (RadioButton) parentView.findViewById(R.id.rbTongZhi);
		rbDongTai = (RadioButton) parentView.findViewById(R.id.rbDongTai);
		viewPager = (ViewPager) parentView.findViewById(R.id.viewpagerHuDong);
		imageView = (ImageView) parentView.findViewById(R.id.iv_add);

		listFragments = new ArrayList<Fragment>();
		TongzhiFragment tongzhiFragment = new TongzhiFragment();
		DongtaiFragment dongtaiFragment = new DongtaiFragment();

		listFragments.add(tongzhiFragment);
		listFragments.add(dongtaiFragment);

		ZxzcAdapter zxzc = new ZxzcAdapter(getChildFragmentManager(),
				listFragments);
		viewPager.setAdapter(zxzc);
		zxzc.notifyDataSetChanged();

		radioGroup.setOnCheckedChangeListener(this);
		rbTongZhi.setChecked(true);

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				switch (arg0) {
				case 0:
					rbTongZhi.setChecked(true);
					break;
				case 1:
					rbDongTai.setChecked(true);
					break;
				default:
					break;
				}
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

		return parentView;
	}

	class ZxzcAdapter extends FragmentStatePagerAdapter {

		List<Fragment> list;

		public ZxzcAdapter(FragmentManager fm, List<Fragment> list) {
			super(fm);
			this.list = list;
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public int getCount() {

			return list.size();
		}

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkId) {
		// TODO Auto-generated method stub
		if (checkId == rbTongZhi.getId()) {
			viewPager.setCurrentItem(0);
		} else if (checkId == rbDongTai.getId()) {
			viewPager.setCurrentItem(1);
		}
	}

}
