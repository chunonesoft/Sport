package com.chunsoft.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chunsoft.bean.MatchesBean;
import com.chunsoft.sport.R;

public class Match_Adapter extends BaseAdapter {
	private List<MatchesBean> datas = new ArrayList<MatchesBean>();
	private Context mContext;

	public Match_Adapter(List<MatchesBean> datas, Context mContext) {
		this.mContext = mContext;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		int count = 0;
		if (datas != null) {
			count = datas.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		MatchesBean bean = null;
		if (datas != null) {
			bean = datas.get(position);
		}
		return bean;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		HolderView holderView = null;
		if (convertView == null) {
			holderView = new HolderView();

			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.match_item, null);

			holderView.tv_status = (TextView) convertView
					.findViewById(R.id.tv_status);
			holderView.tv_cn_name = (TextView) convertView
					.findViewById(R.id.tv_cn_name);
			holderView.match_time = (TextView) convertView
					.findViewById(R.id.match_time);
			holderView.tv_team1 = (TextView) convertView
					.findViewById(R.id.tv_status);
			holderView.tv_team2 = (TextView) convertView
					.findViewById(R.id.tv_team2);
			holderView.tv_home_score = (TextView) convertView
					.findViewById(R.id.tv_home_score);
			holderView.tv_guest_score = (TextView) convertView
					.findViewById(R.id.tv_guest_score);
			convertView.setTag(holderView);
		} else {
			holderView = (HolderView) convertView.getTag();
		}

		holderView.tv_status.setText(datas.get(position).status.toString());
		holderView.tv_cn_name.setText(datas.get(position).league.cn_name
				.toString());
		holderView.match_time.setText(datas.get(position).match_time);
		holderView.tv_team1.setText(datas.get(position).team1.cn_name
				.toString());
		holderView.tv_team2.setText(datas.get(position).team2.cn_name
				.toString());
		holderView.tv_home_score
				.setText(datas.get(position).current_match.home_score
						.toString());
		holderView.tv_guest_score
				.setText(datas.get(position).current_match.guest_score
						.toString());

		return convertView;
	}

	static class HolderView {
		TextView tv_status;
		TextView tv_cn_name;
		TextView match_time;
		TextView tv_team1;
		ImageView iv_image1;
		ImageView iv_image2;
		TextView tv_team2;
		ImageView iv_focus;
		TextView tv_home_score;
		TextView tv_guest_score;
		ImageView iv_big_data;
	}
}
