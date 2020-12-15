package com.jarchie.tiktok;

/**
 * 作者：created by Jarchie
 * 时间：2020/12/7 11:20:48
 * 邮箱：jarchie520@gmail.com
 * 说明：页面滑动监听
 */
public interface OnPageSlideListener {
    //释放的监听
    void onPageRelease(boolean isNext, int position);

    //选中的监听以及判断是否滑动到底部
    void onPageSelected(int position, boolean isBottom);
}
