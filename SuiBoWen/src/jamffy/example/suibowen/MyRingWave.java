package jamffy.example.suibowen;

import java.util.ArrayList;
import java.util.List;

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

/**
 * @author tmac 能同时显示多个水波纹
 */
public class MyRingWave extends View {

	// 记录触摸时的位置
	private int currX;
	private int currY;

	private float radius;
	private boolean flag_touch = false;

	private Paint mPaint;

	// 存放wave对象的集合
	private List<Wave> waves;

	// 只要还有一个wave能看见，就置为true
	private boolean isRunning = false;

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
			if (isRunning) {
				handler.sendEmptyMessageDelayed(0, 50);
			}
			super.handleMessage(msg);
		}

	};

	public MyRingWave(Context context, AttributeSet attrs) {
		super(context, attrs);
		waves = new ArrayList<MyRingWave.Wave>();
	}

	private void initView() {
		radius = 0;
		// 初始化画笔
		mPaint = new Paint();
		// 打开抗锯齿
		mPaint.setAntiAlias(true);
		// 设置画笔的随机颜色
		mPaint.setColor(colors[(int) (Math.random() * (colors.length))]);
		// 这样画出来的圆是空心的
		mPaint.setStyle(Style.STROKE);
		// 设置半径厚度
		mPaint.setStrokeWidth(radius / 3);
		// 设置透明度
		mPaint.setAlpha(255);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (flag_touch) {// 只有触摸后才画圆
			// 根据参数画圆
			for (Wave wave : waves) {
				canvas.drawCircle(wave.wX, wave.wY, wave.wR, wave.wPaint);
			}
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
			addWave2List(currX, currY, mPaint);
			// 只要在第一次点击时向handler发送消息即可，后面的消息由handler控制延迟发送
			// startAnim();
			break;

		default:
			break;
		}

		return true;
	}

	private void startAnim() {
		handler.sendEmptyMessage(0);
	}

	// 负责的功能：
	// 重新设置waves中所有wave的参数
	// 根据wave的画笔的透明度,决定是否把它从waves中剔除
	// 根据waves中是否还有元素来设置isRunning的值
	private void flushStats() {

		for (int i = 0; i < waves.size(); i++) {
			Wave wave = waves.get(i);
			int nextAlpha = Math.max(0, wave.wPaint.getAlpha() - 5);
			// 负责剔除透明度为0的wave
			if (nextAlpha == 0) {
				waves.remove(i);
				i--;
				// 跳出当前的for循环，重新遍历
				continue;
			}
			// 如果wave的透明度不为0
			// 重新设置waves中所有wave的参数
			wave.wR += 4;
			wave.wPaint.setStrokeWidth(waves.get(i).wR / 4);
			wave.wPaint.setAlpha(nextAlpha);

		}

		// 根据waves中是否还有元素来设置isRunning的值
		if (waves.size() == 0) {
			isRunning = false;
		}

	}

	/**
	 * @author tmac 水波纹类
	 */
	private class Wave {
		// 圆心位置
		private int wX;
		private int wY;
		// 半径
		private int wR;
		// 每个Wave对象都应该有一个paint对象
		private Paint wPaint;

		public Wave() {
			super();
		}

		public Wave(int wX, int wY, int wR, Paint wPaint) {
			super();
			this.wX = wX;
			this.wY = wY;
			this.wR = wR;
			this.wPaint = wPaint;
		}

	}

	/**
	 * 向集合中加入水波纹
	 * 
	 * @param p
	 *            画笔
	 * @param y
	 *            圆心坐标
	 * @param x
	 */
	private void addWave2List(int x, int y, Paint p) {
		if (waves.size() == 0) {
			isRunning = true;
			startAnim();
		}
		// 初始半径为0
		int r = 0;
		Wave wave = new Wave(x, y, r, p);
		waves.add(wave);
	}

}
