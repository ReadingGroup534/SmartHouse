package com.smarthouse.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity{
	
	private Button returnButton;
	private EditText username,passwd,repasswd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		findViewById();
		
		returnButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		username.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus) {
					if (username.getText().toString().trim().length() < 4) {
						Toast.makeText(getApplicationContext(), "用户名不能少于4个字符", 1).show();
					}
				}
			}
		});
		
		passwd.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus) {
					if (passwd.getText().toString().trim().length() < 4) {
						Toast.makeText(getApplicationContext(), "密码不能少于6个字符", 1).show();
					}
				}
			}
		});
		
		repasswd.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus) {
					if (!passwd.getText().toString().trim().equals(repasswd.getText().toString().trim())) {
						Toast.makeText(getApplicationContext(), "两次输入密码不一致", 1).show();
					}
				}
			}
		});
		
	}

	private void findViewById() {
		// TODO Auto-generated method stub
		returnButton = (Button) findViewById(R.id.regrister_back);
		username = (EditText) findViewById(R.id.reg_user_edit);
		passwd = (EditText) findViewById(R.id.reg_passwd_edit);
		repasswd = (EditText) findViewById(R.id.reg_confirm_passwd_edit);
	}

	
}
