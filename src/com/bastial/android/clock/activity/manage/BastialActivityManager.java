package com.bastial.android.clock.activity.manage;

import java.util.Stack;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * 
 * @author Peekaboo
 * 
 */
public class BastialActivityManager extends Stack<FragmentActivity> {
	private static final long serialVersionUID = 1L;
	private final String TAG = "BastialActivityManager";
	private static BastialActivityManager mInstance = null;
	private Stack<FragmentActivity> mActivityManager = null;
	
	

	private BastialActivityManager() {
		mActivityManager = new Stack<FragmentActivity>();
	}

	/**
	 * 获取当前实例
	 * @return
	 */
	public static BastialActivityManager getInstance() {
		if (mInstance == null)
			mInstance = new BastialActivityManager();
		return mInstance;
	}

	/**
	 * 从栈中弹出栈顶的Activity
	 * 
	 * @return Activity 可能返回null，所以需要做一个null检查
	 */
	public synchronized void popTopActivity() {
		if (!mActivityManager.isEmpty()) {
			FragmentActivity activity = mActivityManager.pop();
			if (activity != null) {
				activity.finish();
			}
		}
	}

	/**
	 * 从栈中弹出指定的Activity
	 * 
	 * @return Activity 可能返回null，所以需要做一个null检查
	 */
	public synchronized void popActivity(FragmentActivity activity) {
		if (!mActivityManager.isEmpty()) {
			if (hasActivity(activity)) {
				mActivityManager.remove(activity);
			}
			if (activity != null) {
				activity.finish();
			}
		}
	}

	/**
	 * 将Activity压进栈中
	 * @param activity
	 */
	public synchronized void pushActivity(FragmentActivity activity) {
		Log.d(TAG, "pushActivity");
		if (!hasActivity(activity)) {
			mActivityManager.push(activity);
		}
	}

	/**
	  * 判断一个Activity是否已经存在栈中
	 * @param activity 要检查的Activity
	 * @return 存在与否
	 */
	private boolean hasActivity(FragmentActivity activity) {
		Log.d(TAG, "hasActivity");
		for (FragmentActivity mActivity : mActivityManager) {
			if (mActivity.getClass().equals(activity.getClass())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得当前栈顶Activity
	 * 
	 * @return 当前位于栈顶的Activity
	 */
	public FragmentActivity currentActivity() {
		FragmentActivity activity = null;
		if (!mActivityManager.empty())
			activity = mActivityManager.peek();
		return activity;
	}

	/**
	 * 弹出栈中所有的Activity
	 */
	public void popAllActivity() {
		Log.d(TAG, "popAllActivity");
		while (mActivityManager.size() > 0) {
			FragmentActivity mainActivity = mActivityManager.peek();
			mActivityManager.remove(mainActivity);
			while (!mActivityManager.isEmpty()) {
				FragmentActivity activity = mActivityManager.peek();
				if (activity.getClass().equals(mainActivity.getClass())) {
					break;
				}
				
				assert(activity != null);
				if (activity != null) {
					activity.finish();
				}
				mActivityManager.remove(activity);
				activity = null;
			}
			if (mainActivity != null) {
				mainActivity.finish();
			}
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}
}
