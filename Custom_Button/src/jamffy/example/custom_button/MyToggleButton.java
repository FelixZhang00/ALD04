package jamffy.example.custom_button;

import android.R.raw;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

/*
 * view 对象显示的屏幕上，有几个重要步骤：
 * 1、构造方法 创建 对象。
 * 2、测量view的大小。     onMeasure(int,int);
 * 3、确定view的位置 ，view自身有一些建议权，决定权在 父view手中。  onLayout();
 * 4、绘制 view 的内容 。 onDraw(Canvas)
 */

public class MyToggleButton extends View implements OnClickListener {

	private Bitmap bgBitmap;
	private int bgBitmapId;
	private Bitmap slideBtn;
	private int slideBtnId;
	private Paint paint;

	// 按钮的左边界，改变左边界的值就能实现拖动效果了
	private int slideBtn_left = 0;
	// 在布局文件中可以设置该属性，如果没有将使用默认值
	private boolean slideBtnStat;

	/**
	 * 在代码里面创建对象的时候，使用此构造方法
	 */
	public MyToggleButton(Context context) {
		super(context);
	}

	/**
	 * 在布局文件中声名的view，创建时由系统自动调用。
	 * 
	 * @param context
	 *            上下文对象
	 * @param attrs
	 *            属性集
	 */
	public MyToggleButton(Context context, AttributeSet attrs) {
		super(context, attrs);

		/*
		 * AttributeSet 对XML文件解析后的结果，封装为 AttributeSet 对象。 存储的都是原始数据。仅对数据进行简单加工。
		 */
		int count = attrs.getAttributeCount();
		for (int i = 0; i < count; i++) {
			String name = attrs.getAttributeName(i);
			String value = attrs.getAttributeValue(i);
			System.out.println("name=" + name + ",value=" + value);
		}

		System.out.println("==================");
		/**
		 * TypedArray是对AttributeSet 中的原始数据 按照 图纸中的说明（R.styleable.MyView 中的类型声名）
		 * 创建出具体的对象。
		 */

		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.MyToggleButton);

		int taCount = ta.getIndexCount();
		for (int i = 0; i < taCount; i++) {
			int itemId = ta.getIndex(i);
			System.out.println(itemId + "");

			switch (itemId) {
			case R.styleable.MyToggleButton_bg:
				ta.getDrawable(itemId);
				bgBitmapId = ta.getResourceId(itemId, R.drawable.ic_launcher);
				System.out.println(bgBitmapId + "");
				break;

			case R.styleable.MyToggleButton_slideBtnbg:
				ta.getDrawable(itemId);

				slideBtnId = ta.getResourceId(itemId, R.drawable.ic_launcher);
				System.out.println(slideBtnId + "");
				break;

			case R.styleable.MyToggleButton_slideBtnStat:
				slideBtnStat = ta.getBoolean(itemId, true);
				System.out.println(slideBtnStat);
				break;

			default:
				break;
			}

		}

		initView();
	}

	private void initView() {
		/*
		 * 需要一个写着开关字样的背景、一个按钮图片
		 */
		bgBitmap = BitmapFactory.decodeResource(getResources(), bgBitmapId);

		slideBtn = BitmapFactory.decodeResource(getResources(), slideBtnId);

		// 根据布局文件中设置的属性控制按钮的位置
		if (slideBtnStat) {
			slideBtn_left = bgBitmap.getWidth() - slideBtn.getWidth();
		} else {
			slideBtn_left = 0;
		}
		
		// 初始化画笔
		paint = new Paint();
		paint.setAntiAlias(true); // 打开抗锯齿

		// 设置当前view的点击事件
		setOnClickListener(this);

	}

	@Override
	/*
	 * 测量view的大小
	 */
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 看到父类用的就是下面的方法。直接给此方法传值，不再调用父类。
		setMeasuredDimension(bgBitmap.getWidth(), bgBitmap.getWidth());

		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	/*
	 * 绘制当前view的内容,只有调用了这个方法，view才会被画到屏幕上
	 */
	protected void onDraw(Canvas canvas) {
		// 绘制背景

		canvas.drawBitmap(bgBitmap, 0, 0, paint);

		// 绘制按钮
		canvas.drawBitmap(slideBtn, slideBtn_left, 0, paint);
		super.onDraw(canvas);
	}

	@Override
	/*
	 * 确定view的位置 自定义view的时候作用不大. 主要用在viewgroup中
	 */
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}

	// 判断是否拖动
	private boolean isDrag = false;

	// 记录开关当前的状态. false 为关
	private boolean btnStat = false;

	@Override
	public void onClick(View v) {
		if (!isDrag) {
			btnStat = !btnStat;
			flushViewStat();
		}

	}

	// 记录当前手指拖动按钮的位置
	private int lastx;
	// 记录第一次触摸的值
	private int firstx;

	/*
	 * 返回true在当前view就消费掉触摸事件，不再向下分发
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			firstx = lastx = (int) event.getX();
			isDrag = false;
			break;

		case MotionEvent.ACTION_MOVE:
			// 如果距离大于5个像素就认为是拖动，废了点击(即改变拖动标记)
			if (Math.abs(event.getX() - firstx) > 5) {
				isDrag = true;
			}
			// 手指移动的距离
			int dis = (int) (event.getX() - lastx);

			lastx = (int) event.getX();
			slideBtn_left += dis;
			break;

		case MotionEvent.ACTION_UP:
			// 根据最后的按钮位置(maxleft的一半)，将开关置为开或关，而不是留在半路
			// 前提实在拖动状态
			if (isDrag) {
				int maxleft = bgBitmap.getWidth() - slideBtn.getWidth();
				if (slideBtn_left < maxleft / 2) {
					btnStat = false;
				} else {
					btnStat = true;
				}
				flushViewStat();
			}

			break;

		default:
			break;
		}

		flushView();

		return true;
	}

	/**
	 * 刷新当请view的状态
	 */
	private void flushViewStat() {
		if (btnStat) {
			slideBtn_left = bgBitmap.getWidth() - slideBtn.getWidth();
		} else {
			slideBtn_left = 0;
		}
		flushView();
	}

	/**
	 * 刷新前判断范围
	 */
	private void flushView() {
		// 确保按钮在可移动范围内 即 slideBtn_left∈[0,maxleft]
		int maxleft = bgBitmap.getWidth() - slideBtn.getWidth();
		slideBtn_left = (slideBtn_left > 0) ? slideBtn_left : 0;
		slideBtn_left = (slideBtn_left < maxleft) ? slideBtn_left : maxleft;

		invalidate();
	}
}
