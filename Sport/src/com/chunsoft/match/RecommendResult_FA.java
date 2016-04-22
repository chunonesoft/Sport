package com.chunsoft.match;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
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
import com.chunsoft.utils.ToastUtil;
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

	@Bind(R.id.btn_search)
	Button btn_search;

	@Bind(R.id.tv_winRate)
	TextView tv_winRate;

	@Bind(R.id.tv_winNum)
	TextView tv_winNum;

	private int all_num = 0;
	private int win_num = 0;
	private int all_result_type = 0;

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
		btn_end.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(RecommendResult_FA.this, DateSelect_A.class);
				intent.putExtra("type", "2");
				startActivityForResult(intent, 1);
			}
		});
		btn_search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String date1 = btn_start.getText().toString();
				String date2 = btn_end.getText().toString();
				if (date1.equals("") || date1.equals(null)) {
					ToastUtil
							.showShortToast(RecommendResult_FA.this, "请输入开始时间");
					return;
				}

				if (date2.equals("") || date2.equals(null)) {
					ToastUtil
							.showShortToast(RecommendResult_FA.this, "请输入结束时间");
					return;
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
						Locale.CHINA);
				Date d1 = new Date();
				Date d2 = new Date();
				try {
					d1 = sdf.parse(date1);
					d2 = sdf.parse(date2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (d1.before(d2)) {
					String url = Constant.MATCH_RECOMMENDS + "?data_time="
							+ date1 + "&q[data_time_gte]=" + date2;
					Log.e("url----->", url);
					if (dialog == null) {
						dialog = ProgressDialog.show(RecommendResult_FA.this,
								"", "正在加载...");
						dialog.show();
					}
					new getData().execute(url);

				} else {
					ToastUtil.showShortToast(RecommendResult_FA.this,
							"开始时间不能大于结束时间");
					return;
				}

			}
		});
	}

	private void initData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String end_date = getCurrentTime(System.currentTimeMillis());
		Date dt = new Date();
		try {
			dt = sdf.parse(end_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.DAY_OF_YEAR, -5);// 日期-7
		Date dt1 = rightNow.getTime();
		String start_time = sdf.format(dt1);
		// String url = Constant.MATCH_RECOMMENDS
		// +
		// "?data_time=2016-04-04%2000:00&q[data_time_gte]=2016-04-10%2000:00";
		String url = Constant.MATCH_RECOMMENDS + "?data_time=" + start_time
				+ "&q[data_time_gte]=" + end_date;
		Log.e("URL--->", url);
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
		} else if (requestCode == 1) {
			if (resultCode == 0) {
				String date = intents.getExtras().getString("date1");
				btn_end.setText(date);
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
				List<RecommendBean> realrecommends = new ArrayList<RecommendBean>();
				recommends = gson.fromJson(result,
						new TypeToken<List<RecommendBean>>() {
						}.getType());

				for (int i = 0; i < recommends.size(); i++) {
					if (!(recommends.get(i).result_type + "").equals("null")) {
						realrecommends.add(recommends.get(i));
					}
				}
				adapter = new RecommendResultAdapter(RecommendResult_FA.this,
						realrecommends, R.layout.result_statistic_item);
				result_x_lv.setAdapter(adapter);
				if (dialog != null && dialog.isShowing()) {
					dialog.dismiss();
					dialog = null;
				}
				all_num = 0;
				win_num = 0;
				all_result_type = 0;

				for (int i = 0; i < realrecommends.size(); i++) {
					all_result_type += Integer
							.valueOf(realrecommends.get(i).result_type);
					if (Integer.valueOf(realrecommends.get(i).result_type) != 0) {
						all_num += 1;
					}
					if (Integer.valueOf(realrecommends.get(i).result_type) > 0) {
						win_num += 1;
					}
				}
				Log.e("all_num+win_num", all_num + "+" + win_num);
				if (all_num != 0) {
					double win_rate = (double) win_num / (double) all_num;
					java.text.DecimalFormat df = new java.text.DecimalFormat(
							"#.0000");
					Log.e("winrate", String.valueOf(df.format(win_rate)));
					tv_winNum.setText("净胜场次：" + (all_result_type / 2) + "场");
					tv_winRate.setText("胜率："
							+ String.valueOf(df.format(win_rate)).substring(1,
									3)
							+ "."
							+ String.valueOf(df.format(win_rate)).substring(3,
									5) + "%");
				} else {
					tv_winNum.setText("净胜场次：--" + "场");
					tv_winRate.setText("胜率：--");
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
			holder.setText(R.id.tv_name, t.match_des);
			holder.setText(R.id.tv_time, t.match_time);
			switch (t.result_type) {
			case "2":
				holder.setText(R.id.tv_result, "全赢");
				break;
			case "1":
				holder.setText(R.id.tv_result, "半赢");
				break;
			case "-2":
				holder.setText(R.id.tv_result, "全输");
				break;
			case "-1":
				holder.setText(R.id.tv_result, "半输");
				break;
			case "0":
				holder.setText(R.id.tv_result, "走");
				break;
			default:
				break;
			}
		}
	}
}
