package com.example.emobadaragaminglib.Base;

/**
 * Use Sound for files small audio samples.
 * If you have a file more than 1 mb or long
 * use Music instead
 */
public interface Sound {
    /**
     * play this Sound Object
     * @param volume percentage of the volume from 0 to 1
     */
    public void play(float volume);

    /**
     * Free the memory used by this object
     */
    public void dispose();
}
