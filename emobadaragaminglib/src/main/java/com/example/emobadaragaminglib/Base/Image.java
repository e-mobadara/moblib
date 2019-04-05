package com.example.emobadaragaminglib.Base;

import android.graphics.Bitmap;

import com.example.emobadaragaminglib.Base.Graphics.ImageFormat;

public interface Image {
    /**
     * Returns the bitmap's width
     * @return
     */
    int getWidth();

    /**
     * Returns the bitmap's height
     * @return
     */
    int getHeight();

    /**
     * Returns the Bitmap object used by this sprite
     * @return
     */
    Bitmap getBitmap();

    /**
     * Change the Bitmap of the current Sprite
     * @param bitmap
     */
    void setBitmap(Bitmap bitmap);

    /**
     * Get the Format of this image
     * @return ARGB8888, ARGB4444, RGB565
     */
     ImageFormat getFormat();

    /**
     * Clean the memory used by the image manually. If you have performance issues
     * then you need to dispose of the memory used when you don't need an image anymore
     * it depends on your coding style.
     */
    void dispose();
}
