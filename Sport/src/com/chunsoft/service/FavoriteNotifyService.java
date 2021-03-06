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
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.chunsoft.bean.MatchEvent;
import com.chunsoft.match.MatchImmediateShowActivity;
import com.chunsoft.net.GetJsonData;
import com.chunsoft.sport.R;
import com.chunsoft.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FavoriteNotifyService extends IntentService {
	NotificationManager mNotificationManager;
	JSONArray returnData;

	public FavoriteNotifyService() {
		super("FavoriteNotifyService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String lastEventDataTime = PreferencesUtils.getSharePreStr(this,
				"lastEventDataTime", "2016-02-01 00:00");
		Log.e("---------lastEventDataTime", "000" + lastEventDataTime);
		if (lastEventDataTime.contains(" ")) {
			String lastEventDataTime1 = lastEventDataTime;
			String lastEventDataTime2 = lastEventDataTime;
			lastEventDataTime = lastEventDataTime1.substring(0, 10) + "%20"
					+ lastEventDataTime2.substring(11, 16);
			Log.e("lastEventDataTime--------", "字符串拼接" + lastEventDataTime);
		}

		Log.e("lastEventDataTime--------", "1" + lastEventDataTime);
		int userId = PreferencesUtils.getSharePreInt(this, "id", 10022);
		Log.e("userId----------------", userId + "");
		try {
			Gson gson = new Gson();
			// Log.e("--------getMatchEventRecommend1",
			// getMatchEventRecommend(userId, "2016-03-01").toString());
			List<MatchEvent> recommends = new ArrayList<MatchEvent>();
			recommends = gson.fromJson(
					getMatchEventRecommend(userId, lastEventDataTime)
							.toString(), new TypeToken<List<MatchEvent>>() {
					}.getType());

			// 第一次不提示
			if (!lastEventDataTime.equals("2016-02-01%2000:00")) {
				for (MatchEvent evt : recommends) {
					Intent matchShowIntent = new Intent(this,
							MatchImmediateShowActivity.class);
					matchShowIntent.putExtra("match_id", evt.getMatch_id());
					PendingIntent pendingIntent = PendingIntent.getActivity(
							this, evt.getMatch_id(), matchShowIntent, 0);
					Uri alarmSound = RingtoneManager
							.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

					Notification noti = new Notification.Builder(this)
							.setContentTitle(evt.getMatch_des())
							.setContentText(
									evt.getHappen_time() + "' "
											+ evt.getHome_or_guest() + " "
											+ evt.getEvent_type_des() + " "
											+ evt.getPlayer_name1() + " "
											+ evt.getPlayer_name2())
							.setSmallIcon(R.drawable.logo)
							.setContentIntent(pendingIntent)
							.setSound(alarmSound).build();
					mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
					mNotificationManager.notify(evt.getMatch_id(), noti);
				}
			}

			// 记录最后一个比赛时间的时间,记录在android系统中去
			if (recommends.size() > 0) {
				lastEventDataTime = recommends.get(0).getData_time();
			}

			PreferencesUtils.putSharePre(this, "lastEventDataTime",
					lastEventDataTime);

		} catch (Exception ex) {
			Log.e("FavoriteNotidyService Error:" + ex.getMessage(),
					"-----------");
		}
	}

	private JSONArray getMatchEventRecommend(int userId, String data_time) {
		String URL = "my_favorites/" + userId + "/events.json" + "?data_time="
				+ data_time;
		Log.e("URL-----", URL);
		returnData = new JSONArray();
		returnData = new GetJsonData().getJSONArrayDataHGET(URL);
		return returnData;
	}
}
