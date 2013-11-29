package com.example.dialogdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Âä†ËΩΩ‰∏≠Dialog
 * 
 * @author xm
 */
public class LoadingDialog extends ProgressDialog {

    private TextView tips_loading_msg;

    private String message = null;

    public LoadingDialog(Context context) {
        super(context);
        message = "º”‘ÿ÷–";
    }

    public LoadingDialog(Context context, String message) {
        super(context);
        this.message = message;
        this.setCancelable(false);
    }

    public LoadingDialog(Context context, int theme, String message) {
        super(context, theme);
        this.message = message;
        this.setCancelable(false); 
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_tips_loading);
        tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
        tips_loading_msg.setText(this.message);
    }
    public void setMessage(String message) {
		this.message = message;
	}
    @Override
    public void setMessage(CharSequence message) {
    	// TODO Auto-generated method stub
    	this.message = message.toString();
    }
}
