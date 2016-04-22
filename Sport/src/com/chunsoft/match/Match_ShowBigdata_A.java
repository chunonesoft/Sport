package com.chunsoft.match;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.chunsoft.net.Constant;
import com.chunsoft.sport.R;
import com.chunsoft.utils.Manager;
import com.chunsoft.utils.NetworkUtil;
import com.chunsoft.view.WebviewBridge;

/**
 * 大数据报告展示界面
 */
public class Match_ShowBigdata_A extends FragmentActivity implements
		SwipeRefreshLayout.OnRefreshListener {

	@Bind(R.id.web_view_match_recommend_report)
	WebView mWebView;
	@Bind(R.id.match_reommend_report_swipe_refresh_layout)
	SwipeRefreshLayout mSwipeRefreshLayout;
	WebviewBridge mWebVeiwBridge;

	@Bind(R.id.tv_title)
	TextView tv_title;

	ConnectivityManager mConnectivityManager;
	private int matchID = -1;

	private static String q = "matches";
	private static String q2 = "show_bigdata";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_detail);
		Manager mam = Manager.getInstance();
		mam.pushOneActivity(Match_ShowBigdata_A.this);
		ButterKnife.bind(this);
		if (getIntent() != null && getIntent().getExtras() != null) {
			matchID = getIntent().getExtras().getInt("match_id");
		}
		init();

	}

	private void init() {
		tv_title.setText(getResources().getText(R.string.big_data));
		mConnectivityManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		String url = Constant.IP + q + "/" + matchID + "/" + q2;
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (NetworkUtil.isNetWorkAvailable(mConnectivityManager)) {
					mWebView.loadUrl(url);
				} else {
					mWebView.loadUrl("file:///android_asset/net_error.html");
				}
				return false;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				mSwipeRefreshLayout.setRefreshing(false);
			}
		});
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebVeiwBridge = new WebviewBridge(this);
		mWebView.addJavascriptInterface(mWebVeiwBridge, "Android");
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.post(new Runnable() {
			@Override
			public void run() {
				mSwipeRefreshLayout.setRefreshing(true);
			}
		});
		if (NetworkUtil.isNetWorkAvailable(mConnectivityManager)) {
			mWebView.loadUrl(url);
		} else {
			mWebView.loadUrl("file:///android_asset/net_error.html");
		}
	}

	@Override
	public void onRefresh() {
		String url = Constant.IP + q + "/" + matchID + "/" + q2;
		if (NetworkUtil.isNetWorkAvailable(mConnectivityManager)) {
			mWebView.loadUrl(url);
		} else {
			mWebView.loadUrl("file:///android_asset/net_error.html");
		}
	}
}
