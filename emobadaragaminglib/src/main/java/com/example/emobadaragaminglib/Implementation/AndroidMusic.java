package com.example.emobadaragaminglib.Implementation;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;

import com.example.emobadaragaminglib.Base.Music;

/**
 * AndroidMusic handles the background music that makes the Gaming experience more fun
 */
public class AndroidMusic implements Music, OnCompletionListener, OnSeekCompleteListener, OnPreparedListener, OnVideoSizeChangedListener {
    private MediaPlayer mediaPlayer;
    private boolean isPrepared;

    public AndroidMusic(AssetFileDescriptor assetDescriptor) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
                    assetDescriptor.getStartOffset(),
                    assetDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't load music");
        }
    }

    /**
     * Free the space used by this music
     */
    @Override
    public void dispose() {
        if (this.mediaPlayer.isPlaying()){
            this.mediaPlayer.stop();
        }
        this.mediaPlayer.release();
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    @Override
    public boolean isPlaying() {
        return this.mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public void pause() {
        if (this.mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    public void play() {
        if (this.mediaPlayer.isPlaying())
            return;

        try {
            synchronized (this) {
                if (!isPrepared)
                    mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setLooping(boolean isLooping) {
        mediaPlayer.setLooping(isLooping);
    }

    /**
     * change the volume
     * @param volume Percentage of volume
     */
    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    @Override
    public void stop() {
        if (this.mediaPlayer.isPlaying()){
            this.mediaPlayer.stop();

            synchronized (this) {
                isPrepared = false;
            }
        }
    }

    /**
     * To avoid the 1st Frame problem where the Audio is being prepared
     * This function waits for one frame and Solves that problem
     * @param player
     */
    @Override
    public void onCompletion(MediaPlayer player) {
        synchronized (this) {
            isPrepared = false;
        }
    }

    /**
     * Replay the Sound from the beginning
     */
    @Override
    public void seekBegin() {
        mediaPlayer.seekTo(0);
    }

    @Override
    public void onPrepared(MediaPlayer player) {
        synchronized (this) {
            isPrepared = true;
        }
    }

    @Override
    public void onSeekComplete(MediaPlayer player) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onVideoSizeChanged(MediaPlayer player, int width, int height) {
        // TODO Auto-generated method stub

    }
}

