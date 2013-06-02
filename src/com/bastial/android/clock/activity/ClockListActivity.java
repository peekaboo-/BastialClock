package com.bastial.android.clock.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bastial.android.clock.R;
import com.bastial.android.clock.activity.db.entity.Clock;
import com.bastial.android.clock.activity.db.loader.ClockLoader;
import com.bastial.android.clock.activity.db.loader.LoaderConsts;
import com.bastial.android.clock.activity.db.loader.result.ClockListLoaderResult;
import com.bastial.android.clock.activity.db.loader.result.Result;
import com.bastial.android.clock.adapter.ClockListAdapter;
import com.bastial.android.clock.adapter.ClockListAdapter.Callback;

public class ClockListActivity extends BastialFootActivity implements LoaderCallbacks<Result>, Callback{

	
	RelativeLayout vClockListLayer, vLoaderLayer;
	LinearLayout vEmptyLayer;
	ListView vClockList;
	ClockListAdapter adapter;
	TextView vEmptyText;
	
	
	List<Clock> clockList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clock_list);

		initView();
		
	}

	

	private void initView() {
		vClockListLayer = (RelativeLayout) this.findViewById(R.id.clock_list_layer);
		vLoaderLayer = (RelativeLayout) this.findViewById(R.id.loader_layer);
		vEmptyLayer = (LinearLayout) this.findViewById(R.id.empty_layer);
		vClockList = (ListView) this.findViewById(R.id.clock_list);
		vEmptyText = (TextView) this.findViewById(R.id.empty_hint);
		View vHeader = this.getLayoutInflater().inflate(R.layout.item_clock_list_add, null, false);
		vEmptyText.setText("没有闹钟哦，请点击新建！");
		vClockList.addHeaderView(vHeader);
		vClockList.setHeaderDividersEnabled(true);
		adapter = new ClockListAdapter();
		adapter.setLayoutInflater(this.getLayoutInflater());
		vClockList.setAdapter(adapter);
		adapter.setCallback(this);
		vEmptyLayer.setOnClickListener(this);
		loaderClock();
	}
	
	private void loaderClock() {
		vLoaderLayer.setVisibility(View.VISIBLE);
		LoaderManager lm = this.getSupportLoaderManager();
		if (lm.getLoader(LoaderConsts.LOADER_CLOCK_LIST) != null) {
			lm.restartLoader(LoaderConsts.LOADER_CLOCK_LIST, null, this);
		} else {
			lm.initLoader(LoaderConsts.LOADER_CLOCK_LIST, null, this);
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if(v.getId()==R.id.empty_layer){
			vEmptyLayer.setVisibility(View.GONE);
			vClockList.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * Adapter的回调
	 * @param CheckState
	 */
	@Override
	public void onChangedState(boolean CheckState) {
		// TODO Auto-generated method stub
		
	}

	
	//=========Loader的回调============

	@Override
	public Loader<Result> onCreateLoader(int id, Bundle b) {
		if (id == LoaderConsts.LOADER_CLOCK_LIST) {
			ClockLoader loader = new ClockLoader(this);
			loader.forceLoad();
			return loader;
		}
		return null;
	}



	@Override
	public void onLoadFinished(Loader<Result> loader, Result result) {
		vLoaderLayer.setVisibility(View.GONE);
		if(result==null){
			return;
		}
		ClockListLoaderResult r = (ClockListLoaderResult) result;
		clockList = r.getData();
		
		if(clockList!=null && clockList.size()>0){
			vEmptyLayer.setVisibility(View.GONE);
			vClockList.setVisibility(View.VISIBLE);
			adapter.update(clockList);
			adapter.notifyDataSetChanged();
		}else{
			vClockList.setVisibility(View.GONE);
			vEmptyLayer.setVisibility(View.VISIBLE);
		}
		
	}


	@Override
	public void onLoaderReset(Loader<Result> arg0) {
	}



	
}
