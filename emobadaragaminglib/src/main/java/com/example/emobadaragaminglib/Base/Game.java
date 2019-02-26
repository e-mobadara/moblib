package com.example.emobadaragaminglib.Base;

import android.content.res.Resources;

public interface Game {
    public Audio getAudio();

    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen();

    Resources getResources();

    public int getScreenWidth();

    public int getScreenHeight() ;
}
