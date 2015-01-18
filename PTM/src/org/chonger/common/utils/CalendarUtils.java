package org.chonger.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 日历的常用辅助函数
 * 主要用于生成日历计算使用
 * @author Daniel
 *
 */
public class CalendarUtils {
	
	/**
	 * 计算是否是闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year)
	{
		if(year%100==0&&year%400==0)
			return true;
		else if(year%100!=0&&year%4==0)
			return true;
		return false;
	}
	
	/**
	 * 获取指定日期的日历数据
	 * @param date
	 * @return
	 */
	public static String[] getCalendarData(Date date)
	{
		String[] datas=new String[42];
		if(date!=null)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, 1-calendar.get(Calendar.DAY_OF_MONTH));
			int month=calendar.get(Calendar.MONTH)+1;
			int week=calendar.get(Calendar.DAY_OF_WEEK)-1;
			if(week==1)//周一				
				calendar.add(Calendar.DAY_OF_MONTH, -8);
			else
				calendar.add(Calendar.DAY_OF_MONTH, 0-week);
			//展示
			for(int i=0;i<datas.length;i++)
			{
				datas[i]=calendar.get(Calendar.DAY_OF_MONTH)+" "+((calendar.get(Calendar.MONTH)+1)==month?"0":"1");
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		return datas;
	}
	
	public static void main(String[] args)
	{
		
	}
	
}
