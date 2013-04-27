package com.bastial.android.clock.activity;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bastial.android.clock.R;

/**
 * 
 * @author Peekaboo
 *
 */
public class TestBActivity extends BastialFootActivity{

	private final String TAG = "TestBActivity";
	private Button mButtonBack = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b_layout);
		
		mButtonBack = (Button) findViewById(R.id.button_back);
		mButtonBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
