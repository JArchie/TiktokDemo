package com.jarchie.tiktok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * 作者：created by Jarchie
 * 时间：2020/12/7 10:23:40
 * 邮箱：jarchie520@gmail.com
 * 说明：仿抖音主界面效果实现
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecycler;
    private CustomLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    //初始化监听
    private void initListener() {
        mLayoutManager.setOnPageSlideListener(new OnPageSlideListener() {

            @Override
            public void onPageRelease(boolean isNext, int position) {
                int index;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(index);

            }

            @Override
            public void onPageSelected(int position, boolean isNext) {
                playVideo();
            }
        });
    }

    //初始化View
    private void initView() {
        mRecycler = findViewById(R.id.mRecycler);
        mLayoutManager = new CustomLayoutManager(this, OrientationHelper.VERTICAL, false);
        TiktokAdapter mAdapter = new TiktokAdapter(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(mAdapter);
    }

    //播放
    private void playVideo() {
        View itemView = mRecycler.getChildAt(0);
        final CusVideoView mVideoView = itemView.findViewById(R.id.mVideoView);
        final ImageView mPlay = itemView.findViewById(R.id.mPlay);
        final ImageView mThumb = itemView.findViewById(R.id.mThumb);
        final MediaPlayer[] mMediaPlayer = new MediaPlayer[1];
        mVideoView.start();

        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mMediaPlayer[0] = mp;
                mp.setLooping(true);
                mThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });

        //暂停控制
        mPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;

            @Override
            public void onClick(View v) {
                if (mVideoView.isPlaying()) {
                    mPlay.animate().alpha(1f).start();
                    mVideoView.pause();
                    isPlaying = false;
                } else {
                    mPlay.animate().alpha(0f).start();
                    mVideoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    //释放
    private void releaseVideo(int index) {
        View itemView = mRecycler.getChildAt(index);
        final CusVideoView mVideoView = itemView.findViewById(R.id.mVideoView);
        final ImageView mThumb = itemView.findViewById(R.id.mThumb);
        final ImageView mPlay = itemView.findViewById(R.id.mPlay);
        mVideoView.stopPlayback();
        mThumb.animate().alpha(1).start();
        mPlay.animate().alpha(0f).start();
    }
}
