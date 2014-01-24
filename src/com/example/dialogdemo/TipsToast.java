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
		//ʵ����
        TextView loading_msg = (TextView) v.findViewById(R.id.loading_msg);
        ImageView loading_img = (ImageView) v.findViewById(R.id.loadImage);
        loading_msg.setText(text);
        result.setView(v);
        // setGravity方法用于设置位置，此处为垂直居中
        result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        result.setDuration(duration);
        setType(type,loading_img);
        return result;
    }
    //���õ���������
  	private static void setType(DialogType type,ImageView loading_img) {
  		switch (type) {

  		/*case NO_NETWORK:// ������
  			loading_img.setBackgroundResource(R.drawable.unknown);
  			break;*/
  		case LOAD_SUCCESS:// ���سɹ�
  			loading_img.setBackgroundResource(R.drawable.success);
  			break;
  		case LOAD_FAILURE:// ����ʧ��
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
