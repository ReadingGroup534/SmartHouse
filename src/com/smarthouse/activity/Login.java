package com.smarthouse.activity;

import java.net.URL;


import android.R.bool;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {
	/**
	 * 登录界面
	 * 
	 * @author liyangchao
	 */

	private Button loginbuButton, forgetButton, reButton;
	private EditText user, passwd;
	private String userString, passwdString;
	private CheckBox rem_passwd, auto_loginBox;
	private SharedPreferences spPreferences;
	private ProgressDialog mDialog;
	private boolean flag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		findViewById();
		// get the user's input

		loginbuButton.setOnClickListener(this);
		forgetButton.setOnClickListener(this);
		reButton.setOnClickListener(this);

		// rem_passwd.setOnCheckedChangeListener((OnCheckedChangeListener)
		// this);
		// auto_loginBox.setOnCheckedChangeListener((OnCheckedChangeListener)
		// this);

		spPreferences = this.getSharedPreferences("userInfo",
				Context.MODE_WORLD_WRITEABLE);

		rem_passwd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (rem_passwd.isChecked()) {
					Log.i("lyc", "rem_pass is checked");
					spPreferences.edit().putBoolean("ISRMB", true).commit();
				} else {
					Log.i("lyc", "rem_pass is not checked");
					spPreferences.edit().putBoolean("ISRMB", false).commit();
					auto_loginBox.setChecked(false);
				}
			}
		});

		auto_loginBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (rem_passwd.isChecked()) {
					if (auto_loginBox.isChecked()) {
						Log.i("lyc", "auto_login is checked");
						spPreferences.edit().putBoolean("AUTO_LOGIN", true)
								.commit();
					} else {
						Log.i("lyc", "auto_login is not checked");
						spPreferences.edit().putBoolean("AUTO_LOGIN", false)
								.commit();
					}
					// auto_loginBox.setChecked(false);
				}
			}
		});

		if (spPreferences != null) {
			// remember the passwd
			if (spPreferences.getBoolean("ISRMB", false) == true) {
				user.setText(spPreferences.getString("USER", null));
				passwd.setText(spPreferences.getString("PASSWD", null));
				rem_passwd.setChecked(true);
			}
			// 判断自动登陆多选框状�?
			if (spPreferences.getBoolean("AUTO_LOGIN", false) == true) {
				// 设置默认为自动登录状�?
				auto_loginBox.setChecked(true);
				creatDialog();
				new Thread() {
					public void run() {
						try {
							Thread.sleep(2000);
							if (mDialog.isShowing()) {
								mDialog.dismiss();
							}
							Intent intent = new Intent(Login.this,
									LoadingActivity.class);
							startActivity(intent);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		}

		// user.setOnFocusChangeListener(new OnFocusChangeListener() {
		//
		// @Override
		// public void onFocusChange(View arg0, boolean arg1) {
		// // TODO Auto-generated method stub
		// if (!arg1) {
		// user.setText("shiqu jiaodian");
		// }
		// }
		// });
		user.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus) {
					if (user.getText().toString().length() == 0) {
						Toast.makeText(getApplicationContext(), "用户名不能为空", 1)
								.show();
					}
				}
			}
		});
	}

	private void findViewById() {
		loginbuButton = (Button) findViewById(R.id.login_login_btn);
		forgetButton = (Button) findViewById(R.id.forget_passwd);
		reButton = (Button) findViewById(R.id.login_reback_btn);
		user = (EditText) findViewById(R.id.login_user_edit);
		passwd = (EditText) findViewById(R.id.login_passwd_edit);
		rem_passwd = (CheckBox) findViewById(R.id.cb_passwd);
		auto_loginBox = (CheckBox) findViewById(R.id.auto_login);
	}

	private void creatDialog() {
		// TODO Auto-generated method stub
		mDialog = new ProgressDialog(this);
		mDialog.setTitle("验证中");
		mDialog.setMessage("正在登陆请稍等...");
		mDialog.setIndeterminate(true);
		mDialog.setCancelable(true);
		mDialog.show();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login_login_btn:
			flag = addUSer();
			Log.i("lyc", "user:" + userString + "pass:" + passwdString);
			/*
			 * if("lyc".equals(userString)&&"123456".equals(passwdString)){
			 * Log.i("lyc","user:"+userString+"pass:"+passwdString);
			 * //登录�?功和记�?密�?框为选中状�?�?�?存用户信�?� if (rem_passwd.isChecked()) { //记�?用户�??�?密�?
			 * Editor editor = spPreferences.edit(); editor.putString("USER",
			 * userString); editor.putString("PASSWD", passwdString);
			 * editor.commit(); } Intent intent = new
			 * Intent(Login.this,LoadingActivity.class); startActivity(intent);
			 * Login.this.finish(); }else if
			 * ("".equals(userString)||"".equals(passwdString)) { new
			 * AlertDialog.Builder(Login.this)
			 * .setIcon(getResources().getDrawable(
			 * R.drawable.login_error_icon))
			 * .setTitle("登录错误").setMessage("用户�??或者密�?为空�?")
			 * .setPositiveButton("确定", new DialogInterface.OnClickListener() {
			 * 
			 * @Override public void onClick(DialogInterface dialog, int which)
			 * { // TODO Auto-generated method stub dialog.cancel(); }
			 * }).create().show(); }
			 */
			if (flag) {
				Intent intent = new Intent(Login.this, LoadingActivity.class);
				startActivity(intent);
				Login.this.finish();
			}
			break;
		case R.id.forget_passwd:
			Uri uri = Uri.parse("http://www.qq.com");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			Toast.makeText(Login.this, "forget", Toast.LENGTH_SHORT).show();
			break;
		case R.id.login_reback_btn:
			finish();
			break;
		// 监�?�记�?密�?多选框按钮事件
		// case R.id.cb_passwd:
		//
		// break;
		// case R.id.auto_login:
		//
		// break;
		default:
			break;
		}

	}

	private boolean addUSer() {

		userString = user.getText().toString();
		passwdString = passwd.getText().toString();
		if (userString.length() == 0) {
			user.setError("用户名能为空");
			return false;
		}
		if (passwdString.length() == 0) {
			passwd.setError("密码不能为空");
			return false;
		}
		if (!"123".equals(userString) || !"123".equals(passwdString)) {
			new AlertDialog.Builder(Login.this)
					.setIcon(
							getResources().getDrawable(
									R.drawable.login_error_icon))
					.setTitle("登录错误")
					.setMessage("用户名或者密码错误")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int arg1) {
									// TODO Auto-generated method stub
									dialog.cancel();
								}

							}).create().show();
			return false;
		}
		return true;
	}

}
