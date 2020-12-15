package com.jarchie.tiktok;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 作者：created by Jarchie
 * 时间：2020/12/7 15:05:57
 * 邮箱：jarchie520@gmail.com
 * 说明：自定义宽高VideoView
 */
public class CusVideoView extends VideoView {
    public CusVideoView(Context context) {
        super(context);
    }

    public CusVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(getWidth(), widthMeasureSpec);
        int height = getDefaultSize(getHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
