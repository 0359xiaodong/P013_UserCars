package com.liangxiao.usercars.toast;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class myToast extends Handler {
	private Activity activity;

	public myToast(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void handleMessage(Message msg) {

		switch (msg.arg1) {
		case 1:
			showInfo("��¼�ɹ���");
			break;
		case 2:
			showInfo("�ֻ��Ż�����֤�벻��Ϊ��!");
			break;
		case 3:
			showInfo("���ͳɹ�!");
			break;
		case 4:
			showInfo("����δ����!");
			break;
		case 5:
			showInfo("��������!");
			break;
		case 6:
			showInfo("����д�ֻ�����");
			break;
		case 7:
			showInfo("����ʧ��!");
			break;
		case 8:
			showInfo("�û������ֻ��Ų��ܿգ�");
			break;
		case 9:
			showInfo("���벻�ܿգ�");
			break;
		default:
			break;
		}
		super.handleMessage(msg);
	}

	/**
	 * ��ʾ��ʾ��Ϣ
	 * 
	 * @param info
	 */
	public void showInfo(String info) {
		Toast.makeText(activity.getApplicationContext(), info,
				Toast.LENGTH_SHORT).show();
	}
}
