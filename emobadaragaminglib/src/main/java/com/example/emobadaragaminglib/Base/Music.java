package com.example.emobadaragaminglib.Base;

/**
 * Manage Your Music object
 */
public interface Music {
    /**
     * Start playing your Music
     */
    public void play();

    /**
     * Stop the Current music
     */
    public void stop();

    /**
     * Pause the music object
     */
    public void pause();

    /**
     * Control the looping of music.
     * @param looping true or false
     */
    public void setLooping(boolean looping);

    /**
     * Volume of current music from 0 to 1
     * @param volume Percentage of volume
     */
    public void setVolume(float volume);

    /**
     * Get the current state of the music
     * @return playing state of current music
     */
    public boolean isPlaying();

    /**
     * is the music Stopped
     * @return music stopped or not
     */
    public boolean isStopped();

    /**
     * is the Current music object looping
     * @return Looping state of the Music object
     */
    public boolean isLooping();

    /**
     * Dispose and free the memory occupied by the Current Music object
     */
    public void dispose();

    /**
     * Replay the current music object from the start
     */
    public void seekBegin();
}
