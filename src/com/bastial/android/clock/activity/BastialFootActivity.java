package com.bastial.android.clock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ViewAnimator;

import com.bastial.android.clock.R;
import com.bastial.android.clock.adapter.BastialFootMenuAdapter;

/**
 * 
 * @author Peekaboo
 *
 */
public class BastialFootActivity extends BastialBaseActivity implements OnItemClickListener {

	private final String TAG = "BastialFootActivity";
	private GridView mFootMenuGridView = null;
	View mContentLayout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContentLayout = getLayoutInflater().inflate(R.layout.bastial_base_layout, null);
		mFootMenuGridView = (GridView) mContentLayout.findViewById(R.id.foot_menu_layout);
		BaseAdapter adapter = new BastialFootMenuAdapter(this);
		mFootMenuGridView.setAdapter(adapter);
		mFootMenuGridView.setOnItemClickListener(this);
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.d(TAG , "onItemClick>>>>position = " + position);
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(BastialFootActivity.this, MainActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(BastialFootActivity.this, TestBActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(BastialFootActivity.this, TestCActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
