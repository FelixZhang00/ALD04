package jamffy.example.youku_menu;

import android.view.View;
import android.view.animation.RotateAnimation;

public class Utils {

	public static void setAnimationIn(View v) {
		RotateAnimation ra1 = new RotateAnimation(0, 180, v.getWidth() / 2,
				v.getHeight());
		ra1.setDuration(1);
		v.startAnimation(ra1);
		v.setVisibility(View.VISIBLE);

		RotateAnimation ra2 = new RotateAnimation(180, 360, v.getWidth() / 2,
				v.getHeight());
		ra2.setDuration(500);
		ra2.setFillAfter(true); // 动画执行完以后，保持最后的状态
		v.setAnimation(ra2);
	}

	public static void setAnimationout(View v) {
		RotateAnimation ra1=new RotateAnimation(0, 180, v.getWidth()/2, v.getHeight());
		ra1.setDuration(500);
		ra1.setFillAfter(true); //动画执行完以后，保持最后的状态
		v.startAnimation(ra1);
		
	}

}
