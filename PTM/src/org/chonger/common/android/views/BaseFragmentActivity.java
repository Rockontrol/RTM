package org.chonger.common.android.views;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.chonger.rpm.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 基础Fragment布局管理器
 * @author Daniel
 *
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
	
	public BaseFragmentActivity()
	{
		if(fragments==null)
			fragments=new LinkedHashMap<String,BaseFragment>();
		fragments.clear();
		
		if(btns==null)
			btns=new LinkedHashMap<String,String>();
		btns.clear();
	}

	private HashMap<String,BaseFragment> fragments;
	private HashMap<String,String> btns;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	
	private RadioGroup rdoBtns;
	private TextView titleText;
	private ImageView imgView;
	private RelativeLayout rLayout;
	
	private String key;
	
	//private TempDataUtils dataSession;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
		this.setContentView(R.layout.common_views_title);
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.common_views_title);
		titleText = (TextView) findViewById(R.id.titleText);
		rLayout=(RelativeLayout)findViewById(R.id.rlBack);
		
		//初始化布局管理器
		fragmentManager=this.getSupportFragmentManager();
		
		backButtonVisibility(false);
		
		//dataSession=TempDataUtils.getDataSession();
	}
	
	/**
	 * 设置返回按钮是否启用
	 * @param enable
	 */
	public void backButtonVisibility(boolean enable)
	{
		if(rLayout==null)
			rLayout=(RelativeLayout)findViewById(R.id.rlBack);
		rLayout.setVisibility(enable?View.VISIBLE:View.INVISIBLE);
	}
	
	/**
	 * 将指定的{@link BaseFragment}元素id绑定到指定的btnid按钮上
	 * @param id
	 */
	public void addTabs(int id,int btnid)
	{
		BaseFragment fragmentElement = (BaseFragment)fragmentManager.findFragmentById(id);
		if(fragmentElement!=null)
		{
			fragments.put(id+"", fragmentElement);
			btns.put(btnid+"",id+"");
		}
	}
	
	private void hiden()
	{
		fragmentTransaction=fragmentManager.beginTransaction();
		for(BaseFragment fragmentElement : fragments.values())
		{
			fragmentElement.hide();
			fragmentTransaction.hide(fragmentElement);
		}
	}
	
	/**
	 * 展示默认内容，默认为第一个添加的{@link BaseFragment}元素
	 */
	public void show()
	{
		if(fragments.size()>0)
			show(Integer.parseInt(fragments.keySet().toArray()[0].toString()));
	}
	
	/**
	 * 通过id显示{@link BaseFragment}元素
	 * @param id
	 */
	public void show(int id)
	{
		if(fragments.containsKey(id+""))
		{
			
			hiden();
			key=id+"";
			BaseFragment fragmentElement = fragments.get(key);
			String _text=fragmentElement.setTitle();
			titleText.setText(_text);
			fragmentElement.show();
			fragmentTransaction.show(fragmentElement).commit();
		}
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
	 * 绑定指定id的{@link RadioGroup}元素
	 * @param id
	 */
	public void bindBtns(int id)
	{
		rdoBtns=(RadioGroup)findViewById(id);
		if(rdoBtns!=null)
		{
			rdoBtns.setOnCheckedChangeListener(new OnCheckedChangeListener(){
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					if(btns.containsKey(checkedId+""))
					{
						BaseFragmentActivity.this.show(Integer.parseInt(btns.get(checkedId+"")));
					}
				}
			});
		}
	}	
}