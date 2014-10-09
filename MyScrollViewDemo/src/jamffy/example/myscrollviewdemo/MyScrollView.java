package jamffy.example.myscrollviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MyScrollView extends ViewGroup {

	// 手势识别的工具类
	private GestureDetector detector;
	private Context context;

	/**
	 * 在代码里面创建对象的时候，使用此构造方法
	 */
	public MyScrollView(Context context) {
		super(context);
		this.context = context;
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	// 记录当前子view的编号
	private int currId = 0;
	// 记录down 时的位置
	private int firstX = 0;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		detector.onTouchEvent(event);

		// 实现自定义的效果
		// 子view被横向拖动超过屏幕宽度的一半及以上，并且松手时，就自动弹到下一个view
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			firstX = (int) event.getX();
			break;

		case MotionEvent.ACTION_MOVE:

			break;

		case MotionEvent.ACTION_UP:
			// 计算移动的距离
			int dis = (int) (event.getX() - firstX);
			System.out.println("dis=" + dis);
			// 处理切换子view的逻辑
			ctrlSwitchView(dis);
			break;

		default:
			break;
		}

		// onTounch 需要消费掉事件
		return true;
	}

	// 找到下一个子view的编号 ，为处理切换子view的逻辑提供辅助
	private void ctrlSwitchView(int dis) {
		int nextId = currId;
		// 判断手指移动的方向及距离
		if (dis > 0) { // 手指向右移动
			if (dis >= getWidth() / 2) { // 且达到切换下一个view的指标
				nextId--;
			} else { // 没达到指标，当前子view归到原位
				nextId = currId;
			}
		} else { // 手指向左移动
			if (dis <= -getWidth() / 2) {
				nextId++;
			} else {
				nextId = currId;
			}
		}
		moveToDest(nextId);

	}

	/**
	 * 处理切换子view的逻辑。移动被选中的view到屏幕
	 * 
	 * @param nextId
	 */
	private void moveToDest(int nextId) {
		/*
		 * 对 nextId 进行判断 ，确保 是在合理的范围 即 nextId >=0 && nextId <=getChildCount()-1
		 */

		System.out.println("nextid=" + nextId);
		// 仔细看下面这段代码，不能确保在合理的范围
		currId = (nextId <= getChildCount() - 1) ? nextId
				: (getChildCount() - 1);
		currId = (nextId >= 0) ? nextId : 0;

		// 修改如下：
		if (nextId >= 0 && nextId <= (getChildCount() - 1)) {
			currId = nextId;
		} else if (nextId < 0) {
			currId = 0;
		} else if (nextId > (getChildCount() - 1)) {
			currId = (getChildCount() - 1);
		}

		System.out.println("currid=" + currId);
		scrollTo(currId * getWidth(), 0);
	}

	/**
	 * 初始化view
	 */
	private void initView() {
		detector = new GestureDetector(context, new OnGestureListener() {

			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("onSingleTapUp");
				return false;
			}

			@Override
			public void onShowPress(MotionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			/**
			 * 响应手指在屏幕上的滑动事件
			 */
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {

				System.out.println("移动屏幕：" + distanceX);
				scrollBy((int) distanceX, 0);

				return false;
			}

			@Override
			public void onLongPress(MotionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			// 快速滑动时调用
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onDown(MotionEvent e) {
				// TODO Auto-generated method stub
				return false;
			}
		});

	}

	@Override
	/**
	 * 对子view进行布局，确定子view的位置
	 * changed  若为true ，说明布局发生了变化
	 * l\t\r\b\  是指当前viewgroup 在其父view中的位置 
	 */
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			/**
			 * 父view 会根据子view的需求，和自身的情况，来综合确定子view的位置,(确定他的大小)
			 */
			// 指定子view的位置 , 左，上，右，下，是指在viewGround坐标系中的位置
			view.layout(0 + i * getWidth(), 0, getWidth() + i * getWidth(),
					getHeight());
		}

	}

}
