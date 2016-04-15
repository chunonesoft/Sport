package com.chunsoft.my;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.android.volley.Response;
import com.chunsoft.bean.LoginBean;
import com.chunsoft.bean.VolleyDataCallback;
import com.chunsoft.net.AbstractVolleyErrorListener;
import com.chunsoft.net.Constant;
import com.chunsoft.net.GsonRequest;
import com.chunsoft.net.MyApplication;
import com.chunsoft.sport.R;
import com.chunsoft.utils.IntentUti;
import com.chunsoft.utils.ToastUtil;

public class Register_A extends Activity {
	@Bind(R.id.tv_title)
	TextView tv_title;
	@Bind(R.id.et_num)
	EditText et_num;

	@Bind(R.id.et_name)
	EditText et_name;

	@Bind(R.id.et_mail)
	EditText et_mail;

	@Bind(R.id.et_pwd)
	EditText et_pwd;

	@Bind(R.id.et_apwd)
	EditText et_apwd;

	@Bind(R.id.et_checknum)
	EditText et_checknum;

	@Bind(R.id.btn_next)
	Button btn_next;

	private ProgressDialog dialog;
	private String name;
	private String mail;
	private String phone;
	private String password;
	private String apwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		ButterKnife.bind(this);
		init();
	}

	private void init() {
		tv_title.setText(getResources().getText(R.string.register));
		btn_next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initData();
				boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false;
				if (name.equals("")) {
					ToastUtil.showShortToast(Register_A.this, "用户名不能为空");
				} else {
					flag1 = true;
				}
				String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
				Pattern regex = Pattern.compile(check);
				Matcher matcher = regex.matcher(mail);

				if (!matcher.matches()) {
					ToastUtil.showShortToast(Register_A.this, "请输入正确的邮件地址");
				} else {
					flag2 = true;
				}

				Pattern regex1 = Pattern
						.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
				Matcher matcher1 = regex1.matcher(phone);
				if (!matcher1.matches()) {
					ToastUtil.showShortToast(Register_A.this, "请输入正确的手机号码");
				} else {
					flag3 = true;
				}

				if (password != "") {
					if (password.equals(apwd)) {
						flag4 = true;
					} else {
						ToastUtil.showShortToast(Register_A.this, "两次密码不一致");
					}
				} else {
					ToastUtil.showShortToast(Register_A.this, "密码不能为空");
				}
				if (flag1 && flag2 && flag3 && flag4) {
					getRegister(name, phone, password, mail,
							new VolleyDataCallback<LoginBean>() {

								@Override
								public void onSuccess(LoginBean datas) {
									if (!datas.phone.equals("")) {
										IntentUti.IntentTo(Register_A.this,
												Login_A.class);
									} else
										ToastUtil.showShortToast(
												Register_A.this, "邮箱或用户名被使用");
								}
							});
				}
			}
		});
	}

	private void initData() {
		name = et_name.getText().toString();
		mail = et_mail.getText().toString();
		phone = et_num.getText().toString();
		password = et_pwd.getText().toString();
		apwd = et_apwd.getText().toString();
	}

	public void getRegister(String name, String phone, String password,
			String email, final VolleyDataCallback<LoginBean> callback) {
		String URL = Constant.IP + Constant.REGISTER + "?user[user_name]="
				+ name + "&user[email]=" + email + "&user[phone]=" + phone
				+ "&user[password]=" + password;
		Log.e("LoginURL---->", URL);
		if (dialog == null) {
			dialog = ProgressDialog.show(Register_A.this, "", "正在加载...");
			dialog.show();
		}
		// URL =
		// "http://203.110.165.236/users/sign_in.json?user[login]=chunsoft&user[password]=12345678";
		GsonRequest<LoginBean> request = new GsonRequest<LoginBean>(URL, "",
				new Response.Listener<LoginBean>() {

					@Override
					public void onResponse(LoginBean arg0) {
						callback.onSuccess(arg0);
					}
				}, new AbstractVolleyErrorListener(Register_A.this) {
					@Override
					public void onError() {
						if (dialog != null && dialog.isShowing()) {
							dialog.dismiss();
							dialog = null;
						}
					}
				}, LoginBean.class);
		MyApplication.getInstance().addToRequestQueue(request);
	}
}
