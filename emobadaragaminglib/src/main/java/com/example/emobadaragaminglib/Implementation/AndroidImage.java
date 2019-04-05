package com.example.emobadaragaminglib.Implementation;

import android.graphics.Bitmap;

import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Base.Graphics.ImageFormat;

public class AndroidImage implements Image {
    private Bitmap bitmap;
    private ImageFormat format;

    public AndroidImage(Bitmap bitmap, ImageFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    public AndroidImage(Bitmap bitmap){
        this.bitmap = bitmap;
        this.format = ImageFormat.ARGB8888;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public ImageFormat getFormat() {
        return format;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public void dispose() {
        if (bitmap != null && !bitmap.isRecycled()) {
            //this.bitmap.recycle();
            this.bitmap = null;
        }
    }
}
