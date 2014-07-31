package org.chonger.ui.activity;


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.chonger.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 日历面板
 * @author Daniel
 *
 */
public class VProjectFragment extends Fragment {
	
	private Map<String,List<String>> projectLst=null;
	
	private ListView listView;
	private List<String> listDataView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		
		return inflater.inflate(R.layout.fragment_project, container,false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//初始化测试数据
		listView=(ListView)this.getView().findViewById(R.id.listView1);
		
		projectLst=new LinkedHashMap<String,List<String>>();
		listDataView=new LinkedList<String>();
		
		List<String> project_kezaisheng=new LinkedList<String>();
		
		project_kezaisheng.add("系统优化和实时数据上传开发");
		projectLst.put("可再生能源建筑应用示范管理与检测平台", project_kezaisheng);
		
		List<String> project_younong=new LinkedList<String>();
		project_younong.add("优农平台系统开发");
		project_younong.add("追溯产品系统开发");
		projectLst.put("农业部品牌农产品电子商务平台-平台开发", project_younong);
		
		
		for(String key : projectLst.keySet())
		{
			List<String> _datas=projectLst.get(key);
			
			listDataView.add(key);
			listDataView.addAll(_datas);
		}
		
		listView.setAdapter(new ProjectAdapter(this.getActivity()));
	}
	
	private class ProjectAdapter extends BaseAdapter{
		

		private LayoutInflater inflater;
		public ProjectAdapter(Context context)
		{
			inflater=LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			return listDataView.size();
		}

		@Override
		public Object getItem(int position) {
			return listDataView.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view=convertView;
			
			if(projectLst.containsKey(getItem(position)))
			{
				view=inflater.inflate(R.layout.vproject_list_projectgroup,null);
				TextView text=(TextView)view.findViewById(R.id.vproject_projectgroup);
				text.setText((CharSequence)getItem(position));
			}
			else
			{
				view=inflater.inflate(R.layout.vproject_list_projectitem,null);
				TextView text=(TextView)view.findViewById(R.id.vproject_projectitem);
				text.setText((CharSequence)getItem(position));
			}
			
			
			return view;
		}
		
		
	}
	
}
