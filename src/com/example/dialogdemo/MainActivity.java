package com.example.dialogdemo;

import com.example.dialogdemo.ToastDialog.DialogType;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener{

	LoadingDialog mLoadingDialog = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.bt_waittips).setOnClickListener(this);
		findViewById(R.id.bt_righttips).setOnClickListener(this);
		findViewById(R.id.bt_wrongtips).setOnClickListener(this);
		findViewById(R.id.bt_network_error).setOnClickListener(this);
		mLoadingDialog = new LoadingDialog(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bt_waittips:
			mLoadingDialog.show();
			mhandler.postDelayed(hideTask, 1000);
			break;
		case R.id.bt_righttips:
			ToastDialog.show(this,"正确提示",DialogType.LOAD_SUCCESS);
			break;
		case R.id.bt_wrongtips:
			ToastDialog.show(this,"错误提示",DialogType.LOAD_FAILURE);
			break;
		case R.id.bt_network_error:
			ToastDialog.show(this,"网络错误提示",DialogType.NO_NETWORK);
			break;
		}
	}

	Handler mhandler = new Handler();
	Runnable hideTask = new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mLoadingDialog.hide();
		}
	};
}
