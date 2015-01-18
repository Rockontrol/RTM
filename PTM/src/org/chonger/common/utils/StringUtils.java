package org.chonger.common.utils;

import java.util.List;
/**
 * 字符串常用辅助函数
 * 
 * @author Daniel
 * 
 */
public class StringUtils {
	/**
	 * 指定的字符串是否为空
	 * 
	 * @param string
	 * @return
	 * 
	 * @author Daniel
	 */
	public static boolean IsEmpty(String string) {
		return (string == null || "".equals(string) || "".equals(string.trim()));
	}

	public static boolean IsArrayEmpty(String[] string) {
		for (int i = 0; i < string.length; i++) {
			if (StringUtils.IsEmpty(string[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 指定的集合是否为空
	 * 
	 * @param list
	 * @return
	 */
	public static boolean IsEmpty(List list) {
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将指定的起始count个字符转换为大写
	 * 
	 * @param value
	 * @param count
	 * @return
	 * 
	 * @author Daniel
	 */
	public static String toUpperCase(String value, int count) {
		if (IsEmpty(value))
			return value;
		else if (count > value.length())
			return value;
		else {
			return value.substring(0, count).toUpperCase()
					+ value.substring(count);
		}
	}

	/**
	 * 将字符串转换为URL编码
	 * 
	 * @param value
	 * @return
	 * 
	 * @author Daniel
	 * 
	 */
	public static String string2Url(String value) {
		if (IsEmpty(value))
			return value;
		return java.net.URLEncoder.encode(java.net.URLEncoder.encode(value));
	}
}
