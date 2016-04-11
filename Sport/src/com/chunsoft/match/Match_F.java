package com.chunsoft.match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.chunsoft.utils.IntentUti;
import com.chunsoft.utils.ToastUtil;
import com.chunsoft.view.ImageCycleView;
import com.chunsoft.view.ImageCycleView.ImageCycleViewListener;
import com.chunsoft.view.xListview.XListView;
import com.chunsoft.view.xListview.XListView.IXListViewListener;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Match_F extends Fragment implements IXListViewListener,
		OnClickListener {
	/**
	 * widget statement
	 */
	ImageCycleView mAdView;
	View cycleview;
	private XListView myLv;
	private ProgressDialog dialog = null;
	private TextView tv_analysis, tv_statistic;
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
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.match1,
				null);
		FindView(view);
		init();
		Click();
		return view;
	}

	private void init() {
		mContext = getActivity();
		cycleview = LayoutInflater.from(mContext)
				.inflate(R.layout.match2, null);
		mAdView = (ImageCycleView) cycleview.findViewById(R.id.ad_view);
		tv_analysis = (TextView) cycleview.findViewById(R.id.tv_analysis);
		tv_statistic = (TextView) cycleview.findViewById(R.id.tv_statistic);

		myLv.addHeaderView(cycleview);
		myLv.setXListViewListener(this);
		// 设置可以进行下拉加载的功能
		myLv.setPullLoadEnable(true);
		myLv.setPullRefreshEnable(true);
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
	}

	private void FindView(View view) {
		myLv = (XListView) view.findViewById(R.id.x_lv);
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

	public void getMatchesData(final VolleyDataCallback<ImmediateBean> callback) {
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

	class MatchesAdapterC extends CommonAdapter<MatchesBean> {

		public MatchesAdapterC(Context context, List<MatchesBean> datas,
				int layoutId) {
			super(context, datas, layoutId);
		}

		@Override
		public void convert(ViewHolder holder, MatchesBean t) {
			Log.e("比赛ID------------", t.match_id);
			Log.e("比赛名称--------", t.team1.cn_name + ":" + t.team2.cn_name);
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
		onLoad();
	}

	private void Click() {
		tv_analysis.setOnClickListener(this);
		tv_statistic.setOnClickListener(this);
	}

	@Override
	public void onLoadMore() {
		ToastUtil.showLongToast(getActivity().getApplication(), "没有更多数据");
		onLoad();
	}

	/** 停止加载和刷新 */
	private void onLoad() {
		myLv.stopRefresh();
		// 停止加载更多
		myLv.stopLoadMore();
		// 设置最后一次刷新时间
		myLv.setRefreshTime(getCurrentTime(System.currentTimeMillis()));
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_analysis:
			IntentUti.IntentTo(mContext, Analysis_A.class);
			break;
		case R.id.tv_statistic:
			IntentUti.IntentTo(mContext, Match_Statistics_A.class);
			break;
		default:
			break;
		}
	}
}
