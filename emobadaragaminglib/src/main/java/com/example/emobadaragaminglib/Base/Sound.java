package com.example.emobadaragaminglib.Base;

/**
 * Use Sound for files small audio samples.
 * If you have a file more than 1 mb or long
 * use Music instead
 */
public interface Sound {
    public void play(float volume);

    public void dispose();
}
