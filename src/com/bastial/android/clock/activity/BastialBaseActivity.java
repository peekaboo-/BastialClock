package com.bastial.android.clock.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.bastial.android.clock.R;
import com.bastial.android.clock.activity.manage.BastialActivityManager;

/**
 * 
 * @author peekaboo
 * 
 */
public class BastialBaseActivity extends FragmentActivity {

	private final String TAG = "BastialBaseActivity";
	
	private BastialActivityManager mActivityManager = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG , "onCreate");
		mActivityManager = BastialActivityManager.getInstance();
		mActivityManager.pushActivity(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG , "onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG , "onRestart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG , "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG , "onDestroy");
		mActivityManager.popActivity(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG , "onResume");
	}

	protected void onPause() {
		super.onPause();
		Log.d(TAG , "onPause");
	}

	@Override
	public void onBackPressed() {
		BastialActivityManager.getInstance().popActivity(this);
	}
	
	/**
	 *  退出程序
	 * @param context
	 */
    public void exitApplicationDialog(final Context context) {
    	new AlertDialog.Builder(context)
    	.setMessage(R.string.ask_exit_app)
    	.setTitle(R.string.exit_dialog_title)
    	.setIcon(R.drawable.ic_launcher)
    	.setPositiveButton(R.string.btn_ok,new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				try{
					BastialActivityManager.getInstance().popAllActivity();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		})
		.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {				
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//TODO
			}
		}).show();
    }
}
