package jamffy.example.suibowen;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

	// 记录触摸时的位置
	private int currX;
	private int currY;

	private float radius;
	private boolean flag_touch = false;

	private Paint mPaint;
	private int alpha;

	// 有几种供选择的颜色
	private int[] colors = new int[] { Color.RED, Color.YELLOW, Color.GREEN,
			Color.BLUE, Color.rgb(245, 66, 241) };

	/**
	 * 每隔一定的时间刷新view，将改变后的view呈现出来
	 */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			flushStats();
			invalidate();
			// 每隔100ms重新画圆
			if (mPaint.getAlpha() != 0) { // 当透明度为0时，停止发送
				handler.sendEmptyMessageAtTime(0, 50);
			} else {// 重新设置初始参数
				initView();
			}
			super.handleMessage(msg);
		}

	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		radius = 0;
		alpha = 255;
		// 初始化画笔
		mPaint = new Paint();
		// 打开抗锯齿
		mPaint.setAntiAlias(true);
		// 设置画笔的随机颜色
		mPaint.setColor(colors[(int) (Math.random()*(colors.length))]);
		// 这样画出来的圆是空心的
		mPaint.setStyle(Style.STROKE);
		// 设置半径厚度
		mPaint.setStrokeWidth(radius / 3);
		// 设置透明度
		mPaint.setAlpha(alpha);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (flag_touch) {// 只有触摸后才画圆
			// 画圆
			canvas.drawCircle(currX, currY, radius, mPaint);
		}
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			flag_touch = true;
			currX = (int) event.getX();
			currY = (int) event.getY();
			initView();
			startAnim();
			break;

		default:
			break;
		}

		return true;
	}

	private void startAnim() {
		handler.sendEmptyMessage(0);
	}

	// 重新设置圆的参数
	private void flushStats() {

		radius += 5;
		mPaint.setStrokeWidth(radius / 4);
		// 下面的注释的内容逻辑有问题
		// if ((alpha-=20)>=0) {
		// mPaint.setAlpha(alpha);
		// }

		int nextAlpha = Math.max(0, mPaint.getAlpha() - 10);
		mPaint.setAlpha(nextAlpha);

	}

}
