package jamffyexample.guangkaotiao;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ViewPager wVp;

	private final int[] imagesId = new int[] { R.drawable.d, R.drawable.b,
			R.drawable.c, R.drawable.a, R.drawable.e, };

	 //图片标题集合
		private final String[] imageDescriptions = {
				"乐视网TV版大派送",
				"扑树又回来啦！再唱经典老歌引万人大合唱",
				"揭秘北京电影如何升级",
				"巩俐不低俗，我就不能低俗",
				"热血屌丝的反杀"
		};
	
	private List<ImageView> mList;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wVp = (ViewPager) findViewById(R.id.viewpages);
		mList = new ArrayList<ImageView>();

		for (int i : imagesId) {
			ImageView iv = new ImageView(this);
			// BitmapFactory.Options bfo=new BitmapFactory.Options();
			// bfo.inSampleSize=2;
			// Bitmap bm=BitmapFactory.decodeResource(getResources(), i, bfo);
			// iv.setImageBitmap(bm);
			iv.setBackgroundResource(i);
			mList.add(iv);
			
			
			

		}
		wVp.setAdapter(new MyPageAdapter());

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
