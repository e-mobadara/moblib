package com.example.emobadaragaminglib.Implementation;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.emobadaragaminglib.Base.Audio;
import com.example.emobadaragaminglib.Base.Music;
import com.example.emobadaragaminglib.Base.Sound;

public class AndroidAudio implements Audio {
    private Resources assets;
    private SoundPool soundPool;

    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getResources();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music createMusic(int idRessource) {
        AssetFileDescriptor assetDescriptor = assets.openRawResourceFd(idRessource);
        return new AndroidMusic(assetDescriptor);
    }

    @Override
    public Sound createSound(int idRessource) {
        AssetFileDescriptor assetDescriptor = assets.openRawResourceFd(idRessource);
        int soundId = soundPool.load(assetDescriptor, 0);
        return new AndroidSound(soundPool, soundId);
    }
}

