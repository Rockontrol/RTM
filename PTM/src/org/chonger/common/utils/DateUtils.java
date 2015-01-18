package org.chonger.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期常用辅助函数
 * @author Daniel
 *
 */
public class DateUtils {
	/**常用格式化字符串：yyyy-MM-dd*/
	public static final String FORMAT_STRING_TYPE1="yyyy-MM-dd";
	/**常用格式化字符串：yyyy年MM月dd日*/
	public static final String FORMAT_STRING_TYPE2="yyyy年MM月dd日";
	/**常用格式化字符串：yyyy年MM月*/
	public static final String FORMAT_STRING_TYPE3="yyyy年MM月";
	
	
	/**
	 * 用自定义的模板格式化当前日期
	 * @param format
	 * @return
	 */
	public static String formatDateTime(String format)
	{
		return new SimpleDateFormat(format).format(new Date());
	}
	
}