package com.bastial.android.clock.view;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.bastial.android.clock.R;

public class BastialClockView extends BastialBaseView {

	public interface OnTimeChangeListener {
		void onTimeChange(float hour, float minute, float second);
	}

	private static float mMinuteOldTotalAngle = 0;
	private static float mHourOldTotalAngle = 0;
	private static float mMinuteNewAllAngle = 0;
	private static float mHourNewAllAngle = 0;

	private float mHourAngle = 0;
	private float mMinuteAngle = 0;
	private float mSecondAngle = 0;

	private double mHour = 0;
	private double mMinute = 0;
	private double mSecond = 0;

	private int mViewWidth = 0;
	private int mViewHeight = 0;

	private float mPointX = 0;
	private float mPointY = 0;

	private OnTimeChangeListener mOnTimeChangeListener = null;
	private Calendar mCalendar = null;

	private int mBackGroundBitmapSourceId = 0;
	private int mHourBitmapSourceId = 0;
	private int mMinuteBitmapSourceId = 0;
	private int mSecondBitmapSourceId = 0;

	private Bitmap mBackGroundBitmap = null;
	private Bitmap mHourBitmap = null;
	private Bitmap mMinuteBitmap = null;
	private Bitmap mSecondBitmap = null;

	private Matrix mHourMatrix = null;
	private Matrix mMinuteMatrix = null;
	private Matrix mSecondMatrix = null;

	private boolean mIsInitTime = true;
	private boolean mIsMinuteTouchMode = false;
	private boolean mIsHourTouchMode = false;

	public BastialClockView(Context context) {
		super(context);
	}

	public BastialClockView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public BastialClockView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 注册一个时钟时间改变的监听
	 * @param onTimeChangeListener
	 */
	public void registerOnTimeChangeListener(OnTimeChangeListener onTimeChangeListener) {
		this.mOnTimeChangeListener = onTimeChangeListener;
	}

	/**
	 * 设置整个时钟View的大小
	 * @param viewWidth 时钟的宽度
	 * @param viewHeight 时钟的高度
	 */
	public void setViewSize(int viewWidth, int viewHeight) {
		this.mViewWidth = viewWidth;
		this.mViewHeight = viewHeight;
	}

	/**
	 * 设置指针显示的位置。如果不设置，则默认为View的中心
	 * @param pointX 指针显示的X坐标
	 * @param pointY 指针显示的Y坐标
	 */
	public void setPointLocation(int pointX, int pointY) {
		this.mPointX = pointX;
		this.mPointY = pointY;
	}

	/**
	 * 设置时钟图片样式，包括背景钟面以及时钟、分钟、秒钟的图片
	 * @param backGroundBitmapSourceId 背景钟面的图片ID
	 * @param hourBitmapSourceId 时钟的图片ID
	 * @param minuteBitmapSourceId 分钟的图片ID
	 * @param secondBitmapSourceId 秒钟的图片ID
	 */
	public void setClockStyleBitmap(int backGroundBitmapSourceId, int hourBitmapSourceId, int minuteBitmapSourceId,
			int secondBitmapSourceId) {
		this.mBackGroundBitmapSourceId = backGroundBitmapSourceId;
		this.mHourBitmapSourceId = hourBitmapSourceId;
		this.mMinuteBitmapSourceId = minuteBitmapSourceId;
		this.mSecondBitmapSourceId = secondBitmapSourceId;
	}

	/**
	 * 初始化界面数据
	 */
	@Override
	public void initData() {
		getCurrentTime();
		initViewSize(mViewWidth, mViewHeight);
		calculateAngle();
		initClockBitmap();
		initMatrix();
	}

	/**
	 * 获取当前的时间
	 */
	private void getCurrentTime() {
		mCalendar = Calendar.getInstance();
		mSecond = mCalendar.get(Calendar.SECOND);
		mMinute = mCalendar.get(Calendar.MINUTE);
		mHour = mCalendar.get(Calendar.HOUR);
	}

	/**
	 * 计算各指针的角度
	 */
	private void calculateAngle() {
		mSecondAngle = (float) (mSecond / 60.0 * 360);
		mMinuteAngle = (float) (mMinute / 60.0 * 360 + (mSecondAngle / 60.0));
		mHourAngle = (float) (mHour / 12.0 * 360 + (mMinuteAngle / 12));

		mMinuteNewAllAngle = mMinuteAngle;
		mHourNewAllAngle = mHourAngle;
	}

