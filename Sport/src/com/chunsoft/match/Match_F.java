package com.chunsoft.match;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chunsoft.ab.view.AbSlidingPlayView;
import com.chunsoft.sport.R;

public class Match_F extends Fragment {
	/** 首页轮播 */
	private AbSlidingPlayView viewPager;
	/** 存储首页轮播的界面 */
	private ArrayList<View> allListView;
	/** 首页轮播的界面的资源 */
	private int[] resId = {};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.matchf,
				null);
		return view;
	}
}
