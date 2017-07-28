package com.example.ds.myuidemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Warning extends Activity implements OnClickListener {

	private Button btn_ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_warning);

		btn_ok = (Button) findViewById(R.id.btn_ok);
		
		btn_ok.setOnClickListener(this);
		
		mHandler.sendEmptyMessageDelayed(1, 1000);
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			btn_ok.setEnabled(true);
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn_ok:
			Intent intent = new Intent(Warning.this, MainActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
}
