package com.example.emobadaragaminglib.Base;

/**
 * This interface encapsulates the creation and management of audio resources.
 * It allows you direct access to either the Androidsound or AndroidMusic
 */
public interface Audio {
    public Music createMusic(int idRessource);

    public Sound createSound(int idRessource);
}
