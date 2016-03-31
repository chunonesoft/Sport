package com.chunsoft.match;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.chunsoft.event.Event_F;
import com.chunsoft.my.Login_F;
import com.chunsoft.my.My_F;
import com.chunsoft.net.Constant;
import com.chunsoft.service.FavoriteNotifyService;
import com.chunsoft.service.MatchRecommentNotifyService;
import com.chunsoft.sport.R;

public class Main_FA extends FragmentActivity implements OnClickListener {
	/** widget statement */
	private ImageView[] btn_menu = new ImageView[3];

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
	/** login Fragment */
	private Login_F login_F;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main);
		initView();
		startFavoriteRecommendService();
		startMatchRecommendService();
	}

	private void initView() {
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
			if (!Constant.isLogin) {
				if (login_F == null) {
					login_F = new Login_F();
					if (!login_F.isHidden())
						addFragment(login_F);
					showFragment(login_F);
				} else {
					if (login_F.isHidden()) {
						showFragment(login_F);
					}
				}
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
		if (login_F != null) {
			ft.hide(login_F);
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

	private void startFavoriteRecommendService() {
		Intent intent = new Intent(this, FavoriteNotifyService.class);
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
}
