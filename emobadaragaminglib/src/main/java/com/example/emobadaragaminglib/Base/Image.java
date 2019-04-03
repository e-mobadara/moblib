package com.example.emobadaragaminglib.Base;

import android.graphics.Bitmap;

import com.example.emobadaragaminglib.Base.Graphics.ImageFormat;

public interface Image {
    /**
     * Returns the bitmap's width
     * @return
     */
    public int getWidth();

    /**
     * Returns the bitmap's height
     * @return
     */
    public int getHeight();

    public Bitmap getBitmap();

    public void setBitmap(Bitmap bitmap);
    /**
     * Get the Format of this image
     * @return ARGB8888, ARGB4444, RGB565
     */
    public ImageFormat getFormat();

    /**
     * Clean the memory used by the image manually. If you have performance issues
     * then you need to dispose of the memory used when you don't need an image anymore
     * it depends on your coding style.
     */
    public void dispose();
}
