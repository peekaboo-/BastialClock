package com.bastial.android.clock.activity.db.dao;

import java.sql.SQLException;

import com.bastial.android.clock.activity.db.DBHelper;
import com.bastial.android.clock.activity.db.entity.Clock;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ClockDao extends BaseDaoImpl<Clock, String> {

	private DBHelper mHelper;
	
	public ClockDao(ConnectionSource connectionSource) throws SQLException{
		super(connectionSource,Clock.class);
	}

	
	public void setmHelper(DBHelper mHelper){
		this.mHelper = mHelper;
	}
}
