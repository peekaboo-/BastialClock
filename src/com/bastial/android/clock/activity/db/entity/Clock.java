package com.bastial.android.clock.activity.db.entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "clock")
public class Clock implements Serializable{

	private static final long serialVersionUID = -8661983410745539008L;
	
	public static final String MONDAY = "Monday", TUESDAY = "Tuesday", WEDNESDAY = "Wednesday",
							   THURSDAY = "Thursday", FRIDAY = "Friday", SATURDAY = "Saturday",
							   SUNDAY = "Sunday", WORKDAY = "Workday", WEEKENDDAY = "WeekendDay",
							   EVERYDAY = "Everyday";
	
	public static final int RING = 0, ROCK = 1, RANDR = 2;
	
	@DatabaseField(generatedId = true)
	private long clockId;
	
	@DatabaseField
	private Long time;			//闹铃时间
	
	@DatabaseField
	private String clockDate;	//闹铃日期
	
	@DatabaseField
	private int remindStyle;	//提醒类型
	
	@DatabaseField
	private String clockRing;	//闹钟铃声
	
	@DatabaseField
	private String clockState;	//闹钟状态	

	public long getClockId() {
		return clockId;
	}

	public void setClockId(long clockId) {
		this.clockId = clockId;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getClockDate() {
		return clockDate;
	}

	public void setClockDate(String clockDate) {
		this.clockDate = clockDate;
	}

	public int getRemindStyle() {
		return remindStyle;
	}

	public void setRemindStyle(int remindStyle) {
		this.remindStyle = remindStyle;
	}

	public String getClockRing() {
		return clockRing;
	}

	public void setClockRing(String clockRing) {
		this.clockRing = clockRing;
	}

	public String getClockState() {
		return clockState;
	}

	public void setClockState(String clockState) {
		this.clockState = clockState;
	}
	
	
	
	
	
}
