package com.example.emobadaragaminglib.Implementation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.emobadaragaminglib.Base.Audio;
import com.example.emobadaragaminglib.Base.FileIO;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Input;
import com.example.emobadaragaminglib.Base.Screen;

public abstract class AndroidGame extends Activity implements Game {
    private static final String TAG = "AndroidGame";
    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    WakeLock wakeLock;
    private int screenWidth;
    private int screenHeight;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //get width & height
        screenWidth=Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight=Resources.getSystem().getDisplayMetrics().heightPixels;

        Log.i(TAG,"  (w,h) = "+screenWidth+"  ,  "+screenHeight);

        Bitmap frameBuffer = Bitmap.createBitmap(screenWidth, screenHeight, Config.RGB_565);

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, 1, 1);
        screen = getInitScreen();
        setContentView(renderView);

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "MyGame");
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();
        if (isFinishing())
            screen.dispose();
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        this.screen = screen;
    }

    public Screen getCurrentScreen() {
        return screen;
    }
    Resources getRes(){
        return this.getResources();
    }

    @Override
    public int getScreenWidth() {
        return screenWidth;
    }
    @Override
    public int getScreenHeight() {
        return screenHeight;
    }
}
