package com.bastial.android.clock.activity.db.loader.result;

import java.util.List;

import com.bastial.android.clock.activity.db.entity.Clock;

public class ClockListLoaderResult extends Result {
	
	public List<Clock> data;

	public List<Clock> getData() {
		return data;
	}

	public void setData(List<Clock> data) {
		this.data = data;
	}
	
	

}
