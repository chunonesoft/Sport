package com.chunsoft.match;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

import com.chunsoft.sport.R;

public class Analysis_Statistics_F extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.analysis_statistics, null);
		ButterKnife.bind(this, view);
		init();
		return view;
	}

	private void init() {

	}
}
