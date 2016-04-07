package com.chunsoft.match;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.chunsoft.net.Constant;
import com.chunsoft.sport.R;
import com.chunsoft.utils.NetworkUtil;
import com.chunsoft.view.WebviewBridge;

public class Match_Recommend_F extends Fragment implements
		SwipeRefreshLayout.OnRefreshListener {
	@Bind(R.id.web_view_match_recommend_report)
	WebView mWebView;
	@Bind(R.id.match_reommend_report_swipe_refresh_layout)
	SwipeRefreshLayout mSwipeRefreshLayout;

	WebviewBridge mWebVeiwBridge;

	ConnectivityManager mConnectivityManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.match_recommend, null);
		ButterKnife.bind(this, view);
		init();
		return view;
	}

	private void init() {
		mConnectivityManager = (ConnectivityManager) getActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		String url = Constant.IP + Constant.RECOMMEND_RESULT;
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

		mWebVeiwBridge = new WebviewBridge(getActivity());
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
		String url = Constant.IP + Constant.RECOMMEND_RESULT;
		if (NetworkUtil.isNetWorkAvailable(mConnectivityManager)) {
			mWebView.loadUrl(url);
		} else {
			mWebView.loadUrl("file:///android_asset/net_error.html");
		}
	}
}
