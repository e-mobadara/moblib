package com.example.emobadaragaminglib.Base;

import android.content.res.Resources;

public interface Game {
    /**
     * Gives you the object that controls the audio.
     * @return
     */
    Audio getAudio();

    /**
     * TODO
     * @return
     */
    Input getInput();

    /**
     * TODO
     * @return
     */
    FileIO getFileIO();

    /**
     * Gives you the Stream to actually add images to your sprites for example
     * @return
     */
    Graphics getGraphics();

    /**
     * Change the Current Screen to a new one.
     * @param screen
     */
    void setScreen(Screen screen);

    /**
     * Returns the current screen that is showing to the user.
     * @return
     */
    Screen getCurrentScreen();

    /**
     * Get the screen that you initialize your views with.
     * The First Screen that Shows when your AndroidGame Activity starts.
     * It should not be null
     * @return
     */
    Screen getInitScreen();

    /**
     * You need this if you want access to the resources laying in the res folder.
     * @return Resources
     */
    Resources getResources();

    /**
     * Returns the current Device Screen's Width
     * Could be used in the formula of the width of your sprites to make it responsive
     * @return
     */
    int getScreenWidth();

    /**
     * Returns the current Device Screen's Height
     * Could be used in the formula of the height of your sprites to make it responsive
     * @return
     */
    int getScreenHeight() ;
}
