package jamffy.example.youku_menu;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class Utils {

	public static void setAnimationIn(View v) {
		setAnimationIn(v, 0);
	}

	public static void setAnimationIn(View v, int time) {
		RotateAnimation ra1 = new RotateAnimation(0, 180, v.getWidth() / 2,
				v.getHeight());
		ra1.setDuration(1);
		v.startAnimation(ra1);
		v.setVisibility(View.VISIBLE);

		RotateAnimation ra2 = new RotateAnimation(180, 360, v.getWidth() / 2,
				v.getHeight());
		ra2.setDuration(500);
		ra2.setFillAfter(true); // 动画执行完以后，保持最后的状态
		ra2.setStartOffset(time);
		v.setAnimation(ra2);

	}

	public static void setAnimationout(View v) {
		setAnimationout(v, 0);

	}

	/**
	 * @param v
	 * @param time
	 *            延迟时间
	 */
	public static void setAnimationout(View v, int time) {
		RotateAnimation ra1 = new RotateAnimation(0, 180, v.getWidth() / 2,
				v.getHeight());
		ra1.setDuration(500);
		ra1.setFillAfter(true); // 动画执行完以后，保持最后的状态
		ra1.setStartOffset(time); // 设置延迟时间
		v.startAnimation(ra1);

	}

}
