package jamffy.example.waterfalllistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private MyLinearLayout layout;
	private ListView Lv1;
	private ListView Lv2;
	private ListView Lv3;

//	private int[] ids = new int[] { R.drawable.a, R.drawable.b, R.drawable.c,
//			R.drawable.d };
	private int[] ids = new int[] { R.drawable.shape_1, R.drawable.shape_2, R.drawable.shape_3,
			R.drawable.shape_4 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		layout = (MyLinearLayout) findViewById(R.id.layout);
		Lv1 = (ListView) findViewById(R.id.lv1);
		Lv2 = (ListView) findViewById(R.id.lv2);
		Lv3 = (ListView) findViewById(R.id.lv3);

		Lv1.setAdapter(new MyAdapter());
		Lv2.setAdapter(new MyAdapter());
		Lv3.setAdapter(new MyAdapter());

	}

	public class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public Object getItem(int position) {

			return position;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView view = null;
			if (convertView != null) {
				view = (ImageView) convertView;
			} else {
				view = (ImageView) View.inflate(getApplicationContext(),
						R.layout.imagview, null);
				// 产生0~3的随机数
				int index = (int) (Math.random() * 4);
				view.setImageResource(ids[index]);
			}
			return view;
		}
	}

}