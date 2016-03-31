package com.chunsoft.my;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.chunsoft.bean.LoginBean;
import com.chunsoft.bean.VolleyDataCallback;
import com.chunsoft.net.AbstractVolleyErrorListener;
import com.chunsoft.net.Constant;
import com.chunsoft.net.GetJsonData;
import com.chunsoft.net.GsonRequest;
import com.chunsoft.net.MyApplication;
import com.chunsoft.sport.R;
import com.chunsoft.utils.ToastUtil;

public class Login_F extends Fragment implements OnClickListener {
	/**
	 * widget statement
	 */
	private TextView tv_register, tv_wjmm;
	private EditText et_mobile;
	private EditText et_password;
	private Button btn_login;
	private Context mContext;
	private ProgressDialog loadDialog;

	/**
	 * variable statement
	 */
	private String mobile;
	private String password;
	private String URL;
	private JSONObject sendData;
	private LoginBean returnData;
	private String NET_TAG;
	private Intent intent;
	My_F my_F;
	private JSONObject returnDatas = new JSONObject();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		View view = LayoutInflater.from(mContext).inflate(R.layout.login, null);
		FindView(view);
		init();
		Click();
		return view;
	}

	private void init() {

		loadDialog = new ProgressDialog(mContext);
		loadDialog.setTitle("正在登录...");
	}

	private void FindView(View view) {
		tv_wjmm = (TextView) view.findViewById(R.id.tv_wjmm);
		tv_register = (TextView) view.findViewById(R.id.tv_register);
		et_mobile = (EditText) view.findViewById(R.id.et_mobile);
		et_password = (EditText) view.findViewById(R.id.et_password);
		btn_login = (Button) view.findViewById(R.id.btn_login);
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
			URL = Constant.LOGIN;
			boolean flag1 = false;
			boolean flag2 = false;
			mobile = et_mobile.getText().toString();
			password = et_password.getText().toString();

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
				sendData = new JSONObject();
				try {
					sendData.put("user[login]", mobile);
					sendData.put("user[password]", password);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new getData().execute("");
				//
				// getJSONRequest(mobile, password,
				// new VolleyDataCallback<LoginBean>() {
				// @Override
				// public void onSuccess(LoginBean datas) {
				// if (!datas.phone.equals("")) {
				// PreferencesUtils.putSharePre(mContext,
				// "userName", datas.userName);
				// PreferencesUtils.putSharePre(mContext,
				// "phone", datas.phone);
				// PreferencesUtils.putSharePre(mContext,
				// "password", datas.password);
				// PreferencesUtils.putSharePre(mContext,
				// "id", datas.id);
				// } else {
				// ToastUtil.showShortToast(mContext,
				// "用户登录失败，请检查用户名和密码！");
				// }
				// loadDialog.cancel();
				// }
				// });
			}
			break;
		case R.id.tv_register:

			break;
		case R.id.tv_wjmm:
			if (my_F == null) {
				my_F = new My_F();
				if (!my_F.isHidden())
					addFragment(my_F);
				showFragment(my_F);
			} else {
				if (my_F.isHidden()) {
					showFragment(my_F);
				}
			}
			// intent = new Intent(mContext, Wjmm_A.class);
			// startActivity(intent);
			break;
		default:
			break;
		}
	}

	public void getJSONRequest(String mobile, String password,
			final VolleyDataCallback<LoginBean> callback) {
		URL = Constant.IP + Constant.LOGIN;
		Log.e("URL", URL);
		sendData = new JSONObject();
		try {
			sendData.put("user[login]", mobile);
			sendData.put("user[password]", password);
			Log.e("sendData", sendData.toString());

		} catch (JSONException e) {
			e.printStackTrace();
		}
		GsonRequest<LoginBean> request = new GsonRequest<LoginBean>(URL,
				sendData.toString(), new Response.Listener<LoginBean>() {

					@Override
					public void onResponse(LoginBean arg0) {
						returnData = arg0;
						callback.onSuccess(returnData);
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

	/** add Fragment */
	private void addFragment(Fragment fragment) {
		FragmentTransaction ft = getActivity().getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.show_layout, fragment);
		ft.commit();
	}

	/** show Fragment */
	private void showFragment(Fragment fragment) {
		FragmentTransaction ft = getActivity().getSupportFragmentManager()
				.beginTransaction();
		removeFragment(this);
		if (my_F != null) {
			ft.hide(my_F);
		}
		ft.show(fragment);
		ft.commitAllowingStateLoss();
	}

	/** remove Fragment */
	private void removeFragment(Fragment fragment) {
		FragmentTransaction ft = getActivity().getSupportFragmentManager()
				.beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}

	public class getData extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			sendData = new JSONObject();
			try {
				sendData.put("user[login]", "chunsoft");
				sendData.put("user[password]", "12345678");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			returnDatas = new GetJsonData().getJSONObjectDataH(URL, sendData);
			Log.e("---------", sendData.toString());
			Log.e("--------URL", URL + "");
			return returnDatas.toString();
			// String URL = Constant.MATCH_RECOMMENDS + "?data_time="
			// + "2015-03-31";
			// Log.e("--------URL", URL + "");
			// JSONArray returnData = new JSONArray();
			// returnData = new GetJsonData().getJSONArrayDataHGET(URL);
			// return returnData.toString();
		}

		@Override
		protected void onPostExecute(String result) {
			Log.e("--------------result", result);
		}

	}
}