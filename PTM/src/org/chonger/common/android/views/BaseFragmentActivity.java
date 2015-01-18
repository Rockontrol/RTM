package org.chonger.common.android.views;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.chonger.rpm.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 基础Fragment布局管理器
 * @author Daniel
 * modify 2015-01-16	修改按钮逻辑，框架中只预留按钮位置，由每个使用的界面自己负责按钮的设置，框架只调用按钮函数进行显示，有则显示，为null则不显示。
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
	private RelativeLayout rtlButtonBox,rtrButtonBox;
	private Button leftButton,rightButton;
	
	
	
	private String key;
	
	//private TempDataUtils dataSession;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
		this.setContentView(R.layout.common_views_title);
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.common_views_title);
		titleText = (TextView) findViewById(R.id.titleText);
		
		rtlButtonBox=(RelativeLayout)findViewById(R.id.tlButtonBox);
		rtrButtonBox=(RelativeLayout)findViewById(R.id.trButtonBox);
		
		rtlButtonBox.setOnClickListener(titleButtonClickListener);
		rtrButtonBox.setOnClickListener(titleButtonClickListener);
		
//		leftButton=(Button)findViewById(R.id.tlButton);
//		rightButton=(Button)findViewById(R.id.trButton);
		
		//初始化布局管理器
		fragmentManager=this.getSupportFragmentManager();
		
//		backButtonVisibility(false);
		//dataSession=TempDataUtils.getDataSession();
		
//		setLeftButton();
	}
	
	//按钮框事件传递
	private OnClickListener titleButtonClickListener=new OnClickListener(){
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
				case R.id.tlButtonBox:
					if(leftButton!=null)
						leftButton.performClick();
					break;
				case R.id.trButtonBox:
					if(rightButton!=null)
						rightButton.performClick();
					break;
			}
		}
	};
	
//	public void setLeftButton()
//	{
//		leftButton.setText("未登录");
//		rightButton.setBackgroundResource(R.drawable.icon_add);
//		
//	}
	
	
	
	
	
	/**
	 * 设置返回按钮是否启用
	 * @param enable
	 */
//	public void backButtonVisibility(boolean enable)
//	{
//		if(rLayout==null)
//			rLayout=(RelativeLayout)findViewById(R.id.rlBack);
//		rLayout.setVisibility(enable?View.VISIBLE:View.INVISIBLE);
//	}
	
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
	
	//第三方帧显示，不替换Key
	public void ShowOther(int id)
	{
		hiden();
		BaseFragment fragmentElement=(BaseFragment)fragmentManager.findFragmentById(id);
		String _text=fragmentElement.setTitle();
		titleText.setText(_text);
		fragmentElement.show();
		if((leftButton=fragmentElement.btnLeftButton)!=null)
		{
			leftButton.setId(R.id.title_button_left_id);
			this.rtlButtonBox.addView(fragmentElement.btnLeftButton);
		}
		if((rightButton=fragmentElement.btnRightButton)!=null)
		{
			rightButton.setId(R.id.title_button_right_id);
			this.rtrButtonBox.addView(fragmentElement.btnRightButton);
		}
		fragmentTransaction.show(fragmentElement).commit();
	}
	
	//取消
	public void CancelOther()
	{
		show(Integer.parseInt(key));
	}
	
	/**
	 * 
	 */
	private void hiden()
	{
		//Daniel 2015-01-18	1:添加界面切换隐藏输入法
		InputMethodManager imm = ( InputMethodManager )this.getSystemService( Context.INPUT_METHOD_SERVICE ); 
		if(imm.isActive())
			imm.hideSoftInputFromWindow(this.titleText.getWindowToken(), 0);
		//隐藏界面
		fragmentTransaction=fragmentManager.beginTransaction();
		for(BaseFragment fragmentElement : fragments.values())
		{
			fragmentElement.hide();
			//Daniel 内容在切换的时候进行按钮的切换，先移除指定功能按钮
			rtlButtonBox.removeAllViews();
			rtrButtonBox.removeAllViews();
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
			//Daniel 获取指定的功能按钮进行显示
			if((leftButton=fragmentElement.btnLeftButton)!=null)
			{
				leftButton.setId(R.id.title_button_left_id);
				this.rtlButtonBox.addView(fragmentElement.btnLeftButton);
			}
			if((rightButton=fragmentElement.btnRightButton)!=null)
			{
				rightButton.setId(R.id.title_button_right_id);
				this.rtrButtonBox.addView(fragmentElement.btnRightButton);
			}
			
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
	
	//标题栏按钮点击事件
//	private OnClickListener buttonOnClickListener=new OnClickListener(){
//		@Override
//		public void onClick(View v) {
//			switch(v.getId())
//			{
//				case R.id.title_button_left_id:
//					
//					break;
//				case R.id.title_button_right_id:
//					LayoutMainActivity.getThis().ShowOther(R.id.frament_task_add);
//					break;
//			}
//		}
//	};
	
}