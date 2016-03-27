package com.chunsoft.bean;

/**
 * interface callback
 * 
 * @author Administrator
 * 
 * @param <T>
 */
public interface VolleyDataCallback<T> {
	void onSuccess(T datas);
}
