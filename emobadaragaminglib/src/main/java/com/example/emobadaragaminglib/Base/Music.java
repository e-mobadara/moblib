package com.example.emobadaragaminglib.Base;

/**
 * Manage Your Music object
 */
public interface Music {
    /**
     * Start playing your Music
     */
    void play();

    /**
     * Stop the Current music
     */
    void stop();

    /**
     * Pause the music object
     */
    void pause();

    /**
     * Control the looping of music.
     * @param looping true or false
     */
    void setLooping(boolean looping);

    /**
     * Volume of current music from 0 to 1
     * @param volume Percentage of volume
     */
    void setVolume(float volume);

    /**
     * Get the current state of the music
     * @return playing state of current music
     */
    boolean isPlaying();

    /**
     * is the music Stopped
     * @return music stopped or not
     */
    boolean isStopped();

    /**
     * is the Current music object looping
     * @return Looping state of the Music object
     */
    boolean isLooping();

    /**
     * Dispose and free the memory occupied by the Current Music object
     */
    void dispose();

    /**
     * Replay the current music object from the start
     */
    void seekBegin();
}
