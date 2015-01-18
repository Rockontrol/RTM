package org.chonger.rpm.ui.views;

import org.chonger.common.android.views.BaseFragmentActivity;
import org.chonger.rpm.R;

import android.os.Bundle;



public class LayoutMainActivity extends BaseFragmentActivity  {
	
	private static LayoutMainActivity _this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.views_layout_main);	
		
		this.addTabs(R.id.frament_calendar,R.id.rdobtn_calendar);
		this.addTabs(R.id.frament_task,R.id.rdobtn_task);
		this.addTabs(R.id.frament_mail,R.id.rdobtn_mail);
		this.addTabs(R.id.frament_message,R.id.rdobtn_message);
		this.addTabs(R.id.frament_more,R.id.rdobtn_more);
		
		//ÆäËû
		this.addTabs(R.id.frament_vp_login,R.id.frament_vp_login);
		this.addTabs(R.id.frament_task_add,R.id.frament_task_add);
		
		this.show();
		
		this.bindBtns(R.id.bottomGroup);
		
		_this=this;
	}
	
	public static LayoutMainActivity getThis()
	{
		return _this;
	}
	
}
