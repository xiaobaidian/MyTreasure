package com.feicuiedu.treasure_20170606;


import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.treasure_20170606.commons.ActivityUtils;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by 蔡传飞 on 2017-06-06.
 */
// 主页面的视频播放:全屏播放视频
public class MainMp4Fragment extends Fragment implements TextureView.SurfaceTextureListener {

    private TextureView mTextureView;
    private ActivityUtils mActivityUtils;
    private MediaPlayer mMediaPlayer;

    /**
     * 视频播放：
     * 1. MediaPlayer视频播放
     * 2. TextureView和SurfaceView都可以实现播放视频的展示，SurfaceView效率比较高
     * 主要用于展示渲染播放的视频，使用的时候TextureView需要SurfaceTexure，主要用于渲染和呈现播放的内容
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mTextureView = new TextureView(getContext());
        mActivityUtils = new ActivityUtils(this);
        return mTextureView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 当我们的视频控件准备好的时候，可以让MediaPlayer播放视频了
        // 什么时候会准备好，设置监听
        mTextureView.setSurfaceTextureListener(this);
    }

    // -------------------监听重写的方法------------------------

    // 准备好了，可以进行视频播放的展示了
    @Override
    public void onSurfaceTextureAvailable(final SurfaceTexture surface, int width, int height) {
        /**
         * 视频展示的控件准备好了
         * 1. 找到播放的视频资源
         * 2. 什么时候可以播放：使用MediaPlayer播放，当MediaPlayer准备好的播放出来
         * 3. 播放的展示展示到什么控件上，设置循环播放等
         */

        //打开资源文件
        try {
            AssetFileDescriptor openFd = getContext().getAssets().openFd("welcome.mp4");
            //拿到MediaPlayer需要的类型
            FileDescriptor fileDescriptor = openFd.getFileDescriptor();
            mMediaPlayer = new MediaPlayer();
            //给MediaPlayer设置播放资源
            mMediaPlayer.setDataSource(fileDescriptor, openFd.getStartOffset(), openFd.getLength());
            // 异步准备
            mMediaPlayer.prepareAsync();
            // 设置准备的监听：看一下什么时候准备好了
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                // 准备好了
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Surface mySurface = new Surface(surface);
                    mMediaPlayer.setSurface(mySurface);
                    mMediaPlayer.setLooping(true);// 循环播放
                    mMediaPlayer.start();// 开始播放
                }
            });

        } catch (IOException e) {
            mActivityUtils.showToast("媒体文件播放失败了");
        }
    }

    // 视频展示的大小变化的时候 // 视频展示的大小变化的时候
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    // 销毁的时候
    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    // 更新
    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mMediaPlayer != null) {
            // 释放资源
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
