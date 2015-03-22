package com.liangxiao.usercars.application;

import java.util.Hashtable;

import android.app.Application;

import com.thinkland.sdk.util.CommonFun;

public class UserCarsApplication extends Application {
	private static UserCarsApplication webserinfo = null;

	@Override
	public void onCreate() {
		super.onCreate();
		CommonFun.initialize(getApplicationContext(), false);
	}

	private Hashtable<String, Object> memCacheRegion = new Hashtable<String, Object>();

	public static UserCarsApplication getWebserinfo() {
		if (webserinfo == null)
			webserinfo = new UserCarsApplication();
		return webserinfo;
	}

	/**
	 * �����󱣴浽�ڴ滺����
	 * 
	 * @param key
	 * @param value
	 */
	public void setMemCache(String key, Object value) {
		memCacheRegion.put(key, value);
	}

	/**
	 * ���ڴ滺���л�ȡ����
	 * 
	 * @param key
	 * @return
	 */
	public Object getMemCache(String key) {
		return memCacheRegion.get(key);
	}
}
