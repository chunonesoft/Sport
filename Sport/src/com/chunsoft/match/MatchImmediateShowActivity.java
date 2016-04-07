package com.chunsoft.match;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.chunsoft.net.Constant;
import com.chunsoft.sport.R;
import com.chunsoft.view.WebviewBridge;

public class MatchImmediateShowActivity extends Activity {
	@Bind(R.id.web_view_match_show_bigdata)
	WebView mWebView;
	private int matchID = -1;
	WebviewBridge mWebviewBridge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match_show_bigdata);
		if (getIntent() != null && getIntent().getExtras() != null) {
			matchID = getIntent().getExtras().getInt("match_id");
		}
		ButterKnife.bind(this);
		init();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		if (intent.getExtras().containsKey("match_id")) {
			matchID = intent.getIntExtra("match_id", -1);
		}
		init();
	}

	private void init() {
		String URL = Constant.IP + "matches/" + matchID;
		Log.e("URL--------------------", URL);
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebviewBridge = new WebviewBridge(this);
		mWebView.addJavascriptInterface(mWebviewBridge, "Android");
		mWebView.loadUrl(URL);
	}
}
