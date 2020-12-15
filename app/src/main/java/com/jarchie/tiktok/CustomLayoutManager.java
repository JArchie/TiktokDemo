package com.jarchie.tiktok;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者：created by Jarchie
 * 时间：2020/12/7 11:12:49
 * 邮箱：jarchie520@gmail.com
 * 说明：自定义LayoutManager
 */
public class CustomLayoutManager extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener {
    private int mDrift;//位移，用来判断移动方向

    private PagerSnapHelper mPagerSnapHelper;
    private OnPageSlideListener mOnPageSlideListener;

    public CustomLayoutManager(Context context) {
        super(context);
    }

    public CustomLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        mPagerSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        view.addOnChildAttachStateChangeListener(this);
        mPagerSnapHelper.attachToRecyclerView(view);
        super.onAttachedToWindow(view);
    }

    //Item添加进来
    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
        //播放视频操作，判断将要播放的是上一个视频，还是下一个视频
        if (mDrift > 0) { //向上
            if (mOnPageSlideListener != null)
                mOnPageSlideListener.onPageSelected(getPosition(view), true);
        } else { //向下
            if (mOnPageSlideListener != null)
                mOnPageSlideListener.onPageSelected(getPosition(view), false);
        }
    }

    //Item移除出去
    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        //暂停播放操作
        if (mDrift >= 0) {
            if (mOnPageSlideListener != null)
                mOnPageSlideListener.onPageRelease(true, getPosition(view));
        } else {
            if (mOnPageSlideListener != null)
                mOnPageSlideListener.onPageRelease(false, getPosition(view));
        }
    }

    @Override
    public void onScrollStateChanged(int state) { //滑动状态监听
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE:
                View view = mPagerSnapHelper.findSnapView(this);
                int position = getPosition(view);
                if (mOnPageSlideListener != null) {
                    mOnPageSlideListener.onPageSelected(position, position == getItemCount() - 1);
                }
                break;
        }
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    //接口注入
    public void setOnPageSlideListener(OnPageSlideListener mOnViewPagerListener) {
        this.mOnPageSlideListener = mOnViewPagerListener;
    }
}
