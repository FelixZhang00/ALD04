package jamffyexample.guangkaotiao;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
	private final String[] imageDescriptions = { "乐视网TV版大派送",
			"扑树又回来啦！再唱经典老歌引万人大合唱", "揭秘北京电影如何升级", "巩俐不低俗，我就不能低俗", "热血屌丝的反杀" };

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
			if (i ==R.drawable.d) {
				point.setEnabled(false);
			} else {
				point.setEnabled(true);
			}
			
			wLL_pointGroup.addView(point);

		}

		wVp.setAdapter(new MyPageAdapter());

		wVp.setOnPageChangeListener(new OnPageChangeListener() {

			/*
			 * 页面切换后调用的方法
			 */
			@Override
			public void onPageSelected(int position) {
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

	}

	public class MyPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {

			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			container.addView(mList.get(position));
			return mList.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
			object = null;
			// super.destroyItem(container, position, object);
		}

	}

}
