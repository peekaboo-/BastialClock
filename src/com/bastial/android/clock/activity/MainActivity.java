package com.bastial.android.clock.activity;


import android.os.Bundle;
import android.view.KeyEvent;

import com.bastial.android.clock.R;

/**
 * 
 * @author Peekaboo
 *
 */
public class MainActivity extends BastialFootActivity{

	private final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_layout);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		exitApplicationDialog(this);
		return true;
	}
}
