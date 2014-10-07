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
		initListView();
		wIv_down.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 点击下拉菜单后，如果不存在则弹出popwindow
				if (wPop == null) {
					wPop = new PopupWindow();
				}

				if (wPop.isShowing()) {
					// 如果存在则销毁
					wPop.dismiss();
				} else {
					initPopWindow();
				}

			}

		});

	}

	private void initListView() {
		wLv = new ListView(MainActivity.this);
		wLv.setBackgroundResource(R.drawable.listview_background);
		// wLv.setDivider(null); // 设置条目之间的分隔线
		wLv.setVerticalScrollBarEnabled(false);
		wLv.setAdapter(new MyAdapter());

	}

	// 创建popwindow
	private void initPopWindow() {

		// 设置宽高
		// int height=getWindowManager().getDefaultDisplay().getHeight();
		wPop.setHeight(400);
		// 与输入框同宽
		wPop.setWidth(wEt.getWidth());

		// 设置pop的内容
		wPop.setContentView(wLv);
		// 设置pop的位置
		wPop.showAsDropDown(wEt, 0, 0);
		wPop.setOutsideTouchable(true); // 点击popWin 以处的区域，自动关闭 popWin
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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View view = null;
			ViewHolder holder = null;
			if (convertView != null) {
				holder = (ViewHolder) convertView.getTag();
				view = convertView;
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
					mList.remove(position);
					
					MyAdapter.this.notifyDataSetChanged();
				}
			});

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 点击条目后，将内容输出到editview中
					wEt.setText(mList.get(position) + "");
					wPop.dismiss();

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
