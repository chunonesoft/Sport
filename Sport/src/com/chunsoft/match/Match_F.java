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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.chunsoft.adapter.CommonAdapter;
import com.chunsoft.adapter.ViewHolder;
import com.chunsoft.bean.ADInfo;
import com.chunsoft.bean.ImmediateBean;
import com.chunsoft.bean.Immediate_leagues_Bean;
import com.chunsoft.bean.MatchesBean;
import com.chunsoft.bean.VolleyDataCallback;
import com.chunsoft.net.AbstractVolleyErrorListener;
import com.chunsoft.net.Constant;
import com.chunsoft.net.GsonRequest;
import com.chunsoft.net.MyApplication;
import com.chunsoft.sport.R;
import com.chunsoft.utils.ToastUtil;
import com.chunsoft.view.ImageCycleView;
import com.chunsoft.view.ImageCycleView.ImageCycleViewListener;
import com.chunsoft.view.MyListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Match_F extends Fragment implements OnRefreshListener2<ScrollView> {
	/**
	 * widget statement
	 */
	private ImageCycleView mAdView;
	private PullToRefreshScrollView scrollview;
	private LinearLayout layout;
	MyListView myLv;
	ProgressDialog dialog = null;
	/**
	 * variable statement
	 */
	private MatchesAdapterC adapter;
	private Context mContext;
	private ArrayList<ADInfo> infos = new ArrayList<ADInfo>();
	private String[] imageUrls = {
			"http://img1.sc115.com/uploads/sc/jpg/151/15115.jpg",
			"http://imgmini.dfshurufa.com/mobile/20160313061237_1391ad9a537cfa3644b92984340d32c3_1.jpeg",
			"http://imgsrc.baidu.com/forum/w%3D580/sign=086fb4a8319b033b2c88fcd225cf3620/39aa84d6277f9e2fdb4db4a41a30e924b999f3c0.jpg",
			"http://news.cnyixing.cn/files/100210/1405/x_9d635231.jpg",
			"http://n.sinaimg.cn/sports/transform/20151126/yqHo-fxmazmz8880619.jpg" };
	private MatchesBean bean;
	private ImmediateBean datas = new ImmediateBean();
	private List<Immediate_leagues_Bean> immediate_leagues;
	private List<MatchesBean> matches = new ArrayList<MatchesBean>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.matchf,
				null);
		FindView(view);
		init();
		return view;
	}

	private void init() {
		mContext = getActivity();
		for (int i = 0; i < imageUrls.length; i++) {
			ADInfo info = new ADInfo();
			info.setUrl(imageUrls[i]);
			info.setContent("top-->" + i);
			infos.add(info);
		}

		mAdView.setImageResources(infos, mAdCycleViewListener);

		getMatchesData(new VolleyDataCallback<ImmediateBean>() {
			@Override
			public void onSuccess(ImmediateBean datas) {
				matches = new ArrayList<MatchesBean>();
				matches = datas.matches;
				adapter = new MatchesAdapterC(getActivity().getApplication(),
						matches, R.layout.match_item);
				myLv.setAdapter(adapter);
				if (dialog != null && dialog.isShowing()) {
					dialog.dismiss();
					dialog = null;
				}
			}
		});

		// matches = new ArrayList<MatchesBean>();
		//
		// for (int i = 0; i < 6; i++) {
		// bean = new MatchesBean();
		// bean.begin = "";
		// matches.add(bean);
		// }

		// matches = datas.matches;

		// adapter = new Match_Adapter(matches, mContext);

		// myLv.setAdapter(adapter);

		layout.setFocusable(true);
		layout.setFocusableInTouchMode(true);
		layout.requestFocus();
		scrollview.getLoadingLayoutProxy().setLastUpdatedLabel("上次刷新时间");
		scrollview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
		scrollview.getLoadingLayoutProxy().setRefreshingLabel("正在加载更多");
		scrollview.getLoadingLayoutProxy().setReleaseLabel("松开即可刷新");
		// 上拉、下拉设定
		scrollview.setMode(Mode.BOTH);
		scrollview.setOnRefreshListener(this);

	}

	private void FindView(View view) {
		myLv = (MyListView) view.findViewById(R.id.myLv);
		mAdView = (ImageCycleView) view.findViewById(R.id.ad_view);
		scrollview = (PullToRefreshScrollView) view
				.findViewById(R.id.pull_refresh_scrollview);
		layout = (LinearLayout) view.findViewById(R.id.layout);
	}

	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			Toast.makeText(mContext, "content->" + info.getContent(),
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void displayImage(String imageURL, ImageView imageView) {
			ImageLoader.getInstance().displayImage(imageURL, imageView);// 使用ImageLoader对图片进行加装！
		}
	};

	@Override
	public void onResume() {
		super.onResume();
		mAdView.startImageCycle();
	};

	@Override
	public void onPause() {
		super.onPause();
		mAdView.pushImageCycle();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mAdView.pushImageCycle();
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
		refreshData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
		getMoreData();
	}

	/**
	 * get more data
	 */
	private void getMoreData() {
		ToastUtil.showLongToast(getActivity().getApplication(), "没有更多数据");
		scrollview.onRefreshComplete();
	}

	/**
	 * refresh data
	 */
	private void refreshData() {
		matches.clear();
		getMatchesData(new VolleyDataCallback<ImmediateBean>() {
			@Override
			public void onSuccess(ImmediateBean datas) {
				matches = new ArrayList<MatchesBean>();
				matches = datas.matches;
				adapter = new MatchesAdapterC(getActivity().getApplication(),
						matches, R.layout.match_item);
				myLv.setAdapter(adapter);

				if (dialog != null && dialog.isShowing()) {
					dialog.dismiss();
					dialog = null;
				}
			}
		});
		scrollview.onRefreshComplete();
	}

	private void getMatchesData(final VolleyDataCallback<ImmediateBean> callback) {
		String URL = Constant.IP + Constant.immediate;
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
}
