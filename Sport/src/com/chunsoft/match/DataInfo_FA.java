package com.chunsoft.match;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.chunsoft.adapter.CommonAdapter;
import com.chunsoft.adapter.ViewHolder;
import com.chunsoft.bean.OddChangeItemBean;
import com.chunsoft.bean.Odd_ChangesBean;
import com.chunsoft.bean.VolleyDataCallback;
import com.chunsoft.net.AbstractVolleyErrorListener;
import com.chunsoft.net.Constant;
import com.chunsoft.net.GsonRequest;
import com.chunsoft.net.MyApplication;
import com.chunsoft.sport.R;
import com.chunsoft.view.xListview.XListView;
import com.chunsoft.view.xListview.XListView.IXListViewListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

public class DataInfo_FA extends FragmentActivity implements IXListViewListener {
	@Bind(R.id.tv_title)
	TextView tv_title;

	@Bind(R.id.x_lv)
	XListView x_lv;

	private ProgressDialog dialog = null;
	private DataInfoAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_info);
		ButterKnife.bind(this);
		MobclickAgent.openActivityDurationTrack(false);
		init();
		initData();

	}

	private void init() {
		tv_title.setText(getResources().getText(R.string.data_info));
		x_lv.setXListViewListener(this);
		// 设置可以进行下拉加载的功能
		x_lv.setPullLoadEnable(true);
		x_lv.setPullRefreshEnable(true);
	}

	private void initData() {
		getData(new VolleyDataCallback<Odd_ChangesBean>() {

			@Override
			public void onSuccess(Odd_ChangesBean datas) {
				adapter = new DataInfoAdapter(DataInfo_FA.this,
						datas.odd_changes, R.layout.data_info_item);
				x_lv.setAdapter(adapter);
				if (dialog != null && dialog.isShowing()) {
					dialog.dismiss();
					dialog = null;
				}
			}
		});
	}

	/**
	 * 
	 * @author Administrator 适配器
	 */
	class DataInfoAdapter extends CommonAdapter<OddChangeItemBean> {

		public DataInfoAdapter(Context context, List<OddChangeItemBean> datas,
				int layoutId) {
			super(context, datas, layoutId);
		}

		@Override
		public void convert(ViewHolder holder, OddChangeItemBean t) {
			ImageView iv_logo = holder.getView(R.id.iv_logo);
			Log.e("t.team.logo_url", "1" + t.team.logo_url);
			if (!t.team.logo_url.equals("")) {
				Log.e("t.team.logo_url", "2" + t.team.logo_url);
				ImageLoader.getInstance()
						.displayImage(t.team.logo_url, iv_logo);
			} else {
				iv_logo.setImageResource(R.drawable.logo);
			}
			holder.setText(R.id.tv_name, t.team.cn_name);
			holder.setText(R.id.tv_time, t.data_time_str.substring(11));
			if (t.change_type_des.equals("上升")) {
				holder.setText(R.id.tv_content, "盘口" + t.change_type_des);
			} else {
				holder.setText(R.id.tv_content, "水位" + t.change_type_des);
			}

			holder.setText(R.id.tv_state, "[" + t.match_type_des + "]");
		}
	}

	/**
	 * 获取数据
	 */
	private void getData(final VolleyDataCallback<Odd_ChangesBean> callback) {
		String time = getCurrentTime(System.currentTimeMillis()) + "%2000:00";
		String URL = Constant.IP + Constant.ODD_CHANGES_DATA
				+ "?q[data_time_gte]=" + time;
		Log.e("URL---->", URL);
		if (dialog == null) {
			dialog = ProgressDialog.show(DataInfo_FA.this, "", "正在加载...");
			dialog.show();
		}
		GsonRequest<Odd_ChangesBean> request = new GsonRequest<Odd_ChangesBean>(
				Method.GET, URL, "", new Response.Listener<Odd_ChangesBean>() {
					@Override
					public void onResponse(Odd_ChangesBean arg0) {
						callback.onSuccess(arg0);
					}

				}, new AbstractVolleyErrorListener(DataInfo_FA.this) {
					@Override
					public void onError() {
						if (dialog != null && dialog.isShowing()) {
							dialog.dismiss();
							dialog = null;
						}
					}

				}, Odd_ChangesBean.class);
		MyApplication.getInstance().addToRequestQueue(request);
	}

	/** 简单的时间格式 */
	public static SimpleDateFormat mDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static String getCurrentTime(long time) {
		if (0 == time) {
			return "";
		}

		return mDateFormat.format(new Date(time));
	}

	@Override
	public void onRefresh() {
		onLoad();
	}

	@Override
	public void onLoadMore() {
		onLoad();
	}

	/** 停止加载和刷新 */
	private void onLoad() {
		x_lv.stopRefresh();
		// 停止加载更多
		x_lv.stopLoadMore();
		// 设置最后一次刷新时间
		x_lv.setRefreshTime(getCurrentTime(System.currentTimeMillis()));
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("Data Info page");
		MobclickAgent.onPause(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("Data Info page");
		MobclickAgent.onResume(this);
	}
}
