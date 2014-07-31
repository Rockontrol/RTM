package org.chonger.ui.activity.welcome;

import java.util.LinkedList;
import java.util.List;

import org.chonger.R;
import org.chonger.ui.activity.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

/**
 * 欢迎导航界面
 * @author Daniel
 *
 */
public class WelcomeActivity extends Activity {
	
	//翻页控件
	private ViewPager mViewPager;
	
	private ImageView[] imageViews;
	
	private List<View> views;
	
	private Drawable drawable_select,drawable_noselect;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.welcome);
        
        mViewPager=(ViewPager)this.findViewById(R.id.welcome_viewpager);
        mViewPager.setOnPageChangeListener(new myViewPagerChangeListener());
        
        imageViews=new ImageView[5];
        imageViews[0]=(ImageView)this.findViewById(R.id.welcome_page0);
        imageViews[1]=(ImageView)this.findViewById(R.id.welcome_page1);
        imageViews[2]=(ImageView)this.findViewById(R.id.welcome_page2);
        imageViews[3]=(ImageView)this.findViewById(R.id.welcome_page3);
        imageViews[4]=(ImageView)this.findViewById(R.id.welcome_page4);
        
        
        views=new LinkedList<View>();
        LayoutInflater inflater = LayoutInflater.from(this);
        views.add(inflater.inflate(R.layout.welcome_one, null));
        views.add(inflater.inflate(R.layout.welcome_two, null));
        views.add(inflater.inflate(R.layout.welcome_three, null));
        views.add(inflater.inflate(R.layout.welcome_four, null));
        View viewWelcomt=inflater.inflate(R.layout.welcome_five, null);
        views.add(viewWelcomt);
        
        drawable_select=getResources().getDrawable(R.drawable.page_now);
        drawable_noselect=getResources().getDrawable(R.drawable.page);
        
        mViewPager.setAdapter(new myPagerAdapter());
        
        ImageView welcomeBtn=(ImageView)viewWelcomt.findViewById(R.id.welcomebtn);
        welcomeBtn.setOnClickListener(new welcomeBtnClickListener());
	}
	
	class welcomeBtnClickListener implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			//Toast.makeText(WelcomeActivity.this, "欢迎使用", Toast.LENGTH_LONG).show();
			Intent intent=new Intent();
			intent.setClass(WelcomeActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		
	}
	
	class myPagerAdapter extends PagerAdapter
	{

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager)container).removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			View view=views.get(position);
			((ViewPager)container).addView(view);
			return view;
		}
		
		
	}
	
	class myViewPagerChangeListener implements OnPageChangeListener
	{

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			for(int i=0;i<imageViews.length;i++)
			{
				imageViews[i].setImageDrawable(i==arg0?drawable_select:drawable_noselect);
			}
		}
	}
	
	
	
}
