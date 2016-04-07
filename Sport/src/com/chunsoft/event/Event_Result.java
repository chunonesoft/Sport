package com.chunsoft.event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.chunsoft.adapter.CommonAdapter;
import com.chunsoft.adapter.ViewHolder;
import com.chunsoft.bean.ImmediateBean;
import com.chunsoft.bean.MatchesBean;
import com.chunsoft.bean.VolleyDataCallback;
import com.chunsoft.net.AbstractVolleyErrorListener;
import com.chunsoft.net.Constant;
import com.chunsoft.net.GsonRequest;
import com.chunsoft.net.MyApplication;
import com.chunsoft.sport.R;
import com.chunsoft.utils.ToastUtil;
import com.chunsoft.view.xListview.XListView;
import com.chunsoft.view.xListview.XListView.IXListViewListener;

public class Event_Result extends Fragment implements IXListViewListener {
	/**
	 * widget statement
	 */
	@Bind(R.id.lv)
	XListView lv;

	ProgressDialog dialog = null;

	/**
	 * variable statement
	 */
	private Context mContext;
	private List<MatchesBean> datas = new ArrayList<MatchesBean>();
	private MatchesBean bean;
	private List<MatchesBean> matches = new ArrayList<MatchesBean>();
	private EventAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.eventf,
				null);
		ButterKnife.bind(this, view);
		init();
		return view;
	}

	private void init() {
		mContext = getActivity();
		lv.setXListViewListener(this);
		// 设置可以进行下拉加载的功能
		lv.setPullLoadEnable(true);
		lv.setPullRefreshEnable(true);
		getMatchesData(new VolleyDataCallback<ImmediateBean>() {
			@Override
			public void onSuccess(ImmediateBean datas) {
				matches = new ArrayList<MatchesBean>();
				matches = datas.matches;
				adapter = new EventAdapter(getActivity().getApplication(),
						matches, R.layout.match_item);
				lv.setAdapter(adapter);
				if (dialog != null && dialog.isShowing()) {
					dialog.dismiss();
					dialog = null;
				}
			}
		});
	}

	class EventAdapter extends CommonAdapter<MatchesBean> {

		public EventAdapter(Context context, List<MatchesBean> datas,
				int layoutId) {
			super(context, datas, layoutId);
		}

		@Override
		public void convert(ViewHolder holder, MatchesBean t) {
			holder.setText(R.id.tv_status, t.status);
			holder.setText(R.id.tv_cn_name, t.league.cn_name);
			holder.setText(R.id.match_time, t.match_time);
			holder.setText(R.id.tv_team1, t.team1.cn_name);
			holder.setText(R.id.tv_team2, t.team2.cn_name);
			holder.setText(R.id.tv_home_score, t.current_match.home_score);
			holder.setText(R.id.tv_guest_score, t.current_match.guest_score);
		}

	}

	@Override
	public void onRefresh() {
		datas.clear();
		matches.clear();
		getMatchesData(new VolleyDataCallback<ImmediateBean>() {
			@Override
			public void onSuccess(ImmediateBean datas) {
				matches = new ArrayList<MatchesBean>();
				matches = datas.matches;
				adapter = new EventAdapter(getActivity().getApplication(),
						matches, R.layout.match_item);
				lv.setAdapter(adapter);

				if (dialog != null && dialog.isShowing()) {
					dialog.dismiss();
					dialog = null;
				}
			}
		});
		// 停止刷新和加载
		onLoad();
	}

	@Override
	public void onLoadMore() {
		ToastUtil.showLongToast(getActivity().getApplication(), "没有更多数据");
		onLoad();
	}

	/** 停止加载和刷新 */
	private void onLoad() {
		lv.stopRefresh();
		// 停止加载更多
		lv.stopLoadMore();
		// 设置最后一次刷新时间
		lv.setRefreshTime(getCurrentTime(System.currentTimeMillis()));
	}

	/** 简单的时间格式 */
	public static SimpleDateFormat mDateFormat = new SimpleDateFormat(
			"MM-dd HH:mm");

	public static String getCurrentTime(long time) {
		if (0 == time) {
			return "";
		}

		return mDateFormat.format(new Date(time));
	}

	private void getMatchesData(final VolleyDataCallback<ImmediateBean> callback) {
		String URL = Constant.IP + Constant.IMMEDIATE;
		if (dialog == null) {
			dialog = ProgressDialog.show(mContext, "", "正在加载...");
			dialog.show();
		}
		GsonRequest<ImmediateBean> request = new GsonRequest<>(Method.GET, URL,
				"", new Response.Listener<ImmediateBean>() {
					@Override
					public void onResponse(ImmediateBean arg0) {
						callback.onSuccess(arg0);
					}

				}, new AbstractVolleyErrorListener(mContext) {
					@Override
					public void onError() {
						if (dialog != null && dialog.isShowing()) {
							dialog.dismiss();
							dialog = null;
						}
					}

				}, ImmediateBean.class);
		MyApplication.getInstance().addToRequestQueue(request);
	}
}