	/**
	 * 初始化各指针的Matrix
	 */
	private void initMatrix() {
		mHourMatrix = new Matrix();
		mMinuteMatrix = new Matrix();
		mSecondMatrix = new Matrix();

		if (mPointX == 0 && mPointY == 0) {
			mPointX = this.mViewWidth / 2;
			mPointY = this.mViewHeight / 2;
		}
		mHourMatrix.postTranslate(mPointX - mHourBitmap.getWidth() / 2, mPointY - mHourBitmap.getHeight() / 2);
		mMinuteMatrix.postTranslate(mPointX - mMinuteBitmap.getWidth() / 2, mPointY - mMinuteBitmap.getHeight() / 2);
		mSecondMatrix.postTranslate(mPointX - mSecondBitmap.getWidth() / 2, mPointY - mSecondBitmap.getHeight() / 2);
	}

	/**
	 * 得到时钟的大小。如果用户没有设置，那么默认大小就是由屏幕的宽度组成的矩形区域
	 * @param viewWidth
	 * @param viewHeight
	 */
	private void initViewSize(int viewWidth, int viewHeight) {
		if (viewWidth <= 0 || viewHeight <= 0) {
			this.mViewWidth = mScreenWidth;
			this.mViewHeight = mScreenWidth;
		}
	}

	/**
	 * 得到时钟各指针以及钟面背景的图片
	 */
	private void initClockBitmap() {
		if (mBackGroundBitmapSourceId == 0) {
			mBackGroundBitmap = getBitmap(R.drawable.clock_bg, mViewWidth, mViewHeight);
		} else {
			mBackGroundBitmap = getBitmap(mBackGroundBitmapSourceId, mViewWidth, mViewHeight);
		}
		if (mHourBitmapSourceId == 0) {
			mHourBitmap = getBitmap(R.drawable.clock_hour, 45, mViewWidth * 17 / 25);
		} else {
			mHourBitmap = getBitmap(mHourBitmapSourceId, mViewWidth, mViewHeight);
		}
		if (mMinuteBitmapSourceId == 0) {
			mMinuteBitmap = getBitmap(R.drawable.clock_minute, 45, mViewWidth * 17 / 25);
		} else {
			mMinuteBitmap = getBitmap(mMinuteBitmapSourceId, mViewWidth, mViewHeight);
		}
		if (mSecondBitmapSourceId == 0) {
			mSecondBitmap = getBitmap(R.drawable.clock_second, 45, mViewWidth * 17 / 25);
		} else {
			mSecondBitmap = getBitmap(mSecondBitmapSourceId, mViewWidth, mViewHeight);
		}
	}

	/**
	 * 根据时针转动的位置，计算出分针要显示的位置
	 * @param hourAllAngle
	 */
	private void refreshMinuteAngleByHourAngle(float hourAllAngle) {
		mMinuteNewAllAngle = hourAllAngle % 30 * 12;
		mMinuteAngle = mMinuteNewAllAngle - mMinuteOldTotalAngle;
	}

	/**
	 * 通过分针转动的位置，计算出时针要显示的位置
	 * @param minuteAngle
	 */
	private void refreshHourAngleByMinuteAngle(float minuteAngle) {
		mHourAngle = minuteAngle / 12f;
		mHourNewAllAngle += mHourAngle;
		if (mHourNewAllAngle < 0) {
			mHourNewAllAngle += 360;
		} else if (mHourNewAllAngle > 360) {
			mHourNewAllAngle = 0;
		}
	}

	/**
	 * 通过秒针转动的位置，计算出分针以及时针要显示的位置
	 * 
	 * @param secondAngle
	 */
	private void refreshAngleBySecondAngle(float secondAngle) {
		mMinuteAngle = secondAngle / 60f;
		refreshHourAngleByMinuteAngle(mMinuteAngle);
	}

