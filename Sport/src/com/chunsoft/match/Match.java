package com.chunsoft.match;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

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
import com.chunsoft.utils.ListScrollUtil;
import com.chunsoft.view.ImageCycleView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class Match extends Fragment implements OnRefreshListener2<ScrollView> {
	/**
	 * widget statement
	 */
	private ImageCycleView mAdView;
	private PullToRefreshScrollView scrollview;
	private LinearLayout layout;
	ListView myLv;
	ProgressDialog dialog = null;
	/**
	 * variable statement
	 */
	private MatchesAdapterCB adapter;
	private Context mContext;
	private List<MatchesBean> matches = new ArrayList<MatchesBean>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.match1,
				null);
		FindView(view);
		init();
		return view;
	}

	private void init() {
		mContext = getActivity();
		getMatchesData(new VolleyDataCallback<ImmediateBean>() {
			@Override
			public void onSuccess(ImmediateBean datas) {

				matches = new ArrayList<MatchesBean>();
				matches = datas.matches;
				adapter = new MatchesAdapterCB(getActivity().getApplication(),
						matches, R.layout.match_item);
				myLv.setAdapter(adapter);
				ListScrollUtil.setListViewHeightBasedOnChildren(myLv);
				if (dialog != null && dialog.isShowing()) {
					dialog.dismiss();
					dialog = null;
				}
			}
		});
		scrollview.getLoadingLayoutProxy().setLastUpdatedLabel("上次刷新时间");
		scrollview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
		scrollview.getLoadingLayoutProxy().setRefreshingLabel("正在加载更多");
		scrollview.getLoadingLayoutProxy().setReleaseLabel("松开即可刷新");
		// 上拉、下拉设定
		scrollview.setMode(Mode.BOTH);
		scrollview.setOnRefreshListener(this);
	}

	private void FindView(View view) {
		myLv = (ListView) view.findViewById(R.id.myLv);
		scrollview = (PullToRefreshScrollView) view
				.findViewById(R.id.pull_refresh_scrollview);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
		// TODO Auto-generated method stub

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

					}

				}, ImmediateBean.class);
		MyApplication.getInstance().addToRequestQueue(request);
	}

	class MatchesAdapterC extends CommonAdapter<MatchesBean> {

		public MatchesAdapterC(Context context, List<MatchesBean> datas,
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

	class MatchesAdapterCB extends CommonAdapter<MatchesBean> {

		public MatchesAdapterCB(Context context, List<MatchesBean> datas,
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
}
