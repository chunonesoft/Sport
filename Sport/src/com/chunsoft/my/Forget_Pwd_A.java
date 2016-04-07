package com.chunsoft.my;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;

import com.chunsoft.sport.R;

public class Forget_Pwd_A extends Activity {
	@Bind(R.id.tv_title)
	TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wjmm);
		init();
	}

	private void init() {
		tv_title.setText(getResources().getText(R.string.forget_pwd));
	}
}
