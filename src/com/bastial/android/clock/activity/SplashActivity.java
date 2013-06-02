package com.bastial.android.clock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.bastial.android.clock.R;

/**
 * 
 * @author peekaboo
 *
 */
public class SplashActivity extends BastialBaseActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
				WindowManager.LayoutParams.FLAG_FULLSCREEN);  
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_splash);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {
				RedirectMainActivity();
			}
		} , 2000);
	}
	
	/**
	 * 跳转到主页
	 */
	private void RedirectMainActivity() {
		Intent intent = new Intent(SplashActivity.this,ClockListActivity.class);
		startActivity(intent);
		finish();
	}
}
