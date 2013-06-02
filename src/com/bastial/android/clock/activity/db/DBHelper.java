package com.bastial.android.clock.activity.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bastial.android.clock.activity.db.dao.ClockDao;
import com.bastial.android.clock.activity.db.entity.Clock;
import com.bastial.android.clock.activity.manage.BastialActivityManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DBHelper extends OrmLiteSqliteOpenHelper {
	
	private static final String DATABASE_NAME = "bastial_clock.db";
	private static final int DATABASE_VERSION = 1;
	
	private ClockDao clockDao = null;
	private static DBHelper mInstance = null;
	
	private DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	public static DBHelper getInstance(Context context) {
		if (mInstance == null)
			mInstance = new DBHelper(context);
		return mInstance;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

		try {
			TableUtils.createTable(connectionSource, Clock.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int arg2,
			int arg3) {

		try {
			TableUtils.dropTable(connectionSource, Clock.class, true);
			onCreate(db, connectionSource);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ClockDao getClockDao(){
		if(clockDao==null){
			try {
				clockDao = new ClockDao(this.getConnectionSource());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clockDao;
	}

}
