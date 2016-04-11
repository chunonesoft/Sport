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

public class Analysis_A extends FragmentActivity implements OnClickListener {
	@Bind(R.id.tv_title)
	TextView tv_title;

	@Bind(R.id.tv_index)
	TextView tv_index;

	@Bind(R.id.tv_ball)
	TextView tv_ball;

	@Bind(R.id.tv_statistics)
	TextView tv_statistics;

	@Bind(R.id.v_index)
	View v_index;

	@Bind(R.id.v_ball)
	View v_ball;

	@Bind(R.id.v_statistics)
	View v_statistics;

	Analysis_Index_F index_f;
	Analysis_Ball_F ball_f;
	Analysis_Statistics_F statistics_f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.analysis);
		ButterKnife.bind(this);
		init();
		Click();
	}

	private void init() {
		tv_title.setText(getResources().getText(R.string.match_analysis));
		if (index_f == null) {
			index_f = new Analysis_Index_F();
			addFragment(index_f);
			showFragment(index_f);
		} else {
			if (index_f.isHidden()) {
				showFragment(index_f);
			}
		}
	}

	private void Click() {
		tv_index.setOnClickListener(this);
		tv_ball.setOnClickListener(this);
		tv_statistics.setOnClickListener(this);
	}

	/** 添加Fragment **/
	public void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.show_view, fragment);
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
		if (index_f != null) {
			ft.hide(index_f);
		}
		if (statistics_f != null) {
			ft.hide(statistics_f);
		}
		if (ball_f != null) {
			ft.hide(ball_f);
		}
		ft.show(fragment);
		ft.commitAllowingStateLoss();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_index:
			if (index_f == null) {
				index_f = new Analysis_Index_F();
				addFragment(index_f);
				showFragment(index_f);
			} else {
				showFragment(index_f);
			}
			tv_index.setTextColor(getResources().getColor(R.color.text_click));
			tv_ball.setTextColor(getResources().getColor(R.color.text_normal));
			tv_statistics.setTextColor(getResources().getColor(
					R.color.text_normal));

			v_index.setBackgroundColor(getResources().getColor(
					R.color.text_click));
			v_ball.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			v_statistics.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			break;
		case R.id.tv_ball:
			tv_index.setTextColor(getResources().getColor(R.color.text_normal));
			tv_ball.setTextColor(getResources().getColor(R.color.text_click));
			tv_statistics.setTextColor(getResources().getColor(
					R.color.text_normal));

			v_index.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			v_ball.setBackgroundColor(getResources().getColor(
					R.color.text_click));
			v_statistics.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			break;
		case R.id.tv_statistics:
			tv_index.setTextColor(getResources().getColor(R.color.text_normal));
			tv_ball.setTextColor(getResources().getColor(R.color.text_normal));
			tv_statistics.setTextColor(getResources().getColor(
					R.color.text_click));

			v_index.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			v_ball.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			v_statistics.setBackgroundColor(getResources().getColor(
					R.color.text_click));
			break;

		default:
			break;
		}
	}
}
