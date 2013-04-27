package com.bastial.android.clock.activity.manage;

import java.util.Stack;

import android.app.Activity;
import android.util.Log;

/**
 * 
 * @author Peekaboo
 * 
 */
public class BastialActivityManager extends Stack<Activity> {
	private static final long serialVersionUID = 1L;
	private final String TAG = "BastialActivityManager";
	private static BastialActivityManager mInstance = null;
	private Stack<Activity> mActivityManager = null;

	private BastialActivityManager() {
		mActivityManager = new Stack<Activity>();
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
	 * 获取当前栈顶的Activity
	 * @return 栈顶的Activity
	 */
	public synchronized Activity peekActivity() {
		Log.d(TAG, "peekActivity");
		return mActivityManager.peek();
	}

	/**
	 * 从栈中弹出栈顶的Activity
	 * 
	 * @return Activity 可能返回null，所以需要做一个null检查
	 */
	public synchronized void popTopActivity() {
		if (!mActivityManager.isEmpty()) {
			Activity activity = mActivityManager.pop();
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
	public synchronized void popActivity(Activity activity) {
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
	public synchronized void pushActivity(Activity activity) {
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
	private boolean hasActivity(Activity activity) {
		Log.d(TAG, "hasActivity");
		for (Activity mActivity : mActivityManager) {
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
	public Activity currentActivity() {
		Activity activity = null;
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
			Activity mainActivity = mActivityManager.peek();
			mActivityManager.remove(mainActivity);
			while (!mActivityManager.isEmpty()) {
				Activity activity = mActivityManager.peek();
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
