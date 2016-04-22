package com.chunsoft.net;

import java.util.Calendar;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.chunsoft.service.MatchRecommentNotifyService;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
			boolean isServiceRunning = false;
			ActivityManager manager = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			for (RunningServiceInfo service : manager
					.getRunningServices(Integer.MAX_VALUE)) {
				if ("com.chunsoft.service.MatchRecommentNotifyService"
						.equals(service.service.getClassName()))
				// Service的类名
				{
					isServiceRunning = true;
				}
			}
			if (!isServiceRunning) {
				Intent intents = new Intent(context,
						MatchRecommentNotifyService.class);
				// 设置定时查询
				PendingIntent pendingIntent = PendingIntent.getService(context,
						0, intents, 0);
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.add(Calendar.SECOND, 10);
				long frequency = 120 * 1000; // 2分钟检查一次
				AlarmManager alarmManager = (AlarmManager) context
						.getSystemService(Context.ALARM_SERVICE);
				alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
						calendar.getTimeInMillis(), frequency, pendingIntent);
			}
		}
	}
}
