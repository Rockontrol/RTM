package org.chonger.rpm.ui.views.vp;

import org.chonger.common.android.views.BaseFragment;
import org.chonger.rpm.R;
import org.chonger.rpm.ui.views.LayoutMainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
/**
 * VP系统模块的登陆界面
 * @author Daniel
 *
 */
public class LoginFragment extends BaseFragment {
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.view_vp_login, container, false);
	}

	@Override
	public String setTitle() {
		return this.getString(R.string.vp_login_title);
	}

	@Override
	public void initButton() {
		this.btnLeftButton=this.createButton();
		this.btnLeftButton.setText(this.getResources().getString(R.string.common_cancel));
		this.btnLeftButton.setOnClickListener(leftButtonOnClickListener);
	}
	
	private OnClickListener leftButtonOnClickListener=new OnClickListener(){
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
