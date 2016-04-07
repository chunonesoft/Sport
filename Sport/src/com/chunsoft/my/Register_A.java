package com.chunsoft.my;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.chunsoft.sport.R;

public class Register_A extends Activity {
	@Bind(R.id.tv_title)
	TextView tv_title;

	@Bind(R.id.et_name)
	EditText et_name;

	@Bind(R.id.et_mail)
	EditText et_mail;

	@Bind(R.id.et_checknum)
	EditText et_checknum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		ButterKnife.bind(this);
		init();
	}

	private void init() {
		tv_title.setText(getResources().getText(R.string.register));
	}
}
