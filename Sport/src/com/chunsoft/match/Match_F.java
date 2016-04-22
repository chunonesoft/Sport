package com.chunsoft.match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

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

	@Bind(R.id.match_x_lv)
	XListView myLv;

	private ProgressDialog dialog = null;
	private TextView tv_analysis, tv_statistic;
	/**
	 * variable statement
	 */
	private int curentPage = 1;
	private int perPage = 15;
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
		ButterKnife.bind(this, view);
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
		getMatchesData(curentPage, perPage,
				new VolleyDataCallback<ImmediateBean>() {
					@Override
					public void onSuccess(final ImmediateBean datas) {
						curentPage++;
						matches = new ArrayList<MatchesBean>();
						matches = datas.matches;
						adapter = new MatchesAdapterC(getActivity()
								.getApplication(), matches, R.layout.match_item);
						myLv.setAdapter(adapter);
						if (dialog != null && dialog.isShowing()) {
							dialog.dismiss();
							dialog = null;
						}
						myLv.setOnItemClickListener(new OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {

								Intent intent = new Intent(getActivity(),
										Match_ShowBigdata_A.class);
								intent.putExtra("match_id", datas.matches
										.get((int) parent.getAdapter()
												.getItemId(position)).match_id);
								getActivity().startActivity(intent);
							}
						});
					}
				});
	}

	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			// Toast.makeText(mContext, "content->" + info.getContent(),
			// Toast.LENGTH_SHORT).show();
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

	public void getMatchesData(int page, int per_page,
			final VolleyDataCallback<ImmediateBean> callback) {
		String URL = Constant.IP + Constant.IMMEDIATE
				+ "&q[match_recommands_match_id_not_null]=1" + "&page=" + page
				+ "&per_page=" + per_page;
		Log.e("URL------>", URL);
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
			ImageView teamlogo1 = holder.getView(R.id.iv_teamlogo1);
			ImageView teamlogo2 = holder.getView(R.id.iv_teamlogo2);
			TextView tv_zhu, tv_ke;
			ImageView iv_zhu, iv_ke;

			if (t.match_recommands.get(0).result_type > 0) {
				holder.getView(R.id.iv_mingzhong).setVisibility(View.VISIBLE);
			} else {
				holder.getView(R.id.iv_mingzhong).setVisibility(View.INVISIBLE);
			}
			if (!t.team1.logo_url.equals("")) {
				ImageLoader.getInstance().displayImage(t.team1.logo_url,
						teamlogo1);// 使用ImageLoader对图片进行加装！
			} else {
				teamlogo1.setImageResource(R.drawable.icon_empty);
			}
			if (!t.team2.logo_url.equals("")) {
				ImageLoader.getInstance().displayImage(t.team2.logo_url,
						teamlogo2);// 使用ImageLoader对图片进行加装！
			} else {
				teamlogo2.setImageResource(R.drawable.icon_empty);
			}

			if (!(null == t.half_home_score)) {
				holder.getView(R.id.tv_half).setVisibility(View.VISIBLE);
				holder.setText(R.id.tv_half, t.half_home_score + ":"
						+ t.half_guest_score);
			} else {
				holder.getView(R.id.tv_half).setVisibility(View.INVISIBLE);
			}
			holder.setText(R.id.tv_time, t.match_time);
			holder.setText(R.id.tv_begin, t.begin);
			holder.setText(R.id.tv_group, t.league.cn_name);
			holder.setText(R.id.tv_status, t.status);
			holder.setText(R.id.tv_teamname1, t.team1.cn_name);
			holder.setText(R.id.tv_teamname2, t.team2.cn_name);
			if (t.match_describe.equals("未开")) {
				holder.setText(R.id.tv_dot, "未开");
				holder.getView(R.id.tv_score1).setVisibility(View.INVISIBLE);
				holder.getView(R.id.tv_score2).setVisibility(View.INVISIBLE);
			} else {
				holder.getView(R.id.tv_score1).setVisibility(View.VISIBLE);
				holder.getView(R.id.tv_score2).setVisibility(View.VISIBLE);
				holder.setText(R.id.tv_dot, ":");
				holder.setText(R.id.tv_score1, t.current_match.home_score);
				holder.setText(R.id.tv_score2, t.current_match.guest_score);
			}

			if (t.match_recommands.get(0).team_id == t.team1.team_id) {
				iv_zhu = holder.getView(R.id.iv_zhu);
				iv_zhu.setImageResource(R.drawable.y2);
				tv_zhu = holder.getView(R.id.tv_zhu);
				tv_zhu.setTextColor(getResources().getColor(R.color.yuce));
				iv_ke = holder.getView(R.id.iv_ke);
				iv_ke.setImageResource(R.drawable.y3);
				tv_ke = holder.getView(R.id.tv_ke);
				tv_ke.setTextColor(getResources().getColor(R.color.grey));
			} else if (t.match_recommands.get(0).team_id == t.team2.team_id) {

				iv_ke = holder.getView(R.id.iv_ke);
				iv_ke.setImageResource(R.drawable.y2);
				tv_ke = holder.getView(R.id.tv_ke);
				tv_ke.setTextColor(getResources().getColor(R.color.yuce));

				iv_zhu = holder.getView(R.id.iv_zhu);
				iv_zhu.setImageResource(R.drawable.y3);
				tv_zhu = holder.getView(R.id.tv_zhu);
				tv_zhu.setTextColor(getResources().getColor(R.color.gray));
			}
		}
	}

	@Override
	public void onRefresh() {
		curentPage = 1;
		matches.clear();
		getMatchesData(curentPage, perPage,
				new VolleyDataCallback<ImmediateBean>() {
					@Override
					public void onSuccess(ImmediateBean datas) {
						curentPage++;
						matches = new ArrayList<MatchesBean>();
						matches = datas.matches;
						adapter = new MatchesAdapterC(getActivity(), matches,
								R.layout.match_item);
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
		getMatchesData(curentPage, perPage,
				new VolleyDataCallback<ImmediateBean>() {
					@Override
					public void onSuccess(ImmediateBean datas) {
						curentPage++;
						for (int i = 0; i < datas.matches.size(); i++) {
							matches.add(datas.matches.get(i));
						}
						adapter.notifyDataSetChanged();
						if (dialog != null && dialog.isShowing()) {
							dialog.dismiss();
							dialog = null;
						}
					}
				});
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
			IntentUti.IntentTo(mContext, DataInfo_FA.class);
			break;
		case R.id.tv_statistic:
			IntentUti.IntentTo(mContext, RecommendResult_FA.class);
			break;
		default:
			break;
		}
	}
}
