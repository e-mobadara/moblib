package com.example.emobadaragaminglib.Base;

/**
 * This interface encapsulates the creation and management of audio resources.
 * It allows you direct access to either the Androidsound or AndroidMusic
 */
public interface Audio {
    /**
     * Create Music Object
     * @param idRessource R.raw.my_music
     * @return Music Object
     */
    Music createMusic(int idRessource);

    /**
     * Create a Sound object like Splash Pchaaw
     * @param idRessource R.raw.my_sound
     * @return Sound Object
     */
    Sound createSound(int idRessource);
}
