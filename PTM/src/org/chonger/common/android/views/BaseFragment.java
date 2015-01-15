package org.chonger.common.android.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;


/**
 * 基础Fragment布局
 * @author Daniel
 *
 */
public abstract class BaseFragment extends Fragment  {
	
	//private TempDataUtils dataSession;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//dataSession=TempDataUtils.getDataSession();
	}

	public View findViewById(int id)
	{
		return this.getView().findViewById(id);
	}
	
	public void finish()
	{
		this.getActivity().finish();
	}
	
	public Object getSystemService(String name)
	{
		return this.getActivity().getSystemService(name);
	}
	
//	/**
//	 * put the data in session
//	 * @param key
//	 * @param value
//	 */
//	public void setSessionAttribute(String key,Object value)
//	{
//		dataSession.setAttribute(key, value);
//	}
//	
//	/**
//	 * get data value from session
//	 * @param key
//	 * @return
//	 */
//	public Object getSessionAttribute(String key)
//	{
//		return dataSession.getAttribute(key);
//	}
//	
//	/**
//	 * clear some data value
//	 * @param keys
//	 */
//	public void clearSessionAttribute(String... keys)
//	{
//		dataSession.clearAttribute(keys);
//	}
	
	/**
	 * 设置显示标题
	 * @return
	 */
	public abstract String setTitle();
	
	/**
	 * 当发生隐藏事件
	 */
	public void hide(){};
	
	/**
	 * 当发生显示事件
	 */
	public void show(){};
}