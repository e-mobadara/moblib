package com.example.emobadaragaminglib.Base;

import android.content.res.Resources;

public interface Game {
    /**
     * Gives you the object that controls the audio.
     * @return
     */
    public Audio getAudio();

    /**
     * TODO
     * @return
     */
    public Input getInput();

    /**
     * TODO
     * @return
     */
    public FileIO getFileIO();

    /**
     * Gives you the Stream to actually add images to your sprites for example
     * @return
     */
    public Graphics getGraphics();

    /**
     * TODO
     * @param screen
     */
    public void setScreen(Screen screen);

    /**
     * TODO
     * @return
     */
    public Screen getCurrentScreen();

    /**
     * Get the screen that you initialize your views with.
     * TODO: Add more description
     * @return
     */
    public Screen getInitScreen();

    /**
     * TODO
     * @return
     */
    Resources getResources();

    /**
     * Returns the current Device Screen's Width
     * Could be used in the formula of the width of your sprites to make it responsive
     * @return
     */
    public int getScreenWidth();

    /**
     * Returns the current Device Screen's Height
     * Could be used in the formula of the height of your sprites to make it responsive
     * @return
     */
    public int getScreenHeight() ;
}
