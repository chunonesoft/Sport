package com.chunsoft.match;

import java.util.ArrayList;
import java.util.List;

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

import com.chunsoft.adapter.Match_Adapter;
import com.chunsoft.bean.ADInfo;
import com.chunsoft.bean.MatchBean;
import com.chunsoft.sport.R;
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
	private MyListView myLv;
	private List<MatchBean> datas = new ArrayList<MatchBean>();
	private MatchBean bean;

	/**
	 * variable statement
	 */
	private Match_Adapter adapter;
	private Context mContext;
	private ArrayList<ADInfo> infos = new ArrayList<ADInfo>();
	private String[] imageUrls = {
			"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
			"http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
			"http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
			"http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
			"http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg" };

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

			bean = new MatchBean();
			bean.teamname1 = "1";
			datas.add(bean);
		}

		mAdView.setImageResources(infos, mAdCycleViewListener);
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
		adapter = new Match_Adapter(datas, mContext);
		myLv.setAdapter(adapter);
	}

	private void FindView(View view) {
		mAdView = (ImageCycleView) view.findViewById(R.id.ad_view);
		scrollview = (PullToRefreshScrollView) view
				.findViewById(R.id.pull_refresh_scrollview);
		layout = (LinearLayout) view.findViewById(R.id.layout);
		myLv = (MyListView) view.findViewById(R.id.myLv);
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
		for (int i = 0; i < 5; i++) {
			bean = new MatchBean();
			bean.teamname1 = "1";
			datas.add(bean);
			adapter.notifyDataSetChanged();
		}
	}

	/**
	 * refresh data
	 */
	private void refreshData() {
		for (int i = 0; i < 15; i++) {
			bean = new MatchBean();
			bean.teamname1 = "1";
			datas.add(bean);
			adapter.notifyDataSetChanged();
		}
	}

}
