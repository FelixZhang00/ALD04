package jamffy.example.myscrollviewdemo;

import jamffy.example.myscrollviewdemo.MyScrollView.MyPageChangedListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {

	private MyScrollView wSv;
	private RadioGroup wRG;
	private int[] imageId = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e, R.drawable.f };

	// private int[] imageId={R.drawable.ic_launcher};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		wSv = (MyScrollView) findViewById(R.id.myscrollview);
		wRG = (RadioGroup) findViewById(R.id.radioGroup);
		for (int i = 0; i < imageId.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setBackgroundResource(imageId[i]);
			wSv.addView(iv);
			
			RadioButton rb=new RadioButton(this);
			rb.setId(i);
			wRG.addView(rb);
			if (i == 0) {
				((RadioButton) wRG.getChildAt(i)).setChecked(true);
				
			}
			
		}
		
		wSv.setMyPageChangedListener(new MyPageChangedListener() {
			
			@Override
			public void moveToDest(int currId) {
				((RadioButton) wRG.getChildAt(currId)).setChecked(true);
				
			}
		});
		
		wRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				wSv.moveToDest(checkedId);	
				
			}
		});
		
	}

}
