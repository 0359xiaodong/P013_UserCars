package com.liangxiao.usercars;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.liangxiao.usercars.application.UserCarsApplication;
import com.liangxiao.usercars.login.MainActivity_registered;
import com.liangxiao.usercars.tabviewpager.MainActivity_FragmentViewpager;
import com.liangxiao.usercars.toast.BaseActivity;
import com.liangxiao.usercars.toast.myToast;

public class MainActivity_login extends BaseActivity implements OnClickListener {
	private Button btn_login, btn_registered;// ��¼,ע��
	private EditText et_put_user, et_put_pwd;// �û���/�ֻ���,����
	private Handler handler;
	private Message msg;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		handler = new myToast(MainActivity_login.this);
		initView();
	}

	/**
	 * ��ʼ��
	 */
	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	private void initView() {
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_registered = (Button) findViewById(R.id.btn_registered);
		et_put_user = (EditText) findViewById(R.id.et_put_user);
		et_put_pwd = (EditText) findViewById(R.id.et_put_pwd);

		btn_login.setOnClickListener(this);
		btn_registered.setOnClickListener(this);

		// ���ʵ������
		sp = this.getSharedPreferences("userinfo", Context.MODE_WORLD_READABLE);
		et_put_user.setText(sp.getString("phoneNum", ""));
		et_put_pwd.setText(sp.getString("pwd", ""));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			submit();
			break;
		case R.id.btn_registered:
			Intent intent = new Intent(MainActivity_login.this,
					MainActivity_registered.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void submit() {
		String phoneNum = et_put_user.getText().toString().trim();
		String pwd = et_put_pwd.getText().toString().trim();
		isEditEmpty(phoneNum, pwd);
	}

	/**
	 * �ж�������Ƿ�Ϊ��
	 */
	private void isEditEmpty(String phoneNum, String pwd) {

		// �жϲ���Ϊ���ֻ��Ż������
		if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(pwd)) {
			msg = handler.obtainMessage();
			msg.arg1 = 8;
			handler.sendMessage(msg);
			return;
		}
		// showDialogs(phoneNum, code);
		// loading��ȡ��ing
		showDialog(getResources().getString(
				R.string.smssdk_get_verification_code_content));
		// �����¼״̬
		Editor editor = sp.edit();
		editor.putString("phoneNum", phoneNum);
		editor.putString("pwd", pwd);
		editor.commit();
		// ���뻺��
		UserCarsApplication.getWebserinfo().setMemCache("phoneNum", phoneNum);
		UserCarsApplication.getWebserinfo().setMemCache("pwd", pwd);

		new Thread(new Runnable() {

			@Override
			public void run() {

				try {

					Thread.sleep(1000);
					closeDialog();

				} catch (Exception e) {
					e.printStackTrace();
				}
				Intent intent = new Intent(MainActivity_login.this,
						MainActivity_FragmentViewpager.class);
				startActivity(intent);
			}
		}).start();

	}
}
