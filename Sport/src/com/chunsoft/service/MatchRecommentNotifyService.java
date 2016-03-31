package com.chunsoft.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.chunsoft.bean.MatchRecommend;
import com.chunsoft.match.MatchImmediateShowActivity;
import com.chunsoft.net.Constant;
import com.chunsoft.net.GetJsonData;
import com.chunsoft.sport.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MatchRecommentNotifyService extends IntentService {
	NotificationManager mNotificationManager;
	SharedPreferences mPrefs;
	private JSONArray returnData;

	public MatchRecommentNotifyService() {
		super("MatchRecommentNotifyService");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		mPrefs = getSharedPreferences("com.yinglang.sport", 0);
		String lastRecommendDataTime = mPrefs.getString(
				"last_match_recommends_data_time", "2016-02-01 00:00");
		String lastRecommendDataTime1 = lastRecommendDataTime;
		String lastRecommendDataTime2 = lastRecommendDataTime;
		lastRecommendDataTime = lastRecommendDataTime1.substring(0, 10) + "%20"
				+ lastRecommendDataTime2.substring(12, 16);
		Log.e("-------1111", lastRecommendDataTime);
		Gson gson = new Gson();
		Log.e("--------", getMatchRecommend("2016-03-31").toString());
		List<MatchRecommend> recommends = new ArrayList<MatchRecommend>();
		recommends = gson.fromJson(getMatchRecommend(lastRecommendDataTime)
				.toString(), new TypeToken<List<MatchRecommend>>() {
		}.getType());
		if (!lastRecommendDataTime.equals("2016-02-01%2000:00")) {
			for (MatchRecommend recommend : recommends) {
				Intent matchShowIntent = new Intent(this,
						MatchImmediateShowActivity.class);
				matchShowIntent.putExtra("match_id", recommend.getMatch_id());
				PendingIntent pendingIntent = PendingIntent.getActivity(this,
						recommend.getMatch_id(), matchShowIntent, 0);
				Uri alarmSound = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				Notification noti = new Notification.Builder(this)
						.setContentTitle(recommend.getRecommend_type_des())
						.setContentText(
								recommend.getLeague_name() + ":"
										+ recommend.getMatch_des()
										+ recommend.getMatch_time())
						.setSmallIcon(R.drawable.logo)
						.setContentIntent(pendingIntent).setSound(alarmSound)
						.build();
				mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(recommend.getMatch_id(), noti);
			}
		}
		// 记录最后一个比赛的时间的时间，记录到Android系统中
		if (recommends.size() > 0) {
			lastRecommendDataTime = recommends.get(0).getData_time().toString();
		}

		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putString("last_match_recommends_data_time",
				lastRecommendDataTime);
		editor.commit();
	}

	private JSONArray getMatchRecommend(String data_time) {
		String URL = Constant.MATCH_RECOMMENDS + "?data_time=" + data_time;
		returnData = new JSONArray();
		returnData = new GetJsonData().getJSONArrayDataHGET(URL);
		return returnData;
	}
}
