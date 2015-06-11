package com.smarthouse.activity;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class Main extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment fragment = new EssayFragment();
		ft.replace(R.id.fragment_layout, fragment);
		ft.commit();
	}
		
}
