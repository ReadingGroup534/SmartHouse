package com.smarthouse.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smarthouse.activity.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DongtaiFragment extends Fragment {
	
	private ListView listView = null;
	private List<Map<String,Object>> mData;
	private MyAdapter adapter;
	
	 public static String title[]=new String[]{"动态0","动态1","动态2","动态3","动态4","动态5"};  
	 public static String info[]=new String[]{ "￥：28","￥：28","￥：28","￥：28","￥：28","￥：28"};  

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_dongtai, container, false);
		listView = (ListView) view.findViewById(R.id.listView);
		mData = getData();
		adapter = new MyAdapter(getActivity());  
		listView.setAdapter(adapter);
		return view;
	}
	
	private List<Map<String, Object>> getData(){
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < title.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", title[i]);
			map.put("info", info[i]);
			list.add(map);
		}
		return list;
		
	}
		
	public class MyAdapter extends BaseAdapter{
		
		private LayoutInflater mInflater = null;
//		private List<Map<String, Object>> mData;
		
		public MyAdapter(Context context){
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;  
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.details_item, null);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.info = (TextView) convertView.findViewById(R.id.info);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.title.setText((String)mData.get(position).get("title"));
			holder.info.setText((String)mData.get(position).get("info"));
			return convertView;
		}
		
	}
	
	class ViewHolder {  
        public TextView title;  
        public TextView info;  
    }  

}
