package jamffy.example.waterfalllistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author tmac 如果不做处理MyLinearLayout中的子view能自行处理touch事件。
 *         现在我希望当我在屏幕中间上方拖动时，整个屏幕的子view一起向上拖动，
 *         这是就需要在满足条件时，中断该touch事件，交给MyLinearLayout这个父view来处理。
 *         先中断所有子view的touch事件，然后根据触摸的位置，有父view把点击事件分发给相应的子view。
 *         在分发之前需要给touch事件的对象event重新设置位置，因为子view的坐标系与父view是不同的。
 */

// 这个类专门为三个子listview服务。
public class MyLinearLayout extends LinearLayout {

	public MyLinearLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	// 返回true中断点击事件
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	// 用分发的方式决定哪个子view可以收到点击事件
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int count = getChildCount();
		// 每个子view的宽度
		int width = getWidth() / count;
		int height = getHeight();

		// 当前触摸的位置
		int currX = (int) event.getX();
		int currY = (int) event.getY();
		// 判断位置
		if (currX < width) { // 处理最左边的逻辑
			// 在分发之前需要给touch事件的对象event重新设置位置，因为子view的坐标系与父view是不同的。
			event.setLocation(width / 2, currY);
			getChildAt(0).dispatchTouchEvent(event);
			return true;
		} else if (currX < 2 * width) { // 处理中间的逻辑
			if (currY > height / 2) { // 如果在下方，只移动中间的子view
				event.setLocation(width / 2, currY);
				getChildAt(1).dispatchTouchEvent(event);
				return true;
			} else {// 如果在上方，拖动三个view
				event.setLocation(width, currY);
			// 同时把touch事件分发给三个子view
				for(int i=0;i<count;i++){
					getChildAt(i).dispatchTouchEvent(event);
				}
				return true;
			}

		} else if (currX < 3 * width) { // 处理最右边的逻辑
			event.setLocation(width / 2, currY);
			getChildAt(2).dispatchTouchEvent(event);
			return true;

		}

		return true;
	}

}
