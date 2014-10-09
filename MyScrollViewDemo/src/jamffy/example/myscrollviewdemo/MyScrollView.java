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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		System.out.println(" onTouch");
		detector.onTouchEvent(event);

		// onTounch 需要消费掉事件
		return true;
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
