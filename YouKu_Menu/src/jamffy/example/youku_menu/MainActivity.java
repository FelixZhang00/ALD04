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

	private boolean mIsMenuChecked = false;
	private String TAG="MainActivity";

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
			if (!mIsMenuChecked) {
				// 启动显示动画
				Utils.setAnimationIn(wRL_lev3);
			} else {
				// 启动隐藏动画
				Utils.setAnimationout(wRL_lev3);
			}
			mIsMenuChecked = !mIsMenuChecked;

			break;

		case R.id.iv_home:

			break;

		default:
			break;
		}

	}

}
