package com.example.dialogdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.dialogdemo.TipsToast.DialogType;

public class MainActivity extends Activity implements OnClickListener{

	Handler mhandler ;
	LoadingDialog mLoadingDialog = null;
	updateVersion mupdateVersion = null;
	private BarLoading loadingBar=null;
	boolean toggle = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.bt_waittips).setOnClickListener(this);
		findViewById(R.id.bt_righttips).setOnClickListener(this);
		findViewById(R.id.bt_wrongtips).setOnClickListener(this);
		findViewById(R.id.bt_udpate_dialog).setOnClickListener(this);
		findViewById(R.id.bt_waitingtips_on_ui).setOnClickListener(this);
		
		loadingBar=(BarLoading) findViewById(R.id.progress_loadingbar);
		loadingBar.setVisibility(View.VISIBLE);
		mhandler = new Handler();
		mLoadingDialog = new LoadingDialog(this);
		mLoadingDialog.setMessage("加载中...");
		mupdateVersion = new updateVersion(this);
		mupdateVersion.setOnClickListener(this);
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
			mhandler.postDelayed(new hideTask(), 3000);
			break;
		case R.id.bt_righttips:
			TipsToast.makeText(this, "正确提示", Toast.LENGTH_SHORT, DialogType.LOAD_SUCCESS).show();
			break;
		case R.id.bt_wrongtips:
			TipsToast.makeText(this, "错误提示", Toast.LENGTH_SHORT, DialogType.LOAD_FAILURE).show();
			break;
		case R.id.bt_udpate_dialog:
			mupdateVersion.show();
			break;
		//更新对话框
		case R.id.load_ok: //点击OK
			mupdateVersion.dismiss();
			break;
		case R.id.load_cancel: //点击不OK
			mupdateVersion.dismiss();
			break;
		case R.id.bt_waitingtips_on_ui:{
			toggle=!toggle;
			if(toggle){
				loadingBar.start();
			}else{
				loadingBar.stop();
			}
		}
			break;
		}
	}

	class hideTask implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mLoadingDialog.hide();
			//mhandler.removeCallbacks(this);
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//mhandler.removeCallbacks(hideTask);
		//mLoadingDialog = null;
		//mupdateVersion = null;
	}
	
}
