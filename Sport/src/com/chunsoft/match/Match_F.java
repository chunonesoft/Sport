package com.chunsoft.match;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chunsoft.bean.ADInfo;
import com.chunsoft.sport.R;
import com.chunsoft.view.ImageCycleView;
import com.chunsoft.view.ImageCycleView.ImageCycleViewListener;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Match_F extends Fragment {
	/**
	 * widget statement
	 */
	private ImageCycleView mAdView;
	/**
	 * variable statement
	 */
	private Context mContext;
	private ArrayList<ADInfo> infos = new ArrayList<ADInfo>();
	private String[] imageUrls = {
			"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
			"http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
			"http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
			"http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
			"http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.matchf,
				null);
		FindView(view);
		init();
		return view;
	}

	private void init() {
		mContext = getActivity();
		for (int i = 0; i < imageUrls.length; i++) {
			ADInfo info = new ADInfo();
			info.setUrl(imageUrls[i]);
			info.setContent("top-->" + i);
			infos.add(info);
		}

		mAdView.setImageResources(infos, mAdCycleViewListener);
	}

	private void FindView(View view) {
		mAdView = (ImageCycleView) view.findViewById(R.id.ad_view);
	}

	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			Toast.makeText(mContext, "content->" + info.getContent(),
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void displayImage(String imageURL, ImageView imageView) {
			ImageLoader.getInstance().displayImage(imageURL, imageView);// 使用ImageLoader对图片进行加装！
		}
	};

	/*
	 * @Override protected void onResume() { super.onResume();
	 * mAdView.startImageCycle(); };
	 * 
	 * @Override protected void onPause() { super.onPause();
	 * mAdView.pushImageCycle(); }
	 * 
	 * @Override protected void onDestroy() { super.onDestroy();
	 * mAdView.pushImageCycle(); }
	 */

}