	/**
	 * 刷新各指针的位置
	 */
	private void refreshAngle() {
		mSecondAngle = 6f;
		refreshAngleBySecondAngle(mSecondAngle);
		mMinuteNewAllAngle += mMinuteAngle;
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(new Rect(0, 0, mViewWidth, mViewHeight), new Paint());
		if (mIsInitTime) {
			mIsInitTime = false;
		} else {
			refreshAngle();
		}

		if (mIsTouchMode) {
			mSecondAngle = 0;
		}
		mMinuteMatrix.preRotate(mMinuteAngle, mMinuteBitmap.getWidth() / 2f, mMinuteBitmap.getHeight() / 2f);
		mHourMatrix.preRotate(mHourAngle, mHourBitmap.getWidth() / 2f, mHourBitmap.getHeight() / 2f);
		mSecondMatrix.preRotate(mSecondAngle, mSecondBitmap.getWidth() / 2f, mSecondBitmap.getHeight() / 2f);

		int date_minute = (int) Math.floor(mMinuteNewAllAngle / 6);
		int date_hour = (int) Math.floor(mHourNewAllAngle / 30);
		mCalendar = Calendar.getInstance();
		int date_second = mCalendar.get(Calendar.SECOND);

		canvas.drawBitmap(mBackGroundBitmap, 0, 0, null);
		canvas.drawBitmap(mHourBitmap, mHourMatrix, null);
		canvas.drawBitmap(mMinuteBitmap, mMinuteMatrix, null);
		canvas.drawBitmap(mSecondBitmap, mSecondMatrix, null);

		if (mIsTouchMode) {
			mOnTimeChangeListener.onTimeChange(date_hour == 0 ? 12 : date_hour, date_minute, date_second);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x_down = event.getX();
		float y_down = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mIsMinuteTouchMode = isContaintPointByBitmap(x_down, y_down, mMinuteBitmap, mMinuteMatrix);
			if (!mIsMinuteTouchMode) {
				mIsHourTouchMode = isContaintPointByBitmap(x_down, y_down, mHourBitmap, mHourMatrix);
			}
			if (mIsMinuteTouchMode || mIsHourTouchMode) {
				mIsTouchMode = true;
				mIsInitTime = true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (mIsTouchMode) {
				mIsInitTime = true;
				if (mIsMinuteTouchMode) {
					mMinuteOldTotalAngle = mMinuteNewAllAngle;
					mMinuteAngle = getTouchModeUpdateAngle(x_down, y_down);
					mMinuteNewAllAngle = mMinuteAngle;
					if ((mMinuteNewAllAngle - mMinuteOldTotalAngle) < 0 && (mMinuteNewAllAngle - mMinuteOldTotalAngle) < -180) {
						mMinuteAngle = mMinuteNewAllAngle - mMinuteOldTotalAngle + 360;
					} else if ((mMinuteNewAllAngle - mMinuteOldTotalAngle) > 0
							&& (mMinuteNewAllAngle - mMinuteOldTotalAngle) > 180) {
						mMinuteAngle = mMinuteNewAllAngle - mMinuteOldTotalAngle - 360;
					} else {
						mMinuteAngle = mMinuteNewAllAngle - mMinuteOldTotalAngle;
					}
					this.refreshHourAngleByMinuteAngle(mMinuteAngle);

				} else if (mIsHourTouchMode) {
					mHourOldTotalAngle = mHourNewAllAngle;
					mHourAngle = getTouchModeUpdateAngle(x_down, y_down);
					mHourNewAllAngle = mHourAngle;
					mHourAngle = mHourNewAllAngle - mHourOldTotalAngle;
					// this.refreshMinuteAngleByHourAngle(hourNewAllAngle);
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			mIsMinuteTouchMode = false;
			mIsHourTouchMode = false;
			mMinuteOldTotalAngle = mMinuteNewAllAngle;
			mHourOldTotalAngle = mHourNewAllAngle;
			refreshMinuteAngleByHourAngle(mHourNewAllAngle);
			mIsTouchMode = false;
			break;
		default:
			break;
		}
		invalidate();
		return true;
	}

	/**
	 * 通过触摸计算偏移的角度
	 * @param x 按下的x坐标
	 * @param y 按下的y坐标
	 * @return 通过移动偏移的角度
	 */
	private float getTouchModeUpdateAngle(float x, float y) {
		float updateAngle = 0;
		float pointTanValue = (x - mViewWidth / 2) / (mViewHeight / 2 - y);
		float tempUpdateAngle = (float) Math.toDegrees(Math.atan(pointTanValue));
		if (pointTanValue < 0) {
			if (x < mViewWidth / 2) {
				updateAngle = 360 + tempUpdateAngle;

			} else {
				updateAngle = 180 + tempUpdateAngle;
			}
		} else {
			if (x < mViewWidth / 2) {
				updateAngle = 180 + tempUpdateAngle;
			} else {
				updateAngle = tempUpdateAngle;
			}
		}
		return updateAngle;
	}

	public void myInvalidate() {
		mIsInit = true;
		mIsInitTime = true;
		super.invalidate();
	}
	
	/**
	 * 通过按下的x和y坐标判断是否在一个图片里面（相当于判断是否点中了图片）
	 * @param x 按下的x坐标
	 * @param y 按下的y坐标
	 * @param bitmap 要判断的图片
	 * @param matrix 图片的Matrix对象
	 * @return true 点中了图片 flase 没有点中图片
	 */
	private boolean isContaintPointByBitmap(float x, float y, Bitmap bitmap, Matrix matrix) {
		Matrix tempMatrix = new Matrix();
		tempMatrix.set(matrix);
		tempMatrix.invert(tempMatrix);
		float[] xyPointArr = new float[2];
		tempMatrix.mapPoints(xyPointArr, new float[] { x, y });

		if (xyPointArr[0] > 0 && xyPointArr[0] < bitmap.getWidth() && xyPointArr[1] > 0
				&& xyPointArr[1] < bitmap.getHeight() / 2) {
			return true;
		} else {
			return false;
		}
	}
}
