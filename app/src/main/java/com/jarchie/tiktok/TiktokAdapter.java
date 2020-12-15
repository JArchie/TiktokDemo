package com.jarchie.tiktok;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：created by Jarchie
 * 时间：2020/12/7 10:28:01
 * 邮箱：jarchie520@gmail.com
 * 说明：仿抖音主界面适配器
 */
public class TiktokAdapter extends RecyclerView.Adapter<TiktokAdapter.ViewHolder> {
    private int[] videos = {R.raw.v1, R.raw.v2};
    private int[] imgs = {R.drawable.fm1, R.drawable.fm2};
    private List<String> mTitles = new ArrayList<>();
    private List<String> mMarqueeList = new ArrayList<>();
    private Context mContext;

    public TiktokAdapter(Context context) {
        this.mContext = context;
        mTitles.add("@乔布奇\nAndroid仿抖音主界面UI效果,\n一起来学习Android开发啊啊啊啊啊\n#Android高级UIAndroid开发");
        mTitles.add("@乔布奇\nAndroid RecyclerView自定义\nLayoutManager的使用方式，仿抖音效果哦");
        mMarqueeList.add("哈哈创作的原声-乔布奇");
        mMarqueeList.add("嘿嘿创作的原声-Jarchie");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_tiktok_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int pos) {
        //第一种方式：获取视频第一帧作为封面图片
//        MediaMetadataRetriever media = new MediaMetadataRetriever();
//        media.setDataSource(mContext,Uri.parse("android.resource://" + mContext.getPackageName() + "/" + videos[pos % 2]));
//        holder.mThumb.setImageBitmap(media.getFrameAtTime());
        //第二种方式：使用固定图片作为封面图片
        holder.mThumb.setImageResource(imgs[pos % 2]);
        holder.mVideoView.setVideoURI(Uri.parse("android.resource://" + mContext.getPackageName() + "/" + videos[pos % 2]));
        holder.mTitle.setText(mTitles.get(pos % 2));
        holder.mMarquee.setText(mMarqueeList.get(pos % 2));
        holder.mMarquee.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mRootView;
        ImageView mThumb;
        ImageView mPlay;
        TextView mTitle;
        TextView mMarquee;
        CusVideoView mVideoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRootView = itemView.findViewById(R.id.mRootView);
            mThumb = itemView.findViewById(R.id.mThumb);
            mPlay = itemView.findViewById(R.id.mPlay);
            mVideoView = itemView.findViewById(R.id.mVideoView);
            mTitle = itemView.findViewById(R.id.mTitle);
            mMarquee = itemView.findViewById(R.id.mMarquee);
        }
    }

}
