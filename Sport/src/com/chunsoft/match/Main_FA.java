package com.chunsoft.match;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.chunsoft.event.Event_F;
import com.chunsoft.my.Login_A;
import com.chunsoft.my.My_F;
import com.chunsoft.service.MatchRecommentNotifyService;
import com.chunsoft.sport.R;
import com.chunsoft.utils.IntentUti;
import com.chunsoft.utils.PreferencesUtils;

public class Main_FA extends FragmentActivity implements OnClickListener {
	/** widget statement */
	private ImageView[] btn_menu = new ImageView[3];
	NotificationManager mNotificationManager;
	/** resources statement */
	int menu_id[] = { R.id.iv_menu_0, R.id.iv_menu_1, R.id.iv_menu_2 };
	int selectOn[] = { R.drawable.guide_home_on, R.drawable.guide_tfaccount_on,
			R.drawable.guide_account_on };
	int selectOff[] = { R.drawable.bt_menu_0_select,
			R.drawable.bt_menu_1_select, R.drawable.bt_menu_2_select };
	protected AlarmManager alarmManager;
	/** Match Fragment */
	// private Match_F match_F;
	private Match_F match_F;
	/** Event Fragment */
	private Event_F event_F;
	/** My Fragment */
	private My_F my_F;
	private Context mContext;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main);
		initView();
		displayNotifyIcon();
		// startFavoriteRecommendService();
		startMatchRecommendService();
	}

	private void initView() {
		mContext = Main_FA.this;
		for (int i = 0; i < btn_menu.length; i++) {
			btn_menu[i] = (ImageView) findViewById(menu_id[i]);
			btn_menu[i].setOnClickListener(this);
		}
		if (match_F == null) {
			// match_F = new Match_F();
			match_F = new Match_F();
			if (!match_F.isHidden()) {
				addFragment(match_F);
				showFragment(match_F);
			}
		} else {
			if (match_F.isHidden()) {
				showFragment(match_F);
			}
		}
		btn_menu[0].setImageResource(selectOn[0]);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_menu_0:
			if (match_F == null) {
				// match_F = new Match_F();
				match_F = new Match_F();
				if (!match_F.isHidden())
					addFragment(match_F);
				showFragment(match_F);
			} else {
				if (match_F.isHidden()) {
					showFragment(match_F);
				}
			}
			break;
		case R.id.iv_menu_1:
			if (event_F == null) {
				event_F = new Event_F();
				if (!event_F.isHidden())
					addFragment(event_F);
				showFragment(event_F);
			} else {
				if (event_F.isHidden()) {
					showFragment(event_F);
				}
			}
			break;
		case R.id.iv_menu_2:
			String phone = PreferencesUtils.getSharePreStr(mContext, "phone",
					"");
			if (phone.equals("")) {
				IntentUti.IntentTo(mContext, Login_A.class);
			} else {
				if (my_F == null) {
					my_F = new My_F();
					if (!my_F.isHidden())
						addFragment(my_F);
					showFragment(my_F);
				} else {
					if (my_F.isHidden()) {
						showFragment(my_F);
					}
				}
			}
			break;

		default:
			break;
		}
		// 设置按钮的选中和未选中资源
		for (int i = 0; i < menu_id.length; i++) {
			btn_menu[i].setImageResource(selectOff[i]);
			if (v.getId() == menu_id[i]) {
				btn_menu[i].setImageResource(selectOn[i]);
			}
		}
	}

	/** add Fragment */
	private void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.show_layout, fragment);
		ft.commit();
	}

	/** remove Fragment */
	private void removeFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}

	/** show Fragment */
	private void showFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		if (match_F != null) {
			ft.hide(match_F);
		}
		if (event_F != null) {
			ft.hide(event_F);
		}
		if (my_F != null) {
			ft.hide(my_F);
		}
		ft.show(fragment);
		ft.commitAllowingStateLoss();
	}

	private void startMatchRecommendService() {

		Intent intent = new Intent(this, MatchRecommentNotifyService.class);
		// 设置定时查询
		PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent,
				0);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.SECOND, 10);
		long frequency = 120 * 1000; // 2分钟检查一次
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
				calendar.getTimeInMillis(), frequency, pendingIntent);
	}

	// private void startFavoriteRecommendService() {
	// Intent intent = new Intent(this, FavoriteNotifyService.class);
	// // 设置定时查询
	// PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent,
	// 0);
	// Calendar calendar = Calendar.getInstance();
	// calendar.setTimeInMillis(System.currentTimeMillis());
	// calendar.add(Calendar.SECOND, 10);
	// long frequency = 120 * 1000; // 2分钟检查一次
	// alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
	// alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
	// calendar.getTimeInMillis(), frequency, pendingIntent);
	// }
	private void displayNotifyIcon() {
		Intent mainIntent = new Intent(this, Main_FA.class);

		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				mainIntent, 0);

		Notification noti = new Notification.Builder(this)
				.setContentTitle(getString(R.string.app_name))
				.setContentText(getString(R.string.app_awesome_name))
				.setContentIntent(pendingIntent).setSmallIcon(R.drawable.logo)
				.build();
		noti.flags = Notification.FLAG_NO_CLEAR;
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(-1, noti);
	}
}
