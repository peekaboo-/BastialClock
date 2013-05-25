package com.bastial.android.clock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewAnimator;

import com.bastial.android.clock.R;

/**
 * 
 * @author Peekaboo
 *
 */
public class BastialFootActivity extends BastialBaseActivity implements OnClickListener {

	private final String TAG = "BastialFootActivity";
	private LinearLayout mFootMenuGridView = null;
	private RelativeLayout mClockLayer, mClockListLayer, mClockSetLayer;
	View mContentLayout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initUI();
	}

	private void initUI() {
		mContentLayout = getLayoutInflater().inflate(R.layout.activity_bastial_base_layout, null);
		mFootMenuGridView = (LinearLayout) mContentLayout.findViewById(R.id.foot_menu_layout);
		mClockLayer = (RelativeLayout) mContentLayout.findViewById(R.id.clock_layer);
		mClockListLayer = (RelativeLayout) mContentLayout.findViewById(R.id.clock_list_layer);
		mClockSetLayer = (RelativeLayout) mContentLayout.findViewById(R.id.clock_set_layer);
		
		mClockLayer.setOnClickListener(this);
		mClockListLayer.setOnClickListener(this);
		mClockSetLayer.setOnClickListener(this);
		
	}

	@Override
	public void setContentView(int layoutResID) {
		ViewAnimator content_view = (ViewAnimator) mContentLayout.findViewById(R.id.content_view);
		content_view.addView(LayoutInflater.from(this).inflate(layoutResID , null));
		super.setContentView(mContentLayout);
	}
	
	@Override
	public void setContentView(View view) {
		ViewAnimator content_view = (ViewAnimator) mContentLayout.findViewById(R.id.content_view);
		content_view.addView(view);
		super.setContentView(mContentLayout);
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		ViewAnimator content_view = (ViewAnimator) mContentLayout.findViewById(R.id.content_view);
		content_view.addView(view);
		super.setContentView(mContentLayout, params);
	}


	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.clock_layer:
			intent = new Intent(BastialFootActivity.this, MainActivity.class);
			startActivity(intent);
			break;
		case R.id.clock_list_layer:
			intent = new Intent(BastialFootActivity.this, ClockListActivity.class);
			startActivity(intent);
			break;
		case R.id.clock_set_layer:
			
			break;

		default:
			break;
		}
		
	}
}
