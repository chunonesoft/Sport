package com.chunsoft.match;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.chunsoft.sport.R;

public class Match_Statistics_A extends FragmentActivity implements
		OnClickListener {
	/**
	 * widget
	 */
	@Bind(R.id.tv_title)
	TextView tv_title;

	@Bind(R.id.tv_infoControl)
	TextView tv_infoControl;

	@Bind(R.id.tv_recommend)
	TextView tv_recommend;

	@Bind(R.id.v_infoControl)
	View v_infoControl;

	@Bind(R.id.v_recommend)
	View v_recommend;

	/**
	 * variable
	 */
	Match_InfoControl_F info_f;
	Match_Recommend_F recommend_f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match_statistics);
		ButterKnife.bind(this);
		init();
		Click();
	}

	private void Click() {
		tv_infoControl.setOnClickListener(this);
		tv_recommend.setOnClickListener(this);
	}

	private void init() {
		tv_title.setText(getResources().getText(R.string.statistic));
		if (info_f == null) {
			info_f = new Match_InfoControl_F();
			addFragment(info_f);
			showFragment(info_f);
		} else {
			if (info_f.isHidden()) {
				showFragment(info_f);
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_infoControl:
			if (info_f == null) {
				info_f = new Match_InfoControl_F();
				addFragment(info_f);
				showFragment(info_f);
			} else {
				showFragment(info_f);
			}
			tv_infoControl.setTextColor(getResources().getColor(
					R.color.text_click));
			tv_recommend.setTextColor(getResources().getColor(
					R.color.text_normal));

			v_infoControl.setBackgroundColor(getResources().getColor(
					R.color.text_click));
			v_recommend.setBackgroundColor(getResources().getColor(
					R.color.line_normal));

			break;
		case R.id.tv_recommend:
			if (recommend_f == null) {
				recommend_f = new Match_Recommend_F();
				addFragment(recommend_f);
				showFragment(recommend_f);
			} else {
				showFragment(recommend_f);
			}
			tv_infoControl.setTextColor(getResources().getColor(
					R.color.text_normal));
			tv_recommend.setTextColor(getResources().getColor(
					R.color.text_click));

			v_infoControl.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			v_recommend.setBackgroundColor(getResources().getColor(
					R.color.text_click));

			break;

		default:
			break;
		}
	}

	/** 添加Fragment **/
	public void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.statistcs_show_view, fragment);
		ft.commit();
	}

	/** 删除Fragment **/
	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}

	/** 显示Fragment **/
	public void showFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		// 设置Fragment的切换动画
		ft.setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);

		// 判断页面是否已经创建，如果已经创建，那么就隐藏掉
		if (info_f != null) {
			ft.hide(info_f);
		}
		if (recommend_f != null) {
			ft.hide(recommend_f);
		}
		ft.show(fragment);
		ft.commitAllowingStateLoss();
	}
}
