package com.example.dialogdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
版权所有：版权所有(C)2013，固派软件
文件名称：com.example.waitloading.BarLoading.java
系统编号：
系统名称：waitloading
模块编号：
模块名称：
设计文档：
创建日期：2013-11-27 下午2:46:30
作 者：何鹏程
Version: 1.0
内容摘要：自定义数据加载等待组件
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class BarLoading extends BasicLoading{

	private static final int DEFAULT_POINT_COUNT=4;
	
	private int points=0;
	
	private ImageView[] pointers=null;
	
	private Drawable animation_before;

	private Drawable animation_after;
	
	/**
	 * 上一次选中项索引
	 */
	private int lastIndex=-1;
	
	/**
	 * 当前选中项索引
	 */
	private int currIndex=-1;
	
	private boolean isExit=false;
	
	private boolean isStarted= false;
	
	private TaskThread thread=null;
	
	private final int HANDLE_FLAG_START = 0;
	private final int HANDLE_FLAG_END = 1;
	
	public BarLoading(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.BarLoading);
		setPoints(ta.getInt(R.styleable.BarLoading_points, DEFAULT_POINT_COUNT));
		this.animation_before=ta.getDrawable(R.styleable.BarLoading_animation_before);
		this.animation_after=ta.getDrawable(R.styleable.BarLoading_animation_after);
		setDrawingCacheEnabled(false);
		ta.recycle();
		init(context,attrs);
	}

	public BarLoading(Context context) {
		this(context,null);
	}
	
	private void init(Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		ImageView pointer=null;
		pointers=new ImageView[this.points];
		for (int i = 0; i < this.points; i++) {
			pointer = new ImageView(getContext());
			pointer.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			pointer.setImageDrawable(animation_before);
			pointer.setPadding(0, 0, 5, 0);
			addView(pointer, i + 1);
			pointers[i] = pointer;
		}

	}
	
	private void setPoints(int points){
		this.points=points;
		if(this.points<0){
			this.points=DEFAULT_POINT_COUNT;
		}
	}
	
	public int getPoints(){
		return this.points;
	}
	
	public Drawable getBeforeAnimationDrawable() {
		return animation_before;
	}

	public void setBeforeAnimationDrawable(int resId) {
		Drawable drawable=getResources().getDrawable(resId);
		if(null ==drawable){
			throw new RuntimeException("not found resource for resId");
		}
		this.animation_before = drawable;
	}

	public Drawable getAfterAnimationDrawable() {
		return animation_after;
	}

	public void setAfterAnimationDrawable(int resId) {
		Drawable drawable=getResources().getDrawable(resId);
		if(null ==drawable){
			throw new RuntimeException("not found resource for resId");
		}
		this.animation_after= drawable;
	}
	
	public void start(){
		if(isStarted){
			throw new RuntimeException("task thread already start");
		}
		isExit=false;
		pointers[0].setImageDrawable(animation_after);
		currIndex=0;
		thread=new TaskThread();
		thread.start();
	}
	
	public void stop(){
		isExit=true;
		thread=null;
		lastIndex=-1;
	}
	
	private void reset(){
		this.currIndex=0;
	}
	
	@Override
	protected void handle(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case HANDLE_FLAG_START://doing
			{
			lastIndex=currIndex;
			currIndex++;
			if(currIndex==points){
				reset();
			}
			pointers[lastIndex].setImageDrawable(animation_before);
			pointers[currIndex].setImageDrawable(animation_after);
		}
			break;
		case HANDLE_FLAG_END://end
		{
				pointers[currIndex].setImageDrawable(animation_before);
		}
			break;
		default:
			break;
		}
	}
				
	/**
	 * 睡眠线程，每隔指定的duration时间后通知更新当前的选中圆点
	 */
	class TaskThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(!isExit){
				try {
					Thread.sleep(getDuration());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.sendEmptyMessage(HANDLE_FLAG_START);
			}
			handler.sendEmptyMessage(HANDLE_FLAG_END);
		}
	}

	
	
}
