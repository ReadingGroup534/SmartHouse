package com.smarthouse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
public class LoginActivity extends Activity implements OnClickListener{
	
	private Button log_Button,reg_Button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		findViewById();
		log_Button.setOnClickListener(this);
		reg_Button.setOnClickListener(this);
		
	}
	
	private void findViewById() {
		log_Button = (Button) findViewById(R.id.main_login_btn);
		reg_Button = (Button) findViewById(R.id.main_regist_btn);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.main_login_btn:
			Intent i = new Intent(LoginActivity.this,Login.class);
			startActivity(i);
			LoginActivity.this.finish();
			break;
		case R.id.main_regist_btn:
			Intent intent = new Intent(LoginActivity.this,Register.class);
			startActivity(intent);
			LoginActivity.this.finish();
			//Toast.makeText(LoginActivity.this, "敬请期待。。。",Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
