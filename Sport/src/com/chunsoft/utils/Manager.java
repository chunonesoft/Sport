package com.chunsoft.utils;

import java.util.Stack;

import android.support.v4.app.FragmentActivity;

/**
 * Created by chunsoft on 16/3/3.
 */

public class Manager {
	private static Manager instance;
	private Stack<FragmentActivity> activityStack;// activity栈

	private Manager() {
	}

	// 单例模式
	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	// 把一个activity压入栈中
	public void pushOneActivity(FragmentActivity actvity) {
		if (activityStack == null) {
			activityStack = new Stack<FragmentActivity>();
		}
		activityStack.add(actvity);
		// Log.d("MyActivityManager ", "size = " + activityStack.size());
	}

	// 获取栈顶的activity，先进后出原则
	public FragmentActivity getLastActivity() {
		return activityStack.lastElement();
	}

	// 移除一个activity
	public void popOneActivity(FragmentActivity activity) {
		if (activityStack != null && activityStack.size() > 0) {
			if (activity != null) {
				activity.finish();
				activityStack.remove(activity);
				activity = null;
			}
		}
	}

	// 退出所有activity
	public void finishAllActivity() {
		if (activityStack != null) {
			while (activityStack.size() > 0) {
				FragmentActivity activity = getLastActivity();
				if (activity == null)
					break;
				popOneActivity(activity);
			}
		}
	}
}
