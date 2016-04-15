package com.chunsoft.match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.chunsoft.adapter.CommonAdapter;
import com.chunsoft.adapter.ViewHolder;
import com.chunsoft.bean.RecommendBean;
import com.chunsoft.net.Constant;
import com.chunsoft.net.GetJsonData;
import com.chunsoft.sport.R;
import com.chunsoft.utils.Manager;
import com.chunsoft.view.xListview.XListView;
import com.chunsoft.view.xListview.XListView.IXListViewListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RecommendResult_FA extends FragmentActivity implements
		IXListViewListener {
	@Bind(R.id.tv_title)
	TextView tv_title;

	@Bind(R.id.result_x_lv)
	XListView result_x_lv;

	@Bind(R.id.btn_start)
	Button btn_start;

	@Bind(R.id.btn_end)
	Button btn_end;

	private ProgressDialog dialog = null;
	private RecommendResultAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_statistic);
		Manager mam = Manager.getInstance();
		mam.pushOneActivity(RecommendResult_FA.this);
		ButterKnife.bind(this);
		init();
		initData();
	}

	private void init() {
		tv_title.setText(getResources().getText(R.string.statistics_recommend));
		result_x_lv.setXListViewListener(this);
		// 设置可以进行下拉加载的功能
		result_x_lv.setPullLoadEnable(true);
		result_x_lv.setPullRefreshEnable(true);
		btn_start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(RecommendResult_FA.this, DateSelect_A.class);
				intent.putExtra("type", "1");
				startActivityForResult(intent, 0);
			}
		});
	}

	private void initData() {
		String url = Constant.MATCH_RECOMMENDS
				+ "?data_time=2016-04-04%2000:00&q[data_time_gte]=2016-04-10%2000:00";
		if (dialog == null) {
			dialog = ProgressDialog
					.show(RecommendResult_FA.this, "", "正在加载...");
			dialog.show();
		}
		new getData().execute(url);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intents) {
		if (requestCode == 0) {
			if (resultCode == 0) {
				String date = intents.getExtras().getString("date1");
				btn_start.setText(date);
			}
		}
		super.onActivityResult(requestCode, resultCode, intents);

	}

	/**
	 * 获取数据
	 */
	private class getData extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			String URL = params[0];

			return new GetJsonData().getJSONArrayDataHGET(URL).toString();
		}

		@Override
		protected void onPostExecute(String result) {
			if (!result.equals("")) {
				Gson gson = new Gson();
				// Log.e("--------getMatchEventRecommend1",
				// getMatchEventRecommend(userId, "2016-03-01").toString());
				List<RecommendBean> recommends = new ArrayList<RecommendBean>();
				recommends = gson.fromJson(result,
						new TypeToken<List<RecommendBean>>() {
						}.getType());
				adapter = new RecommendResultAdapter(RecommendResult_FA.this,
						recommends, R.layout.result_statistic_item);
				result_x_lv.setAdapter(adapter);
				if (dialog != null && dialog.isShowing()) {
					dialog.dismiss();
					dialog = null;
				}
			}
		}

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
		result_x_lv.stopRefresh();
		// 停止加载更多
		result_x_lv.stopLoadMore();
		// 设置最后一次刷新时间
		result_x_lv.setRefreshTime(getCurrentTime(System.currentTimeMillis()));
	}

	class RecommendResultAdapter extends CommonAdapter<RecommendBean> {

		public RecommendResultAdapter(Context context,
				List<RecommendBean> datas, int layoutId) {
			super(context, datas, layoutId);
		}

		@Override
		public void convert(ViewHolder holder, RecommendBean t) {
			if (t.result_type != "" && t.result_type != null) {
				holder.setText(R.id.tv_name, t.match_des);
				holder.setText(R.id.tv_time, t.match_time);
				switch (t.result_type) {
				case "2":
					holder.setText(R.id.tv_result, "全赢");
					holder.setText(R.id.tv_win, "赢");
					break;
				case "1":
					holder.setText(R.id.tv_result, "半赢");
					holder.setText(R.id.tv_win, "赢");
					break;
				case "-2":
					holder.setText(R.id.tv_result, "全输");
					holder.setText(R.id.tv_win, "输");
					break;
				case "-1":
					holder.setText(R.id.tv_result, "半输");
					holder.setText(R.id.tv_win, "输");
					break;
				case "0":
					holder.setText(R.id.tv_result, "走");
					holder.setText(R.id.tv_win, "走");
					break;
				default:
					break;
				}

			} else
				return;
		}
	}
}
