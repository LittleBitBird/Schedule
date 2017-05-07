package com.example.Schedule;

import java.util.ArrayList;

import com.example.Schedule.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 *   ≈‰∆˜
 * 
 * @author Administrator
 *
 */
public class ListViewAdapter extends BaseAdapter {

	private LayoutInflater inflater;

	private ArrayList<String> list;
	

	private int i;

	public ListViewAdapter(Context context, ArrayList<String> list, int i) {
		super();
		this.inflater = LayoutInflater.from(context);
		this.list = list;	
		this.i = i;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.lv_items, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.text);
		tv.setText(list.get(position));
		if (position == i) {
			tv.setBackgroundResource(R.drawable.text2);
			tv.setTextColor(Color.parseColor("#ffffff"));
		} else{
			tv.setBackgroundResource(R.drawable.text3);
		    tv.setTextColor(Color.parseColor("#000000"));
		}
		return convertView;
	}

}