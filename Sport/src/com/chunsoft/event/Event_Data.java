package com.chunsoft.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

import com.chunsoft.sport.R;

public class Event_Data extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.event_data, null);
		ButterKnife.bind(this, view);
		init();
		return view;
	}

	private void init() {
		// TODO Auto-generated method stub

	}
}
