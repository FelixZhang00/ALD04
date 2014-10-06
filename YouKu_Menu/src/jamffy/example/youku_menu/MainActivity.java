package jamffy.example.youku_menu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {

	private RelativeLayout wRL_lev1;
	private RelativeLayout wRL_lev2;
	private RelativeLayout wRL_lev3;
	private ImageView wIm_home;
	private ImageView wIm_menu;

	private boolean mIsLev3Show = false;
	private boolean mIsLev2Show = false;
	private String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();
		setupViewListener();
	}

	private void setupView() {

		wRL_lev1 = (RelativeLayout) findViewById(R.id.level1);
		wRL_lev2 = (RelativeLayout) findViewById(R.id.level2);
		wRL_lev3 = (RelativeLayout) findViewById(R.id.level3);

		wIm_home = (ImageView) findViewById(R.id.iv_home);
		wIm_menu = (ImageView) findViewById(R.id.iv_menu);

	}

	private void setupViewListener() {
		wIm_home.setOnClickListener(this);
		wIm_menu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_menu:

			// 点击后实现 第三级菜单的旋转动画效果
			if (!mIsLev3Show) {
				// 启动显示动画
				Utils.setAnimationIn(wRL_lev3);
			} else {
				// 启动隐藏动画
				Utils.setAnimationout(wRL_lev3);
			}
			mIsLev3Show = !mIsLev3Show;

			break;

		case R.id.iv_home:
			// 点击home图标后，如果当前没有2级菜单就显示，存在2、3级菜单就有层次地隐藏

			if (mIsLev2Show) {
				// 存在2、3级菜单就有层次地隐藏
				Utils.setAnimationout(wRL_lev2);
				mIsLev2Show = false;
				if (mIsLev3Show) {
					Utils.setAnimationout(wRL_lev3, 200); // 延迟隐藏，有层次感
					mIsLev3Show = false;
				}

			} else {
				// 如果当前没有2级菜单就显示
				Utils.setAnimationIn(wRL_lev2);
				mIsLev2Show = true;
			}

			break;

		default:
			break;
		}

	}

}
