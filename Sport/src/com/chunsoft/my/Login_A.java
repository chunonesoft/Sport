package com.chunsoft.my;

import java.io.IOException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
import com.chunsoft.match.Main_FA_new;
import com.chunsoft.net.AbstractVolleyErrorListener;
import com.chunsoft.net.Constant;
import com.chunsoft.net.GsonRequest;
import com.chunsoft.net.MyApplication;
import com.chunsoft.sport.R;
import com.chunsoft.utils.IntentUti;
import com.chunsoft.utils.Manager;
import com.chunsoft.utils.PreferencesUtils;
import com.chunsoft.utils.ToastUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class Login_A extends FragmentActivity implements OnClickListener {
	/**
	 * widget statement
	 */
	@Bind(R.id.tv_register)
	TextView tv_register;
	@Bind(R.id.tv_wjmm)
	TextView tv_wjmm;
	@Bind(R.id.et_mobile)
	EditText et_mobile;
	@Bind(R.id.et_password)
	EditText et_password;
	@Bind(R.id.btn_login)
	Button btn_login;
	@Bind(R.id.tv_title)
	TextView tv_title;
	private Context mContext;
	private ProgressDialog loadDialog;

	/**
	 * variable statement
	 */
	private String mobile;
	private String password;
	private String URL;
	private String NET_TAG = "Login_A";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ButterKnife.bind(this);
		Manager mam = Manager.getInstance();
		mam.pushOneActivity(Login_A.this);
		init();
		Click();
	}

	private void init() {
		mContext = Login_A.this;
		tv_title.setText(getResources().getText(R.string.login));
		loadDialog = new ProgressDialog(mContext);
		loadDialog.setTitle("正在登录...");
	}

	private void Click() {
		btn_login.setOnClickListener(this);
		tv_register.setOnClickListener(this);
		tv_wjmm.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			boolean flag1 = false;
			boolean flag2 = false;
			mobile = et_mobile.getText().toString().trim();
			password = et_password.getText().toString().trim();
			if (!password.equals("")) {
				flag1 = true;
			} else {
				ToastUtil.showShortToast(mContext, "用户名不能为空");
			}

			if (!password.equals("")) {
				flag2 = true;
			} else {
				ToastUtil.showShortToast(mContext, "密码不能为空");
			}
			if (flag1 && flag2) {

				getJSONRequest(mobile, password,
						new VolleyDataCallback<LoginBean>() {
							@Override
							public void onSuccess(LoginBean datas) {
								if (!datas.id.equals("")) {
									PreferencesUtils.putSharePre(mContext,
											"userName", datas.userName);
									PreferencesUtils.putSharePre(mContext,
											"phone", datas.phone);
									PreferencesUtils.putSharePre(mContext,
											"id", datas.id);

									IntentUti.IntentTo(mContext,
											Main_FA_new.class);
								} else {
									ToastUtil.showShortToast(mContext,
											"用户登录失败，请检查用户名和密码！");
								}
								loadDialog.cancel();
							}
						});
			}
			break;
		case R.id.tv_register:
			IntentUti.IntentTo(mContext, Register_A.class);
			break;
		case R.id.tv_wjmm:
			IntentUti.IntentTo(mContext, Forget_Pwd_A.class);
			break;
		default:
			break;
		}
	}

	public void getJSONRequest(String mobile, String password,
			final VolleyDataCallback<LoginBean> callback) {
		URL = Constant.IP + Constant.LOGIN + "?user[login]=" + mobile
				+ "&user[password]=" + password;
		Log.e("LoginURL---->", URL);
		// URL =
		// "http://203.110.165.236/users/sign_in.json?user[login]=chunsoft&user[password]=12345678";
		GsonRequest<LoginBean> request = new GsonRequest<LoginBean>(URL, "",
				new Response.Listener<LoginBean>() {

					@Override
					public void onResponse(LoginBean arg0) {
						callback.onSuccess(arg0);
					}
				}, new AbstractVolleyErrorListener(mContext) {
					@Override
					public void onError() {
						loadDialog.cancel();
						Log.e(NET_TAG, "----onError");
					}
				}, LoginBean.class);
		MyApplication.getInstance().addToRequestQueue(request);
	}

	public class getData extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... params) {
			String result = handleNetTaskPost(params[0]);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			Log.e("-------------result", result);
		}

	}

	public static String handleNetTaskPost(String URL) {
		String responceInfo = null;
		HttpUtils httpUtils = new HttpUtils();
		// url =
		// "http://203.110.165.236/users/sign_in.json?user[login]=chunsoft&user[password]=12345678";
		ResponseStream responseStream;

		try {
			responseStream = httpUtils.sendSync(HttpMethod.POST, URL);
			String string;
			string = responseStream.readString();
			responceInfo = string;
		} catch (com.lidroid.xutils.exception.HttpException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responceInfo;
	}

}
