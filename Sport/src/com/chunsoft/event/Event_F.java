package com.chunsoft.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.chunsoft.sport.R;

public class Event_F extends Fragment implements OnClickListener {
	/**
	 * widget statements
	 */
	@Bind(R.id.tv_title)
	TextView tv_title;

	@Bind(R.id.tv_result)
	TextView tv_result;

	@Bind(R.id.tv_schedule)
	TextView tv_schedule;

	@Bind(R.id.tv_data)
	TextView tv_data;

	@Bind(R.id.v_result)
	View v_result;

	@Bind(R.id.v_schedule)
	View v_schedule;

	@Bind(R.id.v_data)
	View v_data;

	/**
	 * variable statements
	 */
	Event_Result event_Result;
	Event_Schedule event_Schedule;
	Event_Data event_Data;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.event_f, null);
		ButterKnife.bind(this, view);
		initView();
		Click();
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_result:
			if (event_Result == null) {
				event_Result = new Event_Result();
				addFragment(event_Result);
				showFragment(event_Result);
			} else {
				showFragment(event_Result);
			}
			tv_result.setTextColor(getResources().getColor(R.color.text_click));
			tv_schedule.setTextColor(getResources().getColor(
					R.color.text_normal));
			tv_data.setTextColor(getResources().getColor(R.color.text_normal));

			v_result.setBackgroundColor(getResources().getColor(
					R.color.text_click));
			v_schedule.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			v_data.setBackgroundColor(getResources().getColor(
					R.color.line_normal));

			break;
		case R.id.tv_schedule:
			if (event_Schedule == null) {
				event_Schedule = new Event_Schedule();
				addFragment(event_Schedule);
				showFragment(event_Schedule);
			} else {
				showFragment(event_Schedule);
			}
			tv_result
					.setTextColor(getResources().getColor(R.color.text_normal));
			tv_schedule.setTextColor(getResources()
					.getColor(R.color.text_click));
			tv_data.setTextColor(getResources().getColor(R.color.text_normal));

			v_result.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			v_schedule.setBackgroundColor(getResources().getColor(
					R.color.text_click));
			v_data.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			break;
		case R.id.tv_data:
			if (event_Data == null) {
				event_Data = new Event_Data();
				addFragment(event_Data);
				showFragment(event_Data);
			} else {
				showFragment(event_Data);
			}
			tv_result
					.setTextColor(getResources().getColor(R.color.text_normal));
			tv_schedule.setTextColor(getResources().getColor(
					R.color.text_normal));
			tv_data.setTextColor(getResources().getColor(R.color.text_click));

			v_result.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			v_schedule.setBackgroundColor(getResources().getColor(
					R.color.line_normal));
			v_data.setBackgroundColor(getResources().getColor(
					R.color.text_click));
			break;

		default:
			break;
		}
	}

	private void Click() {
		tv_result.setOnClickListener(this);
		tv_schedule.setOnClickListener(this);
		tv_data.setOnClickListener(this);
	}

	private void initView() { // 设置默认界面
		tv_title.setText(getResources().getText(R.string.event));
		if (event_Result == null) {
			event_Result = new Event_Result();
			addFragment(event_Result);
			showFragment(event_Result);
		} else {
			if (event_Result.isHidden()) {
				showFragment(event_Result);
			}
		}

	}

	/** 添加Fragment **/
	public void addFragment(Fragment fragment) {
		FragmentTransaction ft = getActivity().getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.show_view, fragment);
		ft.commit();
	}

	/** 删除Fragment **/
	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = getActivity().getSupportFragmentManager()
				.beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}

	/** 显示Fragment **/
	public void showFragment(Fragment fragment) {
		FragmentTransaction ft = getActivity().getSupportFragmentManager()
				.beginTransaction();
		// 设置Fragment的切换动画
		ft.setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);

		// 判断页面是否已经创建，如果已经创建，那么就隐藏掉
		if (event_Result != null) {
			ft.hide(event_Result);
		}
		if (event_Schedule != null) {
			ft.hide(event_Schedule);
		}
		if (event_Data != null) {
			ft.hide(event_Data);
		}
		ft.show(fragment);
		ft.commitAllowingStateLoss();

	}
}
