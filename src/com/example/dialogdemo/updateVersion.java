package com.example.dialogdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

public class updateVersion extends AlertDialog{
	
	android.view.View.OnClickListener listener;
	
	protected updateVersion(Context context, int theme) {
		super(context, theme);
		setCancelable(false);
	}
	protected updateVersion(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		setCancelable(false);
	}

	protected updateVersion(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setCancelable(false);
	}

	//确定下载按钮
    private Button load_ok=null;
    //取消下载
    private Button load_cancel=null;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_update_version);
		init();
		addEvent();
	}

	public void init() {
		// TODO Auto-generated method stub
		load_ok=(Button)findViewById(R.id.load_ok);
		load_cancel=(Button)findViewById(R.id.load_cancel);
	}

	public void addEvent() {
		// TODO Auto-generated method stub
		load_ok.setOnClickListener(listener);
		load_cancel.setOnClickListener(listener);
	}
 
	public void setOnClickListener(android.view.View.OnClickListener listener){
		this.listener = listener; 
	}
}
