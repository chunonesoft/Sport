package com.chunsoft.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.chunsoft.sport.R;
import com.chunsoft.utils.IntentUti;
import com.chunsoft.utils.PreferencesUtils;
import com.chunsoft.utils.ToastUtil;
import com.chunsoft.utils.UpdateManager;

public class My_F extends Fragment implements OnClickListener {
	@Bind(R.id.ll_exit)
	LinearLayout ll_exit;

	@Bind(R.id.ll_focus)
	LinearLayout ll_focus;

	@Bind(R.id.ll_set)
	LinearLayout ll_set;

	@Bind(R.id.ll_checkUpdate)
	LinearLayout ll_checkUpdate;

	@Bind(R.id.tv_title)
	TextView tv_title;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.myf,
				null);
		ButterKnife.bind(this, view);
		tv_title.setText(R.string.my);
		Click();
		return view;
	}

	private void Click() {
		ll_exit.setOnClickListener(this);
		ll_checkUpdate.setOnClickListener(this);
		ll_set.setOnClickListener(this);
		ll_focus.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_exit:
			PreferencesUtils.clearSharePre(getActivity());
			IntentUti.IntentTo(getActivity(), Login_A.class);
			break;
		case R.id.ll_checkUpdate:
			UpdateManager manager = new UpdateManager(getActivity());
			// manager.checkUpdate();
			break;
		case R.id.ll_set:
			ToastUtil.showShortToast(getActivity(), "程序员正在紧张开发中。。。。");
			// IntentUti.IntentTo(getActivity(), Set_A.class);
			break;
		case R.id.ll_focus:
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(
					Intent.EXTRA_TEXT,
					"http://git.oschina.net/chengdh/yinglang-sport-app-release/raw/master/app-release.apk (来自赢朗体育)。");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(Intent.createChooser(intent, "分享"));
			break;
		default:
			break;
		}
	}
}
