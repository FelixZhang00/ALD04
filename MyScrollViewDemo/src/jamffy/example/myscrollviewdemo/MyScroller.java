package jamffy.example.myscrollviewdemo;

import android.content.Context;
import android.os.SystemClock;

/**
 * 用于计算移动距离的工具类，提供匀速的动画效果
 * 
 * @author tmac
 *
 */
public class MyScroller {
	// 设置默认的动画时间为300ms
	int duration = 300;
	private Context context;

	private int startX;
	private int startY;
	private int disX;
	private int disY;
	// 获取准备移动时的时间点
	private long startTime;
	/**
	 * 判断是否正在执行动画 true 是还在运行 false 已经停止
	 */
	private boolean isFinish;

	// 计算出的当前的位置
	private int currX;
	private int currY;

	public MyScroller() {

	}

	public MyScroller(Context context) {
		this.context = context;
	}

	/**
	 * 先设置初始值
	 * 
	 * @param scrollX
	 * @param startY
	 * @param distance
	 * @param disY
	 */
	public void startScroll(int scrollX, int startY, int distance, int disY) {
		this.startX = scrollX;
		this.startY = startY;
		this.disX = distance;
		this.disY = disY;
		this.startTime = SystemClock.uptimeMillis();
		this.isFinish = false;

	}

	/**
	 * 计算当前位置
	 * 
	 * @return true 没有到达目的地，还要继续刷新移动
	 */
	public boolean computeScrollOffset() {
		if (isFinish) {
			return false;
		}

		// 获取时间间隔
		long passtime = SystemClock.uptimeMillis()- startTime;
		// 判断何时运动结束，将isFinish置为true
		if (passtime < duration) {
			currX = (int) (startX + disX * passtime / duration);
			currY = (int) (startY + disY * passtime / duration);

		} else {
			currX = startX + disX;
			currY = startY + disY;
			isFinish=true;
		}

		return true;
	}

	public int getCurrX() {
		return currX;
	}

	public int getCurrY() {
		return currY;
	}

}
