package com.example.dialogdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
版权所有：版权所有(C)2013，固派软件
文件名称：com.example.waitloading.BasicLoading.java
系统编号：
系统名称：waitloading
模块编号：
模块名称：
设计文档：
创建日期：2013-11-28 下午2:19:00
作 者：何鹏程
Version: 1.0
内容摘要：
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public abstract class BasicLoading extends LinearLayout{
	
	private static final int DEFAULT_DURATION_TIME=300;  //300ms
	
	private static final int MAX_DURATION_TIME=1000;  //1000ms
	
	private int duration;
	
	private TextView title;
	private String titleString="";

	public BasicLoading(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.BasicLoading);
		setDuration(ta.getInt(R.styleable.BasicLoading_duration, DEFAULT_DURATION_TIME));
		titleString = ta.getString(R.styleable.BasicLoading_title);
		ta.recycle();
		init(context);
	}

	public BasicLoading(Context context) {
		// TODO Auto-generated constructor stub
		this(context, null);
	}
	
	protected abstract void start();
	
	protected abstract void stop();
	
	protected abstract void handle(Message msg);
	
	private void init(Context context){
		addTitle(context);
	}
	
	private void addTitle(Context context){
		title = new TextView(context);
		title.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		title.setPadding(0, 0, 5, 0);
		setText(titleString);
		addView(title);
	}
	
	public void setDuration(int duration){
		this.duration=duration;
		if(this.duration<0||this.duration>MAX_DURATION_TIME){
			this.duration=DEFAULT_DURATION_TIME;
		}
	}

	public int getDuration(){
		return this.duration;
	}
	
	public void setText(CharSequence text){
		title.setText(text);
	}
	
	public CharSequence getText(){
		return this.title.getText();
	}
	
	protected Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			handle(msg);
		}
	};
}
