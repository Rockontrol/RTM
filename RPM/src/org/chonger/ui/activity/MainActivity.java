package org.chonger.ui.activity;

import org.chonger.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 主界面，包含菜单
 * @author Daniel
 *
 */
public class MainActivity extends FragmentActivity {

	private Fragment[] fragments;
	private RadioGroup bottomRg;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private RadioButton rb_Cal,rb_Project,rb_OA,rb_Rtx,rb_package,rb_setting;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化功能片段
		fragments=new Fragment[3];
		fragmentManager=this.getSupportFragmentManager();
		fragments[0]=fragmentManager.findFragmentById(R.id.frament_cal);
		fragments[1]=fragmentManager.findFragmentById(R.id.frament_project);
		fragments[2]=fragmentManager.findFragmentById(R.id.frament_oa);
		
		fragmentTransaction = fragmentManager.beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2]);
		fragmentTransaction.show(fragments[0]).commit();
		
		setFragmentIndicator();
		
	}
	
	private void setFragmentIndicator()
	{
		bottomRg=(RadioGroup)findViewById(R.id.bottomGroup);
		rb_Cal=(RadioButton)findViewById(R.id.rbCal);
		rb_Project=(RadioButton)findViewById(R.id.rbProject);
		rb_OA=(RadioButton)findViewById(R.id.rbOa);
		rb_Rtx=(RadioButton)findViewById(R.id.rbmsg);
		//rb_package=(RadioButton)findViewById(R.id.rbpackage);
		rb_setting=(RadioButton)findViewById(R.id.rbmenus);
		
		bottomRg.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				fragmentTransaction = fragmentManager.beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2]);
				switch(checkedId)
				{
					case R.id.rbCal:
						fragmentTransaction.show(fragments[0]).commit();
						break;
					case R.id.rbProject:
						fragmentTransaction.show(fragments[1]).commit();
						break;
					case R.id.rbOa:
						fragmentTransaction.show(fragments[2]).commit();
						break;
				}
			}
			
		});
	}
}
