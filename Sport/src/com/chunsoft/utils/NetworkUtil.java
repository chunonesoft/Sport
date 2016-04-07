package com.chunsoft.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
	public static boolean isNetWorkAvailable(ConnectivityManager cm) {

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();
		return isConnected;
	}
}
