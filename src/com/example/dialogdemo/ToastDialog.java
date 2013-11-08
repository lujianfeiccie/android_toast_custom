package com.example.dialogdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 鍔犺浇涓璂ialog
 * 
 * @author xm
 */
public class ToastDialog extends AlertDialog {

	private TextView loading_msg;
	private ImageView loading_img;
	private String message = null;
	private DialogType type=null;
	private final long DELAYMILLIS=2000;

	public ToastDialog(Context context) {
		super(context);
		
		this.message = "加载中。。。";
		this.type=DialogType.LOAD_SUCCESS;
	}
	
	public static void show(Context context, String message,DialogType type){
		new ToastDialog(context, message,type);
	}

	//自动销毁弹出框
	public ToastDialog(Context context, String message,DialogType type) {
		super(context);
		this.message = message;
		this.type=type;
		new Handler().postDelayed(timeOutTask, DELAYMILLIS);
		this.setCancelable(false);
		this.show();
	}
	
	Runnable timeOutTask = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			dismiss();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.view_tips_toast_loading);
		//实例化
		loading_msg = (TextView) findViewById(R.id.loading_msg);
		loading_img = (ImageView) findViewById(R.id.loadImage);

		//赋默认值
		loading_msg.setText(this.message);
		setType(this.type);
	}

	//设置文本
	public void setText(String message) {
		this.message = message;
		loading_msg.setText(this.message);
	}

	//设置弹出框类型
	public void setType(DialogType type) {

		this.type=type;
		
		switch (type) {

		case NO_NETWORK:// 无网络
			loading_img.setBackgroundResource(R.drawable.unknown);
			break;
		case LOAD_SUCCESS:// 加载成功
			loading_img.setBackgroundResource(R.drawable.success);
			break;
		case LOAD_FAILURE:// 加载失败
			loading_img.setBackgroundResource(R.drawable.failure);
			break;
		default:
			break;
		}
	}
	
	public enum DialogType{
		NO_NETWORK, LOAD_SUCCESS, LOAD_FAILURE
	}
}
