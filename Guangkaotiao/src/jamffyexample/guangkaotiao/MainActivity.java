package jamffyexample.guangkaotiao;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ViewPager wVp;
	private LinearLayout wLL_pointGroup;
	private TextView wTv_imagDesc;
	private final int[] imagesId = new int[] { R.drawable.d, R.drawable.b,
			R.drawable.c, R.drawable.a, R.drawable.e, };

	// 图片标题集合
	private final String[] imageDescriptions = { "原创：中国“飞鱼”鹰击8系列反舰导弹族谱",
			"土耳其库尔德人抗议引发冲突致14死", "【新浪有奖征集】国庆晒人肉", "全球多地现月全食景观", "自驾游汽车运输班列返京 车主盛赞方便" };

	private List<ImageView> mList;
	private int lastposition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wVp = (ViewPager) findViewById(R.id.viewpages);
		wLL_pointGroup = (LinearLayout) findViewById(R.id.pointsgroup);
		wTv_imagDesc = (TextView) findViewById(R.id.tv_imagdesc);

		wTv_imagDesc.setText(imageDescriptions[0]);

		mList = new ArrayList<ImageView>();
		for (int i : imagesId) {
			// 初始化图片资源
			ImageView iv = new ImageView(this);
			// BitmapFactory.Options bfo=new BitmapFactory.Options();
			// bfo.inSampleSize=2;
			// Bitmap bm=BitmapFactory.decodeResource(getResources(), i, bfo);
			// iv.setImageBitmap(bm);
			iv.setBackgroundResource(i);
			mList.add(iv);

			// 添加指示点
			ImageView point = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			params.rightMargin = 20;
			point.setLayoutParams(params);

			point.setBackgroundResource(R.drawable.point_selector);
			// 处理point的显示状态
			if (i == R.drawable.d) {
				point.setEnabled(false);
			} else {
				point.setEnabled(true);
			}

			wLL_pointGroup.addView(point);

		}

		wVp.setAdapter(new MyPageAdapter());
		// 找到一个%mList.size()后为0的位置，即展示第一张图片实际上位置不是第一个
		wVp.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2)
				% mList.size());
		wVp.setOnPageChangeListener(new OnPageChangeListener() {

			/*
			 * 页面切换后调用的方法
			 */
			@Override
			public void onPageSelected(int position) {
				position = position % mList.size();
				// 设置图片说明
				wTv_imagDesc.setText(imageDescriptions[position]);
				// 改变指示点状态
				wLL_pointGroup.getChildAt(position).setEnabled(false);
				// 改变上一个指示点的状态
				wLL_pointGroup.getChildAt(lastposition).setEnabled(true);
				lastposition = position;

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub

			}
		});
		isRunning=true;
		handler.sendEmptyMessageDelayed(0, 1000);

	}

	private boolean isRunning = false;
	// 用handler 发送延时消息,实现viewpage自动循环滚动
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
			wVp.setCurrentItem(wVp.getCurrentItem()+1);
			
			if (isRunning) {
				handler.sendEmptyMessageDelayed(0, 1000);				
			}
			super.handleMessage(msg);
		}

	};

	@Override
	protected void onDestroy() {
		isRunning = false;
		super.onDestroy();
	}

	public class MyPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			if (view == object) {
				return true;
			} else {
				return false;
			}

		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			container.addView(mList.get(position % mList.size()));
			return mList.get(position % mList.size());
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
			object = null;
			// super.destroyItem(container, position, object);
		}

	}

}
