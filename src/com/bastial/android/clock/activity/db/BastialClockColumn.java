package com.bastial.android.clock.activity.db;

import android.provider.BaseColumns;

/**
 * 
 * @author Peekaboo
 *
 */
public interface BastialClockColumn extends BaseColumns {
	
	String DATABASE_NAME="bastial_clock.db";
	String TABLE_NAME = "clock";
	
	final static String CLOCK_TIME = "clockTime";
}
