package com.example.dialogdemo;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TipsToast extends Toast {

    private TipsToast(Context context) {
        super(context);
    }
    /**
     * @param context
     * @param text
     * @param duration
     * @return
     */
    public static TipsToast makeText(Context context, CharSequence text, int duration,DialogType type) {
        TipsToast result = new TipsToast(context);
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.view_tips_toast_loading, null);
		//实例化
        TextView loading_msg = (TextView) v.findViewById(R.id.loading_msg);
        ImageView loading_img = (ImageView) v.findViewById(R.id.loadImage);
        loading_msg.setText(text);
        result.setView(v);
        // setGravity鏂规硶鐢ㄤ簬璁剧疆浣嶇疆锛屾澶勪负鍨傜洿灞呬腑
        result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        result.setDuration(duration);
        setType(type,loading_img);
        return result;
    }
    //设置弹出框类型
  	private static void setType(DialogType type,ImageView loading_img) {
  		switch (type) {

  		/*case NO_NETWORK:// 无网络
  			loading_img.setBackgroundResource(R.drawable.unknown);
  			break;*/
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
		 LOAD_SUCCESS, LOAD_FAILURE
	}
}
