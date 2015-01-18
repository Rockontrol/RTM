package org.chonger.rpm.ui.views.main;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.chonger.common.android.views.BaseFragment;
import org.chonger.rpm.R;
import org.chonger.rpm.ui.views.LayoutMainActivity;
import org.chonger.rpm.ui.views.vp.LoginFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TaskFragment extends BaseFragment {
	
	private Map<String,List<String>> projectLst=null;
	private ListView listView;
	private List<String> listDataView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		return inflater.inflate(R.layout.view_main_task, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// 初始化测试数据
		listView = (ListView)this.findViewById(R.id.listView1);

		projectLst = new LinkedHashMap<String, List<String>>();
		listDataView = new LinkedList<String>();

		List<String> project_kezaisheng = new LinkedList<String>();

		project_kezaisheng.add("系统优化和实时数据上传开发");
		projectLst.put("可再生能源建筑应用示范管理与检测平台", project_kezaisheng);

		List<String> project_younong = new LinkedList<String>();
		project_younong.add("优农平台系统开发");
		project_younong.add("追溯产品系统开发");
		projectLst.put("农业部品牌农产品电子商务平台-平台开发", project_younong);

		for (String key : projectLst.keySet()) {
			List<String> _datas = projectLst.get(key);

			listDataView.add(key);
			listDataView.addAll(_datas);
		}
		
		listView.setAdapter(new ProjectAdapter(this.getActivity()));
		
	}



	@Override
	public String setTitle() {
		return this.getString(R.string.menu_task);
	}
	
	@Override
	public void initButton() {
		this.btnLeftButton=this.createButton();
		this.btnLeftButton.setText("未登录");
		this.btnLeftButton.setOnClickListener(buttonOnClickListener);
		
		this.btnRightButton=this.createButton();
		this.btnRightButton.setBackgroundResource(R.drawable.icon_add);
		this.btnRightButton.setOnClickListener(buttonOnClickListener);
	}
	
//	public void leftButtonOnClickListener(View v)
//	{
//		
//	}
//	
//	public void rightButtonOnClickListener(View v)
//	{
//		
//	}
	
	private OnClickListener buttonOnClickListener=new OnClickListener(){
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
				case R.id.title_button_left_id:
					LayoutMainActivity.getThis().ShowOther(R.id.frament_vp_login);
					break;
				case R.id.title_button_right_id:
					LayoutMainActivity.getThis().ShowOther(R.id.frament_task_add);
					break;
			}
		}
	};
	
	
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
				view=inflater.inflate(R.layout.view_task_list_projectgroup,null);
				TextView text=(TextView)view.findViewById(R.id.task_list_groupTitle);
				text.setText((CharSequence)getItem(position));
			}
			else
			{
				view=inflater.inflate(R.layout.view_task_list_projectitem,null);
				TextView text=(TextView)view.findViewById(R.id.task_list_itemTitle);
				text.setText((CharSequence)getItem(position));
			}
			
			
			return view;
		}
	}
	
}
