package com.example.emobadaragaminglib.Base;

import com.example.emobadaragaminglib.Base.Graphics.ImageFormat;

public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
}
