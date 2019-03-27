package com.example.emobadaragaminglib.Base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.SharedPreferences;

public interface FileIO {
    /**
     * Reads a file from your device
     * @param file path to the file
     * @return
     * @throws IOException
     */
    public InputStream readFile(String file) throws IOException;

    /**
     * Make a file in your Android Device
     * @param file The complete path to the file to be created
     * @return
     * @throws IOException
     */
    public OutputStream writeFile(String file) throws IOException;

    /**
     * Reads a file from your device
     * @param file absolute path to the file
     * @return
     * @throws IOException
     */
    public InputStream readAsset(String file) throws IOException;

    /**
     * Get the Shared preferences of the App. use them to save current game state,
     * Like Current Level or current Score. So the player wont have to start all over again.
     * @return SharedPreferences
     */
    public SharedPreferences getSharedPref();
}
