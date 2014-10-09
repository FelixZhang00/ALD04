package jamffy.example.myscrollviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private MyScrollView wSv;
	private int[] imageId = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e, R.drawable.f };

	// private int[] imageId={R.drawable.ic_launcher};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		wSv = (MyScrollView) findViewById(R.id.myscrollview);

		for (int i = 0; i < imageId.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setBackgroundResource(imageId[i]);
			wSv.addView(iv);
		}
	}

}
