package com.bastial.android.clock.activity.db;

import static android.provider.BaseColumns._ID;
import static com.bastial.android.clock.activity.db.BastialClockColumn.CLOCK_TIME;
import static com.bastial.android.clock.activity.db.BastialClockColumn.TABLE_NAME;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 * @author Peekaboo
 *
 */
public class BastialClockDBOpenHelper extends SQLiteOpenHelper {
	private final String TAG = "BastialClockDBOpenHelper";
	
	public BastialClockDBOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "Create table " + TABLE_NAME + "( " + _ID + " integer primary key autoincrement," + CLOCK_TIME
				+  " text)";
		Log.d(TAG , "onCreate >>>> Sql = " + sql);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG , "onUpgrade");
	}
}
