package com.bastial.android.clock.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bastial.android.clock.R;

/**
 * 
 * @author Peekaboo
 *
 */
public class BastialFootMenuAdapter extends BaseAdapter {
	
	private final String TAG = "BastialFootMenuAdapter";
	private Context mContext = null;
	private int[] mMenuImageIds = new int[]{R.drawable.home , R.drawable.home , R.drawable.home};
	private String[] mMenuTexts = new String[]{"test" , "test" , "test"};
	private View mFootMenuLayout = null;
	
	public BastialFootMenuAdapter(Context context) {
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return mMenuImageIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mFootMenuLayout;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(TAG , "getView>>>>position = " + position);
		Holder holder = null;
		if (convertView == null) {
			holder = new Holder();
			convertView = mFootMenuLayout = LayoutInflater.from(mContext).inflate(R.layout.foot_menu_content_layout, null);
			holder.imageview_menu_icon = (ImageView) convertView.findViewById(R.id.imageview_menu_icon);
			holder.textview_menu_text = (TextView) convertView.findViewById(R.id.textview_menu_text);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		
		holder.imageview_menu_icon.setImageResource(mMenuImageIds[position]);
		holder.textview_menu_text.setText(mMenuTexts[position]);
		
		return convertView;
	}

	public final class Holder{
        public ImageView imageview_menu_icon = null;
        public TextView textview_menu_text = null;
    }
	
}
