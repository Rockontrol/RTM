package org.chonger.rpm.ui.views.task;

import org.chonger.common.android.views.BaseFragment;
import org.chonger.rpm.R;
import org.chonger.rpm.ui.views.LayoutMainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
/**
 * 任务添加界面
 * @author Daniel
 *
 */
public class AddTaskFragment extends BaseFragment {
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.view_task_add, container, false);
	}

	@Override
	public String setTitle() {
		return this.getString(R.string.task_add_title);
	}

	@Override
	public void initButton() {
		this.btnLeftButton=this.createButton();
		this.btnLeftButton.setText(this.getResources().getString(R.string.common_cancel));
		this.btnLeftButton.setOnClickListener(buttonOnClickListener);
		
		this.btnRightButton=this.createButton();
		this.btnRightButton.setText(this.getResources().getString(R.string.common_save));
		this.btnRightButton.setOnClickListener(buttonOnClickListener);
	}
	
	/**
	 * 按钮点击事件
	 */
	private OnClickListener buttonOnClickListener=new OnClickListener(){
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
				case R.id.title_button_left_id:
					LayoutMainActivity.getThis().CancelOther();
					break;
				case R.id.title_button_right_id:
					break;
			}
		}
		
	};
	
}
