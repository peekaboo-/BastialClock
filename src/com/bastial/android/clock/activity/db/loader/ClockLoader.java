package com.bastial.android.clock.activity.db.loader;

import java.util.List;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.bastial.android.clock.activity.db.DBHelper;
import com.bastial.android.clock.activity.db.dao.ClockDao;
import com.bastial.android.clock.activity.db.entity.Clock;
import com.bastial.android.clock.activity.db.loader.result.ClockListLoaderResult;
import com.bastial.android.clock.activity.db.loader.result.Result;

public class ClockLoader extends AsyncTaskLoader<Result> {
	Context mContext;
	public ClockLoader(Context context){
		super(context);
		this.mContext = context;
	}

	@Override
	public Result loadInBackground() {
		ClockListLoaderResult result = new ClockListLoaderResult();
		List<Clock> clockList = null;
		try {
			ClockDao clockDao = DBHelper.getInstance(mContext).getClockDao();
			clockList = clockDao.queryForAll();
			result.setData(clockList);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
