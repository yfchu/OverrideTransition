package com.example.administrator.overridetransition;

import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	RelativeLayout tvSearchRlt;
	RelativeLayout rlayout;
	ImageView img_elm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		BroadCastReceiver receiver = new BroadCastReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("yfchu");
		registerReceiver(receiver, filter);

		img_elm = (ImageView) findViewById(R.id.img_elm);
		rlayout = (RelativeLayout) findViewById(R.id.rlayout);
		tvSearchRlt = (RelativeLayout) findViewById(R.id.tv_search_rlt);
		tvSearchRlt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, SearchActivity.class);
				int location[] = new int[2];
				tvSearchRlt.getLocationOnScreen(location);
				intent.putExtra("x", location[0]);
				intent.putExtra("y", location[1]);
				startActivity(intent);
				overridePendingTransition(0, 0);

			}
		});
	}

	class BroadCastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (rlayout.getAlpha() == 0f) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						rlayout.setAlpha(1f);
					}
				}, 450);
			} else
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						rlayout.setAlpha(0f);
					}
				}, 50);
		}
	}

}
