package com.chunsoft.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {
	public static String Sport = "Sport";

	public static String getSharePreStr(Context mContext, String field,
			String defaultValue) {
		SharedPreferences sp = (SharedPreferences) mContext
				.getSharedPreferences(Sport, 0);
		String s = sp.getString(field, defaultValue);
		return s;
	}

	public static int getSharePreInt(Context mContext, String field,
			int defaultValue) {
		SharedPreferences sp = (SharedPreferences) mContext
				.getSharedPreferences(Sport, 0);
		int i = sp.getInt(field, defaultValue);
		return i;
	}

	public static boolean getSharePreBoolean(Context mContext, String field,
			boolean defaultValue) {
		SharedPreferences sp = (SharedPreferences) mContext
				.getSharedPreferences(Sport, 0);
		boolean i = sp.getBoolean(field, defaultValue);
		return i;
	}

	public static void putSharePre(Context mContext, String field, String value) {
		SharedPreferences sp = (SharedPreferences) mContext
				.getSharedPreferences(Sport, 0);
		sp.edit().putString(field, value).commit();
	}

	public static void putSharePre(Context mContext, String field, int value) {
		SharedPreferences sp = (SharedPreferences) mContext
				.getSharedPreferences(Sport, 0);
		sp.edit().putInt(field, value).commit();
	}

	public static void putSharePre(Context mContext, String field, Boolean value) {
		SharedPreferences sp = (SharedPreferences) mContext
				.getSharedPreferences(Sport, 0);
		sp.edit().putBoolean(field, value).commit();
	}

	public static void clearSharePre(Context mContext) {
		try {
			SharedPreferences sp = (SharedPreferences) mContext
					.getSharedPreferences(Sport, 0);
			sp.edit().clear().commit();
		} catch (Exception e) {
		}
	}

	public static void clearKeySharePre(Context mContext, String key) {
		SharedPreferences sp = mContext.getSharedPreferences(Sport, 0);
		sp.edit().remove(key).commit();
	}

}
