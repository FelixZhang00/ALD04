package jamffy.example.suibowen;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private Paint mPaint;

	private void initView() {
		// 初始化画笔
		mPaint = new Paint();
		// 打开抗锯齿
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.GREEN);
		// 这样画出来的圆是空心的
		mPaint.setStyle(Style.STROKE);
		// 设置半径厚度
		mPaint.setStrokeWidth(radius / 3);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (flag_touch) {// 只有触摸后才画圆
			// 画圆
			canvas.drawCircle(currX, currY, radius, mPaint);
		}
		super.onDraw(canvas);
	}

	// 记录触摸时的位置
	private int currX;
	private int currY;

	float radius = 50;
	private boolean flag_touch = false;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			flag_touch = true;
			currX = (int) event.getX();
			currY = (int) event.getY();
			invalidate();
			break;

		default:
			break;
		}

		return true;
	}

}
