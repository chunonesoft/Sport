package com.chunsoft.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.chunsoft.bean.MatchBean;
import com.chunsoft.sport.R;

public class Match_Adapter extends BaseAdapter {

	/**
	 * 
	 */
	private List<MatchBean> datas;
	private Context mContext;

	public Match_Adapter(List<MatchBean> datas, Context mContext) {
		super();
		this.datas = datas;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {

		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.match_item,
				null);
		return view;
	}

}
