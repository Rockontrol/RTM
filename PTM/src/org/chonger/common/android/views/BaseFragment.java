package org.chonger.common.android.views;

import org.chonger.rpm.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


/**
 * 基础Fragment布局
 * @author Daniel
 *
 */
public abstract class BaseFragment extends Fragment  {
	
	//private TempDataUtils dataSession;
	/**
	 * 标题栏左侧按钮
	 */
	public Button btnLeftButton=null;
	/**
	 * 标题栏右侧按钮
	 */
	public Button btnRightButton=null;
	
	/**
	 * 标题栏左侧按钮
	 */
//	public Button getLeftButton() {
//		if(btnLeftButton==null)
//			btnLeftButton=createButton();
//		return btnLeftButton;
//	}
	
	/**
	 * 标题栏右侧按钮
	 */
//	public Button getRightButton() {
//		if(btnRightButton==null)
//			btnRightButton=createButton();
//		return btnRightButton;
//	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//dataSession=TempDataUtils.getDataSession();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.initButton();
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
	 * 按钮初始化操作，如果不使用按钮则无需理会
	 */
	public abstract void initButton();
	
	/**
	 * 创建可用的Button
	 * @return
	 */
	public Button createButton()
	{
		return (Button)LayoutInflater.from(this.getView().getContext()).inflate(R.layout.common_views_button,null);
	}
	
	/**
	 * 当发生隐藏事件
	 */
	public void hide(){};
	
	/**
	 * 当发生显示事件
	 */
	public void show(){};
}