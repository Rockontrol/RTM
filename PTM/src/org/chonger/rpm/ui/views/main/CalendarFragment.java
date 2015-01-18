package org.chonger.rpm.ui.views.main;

import java.util.Date;

import org.chonger.common.android.views.BaseFragment;
import org.chonger.common.utils.CalendarUtils;
import org.chonger.common.utils.DateUtils;
import org.chonger.rpm.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class CalendarFragment extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.view_main_calendar, container, false);
	}
	
	private GridView gv;
	private ListView lv;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		gv=(GridView)this.findViewById(R.id.calendar_grid);
		gv.setAdapter(new CalendarAdapter(this.getActivity()));
		
		lv=(ListView)this.findViewById(R.id.listView2);
		lv.setAdapter(new TaskAdapter(this.getActivity()));
		
	}



	@Override
	public String setTitle() {
		return DateUtils.formatDateTime(DateUtils.FORMAT_STRING_TYPE3);
	}

	@Override
	public void initButton() {
		
		this.btnRightButton=this.createButton();
		this.btnRightButton.setBackgroundResource(R.drawable.icon_add);
	}
	
	/**
	 * 日历项适配模板
	 * @author Daniel
	 *
	 */
	public class CalendarAdapter extends BaseAdapter{
		
		private boolean isLeapyear = false;  //是否为闰年
		private int daysOfMonth = 0;      //某月的天数
		private int dayOfWeek = 0;        //具体某一天是星期几
		private int lastDaysOfMonth = 0;  //上一个月的总天数  
		private String[] dayNumber = new String[42];  //一个gridview中的日期存入此数组中
		
		//private SpecialCalendar sc = null;  
		//private LunarCalendar lc = null;   
		private Resources res = null;  
		private Drawable drawable = null; 
		 
		private Context context;  
		
		
		private LayoutInflater inflater;
		public CalendarAdapter(Context context)
		{
			inflater=LayoutInflater.from(context);
			
			dayNumber=CalendarUtils.getCalendarData(new Date());
		}
		
		@Override
		public int getCount() {
			return dayNumber.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView==null)
				convertView=inflater.inflate(R.layout.view_calendar_item,null);
			TextView textView = (TextView) convertView.findViewById(R.id.txtDate);  
			
//			String v="初一";
			String d=dayNumber[position].split(" ")[0];
			String t=dayNumber[position].split(" ")[1];
			if(d!=null)
			{
//				SpannableString sp = new SpannableString(d+"\n"+v);  
//				sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, d.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//				sp.setSpan(new RelativeSizeSpan(1.5f) , 0, d.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  
//				if(d != null ||v != ""){  
//					sp.setSpan(new RelativeSizeSpan(0.75f), d.length()+1,(d+v).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  
//		        } 
	
				textView.setText(d);
				if(t.equals("0"))
					textView.setTextColor(Color.parseColor("#545454"));
				else
					textView.setTextColor(Color.parseColor("#b9b9b9"));
			}
			return convertView;
		}
		
	}
	
	/**
	 * 事项适配器
	 * @author Daniel
	 *
	 */
	public class TaskAdapter extends BaseAdapter{
		
		private LayoutInflater inflater;
		public TaskAdapter(Context context)
		{
			inflater=LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			return 10;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView==null)
				convertView=inflater.inflate(R.layout.view_task_list_item,null);
			
			TextView tv=(TextView)convertView.findViewById(R.id.task_list_itemTitle11);
			tv.setText("测试代办事项");
			
			return convertView;
		}
		
	}
}
