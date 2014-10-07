package jamffy.example.pull_down_menu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ImageView wIv_down;
	private EditText wEt;
	private PopupWindow wPop;
	private ListView wLv;

	private List<Integer> mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		wIv_down = (ImageView) findViewById(R.id.iv_down);
		wEt = (EditText) findViewById(R.id.editText1);

		// 创造20个号码
		mList = new ArrayList<Integer>();
		for (int i = 0; i < 20; i++) {
			mList.add(100000 + i);
		}

		wIv_down.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 创建popwindow
				wPop = new PopupWindow();

				wLv = new ListView(MainActivity.this);
				wLv.setAdapter(new MyAdapter());
				

			}
		});

	}

	public class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			ViewHolder holder = null;
			if (convertView != null) {
				holder = (ViewHolder) convertView.getTag();

			} else {
				view = View.inflate(getApplicationContext(), R.layout.list,
						null);
				holder = new ViewHolder();
				holder.tv = (TextView) view.findViewById(R.id.tv_number);
				holder.iv = (ImageView) view.findViewById(R.id.iv_delete);
				view.setTag(holder);
			}
			holder.tv.setText(mList.get(position) + "");

			holder.iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 点击删除后，从集合中删除数据，并通知listview更新

				}
			});

			return view;
		}

	}

	private static class ViewHolder {
		TextView tv;
		ImageView iv;
	}

}
