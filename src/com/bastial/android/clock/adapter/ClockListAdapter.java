package com.bastial.android.clock.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bastial.android.clock.R;
import com.bastial.android.clock.activity.db.entity.Clock;
import com.bastial.android.clock.view.SlipButton;
import com.bastial.android.clock.view.SlipButton.OnChangedListener;

public class ClockListAdapter extends BaseListAdapter<Clock> implements OnChangedListener {
	
	private Callback mCallback;
	
	public void setCallback(Callback mCallback){
		this.mCallback = mCallback;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Clock clock = data.get(position);
		
		ViewHolder holder = null;
		
		if(convertView==null){
			convertView=inflater.inflate(R.layout.item_clock_list, null);
			holder = new ViewHolder();
			holder.vClockTiem = (TextView) convertView.findViewById(R.id.clock_list_time);
			holder.vSlipBtn = (SlipButton) convertView.findViewById(R.id.state_switch);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.vSlipBtn.SetOnChangedListener(this);
		
		return convertView;
	}
	
	private class ViewHolder{
		TextView vClockTiem;
		SlipButton vSlipBtn;
	}

	@Override
	public void OnChanged(boolean CheckState) {
		mCallback.onChangedState(CheckState);
	}
	
	public interface Callback{
		public void onChangedState(boolean CheckState);
	}

}
