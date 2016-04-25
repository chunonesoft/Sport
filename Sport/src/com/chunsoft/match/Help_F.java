package com.chunsoft.match;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.chunsoft.adapter.CommonAdapter;
import com.chunsoft.adapter.ViewHolder;
import com.chunsoft.sport.R;
import com.umeng.analytics.MobclickAgent;

public class Help_F extends Fragment {
	HelpAdapter adapter;
	List<String> datas;
	String[] data = { "欧洲盘赔率", "亚盘赔率 ", "走盘", "水位", "升盘降盘" };
	private TextView tv_title;
	private ListView lv_help;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.help_info, null);
		MobclickAgent.openActivityDurationTrack(false);
		lv_help = (ListView) view.findViewById(R.id.lv_help);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_title.setText(getResources().getText(R.string.help));
		datas = new ArrayList<String>();
		for (int i = 0; i < data.length; i++) {
			datas.add(data[i]);
			Log.e("data[i]", data[i]);
		}
		adapter = new HelpAdapter(getActivity().getApplicationContext(), datas,
				R.layout.help_item);
		Log.e("datas", datas.size() + "");
		lv_help.setAdapter(adapter);
		lv_help.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), Help_Detail.class);
				intent.putExtra("type", position);
				startActivity(intent);
			}

		});

		return view;
	}

	class HelpAdapter extends CommonAdapter<String> {
		public HelpAdapter(Context context, List<String> datas, int layoutId) {
			super(context, datas, layoutId);
		}

		@Override
		public void convert(ViewHolder holder, String t) {
			holder.setText(R.id.tv_help_title, t);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("assistant page");
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("assistant page");
	}
}
