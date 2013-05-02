package com.bastial.android.clock.activity;


import android.os.Bundle;

import com.bastial.android.clock.R;

/**
 * 
 * @author Peekaboo
 *
 */
public class MainActivity extends BastialFootActivity{

	@SuppressWarnings("unused")
	private final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_layout);
	}
	
	@Override
	public void onBackPressed() {
		exitApplicationDialog(this);
	}
}
