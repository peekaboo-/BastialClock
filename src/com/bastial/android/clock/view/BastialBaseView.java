package com.bastial.android.clock.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author peekaboo
 */
public class BastialBaseView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

	protected Context mContext = null;
	private SurfaceHolder mHolder;

	protected int mScreenWidth;
	protected int mScreenHeight;

	private boolean mIsRunning; // 绘图线程的标记
	protected boolean mIsInit = true;
	protected boolean mIsTouchMode = false;

	
	private Thread mThread;// 绘图线程

	public BastialBaseView(Context context) {
		super(context);
		this.mContext = context;
		init(context);
	}

	public BastialBaseView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		init(context);
	}

	public BastialBaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		init(context);
	}

	private void init(Context context) {
		if (context instanceof Activity) {
			mScreenWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
			mScreenHeight = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
		}

		mHolder = getHolder(); // 得到SurfaceHolder对象
		mHolder.addCallback(this); // 添加监听器
	}

	protected void initData() {

	}

	/**
	 * Surface被创建, 一般在这里开启绘图线程
	 */
	public void surfaceCreated(SurfaceHolder holder) {
		mIsRunning = true;
		mThread = new Thread(this);
		mThread.start(); // 开启绘图线程
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	/**
	 * Surface被销毁, 一般在这里销毁绘图线程
	 */
	public void surfaceDestroyed(SurfaceHolder holder) {
		mIsRunning = false;
		// 销毁绘图线程
		if (mThread != null && mThread.isAlive()) {
			try {
				mThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		while (mIsRunning) {
			Canvas canvas = null;
			try {
				synchronized (mHolder) {
					canvas = mHolder.lockCanvas(); // 锁定画布
				}
				if (mIsInit) {
					initData();
					System.out.println("haihai");
					mIsInit = false;
				}
				onDraw(canvas); 
				if (!mIsTouchMode) {
					Thread.sleep(1000);
				} else {
					Thread.sleep(30); 
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (canvas != null) {
					mHolder.unlockCanvasAndPost(canvas);// 解锁画布, 在主线程内将图像渲染到屏幕上
				}
			}
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
		super.onDraw(canvas);
	};

	/**
	 * 得到指定宽高的Bitmap
	 */
	public Bitmap getBitmap(int id, int width, int height) {
		Bitmap paper = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas board = new Canvas(paper);
		Drawable image = getResources().getDrawable(id);
		image.setBounds(0, 0, width, height);
		image.draw(board);
		return paper;
	}
}
